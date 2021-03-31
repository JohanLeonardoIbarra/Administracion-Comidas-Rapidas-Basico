/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class Venta {
    private int local;
    private List<Comidas> comidas;
    private int cantidad;
    private double total;

    public Venta() {
    }
    
    

    public Venta(int local, int cantidad, double total) {
        this.local = local;
        this.cantidad = cantidad;
        this.total = total;
    }
    
    
    
    public double totalVenta(){
        double total = 0;
        for (Comidas comida: comidas){
            total+= comida.getPrecio();
        }
        return total;
    }
    
    public int cantidadProductosVendidos(){
        return comidas.size();
    }

    public Venta(int local) {
        this.local = local;
        this.comidas = new LinkedList();
    }

    public Venta(int local, List<Comidas> comidas) {
        this.local = local;
        this.comidas = comidas;
        this.total = comidas.size();
        this.total = totalVenta();
    }
    
    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public List<Comidas> getComidas() {
        return comidas;
    }

    public void setComidas(List<Comidas> comidas) {
        this.comidas = comidas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Venta{" + "local=" + local + ", comidas=" + comidas + ", cantidad=" + cantidad + ", total=" + total + '}';
    }
    
    
    
}
