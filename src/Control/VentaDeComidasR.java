/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Comidas;
import Modelo.Local;
import Modelo.Venta;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class VentaDeComidasR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Persistencia p = new Persistencia();
        List <Venta> ventas = new LinkedList();
        ventas.add(new Venta(1, 3, 150));
        ventas.add(new Venta(1, 5, 200));
        
        List <Comidas> comidas = new LinkedList<>();
        comidas.add(new Comidas("Hamburquesa", (double)4000));
        comidas.add(new Comidas("Empanada", (double)800));
        
        //p.setVentas(ventas);
        p.setComida(comidas);
        p.guardarComidas();
        //p.guardarVenta();
        
        //for(Venta v : p.leerVentas()){
        //    System.out.println(v);
        //}
        for(Comidas c : p.leerComidas()){
            System.out.println(c);
        }
    }
    
}
