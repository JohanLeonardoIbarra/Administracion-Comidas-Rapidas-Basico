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
    /**
     * Llama a la clase de persistenca y asigna el valor de las comidas
     */
    public List<Comidas> getComidas() {
        comidas = p.leerComidas();
        return comidas;
    }

    /**
     * retorna un arreglo de Strings para mostrarlo en el combobox
     */
    public String[] comboBoxComidaPrecio() {
        String a = "";
        for (Comidas s : comidas) {
            a += s.getNombre() + " / " + s.getPrecio() + ":";
        }
        return a.substring(0, a.length()).split(":");
    }

    /**
     * Busca una comida en especifico y devuelve el objeto
     *
     * @param nombre
     */
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

    /**
     * Recibe el nombre y el precio de una comida y los guarda llamando a la
     * clase de persistencia
     *
     * @param nombre
     * @param precio
     */
    public void AgregarComida(String nombre, String precio) {
        Double precioReal = Double.parseDouble(precio);
        comidas.add(new Comidas(nombre, precioReal));
        p.setComida(comidas);
        p.guardarComidas();
    }

    /**
     * recibe el nombre de una comida y la borra llamando a la clase de
     * persistencia
     */
    public void borrarComida(String nombre) {
        for (int i = 0; i < comidas.size(); i++) {
            if (comidas.get(i).getNombre().equals(nombre)) {
                comidas.remove(i);
            }
        }
        p.setComida(comidas);
        p.guardarComidas();
    }

    /**
     * Devuelve un arreglo de Strings para el combobox de comidas
     */
    public String[] comboBoxComida() {
        String a = "";
        for (Comidas s : comidas) {
            a += s.getNombre() + ":";
        }
        return a.substring(0, a.length()).split(":");
    }

    //METODOS DEL AREA DE LOCALES
    /**
     * Llama a la clase de persistenca y asigna el valor de los locales
     */
    public List<Local> getLocales() {
        locales = p.leerLocales();
        return locales;
    }

    /**
     * Devuelve un arreglo de Strings para el combobox de locales
     */
    public String[] comboBoxLocal() {
        String a = "";
        for (Local s : locales) {
            a += s.getNombre() + ":";
        }
        return a.substring(0, a.length()).split(":");
    }

    /**
     * Devuelve un arreglo de Strings para mostrar en el combobox
     */
    public String[] comboBoxLocalHistorial() {
        String a = "Todos:";
        for (Local s : locales) {
            a += s.getNombre() + ":";
        }
        return a.substring(0, a.length()).split(":");
    }

    /**
     * Busca un local en especifico y devuelve el objeto
     */
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

    /**
     * Recibe los datos de un local y los guarda llamando a la clase de
     * persistencia
     */
    public void agregarLocal(String codigo, String nombre, String direccion, String telefono) {
        int code = Integer.parseInt(codigo);
        locales.add(new Local(code, nombre, direccion, telefono));
        p.setLocal(locales);
        p.guardarLocal();
    }

    /**
     * recibe el nombre de un local y lo borra llamando a la clase de
     * persistencia
     */
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
    /**
     * devuelve un arreglo de Strings para mostrar en la lista del carrito de
     * compras
     */
    public String[] carroLista() {
        if (carro.getComidas().isEmpty()) {
            return "N/A:N/A".split(":");
        }
        String car = "";
        for (Comidas c : carro.getComidas()) {
            car += c.getNombre() + " - " + c.getPrecio() + ":";
        }
        return car.substring(0, car.length()).split(":");
    }

    /**
     * Toma el nombre de una comida y la agrega al carrito
     *
     * @param nombre
     */
    public Venta agregarAlCarro(String nombre) {
        List<Comidas> car = carro.getComidas();
        for (Comidas c : comidas) {
            if (c.getNombre().equals(nombre)) {
                car.add(c);
            }
        }
        return carro;
    }

    /**
     * Saca una comida del carrito
     *
     * @param index
     */
    public Venta sacarDelCarro(int index) {
        List<Comidas> c = carro.getComidas();
        c.remove(index);
        return carro;
    }

    /**
     * Crea una venta con todos los objetos del carrito de compra
     *
     * @param local
     */
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

    /**
     * Vacia el carrito de compras
     */
    public void limpiarCaja() {
        carro = new Venta();
    }

    //METODOS DEL HISTORIAL DE VENTAS
    /**
     * Recibe como parametro el nombre de un local y devuelve un arreglo con las
     * ventas realizadas de dicho local
     *
     * @param local
     */
    public String[] ventasLista(String local) {
        if (ventas.isEmpty()) {
            return "N/A".split(":");
        }
        String car = "";

        if (local.equals("Todos")) {
            for (Venta c : ventas) {
                car += c.toString() + ":";
            }
        } else {
            for (Venta c : ventas) {
                if (c.getLocal() == buscarLocal(local).getCod()) {
                    car += c.toString() + ":";
                }
            }
        }
        return car.substring(0, car.length()).split(":");
    }

    /**
     * Borra una venta del historial
     *
     * @param venta
     */
    public void borrarRegistro(String venta) {
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).toString().equals(venta)) {
                ventas.remove(i);
            }
        }
        p.setVentas(ventas);
        p.guardarVenta();
    }

    /**
     * Borra todo del historial de ventas
     */
    public void destruirEvidencias() {
        ventas.clear();
        p.setVentas(ventas);
        p.guardarVenta();
    }
}
