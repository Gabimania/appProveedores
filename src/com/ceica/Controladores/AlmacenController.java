package com.ceica.Controladores;

import com.ceica.Modelos.Pedido;
import com.ceica.Modelos.Pieza;
import com.ceica.Modelos.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class AlmacenController {
    private List<Proveedor> proveedorList;
    private List<Pieza> piezaList;
    private List<Pedido>pedidoList;

    public AlmacenController() {
        proveedorList= new ArrayList<>();
        pedidoList = new ArrayList<>();
        piezaList = new ArrayList<>();
    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia){
        Proveedor p = new Proveedor(cif,nombre);
        p.setDireccion(direccion);
        p.setLocalidad(localidad);
        p.setProvincia(provincia);
        return proveedorList.add(p);
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



