package src.services;

import src.models.ConfiguracaoSimulacao;
import src.models.ResultadoCenario;
import src.models.ResultadoSimulacao;
import src.utils.Estatistica;
import src.utils.Estatistica.IntervaloConfianca;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AnalisadorCenarios {
    private final ConfiguracaoSimulacao config;

    public AnalisadorCenarios(ConfiguracaoSimulacao config) {
        this.config = config;
    }

    public List<ResultadoCenario> executar() {
        List<ResultadoCenario> resultados = new ArrayList<>();

        for (int atendentes = config.getAtendentesMin(); atendentes <= config.getAtendentesMax(); atendentes++) {
            ResultadoSimulacao[] execucoes = new ResultadoSimulacao[config.getRepeticoesPorCenario()];

            int totalThreads = Runtime.getRuntime().availableProcessors();
            ExecutorService executor = Executors.newFixedThreadPool(totalThreads);

            List<Future<?>> futures = new ArrayList<>();

            for (int i = 0; i < config.getRepeticoesPorCenario(); i++) {
                final int indice = i;
                final int qtdAtendentes = atendentes;

                futures.add(executor.submit(() -> {
                    long seed = (qtdAtendentes * 100000L) + indice + 7L;
                    SimuladorFila simulador = new SimuladorFila(config);
                    execucoes[indice] = simulador.executar(qtdAtendentes, seed);
                }));
            }

            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Execução interrompida", e);
                } catch (ExecutionException e) {
                    throw new RuntimeException("Erro ao executar cenário", e);
                }
            }

            executor.shutdown();

            List<Double> mediasEspera = new ArrayList<>();
            double somaClientesGerados = 0.0;
            double somaThroughput = 0.0;
            double somaTempoEspera = 0.0;
            double maxTempoEspera = 0.0;
            double somaTempoAtendimento = 0.0;
            double maxTempoAtendimento = 0.0;
            double somaLeadTime = 0.0;
            double somaPercentualMeta = 0.0;

            for (ResultadoSimulacao execucao : execucoes) {
                mediasEspera.add(execucao.getTempoMedioEsperaFila());
                somaClientesGerados += execucao.getClientesGerados();
                somaThroughput += execucao.getClientesConcluidosAte13h();
                somaTempoEspera += execucao.getTempoMedioEsperaFila();
                somaTempoAtendimento += execucao.getTempoMedioAtendimento();
                somaLeadTime += execucao.getLeadTimeMedio();
                somaPercentualMeta += execucao.getPercentualClientesDentroMeta();

                if (execucao.getTempoMaximoEsperaFila() > maxTempoEspera) {
                    maxTempoEspera = execucao.getTempoMaximoEsperaFila();
                }

                if (execucao.getTempoMaximoAtendimento() > maxTempoAtendimento) {
                    maxTempoAtendimento = execucao.getTempoMaximoAtendimento();
                }
            }

            IntervaloConfianca ic95 = Estatistica.ic95(mediasEspera);
            int repeticoes = config.getRepeticoesPorCenario();

            ResultadoCenario cenario = new ResultadoCenario();
            cenario.setAtendentes(atendentes);
            cenario.setRepeticoes(repeticoes);
            cenario.setMediaClientesGerados(somaClientesGerados / repeticoes);
            cenario.setMediaThroughput(somaThroughput / repeticoes);
            cenario.setMediaTempoEsperaFila(somaTempoEspera / repeticoes);
            cenario.setMaxTempoEsperaFilaObservado(maxTempoEspera);
            cenario.setMediaTempoAtendimento(somaTempoAtendimento / repeticoes);
            cenario.setMaxTempoAtendimentoObservado(maxTempoAtendimento);
            cenario.setMediaLeadTime(somaLeadTime / repeticoes);
            cenario.setMediaPercentualClientesDentroMeta(somaPercentualMeta / repeticoes);
            cenario.setIc95InferiorEsperaFila(ic95.inferior());
            cenario.setIc95SuperiorEsperaFila(ic95.superior());
            cenario.setCumpreMeta(ic95.superior() <= config.getMetaEsperaSegundos());

            resultados.add(cenario);
        }

        return resultados;
    }
}