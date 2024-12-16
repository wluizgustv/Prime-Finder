/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sequencial;

/**
 *
 * @author luizg
 */
import java.util.ArrayList;
import java.util.List;

public class BuscaPrimos {

    public static void main(String[] args) {
        // Intervalo para buscar primos
        long inicio = 1;
        long fim = 10000000;

        System.out.println("Buscando números primos entre " + inicio + " e " + fim + "...");
        //Receber tempo inicial da execucao
        long tempoInicial = System.nanoTime();

        List<Long> primos = buscarPrimos(inicio, fim); //Declaracao da lista para armazenar os primos

        //Receber tempo final da execucao
        long tempoFinal = System.nanoTime();
        double totalTempo = (tempoFinal - tempoInicial) / 1_000_000; //Calculo para obter tempo em ms
        System.out.println("Primos encontrados: " + primos.size()); //Imprime o total de primos encontrados
        System.out.println("Tempo de execucao: " + totalTempo + " ms"); //Imprime o tempo total de execucao em ms
    }

    //Funcao para adicionar os numeros primos no array
    public static List<Long> buscarPrimos(long inicio, long fim) {
        List<Long> primos = new ArrayList<>();

        for (long numero = inicio; numero <= fim; numero++) {
            if (ehPrimo(numero)) { //Caso o numero seja primo:
                primos.add(numero); //adiciona no array.
            }
        }

        return primos; //Retorna o array com os primos
    }

    //Logica para descobrir se o numero eh primo
    public static boolean ehPrimo(long numero) {
        if (numero < 2) {
            return false;
        }
        if (numero == 2) {
            return true;
        }
        if (numero % 2 == 0) {
            return false;
        }
        long limite = (long) Math.sqrt(numero);
        for (long i = 3; i <= limite; i += 2) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
}
