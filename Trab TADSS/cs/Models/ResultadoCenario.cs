namespace SimuladorFilas.Models
{
    public class ResultadoCenario
    {
        public int Atendentes { get; set; }
        public int Repeticoes { get; set; }

        public double MediaClientesGerados { get; set; }
        public double MediaThroughput { get; set; }

        public double MediaTempoEsperaFila { get; set; }
        public double MaxTempoEsperaFilaObservado { get; set; }

        public double MediaTempoAtendimento { get; set; }
        public double MaxTempoAtendimentoObservado { get; set; }

        public double MediaLeadTime { get; set; }

        public double MediaPercentualClientesDentroMeta { get; set; }

        public double IC95InferiorEsperaFila { get; set; }
        public double IC95SuperiorEsperaFila { get; set; }

        public bool CumpreMeta { get; set; }
    }
}