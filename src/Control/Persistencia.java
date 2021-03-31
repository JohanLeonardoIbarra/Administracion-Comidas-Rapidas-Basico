/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Comidas;
import java.io.File;
import Modelo.Local;
import Modelo.Venta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author User
 */
public class Persistencia {

    private List<Venta> ventas;
    private List<Local> local;
    private List<Comidas> comidas;
    
    public Persistencia() {
    }

    public Persistencia(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Local> leerLocales(){
        try(BufferedReader br = new BufferedReader(new FileReader("Save\\locales.txt"))){
            List <Local> l = new LinkedList<>();
            String linea = br.readLine();
            while(linea != null){
                String[] x = linea.split(":");
                l.add(new Local(Integer.parseInt(x[0]), x[1], x[2], x[3]));
            }
            return l;
        }
        catch(Exception e){          
            System.out.println("error 4");
            return null;
        }
    }
    
    public boolean guardarLocal(){
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Save\\locales.txt"))){
            for (Local l:local){
                bw.write(l.getCod()+":"+l.getNombre()+":"+l.getDireccion()+":"+l.getTelefono());
                bw.newLine();
            }        
            return true;
        }catch(Exception e){
            System.out.println("error 3");
            return false;
        }
    }
    
    public List<Comidas> leerComidas(){
        try(BufferedReader br = new BufferedReader(new FileReader("Save\\comidas.txt"))){
            List <Comidas> l = new LinkedList<>();
            String linea = br.readLine();
            while(linea != null){
                String[] x = linea.split(":");
                l.add(new Comidas(x[0], Double.parseDouble(x[1])));
                linea = br.readLine();
            }
            return l;
        }
        catch(Exception e){          
            System.out.println("error 6");
            return null;
        }
    }
    
    public boolean guardarComidas(){
        
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("Save\\comidas.txt"));){
            for (Comidas c:comidas){
                bw.write(c.getNombre()+":"+c.getPrecio());
                bw.newLine();
            }        
            return true;
        }catch(Exception e){
            System.out.println("error 5");
            return false;
        }
    }
    
    protected List<Venta> leerVentas(){
        try(BufferedReader br = new BufferedReader(new FileReader("Save\\ventas.txt"));)
        {
            List<Venta> ventas = new LinkedList();
            String linea=br.readLine();
            while(linea!=null){
                String[] x = linea.split(":");
                ventas.add(new Venta(Integer.parseInt(x[0]), Integer.parseInt(x[1]), Double.parseDouble(x[2])));
                linea=br.readLine();
            }
            return ventas;
        }
        catch(Exception e){
            System.out.println("error 2");
            return null;
        }
    }
    
    protected boolean guardarVenta(){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter("Save\\ventas.txt"));){
            for(Venta v:ventas){
                bw.write(v.getLocal()+":"+v.getCantidad()+":"+v.getTotal());
                bw.newLine();
            }
            return true;
        }
        catch(Exception e){
            System.out.println("error");
            return false;
        }
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Local> getLocal() {
        return local;
    }

    public void setLocal(List<Local> local) {
        this.local = local;
    }

    public List<Comidas> getComida() {
        return comidas;
    }

    public void setComida(List<Comidas> comida) {
        this.comidas = comida;
    }

    
    
    
    
    
}
