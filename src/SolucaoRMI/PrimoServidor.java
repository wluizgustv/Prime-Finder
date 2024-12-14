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
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

//Implementacao do servico remoto
class PrimoGerenciadorImpl extends UnicastRemoteObject implements PrimoGerenciador {

    //Construtor da classe base UnicastRemoteObject
    protected PrimoGerenciadorImpl() throws RemoteException {
        super();
    }

    @Override
    public List<Integer> buscaPrimosNoRange(int inicio, int fim) throws RemoteException { //Implementa a logica para encontrar os primos
        List<Integer> primos = new ArrayList<>(); //Declaracao do array para armazenar os primos
        for (int i = Math.max(inicio, 2); i <= fim; i++) {
            if (ehPrimo(i)) {
                primos.add(i); //Adiciona no array caso seja primo
            }
        }
        return primos;
    }

    //Funcao para saber se eh primo
    private boolean ehPrimo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}

//Classe principal do servidor
public class PrimoServidor {
    public static void main(String[] args) {
        try {
            java.rmi.registry.LocateRegistry.createRegistry(1099); //Cria o registro RMI na porta 1099
            System.out.println("RMI Registry iniciado na porta 1099.");
            PrimoGerenciador service = new PrimoGerenciadorImpl(); //Instancia a implementacao do servico remoto a ser registrado no RMI
            java.rmi.Naming.rebind("rmi://localhost:1099/PrimosServidor", service); //Nomeia o servico remoto no registro RMI
            System.out.println("Servidor de primos iniciado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
