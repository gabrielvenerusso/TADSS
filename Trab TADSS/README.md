# 📊 Simulador de Sistemas de Filas

## 📌 Descrição

Este projeto implementa um simulador estocástico de filas para análise de atendimento em uma instituição financeira fictícia (Firmeza Investimentos).

O objetivo é determinar o número ideal de atendentes para que o tempo médio de espera não ultrapasse **120 segundos**.

O projeto foi desenvolvido em **C# e Java**, mantendo a mesma lógica e estrutura em ambas as linguagens.

---

## 🎯 Objetivo

Simular o comportamento de clientes em uma fila durante o período de pico (11h às 13h), variando a quantidade de atendentes e analisando métricas de desempenho.

---

## ⚙️ Parâmetros da Simulação

* Janela de tempo: **2 horas (7200 segundos)**
* Intervalo de chegada: **5 a 50 segundos**
* Tempo de atendimento: **30 a 120 segundos**
* Meta de espera: **120 segundos**
* Atendentes testados: **1 a 20**
* Repetições por cenário: **300**

---

## 🧠 Funcionamento

1. Gera clientes com tempos de chegada aleatórios
2. Cada cliente é atendido pelo atendente disponível mais cedo
3. Calcula:

   * Tempo de espera
   * Tempo de atendimento
   * Lead time
4. Repete a simulação várias vezes
5. Aplica análise estatística (IC 95%)

---

## 📊 Métricas Avaliadas

* **Throughput**: clientes atendidos no período
* **Tempo médio de espera**
* **Tempo máximo de espera**
* **Tempo médio de atendimento**
* **Lead Time médio**
* **% de clientes dentro da meta (≤ 120s)**
* **Intervalo de confiança (IC 95%)**

---

## ⚡ Concorrência

O projeto utiliza programação concorrente para execução das simulações:

* **C#**: `Parallel.For`
* **Java**: `ExecutorService` com `ThreadPool`

A concorrência é aplicada na execução das repetições de cada cenário.

---

## 🚀 Execução

### ▶️ C#

```bash
dotnet build
dotnet run
```

---

### ▶️ Java

Compile:

```bash
javac src/**/*.java
```

Execute:

```bash
java src.Main
```

---

## 📁 Estrutura do Projeto

```text
/csharp
  ├── Models/
  ├── Services/
  ├── Utils/
  ├── Program.cs

/java
  ├── src/
      ├── models/
      ├── services/
      ├── utils/
      ├── Main.java
```

---

## 📁 Saída

* Resultado exibido no console
* Arquivo CSV gerado:

```text
resultados_simulacao.csv
```

---

## 📌 Resultado Esperado

Identificar o menor número de atendentes que satisfaça:

```
IC95 superior do tempo médio de espera ≤ 120 segundos
```

---

## 👨‍💻 Autor

Gabriel Mendes Venerusso e Maria Eduarda Granza
