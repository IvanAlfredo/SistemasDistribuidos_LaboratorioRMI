/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_hotel_sis258;

  
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
/**
 *
 * @author Ivan
 */
public class Cliente {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.ConectServer();
    }
    private void ConectServer(){
        Scanner sc = new Scanner(System.in);
        String Inicio, Fin, Fecha, Id_Cliente;
        
        try {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7777);
            Interfaz_Reserva reserva = (Interfaz_Reserva) registro.lookup("RemotoRMI"); //
            System.out.println("Que operación desea realizar: ");
            System.out.println("Opciones: ");
            System.out.println( "1) Cotizar ");
            System.out.println( "2) Reservar");
            System.out.print("Digite su opcion: ");


            int opcion = Integer.parseInt(sc.nextLine());
            System.out.print("Fecha de Inicio (dd-mm-aa):");
            Inicio = sc.nextLine();

            System.out.print("Fecha Fin (dd-mm-aa):");
            Fin = sc.nextLine();
            switch (opcion){
                case 1:
                    System.out.print("Fecha Cotizacion (dd-mm-aa):");
                    Fecha= sc.nextLine();
                    System.out.println("Costo total: " + reserva.cotizar(Inicio,Fin,Fecha) +"Bs");
                    break;
                case 2:
                    System.out.print("Id Cliente:");
                    Id_Cliente = sc.nextLine();
                    System.out.print("Fecha Compra (dd-mm-aa):");
                    Fecha = sc.nextLine();
                    System.out.println( reserva.reservar(Inicio,Fin,Id_Cliente,Fecha) );
                    break;
                default:
                    System.out.println("Opcion no valida");
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
