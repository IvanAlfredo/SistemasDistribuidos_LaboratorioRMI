/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1_hotel_sis258;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author Ivan
 */
interface Interfaz_Reserva extends Remote{
    double cotizar(String inicio, String fin, String fecha_Cotizacion) throws RemoteException;
    String reservar(String inicio, String fin, String id_Cliente, String fecha_Compra) throws RemoteException;
}
