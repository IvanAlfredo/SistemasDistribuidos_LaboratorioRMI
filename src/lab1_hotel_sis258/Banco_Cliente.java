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
import java.net.ServerSocket;
import java.net.Socket;
/**
 *
 * @author Ivan
 */
public class Banco_Cliente {
    public static void main(String[] args) {
        int port = 5001;
        try {
            ServerSocket server = new ServerSocket(port);
            Socket cliente;
            BufferedReader fromCliente;
            PrintStream toCliente;

            System.out.println("El servidor cliente está listo");
            while (true) {
                cliente = server.accept(); // el servidorse queda esperando establecer conexion
                fromCliente = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                String[] datos = fromCliente.readLine().split(","); //cadena obtenida desde el lector
                String Id_cliente = datos[0];
                double saldo = Double.parseDouble(datos[1]);
                String respuesta = "0";

                if ((Id_cliente.equals("1") && saldo <= 300) || (Id_cliente.equals("2") && saldo <= 400) || (Id_cliente.equals("3") && saldo <= 1000)){
                    respuesta = "1";
                }
                toCliente = new PrintStream(cliente.getOutputStream()); //prepara el objetopara devolver
                toCliente.flush();
                toCliente.println(respuesta);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
