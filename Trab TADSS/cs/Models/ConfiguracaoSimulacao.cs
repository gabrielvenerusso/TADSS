namespace SimuladorFilas.Models
{
    public class ConfiguracaoSimulacao
    {
        public int DuracaoJanelaSegundos { get; set; } = 2 * 60 * 60; // 2 horas das 11h as 13h
        public int ChegadaMinSegundos { get; set; } = 5;
        public int ChegadaMaxSegundos { get; set; } = 50;
        public int AtendimentoMinSegundos { get; set; } = 30;
        public int AtendimentoMaxSegundos { get; set; } = 120;
        public int MetaEsperaSegundos { get; set; } = 120;
        public int AtendentesMin { get; set; } = 1;
        public int AtendentesMax { get; set; } = 20;
        public int RepeticoesPorCenario { get; set; } = 300;
    }
}