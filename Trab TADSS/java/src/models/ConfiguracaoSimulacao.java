package src.models;

public class ConfiguracaoSimulacao {
    private int duracaoJanelaSegundos = 2 * 60 * 60;
    private int chegadaMinSegundos = 5;
    private int chegadaMaxSegundos = 50;
    private int atendimentoMinSegundos = 30;
    private int atendimentoMaxSegundos = 120;
    private int metaEsperaSegundos = 120;
    private int atendentesMin = 1;
    private int atendentesMax = 20;
    private int repeticoesPorCenario = 300;

    public int getDuracaoJanelaSegundos() {
        return duracaoJanelaSegundos;
    }

    public void setDuracaoJanelaSegundos(int duracaoJanelaSegundos) {
        this.duracaoJanelaSegundos = duracaoJanelaSegundos;
    }

    public int getChegadaMinSegundos() {
        return chegadaMinSegundos;
    }

    public void setChegadaMinSegundos(int chegadaMinSegundos) {
        this.chegadaMinSegundos = chegadaMinSegundos;
    }

    public int getChegadaMaxSegundos() {
        return chegadaMaxSegundos;
    }

    public void setChegadaMaxSegundos(int chegadaMaxSegundos) {
        this.chegadaMaxSegundos = chegadaMaxSegundos;
    }

    public int getAtendimentoMinSegundos() {
        return atendimentoMinSegundos;
    }

    public void setAtendimentoMinSegundos(int atendimentoMinSegundos) {
        this.atendimentoMinSegundos = atendimentoMinSegundos;
    }

    public int getAtendimentoMaxSegundos() {
        return atendimentoMaxSegundos;
    }

    public void setAtendimentoMaxSegundos(int atendimentoMaxSegundos) {
        this.atendimentoMaxSegundos = atendimentoMaxSegundos;
    }

    public int getMetaEsperaSegundos() {
        return metaEsperaSegundos;
    }

    public void setMetaEsperaSegundos(int metaEsperaSegundos) {
        this.metaEsperaSegundos = metaEsperaSegundos;
    }

    public int getAtendentesMin() {
        return atendentesMin;
    }

    public void setAtendentesMin(int atendentesMin) {
        this.atendentesMin = atendentesMin;
    }

    public int getAtendentesMax() {
        return atendentesMax;
    }

    public void setAtendentesMax(int atendentesMax) {
        this.atendentesMax = atendentesMax;
    }

    public int getRepeticoesPorCenario() {
        return repeticoesPorCenario;
    }

    public void setRepeticoesPorCenario(int repeticoesPorCenario) {
        this.repeticoesPorCenario = repeticoesPorCenario;
    }
}