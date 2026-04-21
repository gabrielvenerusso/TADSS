using SimuladorFilas.Models;

namespace SimuladorFilas.Services
{
    public class SimuladorFila
    {
        private readonly ConfiguracaoSimulacao _config;

        public SimuladorFila(ConfiguracaoSimulacao config)
        {
            _config = config;
        }

        public ResultadoSimulacao Executar(int quantidadeAtendentes, int seed)
        {
            var random = new Random(seed);
            var clientes = GerarClientes(random);

            double[] disponibilidadeAtendentes = new double[quantidadeAtendentes];

            double somaEspera = 0;
            double maxEspera = 0;

            double somaAtendimento = 0;
            double maxAtendimento = 0;

            double somaLeadTime = 0;

            int concluidosAte13h = 0;
            int clientesDentroMeta = 0;

            foreach (var cliente in clientes)
            {
                int indiceAtendente = EncontrarAtendenteMaisLivre(disponibilidadeAtendentes);

                double inicioAtendimento = Math.Max(cliente.InstanteChegada, disponibilidadeAtendentes[indiceAtendente]);
                double tempoEspera = inicioAtendimento - cliente.InstanteChegada;
                double fimAtendimento = inicioAtendimento + cliente.TempoAtendimento;
                double leadTime = fimAtendimento - cliente.InstanteChegada;

                disponibilidadeAtendentes[indiceAtendente] = fimAtendimento;

                somaEspera += tempoEspera;
                if (tempoEspera > maxEspera)
                    maxEspera = tempoEspera;

                somaAtendimento += cliente.TempoAtendimento;
                if (cliente.TempoAtendimento > maxAtendimento)
                    maxAtendimento = cliente.TempoAtendimento;

                somaLeadTime += leadTime;

                if (fimAtendimento <= _config.DuracaoJanelaSegundos)
                    concluidosAte13h++;

                if (tempoEspera <= _config.MetaEsperaSegundos)
                    clientesDentroMeta++;
            }

            int totalClientes = clientes.Count;

            return new ResultadoSimulacao
            {
                Atendentes = quantidadeAtendentes,
                ClientesGerados = totalClientes,
                ClientesConcluidosAte13h = concluidosAte13h,
                TempoMedioEsperaFila = totalClientes == 0 ? 0 : somaEspera / totalClientes,
                TempoMaximoEsperaFila = maxEspera,
                TempoMedioAtendimento = totalClientes == 0 ? 0 : somaAtendimento / totalClientes,
                TempoMaximoAtendimento = maxAtendimento,
                LeadTimeMedio = totalClientes == 0 ? 0 : somaLeadTime / totalClientes,
                PercentualClientesDentroMeta = totalClientes == 0 ? 0 : (clientesDentroMeta * 100.0 / totalClientes)
            };
        }

        private List<Cliente> GerarClientes(Random random)
        {
            var clientes = new List<Cliente>();

            double instanteAtual = 0;
            int id = 1;

            while (true)
            {
                int intervaloChegada = random.Next(_config.ChegadaMinSegundos, _config.ChegadaMaxSegundos + 1);
                instanteAtual += intervaloChegada;

                if (instanteAtual > _config.DuracaoJanelaSegundos)
                    break;

                int tempoAtendimento = random.Next(_config.AtendimentoMinSegundos, _config.AtendimentoMaxSegundos + 1);

                clientes.Add(new Cliente(id++, instanteAtual, tempoAtendimento));
            }

            return clientes;
        }

        private int EncontrarAtendenteMaisLivre(double[] disponibilidadeAtendentes)
        {
            int indiceMenor = 0;

            for (int i = 1; i < disponibilidadeAtendentes.Length; i++)
            {
                if (disponibilidadeAtendentes[i] < disponibilidadeAtendentes[indiceMenor])
                    indiceMenor = i;
            }

            return indiceMenor;
        }
    }
}