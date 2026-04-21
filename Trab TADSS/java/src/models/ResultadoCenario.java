package src.models;

public class ResultadoCenario {
    private int atendentes;
    private int repeticoes;

    private double mediaClientesGerados;
    private double mediaThroughput;

    private double mediaTempoEsperaFila;
    private double maxTempoEsperaFilaObservado;

    private double mediaTempoAtendimento;
    private double maxTempoAtendimentoObservado;

    private double mediaLeadTime;

    private double mediaPercentualClientesDentroMeta;

    private double ic95InferiorEsperaFila;
    private double ic95SuperiorEsperaFila;

    private boolean cumpreMeta;

    public int getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(int atendentes) {
        this.atendentes = atendentes;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public double getMediaClientesGerados() {
        return mediaClientesGerados;
    }

    public void setMediaClientesGerados(double mediaClientesGerados) {
        this.mediaClientesGerados = mediaClientesGerados;
    }

    public double getMediaThroughput() {
        return mediaThroughput;
    }

    public void setMediaThroughput(double mediaThroughput) {
        this.mediaThroughput = mediaThroughput;
    }

    public double getMediaTempoEsperaFila() {
        return mediaTempoEsperaFila;
    }

    public void setMediaTempoEsperaFila(double mediaTempoEsperaFila) {
        this.mediaTempoEsperaFila = mediaTempoEsperaFila;
    }

    public double getMaxTempoEsperaFilaObservado() {
        return maxTempoEsperaFilaObservado;
    }

    public void setMaxTempoEsperaFilaObservado(double maxTempoEsperaFilaObservado) {
        this.maxTempoEsperaFilaObservado = maxTempoEsperaFilaObservado;
    }

    public double getMediaTempoAtendimento() {
        return mediaTempoAtendimento;
    }

    public void setMediaTempoAtendimento(double mediaTempoAtendimento) {
        this.mediaTempoAtendimento = mediaTempoAtendimento;
    }

    public double getMaxTempoAtendimentoObservado() {
        return maxTempoAtendimentoObservado;
    }

    public void setMaxTempoAtendimentoObservado(double maxTempoAtendimentoObservado) {
        this.maxTempoAtendimentoObservado = maxTempoAtendimentoObservado;
    }

    public double getMediaLeadTime() {
        return mediaLeadTime;
    }

    public void setMediaLeadTime(double mediaLeadTime) {
        this.mediaLeadTime = mediaLeadTime;
    }

    public double getMediaPercentualClientesDentroMeta() {
        return mediaPercentualClientesDentroMeta;
    }

    public void setMediaPercentualClientesDentroMeta(double mediaPercentualClientesDentroMeta) {
        this.mediaPercentualClientesDentroMeta = mediaPercentualClientesDentroMeta;
    }

    public double getIc95InferiorEsperaFila() {
        return ic95InferiorEsperaFila;
    }

    public void setIc95InferiorEsperaFila(double ic95InferiorEsperaFila) {
        this.ic95InferiorEsperaFila = ic95InferiorEsperaFila;
    }

    public double getIc95SuperiorEsperaFila() {
        return ic95SuperiorEsperaFila;
    }

    public void setIc95SuperiorEsperaFila(double ic95SuperiorEsperaFila) {
        this.ic95SuperiorEsperaFila = ic95SuperiorEsperaFila;
    }

    public boolean isCumpreMeta() {
        return cumpreMeta;
    }

    public void setCumpreMeta(boolean cumpreMeta) {
        this.cumpreMeta = cumpreMeta;
    }
}