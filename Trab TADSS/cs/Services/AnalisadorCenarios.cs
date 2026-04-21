using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SimuladorFilas.Models;
using SimuladorFilas.Utils;

namespace SimuladorFilas.Services
{
    public class AnalisadorCenarios
    {
        private readonly ConfiguracaoSimulacao _config;

        public AnalisadorCenarios(ConfiguracaoSimulacao config)
        {
            _config = config;
        }

        public List<ResultadoCenario> Executar()
        {
            var resultados = new List<ResultadoCenario>();

            for (int atendentes = _config.AtendentesMin; atendentes <= _config.AtendentesMax; atendentes++)
            {
                var execucoes = new ResultadoSimulacao[_config.RepeticoesPorCenario];

                Parallel.For(0, _config.RepeticoesPorCenario, i =>
                {
                    int seed = (atendentes * 100000) + i + 7;
                    var simulador = new SimuladorFila(_config);
                    execucoes[i] = simulador.Executar(atendentes, seed);
                });

                var mediasEspera = execucoes.Select(x => x.TempoMedioEsperaFila).ToList();
                var ic95 = Estatistica.IC95(mediasEspera);

                var cenario = new ResultadoCenario
                {
                    Atendentes = atendentes,
                    Repeticoes = _config.RepeticoesPorCenario,
                    MediaClientesGerados = execucoes.Average(x => x.ClientesGerados),
                    MediaThroughput = execucoes.Average(x => x.ClientesConcluidosAte13h),
                    MediaTempoEsperaFila = execucoes.Average(x => x.TempoMedioEsperaFila),
                    MaxTempoEsperaFilaObservado = execucoes.Max(x => x.TempoMaximoEsperaFila),
                    MediaTempoAtendimento = execucoes.Average(x => x.TempoMedioAtendimento),
                    MaxTempoAtendimentoObservado = execucoes.Max(x => x.TempoMaximoAtendimento),
                    MediaLeadTime = execucoes.Average(x => x.LeadTimeMedio),
                    MediaPercentualClientesDentroMeta = execucoes.Average(x => x.PercentualClientesDentroMeta),
                    IC95InferiorEsperaFila = ic95.inferior,
                    IC95SuperiorEsperaFila = ic95.superior,
                    CumpreMeta = ic95.superior <= _config.MetaEsperaSegundos
                };

                resultados.Add(cenario);
            }

            return resultados;
        }
    }
}