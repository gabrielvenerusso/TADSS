package src.models;

public class ResultadoSimulacao {
    private int atendentes;
    private int clientesGerados;
    private int clientesConcluidosAte13h;

    private double tempoMedioEsperaFila;
    private double tempoMaximoEsperaFila;

    private double tempoMedioAtendimento;
    private double tempoMaximoAtendimento;

    private double leadTimeMedio;

    private double percentualClientesDentroMeta;

    public int getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(int atendentes) {
        this.atendentes = atendentes;
    }

    public int getClientesGerados() {
        return clientesGerados;
    }

    public void setClientesGerados(int clientesGerados) {
        this.clientesGerados = clientesGerados;
    }

    public int getClientesConcluidosAte13h() {
        return clientesConcluidosAte13h;
    }

    public void setClientesConcluidosAte13h(int clientesConcluidosAte13h) {
        this.clientesConcluidosAte13h = clientesConcluidosAte13h;
    }

    public double getTempoMedioEsperaFila() {
        return tempoMedioEsperaFila;
    }

    public void setTempoMedioEsperaFila(double tempoMedioEsperaFila) {
        this.tempoMedioEsperaFila = tempoMedioEsperaFila;
    }

    public double getTempoMaximoEsperaFila() {
        return tempoMaximoEsperaFila;
    }

    public void setTempoMaximoEsperaFila(double tempoMaximoEsperaFila) {
        this.tempoMaximoEsperaFila = tempoMaximoEsperaFila;
    }

    public double getTempoMedioAtendimento() {
        return tempoMedioAtendimento;
    }

    public void setTempoMedioAtendimento(double tempoMedioAtendimento) {
        this.tempoMedioAtendimento = tempoMedioAtendimento;
    }

    public double getTempoMaximoAtendimento() {
        return tempoMaximoAtendimento;
    }

    public void setTempoMaximoAtendimento(double tempoMaximoAtendimento) {
        this.tempoMaximoAtendimento = tempoMaximoAtendimento;
    }

    public double getLeadTimeMedio() {
        return leadTimeMedio;
    }

    public void setLeadTimeMedio(double leadTimeMedio) {
        this.leadTimeMedio = leadTimeMedio;
    }

    public double getPercentualClientesDentroMeta() {
        return percentualClientesDentroMeta;
    }

    public void setPercentualClientesDentroMeta(double percentualClientesDentroMeta) {
        this.percentualClientesDentroMeta = percentualClientesDentroMeta;
    }
}