package src.services;

import src.models.ResultadoCenario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ExportadorCsv {

    public static void exportar(String caminho, List<ResultadoCenario> resultados) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
            writer.write("Atendentes,Repeticoes,MediaClientesGerados,MediaThroughput,MediaTempoEsperaFila,IC95InferiorEsperaFila,IC95SuperiorEsperaFila,MaxTempoEsperaFilaObservado,MediaTempoAtendimento,MaxTempoAtendimentoObservado,MediaLeadTime,MediaPercentualClientesDentroMeta,CumpreMeta");
            writer.newLine();

            for (ResultadoCenario resultado : resultados) {
                writer.write(String.join(",",
                        String.valueOf(resultado.getAtendentes()),
                        String.valueOf(resultado.getRepeticoes()),
                        String.format(Locale.US, "%.2f", resultado.getMediaClientesGerados()),
                        String.format(Locale.US, "%.2f", resultado.getMediaThroughput()),
                        String.format(Locale.US, "%.2f", resultado.getMediaTempoEsperaFila()),
                        String.format(Locale.US, "%.2f", resultado.getIc95InferiorEsperaFila()),
                        String.format(Locale.US, "%.2f", resultado.getIc95SuperiorEsperaFila()),
                        String.format(Locale.US, "%.2f", resultado.getMaxTempoEsperaFilaObservado()),
                        String.format(Locale.US, "%.2f", resultado.getMediaTempoAtendimento()),
                        String.format(Locale.US, "%.2f", resultado.getMaxTempoAtendimentoObservado()),
                        String.format(Locale.US, "%.2f", resultado.getMediaLeadTime()),
                        String.format(Locale.US, "%.2f", resultado.getMediaPercentualClientesDentroMeta()),
                        resultado.isCumpreMeta() ? "SIM" : "NAO"
                ));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar CSV", e);
        }
    }
}