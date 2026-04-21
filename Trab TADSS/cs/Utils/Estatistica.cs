using System;
using System.Collections.Generic;
using System.Linq;

namespace SimuladorFilas.Utils
{
    public static class Estatistica
    {
        public static double Media(IEnumerable<double> valores)
        {
            var lista = valores.ToList();
            return lista.Count == 0 ? 0 : lista.Average();
        }

        public static double DesvioPadraoAmostral(IEnumerable<double> valores)
        {
            var lista = valores.ToList();
            int n = lista.Count;

            if (n <= 1)
                return 0;

            double media = lista.Average();
            double soma = lista.Sum(v => Math.Pow(v - media, 2));

            return Math.Sqrt(soma / (n - 1));
        }

        public static (double media, double inferior, double superior) IC95(IEnumerable<double> valores)
        {
            var lista = valores.ToList();
            int n = lista.Count;

            if (n == 0)
                return (0, 0, 0);

            double media = Media(lista);
            double desvio = DesvioPadraoAmostral(lista);
            double erro = n > 1 ? 1.96 * (desvio / Math.Sqrt(n)) : 0;

            return (media, media - erro, media + erro);
        }
    }
}