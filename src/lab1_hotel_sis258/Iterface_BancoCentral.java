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
interface Iterface_BancoCentral extends Remote{
    double CotizacionDolar(String fecha) throws RemoteException;
}
