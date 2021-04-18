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
public class Local {
    private int cod;
    private String nombre;
    private String direccion;
    private String telefono;

    public Local() {
    }
    
    public Local(int cod, String nombre, String direccion, String telefono) {
        this.cod = cod;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public int getCodigoLocal(String name){
        return(this.getNombre().equals(name))?this.getCod():0;
    }
    
    public Local buscarLocal(String nombre){
        if(this.getNombre().equals(nombre)){
            return this;
        }
        return null;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Local{" + "cod=" + cod + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }
    
    
}
