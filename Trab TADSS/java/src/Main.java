package src;

import src.models.ConfiguracaoSimulacao;
import src.models.ResultadoCenario;
import src.services.AnalisadorCenarios;
import src.services.ExportadorCsv;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        ConfiguracaoSimulacao config = new ConfiguracaoSimulacao();
        config.setDuracaoJanelaSegundos(7200);
        config.setChegadaMinSegundos(5);
        config.setChegadaMaxSegundos(50);
        config.setAtendimentoMinSegundos(30);
        config.setAtendimentoMaxSegundos(120);
        config.setMetaEsperaSegundos(120);
        config.setAtendentesMin(1);
        config.setAtendentesMax(20);
        config.setRepeticoesPorCenario(300);

        AnalisadorCenarios analisador = new AnalisadorCenarios(config);
        List<ResultadoCenario> resultados = analisador.executar();

        System.out.println("Resumo dos cenários");
        System.out.println("------------------------------------------------------------------------------------------------------");
        System.out.println("Atd | Esp.Média | IC95 Sup | Lead Time | Throughput | % Clientes <=120s | Meta");
        System.out.println("------------------------------------------------------------------------------------------------------");

        for (ResultadoCenario resultado : resultados) {
            System.out.printf(Locale.US,
                    "%3d | %9.2f | %8.2f | %9.2f | %10.2f | %16.2f%% | %s%n",
                    resultado.getAtendentes(),
                    resultado.getMediaTempoEsperaFila(),
                    resultado.getIc95SuperiorEsperaFila(),
                    resultado.getMediaLeadTime(),
                    resultado.getMediaThroughput(),
                    resultado.getMediaPercentualClientesDentroMeta(),
                    resultado.isCumpreMeta() ? "SIM" : "NÃO"
            );
        }

        ResultadoCenario melhorCenario = null;
        for (ResultadoCenario resultado : resultados) {
            if (resultado.isCumpreMeta()) {
                melhorCenario = resultado;
                break;
            }
        }

        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------");

        if (melhorCenario != null) {
            System.out.println("Menor número de atendentes que cumpre a meta: " + melhorCenario.getAtendentes());
            System.out.printf(Locale.US, "Tempo médio de espera: %.2f s%n", melhorCenario.getMediaTempoEsperaFila());
            System.out.printf(Locale.US, "IC95 superior da espera média: %.2f s%n", melhorCenario.getIc95SuperiorEsperaFila());
        } else {
            System.out.println("Nenhum cenário dentro do intervalo testado cumpriu a meta.");
        }

        ExportadorCsv.exportar("java/resultados_simulacao.csv", resultados);
        System.out.println();
        System.out.println("Arquivo CSV gerado: resultados_simulacao.csv");
    }
}