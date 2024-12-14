/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolucaoParalela;

/**
 *
 * @author luizg
 */
import java.util.ArrayList;
import java.util.List;

public class BuscaThreads {

    // Classe para encontrar primos em um intervalo específico
    static class EncontraPrimosThread extends Thread {
        private final int start; //Inicio do intervalo
        private final int end; //Fim do intervalo
        private final List<Integer> primos; //Lista de primos encontrados

        public EncontraPrimosThread(int start, int end) {
            this.start = start;
            this.end = end;
            this.primos = new ArrayList<>();
        }

        @Override
        public void run() { //Metodo executado pela thread
            for (int i = start; i <= end; i++) {
                if (ehPrimo(i)) {
                    primos.add(i); //Adiciona o numero primo a lista
                }
            }
        }

        public List<Integer> getPrimos() {
            return primos; //Retorna os primos encontrados
        }

        //Logica para encontrar primos
        private boolean ehPrimo(int num) {
            if (num < 2) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //Definicao dos intervalos e do numero de threads a serem usadas
        int inicio = 1;
        int fim = 10_000_000;
        int numThreads = 100;

        //Receber tempo inicial da execucao
        long tempoInicial = System.nanoTime();
        
        // Divisão do intervalo
        int rangePorThread = (fim - inicio + 1) / numThreads;
        List<EncontraPrimosThread> threads = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            int rangeInicial = inicio + i * rangePorThread; //Inicio do subintervalo
            int rangeFinal = (i == numThreads - 1) ? fim : rangeInicial + rangePorThread - 1; //Fim do subintervalo
            EncontraPrimosThread thread = new EncontraPrimosThread(rangeInicial, rangeFinal);//Criacao de uma nova thread
            threads.add(thread);//Adiciona a lista de threads
            thread.start(); //Inicia a thread
        }

        for (EncontraPrimosThread thread : threads) {
            thread.join(); //Espera a thread terminar
        }

        //Combinar os resultados
        List<Integer> totalPrimos = new ArrayList<>();
        for (EncontraPrimosThread thread : threads) {
            totalPrimos.addAll(thread.getPrimos()); //Junta os resultados das threads
        }
        
        //Receber tempo final da execucao
        long tempoFinal = System.nanoTime();
        double totalTempo = (tempoFinal - tempoInicial) / 1_000_000; //Calculo para obter tempo em ms
        
        System.out.println("Total de primos encontrados no intervalo: " + totalPrimos.size()); //Imprime os primos encontrados
        System.out.println("Tempo de execucao: " + totalTempo + " ms");
    }
}

