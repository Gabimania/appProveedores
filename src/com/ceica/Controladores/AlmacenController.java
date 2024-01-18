package com.ceica.Controladores;

import com.ceica.Modelos.Categoria;
import com.ceica.Modelos.Pedido;
import com.ceica.Modelos.Pieza;
import com.ceica.Modelos.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class AlmacenController {
    private List<Proveedor> proveedorList;
    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;
    private List<Categoria> categoriasList;

    public AlmacenController() {
        proveedorList = new ArrayList<>();
        pedidoList = new ArrayList<>();
        piezaList = new ArrayList<>();
        categoriasList= new ArrayList<>();
        categoriasList.add(new Categoria(1,"metal"));
        categoriasList.add(new Categoria(2,"madera"));
        categoriasList.add(new Categoria(3, "plastico"));
    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedor p = new Proveedor(nombre,cif);
        p.setDireccion(direccion);
        p.setLocalidad(localidad);
        p.setProvincia(provincia);
        return proveedorList.add(p);
    }

    public boolean deleteProveedor(String cif) {
       return proveedorList.removeIf(proveedor -> cif.equals(proveedor.getCif()));

    }

    public boolean cambiarNombreProveedor(String cif, String name){
        for(int i= 0; i < proveedorList.size(); i++){
            if (cif.equals(proveedorList.get(i).getCif())) {
                proveedorList.get(i).setName(name);
                return true;
            }
        }
        return false;
    }

    public boolean cambiarDireccionProveedor(String cif, String direccion){
        for(int i= 0; i < proveedorList.size(); i++){
            if (cif.equals(proveedorList.get(i).getCif())) {
                proveedorList.get(i).setDireccion(direccion);
                return true;
            }
        }
        return false;
    }

    public boolean cambiarLocalidadProveedor(String cif, String localidad){
        return proveedorList.stream()
                .filter(proveedor -> cif.equals(proveedor.getCif()))
                .findFirst()
                .map(proveedor -> {
                    proveedor.setLocalidad(localidad);
                    return true;
                })
                .orElse(false);
    }

    public boolean cambiarProvinciaProveedor(String cif, String provincia){
        for(Proveedor proveedor: proveedorList){
            if (cif.equals(proveedor.getCif())) {
                proveedor.setProvincia(provincia);
                return true;
            }
        }
        return false;
    }

    public boolean nuevaPieza(String name, String color, double precio, int idcategoria){
        Pieza pieza = new Pieza(name, color, precio);
        pieza.setCategoría(getCategoriaById(idcategoria));
        piezaList.add(pieza);
        return true;
    }

    public boolean cambiarPrecioPieza(int id, double precio){
        for (Pieza pieza: piezaList){
            if (id == pieza.getId()){
                pieza.setPrecio(precio);
                return true;
            }
        }
        return false;
    }

    private Categoria getCategoriaById(int id){
       return categoriasList.stream()
               .filter(categoria -> categoria.getId()==id).
               findFirst().get();
    }

    @Override
    public String toString() {
        return "AlmacenController{" +"\n"+
                "proveedorList=" + proveedorList + "\n"+
                ", piezaList=" + piezaList +"\n"+
                ", pedidoList=" + pedidoList +"\n"+
                '}';
    }
}



