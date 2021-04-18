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
import vista.Gui;

/**
 *
 * @author User
 */
public class VentaDeComidasR {

    /**
     * @param args the command line arguments
     */
    
    private List <Local> locales;
    private List <Comidas> comidas;
    private List <Venta> ventas;
    private Persistencia storage;

    public VentaDeComidasR() {
        storage = new Persistencia();
        locales = storage.leerLocales();
        comidas = storage.leerComidas();
        ventas = storage.leerVentas();
    }
   
    public static void main(String[] args) {
        VentaDeComidasR v = new VentaDeComidasR();
        Controller control = new Controller(v.comidas, v.locales, v.ventas);
        Gui g = new Gui(control);
        g.setVisible(true);
    }

    public List<Local> getLocales() {
        return locales;
    }

    public void setLocales(List<Local> locales) {
        this.locales = locales;
    }

    public List<Comidas> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comidas> comidas) {
        this.comidas = comidas;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public Persistencia getStorage() {
        return storage;
    }

    public void setStorage(Persistencia storage) {
        this.storage = storage;
    }
    
    
    
}
