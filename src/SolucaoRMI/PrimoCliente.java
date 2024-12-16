/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolucaoRMI;

/**
 *
 * @author luizg
 */
import java.rmi.Naming;
import java.util.List;

public class PrimoCliente {
    public static void main(String[] args) {
        try {
            PrimoGerenciador servidor = (PrimoGerenciador) Naming.lookup("rmi://localhost/PrimosServidor"); //Conecta o cliente ao servidor registrado no RMI

            //Definicao dos intervalos
            int inicio = 1;
            int fim = 10000000;
            
            //Receber tempo inicial da execucao
            long tempoInicial = System.nanoTime();

            System.out.println("Buscando primos no intervalo: " + inicio + " - " + fim);
            List<Integer> primos = servidor.buscaPrimosNoRange(inicio, fim); //Envia o intervalo para buscar os primos e recebe o resultado
            
            //Receber tempo final da execucao
            long tempoFinal = System.nanoTime();
            double totalTempo = (tempoFinal - tempoInicial) / 1_000_000; //Calculo para obter tempo em ms
            
            System.out.println("Quantidade de primos encontrados: " + primos.size()); //Imprime a quantidade de primos encontrados
            System.out.println("Tempo de execucao da busca: " + totalTempo + " ms"); //Imprime o tempo total de execucao da busca
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
