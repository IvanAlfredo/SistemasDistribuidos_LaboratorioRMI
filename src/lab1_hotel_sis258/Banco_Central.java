/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_hotel_sis258;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
/**
 *
 * @author Ivan
 */
public class Banco_Central extends UnicastRemoteObject implements Iterface_BancoCentral {
    private Banco_Central() throws java.rmi.RemoteException {
        super();
    }
    @Override
    public double CotizacionDolar(String fecha) throws RemoteException {
        switch (fecha) {
            case "26-09-19":
                return 6.9;

            case "27-09-19":
                return 6.91;

            case "28-09-19":
                return 6.93;

            case "29-09-19":
                return 6.92;

            case "30-09-19":
                return 6.96;
        }
        return 0;
    }
    public static void main(String args[]) {
        try {
            Banco_Central Cotizar;
            LocateRegistry.createRegistry(1099);
            Cotizar = new Banco_Central();
            Naming.bind("Cotizar", Cotizar);
            System.out.println("El Banco Central esta listo\n");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
