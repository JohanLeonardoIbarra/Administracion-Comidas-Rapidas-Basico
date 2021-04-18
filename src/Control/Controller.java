/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Comidas;
import Modelo.Local;
import Modelo.Venta;
import java.util.List;

/**
 *
 * @author User
 */
public class Controller {

    private List<Comidas> comidas;
    private List<Local> locales;
    private List<Venta> ventas;
    private Venta carro;
    private Persistencia p = new Persistencia();

    public Controller(List<Comidas> comidas, List<Local> locales, List<Venta> ventas) {
        this.comidas = comidas;
        this.locales = locales;
        this.ventas = ventas;
        carro = new Venta();
    }

    // METODOS DEL AREA DE COMIDAS
    public List<Comidas> getComidas() {
        comidas = p.leerComidas();
        return comidas;
    }

    public String[] comboBoxComidaPrecio() {
        String a = "";
        for (Comidas s : comidas) {
            a += s.getNombre() + " / " + s.getPrecio() + ":";
        }
        return a.substring(0, a.length()).split(":");
    }

    public Comidas buscarComidaYPrecio(String nombre) {
        Comidas comida = new Comidas();
        for (Comidas c : comidas) {
            comida = c.buscarComida(nombre);
            if (comida != null) {
                return comida;
            }
        }
        return null;
    }

    public void AgregarComida(String nombre, String precio) {
        Double precioReal = Double.parseDouble(precio);
        comidas.add(new Comidas(nombre, precioReal));
        p.setComida(comidas);
        p.guardarComidas();
    }

    public void borrarComida(String nombre) {
        for (int i = 0; i < comidas.size(); i++) {
            if (comidas.get(i).getNombre().equals(nombre)) {
                comidas.remove(i);
            }
        }
        p.setComida(comidas);
        p.guardarComidas();
    }

    //METODOS DEL AREA DE LOCALES
    public List<Local> getLocales() {
        locales = p.leerLocales();
        return locales;
    }

    public String[] comboBoxLocalHistorial() {
        String a = "Todos:";
        for (Local s : locales) {
            a += s.getNombre() + ":";
        }
        return a.substring(0, a.length()).split(":");
    }

    public Local buscarLocal(String local) {
        Local loc = null;
        for (Local l : locales) {
            loc = l.buscarLocal(local);
            if (loc != null) {
                return loc;
            }
        }
        return loc;
    }

    public void agregarLocal(String codigo, String nombre, String direccion, String telefono) {
        int code = Integer.parseInt(codigo);
        locales.add(new Local(code, nombre, direccion, telefono));
        p.setLocal(locales);
        p.guardarLocal();
    }

    public void borrarLocal(String nombre) {
        for (int i = 0; i < locales.size(); i++) {
            if (locales.get(i).getNombre().equals(nombre)) {
                locales.remove(i);
            }
        }
        p.setLocal(locales);
        p.guardarLocal();
    }

    //METODOS DEL CARRITO DE COMPRAS
    public String[] carroLista() {
        if (carro.getComidas().isEmpty()) {
            return "N/A:N/A:N/A".split(":");
        }
        String car = "";
        for (Comidas c : carro.getComidas()) {
            car += c.getNombre() + " - " + c.getPrecio() + ":";
        }
        return car.substring(0, car.length()).split(":");
    }

    public Venta agregarAlCarro(String nombre) {
        List<Comidas> car = carro.getComidas();
        for (Comidas c : comidas) {
            if (c.getNombre().equals(nombre)) {
                car.add(c);
            }
        }
        return carro;
    }

    public Venta sacarDelCarro(int index) {
        List<Comidas> c = carro.getComidas();
        c.remove(index);
        return carro;
    }

    public void Vender(String local) {
        if (carro.getComidas().isEmpty()) {
            return;
        }
        carro.updateValores();
        for (Local l : locales) {
            if (l.getCodigoLocal(local) != 0) {
                carro.setLocal(l.getCod());
            }
        }
        ventas.add(carro);
        p.setVentas(ventas);
        p.guardarVenta();
        carro = new Venta();
    }

    public void limpiarCaja() {
        carro = new Venta();
    }

    //METODOS DEL HISTORIAL DE VENTAS
    public String[] ventasLista(String local) {
        if (ventas.isEmpty()) {
            return "N/A".split(":");
        }
        String car = "";

        if (local.equals("Todos")) {
            for (Venta c : ventas) {
                car += c.toString()+":";
            }
        } else {
            for (Venta c : ventas) {
                if (c.getLocal() == buscarLocal(local).getCod()) {
                    car += c.toString()+":";
                }
            }
        }
        return car.substring(0, car.length()).split(":");
    }
    
    public void borrarRegistro(String venta){
        for (int i = 0; i < ventas.size(); i++) {
            if(ventas.get(i).toString().equals(venta)){
                ventas.remove(i);
            }
        }
        p.setVentas(ventas);
        p.guardarVenta();
    }
    
    public void destruirEvidencias(){
        ventas.clear();
        p.setVentas(ventas);
        p.guardarVenta();
    }
}
