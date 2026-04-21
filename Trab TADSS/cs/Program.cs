using System;
using System.Globalization;
using System.Linq;
using SimuladorFilas.Models;
using SimuladorFilas.Services;

namespace SimuladorFilas
{
    internal class Program
    {
        static void Main(string[] args)
        {
            CultureInfo.DefaultThreadCurrentCulture = CultureInfo.InvariantCulture;
            CultureInfo.DefaultThreadCurrentUICulture = CultureInfo.InvariantCulture;

            var config = new ConfiguracaoSimulacao
            {
                DuracaoJanelaSegundos = 7200,
                ChegadaMinSegundos = 5,
                ChegadaMaxSegundos = 50,
                AtendimentoMinSegundos = 30,
                AtendimentoMaxSegundos = 120,
                MetaEsperaSegundos = 120,
                AtendentesMin = 1,
                AtendentesMax = 20,
                RepeticoesPorCenario = 300
            };

            var analisador = new AnalisadorCenarios(config);
            var resultados = analisador.Executar();

            Console.WriteLine("Resumo dos cenários");
            Console.WriteLine("----------------------------------------------------------------------------------------------");
            Console.WriteLine("Atd | Esp.Média | IC95 Sup | Lead Time | Throughput | % Clientes <=120s | Meta");
            Console.WriteLine("----------------------------------------------------------------------------------------------");

            foreach (var resultado in resultados)
            {
                Console.WriteLine(
                    $"{resultado.Atendentes,3} | " +
                    $"{resultado.MediaTempoEsperaFila,9:F2} | " +
                    $"{resultado.IC95SuperiorEsperaFila,8:F2} | " +
                    $"{resultado.MediaLeadTime,9:F2} | " +
                    $"{resultado.MediaThroughput,10:F2} | " +
                    $"{resultado.MediaPercentualClientesDentroMeta,16:F2}% | " +
                    $"{(resultado.CumpreMeta ? "SIM" : "NÃO")}"
                );
            }

            var melhorCenario = resultados.FirstOrDefault(r => r.CumpreMeta);

            Console.WriteLine();
            Console.WriteLine("----------------------------------------------------------------------------------------------");

            if (melhorCenario != null)
            {
                Console.WriteLine($"Menor número de atendentes que cumpre a meta: {melhorCenario.Atendentes}");
                Console.WriteLine($"Tempo médio de espera: {melhorCenario.MediaTempoEsperaFila:F2} s");
                Console.WriteLine($"IC95 superior da espera média: {melhorCenario.IC95SuperiorEsperaFila:F2} s");
            }
            else
            {
                Console.WriteLine("Nenhum cenário dentro do intervalo testado cumpriu a meta.");
            }

            ExportadorCsv.Exportar("resultados_simulacao.csv", resultados);
            Console.WriteLine();
            Console.WriteLine("Arquivo CSV gerado: resultados_simulacao.csv");
        }
    }
}