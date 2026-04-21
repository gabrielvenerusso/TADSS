package src.utils;

import java.util.List;

public class Estatistica {

    public static double media(List<Double> valores) {
        if (valores == null || valores.isEmpty()) {
            return 0.0;
        }

        double soma = 0.0;
        for (double valor : valores) {
            soma += valor;
        }

        return soma / valores.size();
    }

    public static double desvioPadraoAmostral(List<Double> valores) {
        if (valores == null || valores.size() <= 1) {
            return 0.0;
        }

        double media = media(valores);
        double soma = 0.0;

        for (double valor : valores) {
            soma += Math.pow(valor - media, 2);
        }

        return Math.sqrt(soma / (valores.size() - 1));
    }

    public static IntervaloConfianca ic95(List<Double> valores) {
        if (valores == null || valores.isEmpty()) {
            return new IntervaloConfianca(0, 0, 0);
        }

        double media = media(valores);
        double desvio = desvioPadraoAmostral(valores);
        double erro = valores.size() > 1 ? 1.96 * (desvio / Math.sqrt(valores.size())) : 0.0;

        return new IntervaloConfianca(media, media - erro, media + erro);
    }

    public record IntervaloConfianca(double media, double inferior, double superior) {
    }
}