/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lab1_hotel_sis258;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
/**
 *
 * @author Ivan
 */
public class Servidor_Reserva extends UnicastRemoteObject implements Interfaz_Reserva {
   private Servidor_Reserva() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public double cotizar(String inicio, String fin, String fecha_Cotizacion) throws RemoteException {
        int dia_Inicio = Integer.parseInt(inicio.substring(0, 2));
        int dia_Fin = Integer.parseInt(fin.substring(0, 2));
        int[] precios = {30, 25, 25, 35, 40};
        int precio_Dolares = 0;
        for (int i = dia_Inicio; i <= dia_Fin; i++)
            precio_Dolares += precios[i - 26];

        Iterface_BancoCentral interfaceCentral;
        double cotizacionDolar = 0;

        try {
            interfaceCentral = (Iterface_BancoCentral) Naming.lookup("rmi://localhost/cotizacionDolar");
            cotizacionDolar = interfaceCentral.CotizacionDolar(fecha_Cotizacion);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return precio_Dolares * cotizacionDolar;
    }

    @Override
    public String reservar(String inicio, String fin, String id_Cliente, String fecha_Compra) throws RemoteException {
        double total = cotizar(inicio, fin, fecha_Compra);
        String datos = id_Cliente + "," + total;
        String respuesta = "";
        int port = 5001;

        try {
            Socket client = new Socket("localhost", port); //conectarse al socket
            PrintStream toServer = new PrintStream(client.getOutputStream());
            BufferedReader fromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

            toServer.println(datos);  //mandar alservidor
            respuesta = fromServer.readLine();  // devolver del servidor
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        if (respuesta.equals("1"))
            return "Compra exitosa";
        return "Compra fallida";
    }

    public static void main(String[] args) {
        try {
            Registry registro = LocateRegistry.createRegistry(7777);
            registro.rebind("RemotoRMI", new Servidor_Reserva());
            System.out.println("El servidor de reserva esta listo");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
