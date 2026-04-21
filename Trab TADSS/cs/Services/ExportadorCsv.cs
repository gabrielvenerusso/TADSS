using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Text;
using SimuladorFilas.Models;

namespace SimuladorFilas.Services
{
    public static class ExportadorCsv
    {
        public static void Exportar(string caminho, IEnumerable<ResultadoCenario> resultados)
        {
            var sb = new StringBuilder();

            sb.AppendLine("Atendentes,Repeticoes,MediaClientesGerados,MediaThroughput,MediaTempoEsperaFila,IC95InferiorEsperaFila,IC95SuperiorEsperaFila,MaxTempoEsperaFilaObservado,MediaTempoAtendimento,MaxTempoAtendimentoObservado,MediaLeadTime,MediaPercentualClientesDentroMeta,CumpreMeta");

            foreach (var resultado in resultados)
            {
                sb.AppendLine(string.Join(",",
                    resultado.Atendentes,
                    resultado.Repeticoes,
                    resultado.MediaClientesGerados.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MediaThroughput.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MediaTempoEsperaFila.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.IC95InferiorEsperaFila.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.IC95SuperiorEsperaFila.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MaxTempoEsperaFilaObservado.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MediaTempoAtendimento.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MaxTempoAtendimentoObservado.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MediaLeadTime.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.MediaPercentualClientesDentroMeta.ToString("F2", CultureInfo.InvariantCulture),
                    resultado.CumpreMeta ? "SIM" : "NAO"
                ));
            }

            File.WriteAllText(caminho, sb.ToString(), Encoding.UTF8);
        }
    }
}