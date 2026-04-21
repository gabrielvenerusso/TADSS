package src.services;

import src.models.Cliente;
import src.models.ConfiguracaoSimulacao;
import src.models.ResultadoSimulacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimuladorFila {
    private final ConfiguracaoSimulacao config;

    public SimuladorFila(ConfiguracaoSimulacao config) {
        this.config = config;
    }

    public ResultadoSimulacao executar(int quantidadeAtendentes, long seed) {
        Random random = new Random(seed);
        List<Cliente> clientes = gerarClientes(random);

        double[] disponibilidadeAtendentes = new double[quantidadeAtendentes];

        double somaEspera = 0.0;
        double maxEspera = 0.0;

        double somaAtendimento = 0.0;
        double maxAtendimento = 0.0;

        double somaLeadTime = 0.0;

        int concluidosAte13h = 0;
        int clientesDentroMeta = 0;

        for (Cliente cliente : clientes) {
            int indiceAtendente = encontrarAtendenteMaisLivre(disponibilidadeAtendentes);

            double inicioAtendimento = Math.max(cliente.instanteChegada(), disponibilidadeAtendentes[indiceAtendente]);
            double tempoEspera = inicioAtendimento - cliente.instanteChegada();
            double fimAtendimento = inicioAtendimento + cliente.tempoAtendimento();
            double leadTime = fimAtendimento - cliente.instanteChegada();

            disponibilidadeAtendentes[indiceAtendente] = fimAtendimento;

            somaEspera += tempoEspera;
            if (tempoEspera > maxEspera) {
                maxEspera = tempoEspera;
            }

            somaAtendimento += cliente.tempoAtendimento();
            if (cliente.tempoAtendimento() > maxAtendimento) {
                maxAtendimento = cliente.tempoAtendimento();
            }

            somaLeadTime += leadTime;

            if (fimAtendimento <= config.getDuracaoJanelaSegundos()) {
                concluidosAte13h++;
            }

            if (tempoEspera <= config.getMetaEsperaSegundos()) {
                clientesDentroMeta++;
            }
        }

        int totalClientes = clientes.size();

        ResultadoSimulacao resultado = new ResultadoSimulacao();
        resultado.setAtendentes(quantidadeAtendentes);
        resultado.setClientesGerados(totalClientes);
        resultado.setClientesConcluidosAte13h(concluidosAte13h);
        resultado.setTempoMedioEsperaFila(totalClientes == 0 ? 0 : somaEspera / totalClientes);
        resultado.setTempoMaximoEsperaFila(maxEspera);
        resultado.setTempoMedioAtendimento(totalClientes == 0 ? 0 : somaAtendimento / totalClientes);
        resultado.setTempoMaximoAtendimento(maxAtendimento);
        resultado.setLeadTimeMedio(totalClientes == 0 ? 0 : somaLeadTime / totalClientes);
        resultado.setPercentualClientesDentroMeta(totalClientes == 0 ? 0 : (clientesDentroMeta * 100.0 / totalClientes));

        return resultado;
    }

    private List<Cliente> gerarClientes(Random random) {
        List<Cliente> clientes = new ArrayList<>();

        double instanteAtual = 0.0;
        int id = 1;

        while (true) {
            int intervaloChegada = random.nextInt(
                    config.getChegadaMaxSegundos() - config.getChegadaMinSegundos() + 1
            ) + config.getChegadaMinSegundos();

            instanteAtual += intervaloChegada;

            if (instanteAtual > config.getDuracaoJanelaSegundos()) {
                break;
            }

            int tempoAtendimento = random.nextInt(
                    config.getAtendimentoMaxSegundos() - config.getAtendimentoMinSegundos() + 1
            ) + config.getAtendimentoMinSegundos();

            clientes.add(new Cliente(id++, instanteAtual, tempoAtendimento));
        }

        return clientes;
    }

    private int encontrarAtendenteMaisLivre(double[] disponibilidadeAtendentes) {
        int indiceMenor = 0;

        for (int i = 1; i < disponibilidadeAtendentes.length; i++) {
            if (disponibilidadeAtendentes[i] < disponibilidadeAtendentes[indiceMenor]) {
                indiceMenor = i;
            }
        }

        return indiceMenor;
    }
}