/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author User
 */
public class Comidas {
    private String nombre;
    private double precio;

    public Comidas() {
    }
    
    public Comidas buscarComida(String nombre){
        if(nombre.equals(this.nombre)){
            return this;
        }
        return null;
    }

    public Comidas(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }
    
    public String getPrecio2(){
        return precio+"";
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Comidas{" + "nombre=" + nombre + ", precio=" + precio + '}';
    }
    
    
}
