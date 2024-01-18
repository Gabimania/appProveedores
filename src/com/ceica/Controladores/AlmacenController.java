package com.ceica.Controladores;

import com.ceica.Modelos.*;


import java.time.LocalDate;
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

    public boolean nuevaPieza(String name, Color color, double precio, int idcategoria){
        Pieza pieza = new Pieza(name, color.toString(), precio);
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

    public String nuevoPedido(String cif, int idPieza, int cantidad){
        Proveedor proveedor = getProveedorByCif(cif);
        if(proveedor != null){
            Pieza pieza = getPiezaById(idPieza);
            if(pieza != null){
                Pedido pedido1 = new Pedido(proveedor,pieza);
                pedido1.setCantidad(cantidad);
                pedido1.setFecha(LocalDate.now());
                pedidoList.add(pedido1);
                return "Pedido creado";
            }else{
                return "Error al crear el pedido, pieza no existe";
            }

        }else{
            return "Error al crear el pedido, proveedor no existe ";
        }

    }

    private Pieza getPiezaById(int id){
        for (int i = 0; i <piezaList.size(); i++) {
            if (piezaList.get(i).getId()==id){
                return piezaList.get(i);
            }

        }
        return null;
    }

    private Proveedor getProveedorByCif(String cif){
        for (Proveedor p : proveedorList){
            if(cif.equals(p.getCif())){
                return p;
            }
        }
        return null;
    }

    public String getPedidosByPieza(int idPieza){
        List<Pedido> pedidosByPieza = new ArrayList<>();
        for(Pedido pedido: pedidoList){
            if(pedido.getPieza().getId()==idPieza){
                pedidosByPieza.add(pedido);

            }
        }
        if(pedidosByPieza.size()>0){
            return pedidosByPieza.toString();
        }else{
            return "No hay pedidos de esta pieza";
        }

    }

    public String getPedidosByProveedor(String cif){
        List<Proveedor> pedidosByProveedor = new ArrayList<>();
        for(Proveedor proveedor: proveedorList){
            if(proveedor.getCif().equals(cif)){
                pedidosByProveedor.add(proveedor);
            }
        }
        if (pedidosByProveedor.size()>0){
            return pedidosByProveedor.toString();
        }else{
            return "No hay pedidos de este proveedor";
        }
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



