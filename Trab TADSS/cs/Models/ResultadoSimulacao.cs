namespace SimuladorFilas.Models
{
    public class ResultadoSimulacao
    {
        public int Atendentes { get; set; }
        public int ClientesGerados { get; set; }
        public int ClientesConcluidosAte13h { get; set; }

        public double TempoMedioEsperaFila { get; set; }
        public double TempoMaximoEsperaFila { get; set; }

        public double TempoMedioAtendimento { get; set; }
        public double TempoMaximoAtendimento { get; set; }

        public double LeadTimeMedio { get; set; }

        public double PercentualClientesDentroMeta { get; set; }
    }
}