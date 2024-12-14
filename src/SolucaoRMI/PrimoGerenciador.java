/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SolucaoRMI;

/**
 *
 * @author luizg
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

//Interface remota definindo o metodo que o cliente pode chamar
public interface PrimoGerenciador extends Remote {
    List<Integer> buscaPrimosNoRange(int inicio, int fim) throws RemoteException; //Metodo que calcula os primos no intervalo desejado
}
