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
        categoriasList = new ArrayList<>();
        proveedorList = Proveedor.getProveedores();
        categoriasList.add(new Categoria(1, "metal"));
        categoriasList.add(new Categoria(2, "madera"));
        categoriasList.add(new Categoria(3, "plastico"));
    }

    /**
     * @param cif
     * @param nombre
     * @param direccion
     * @param localidad
     * @param provincia
     * @return Crear nuevo proveedor y se a la lista de proveedores
     */
    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedor p = new Proveedor(nombre, cif);
        p.setDireccion(direccion);
        p.setLocalidad(localidad);
        p.setProvincia(provincia);
        if (Proveedor.insertar(p)) {
            return proveedorList.add(p);
        } else {
            return false;
        }

    }

    /**
     * @param cif
     * @return Se elimina proveedor a través del cip
     */
    public boolean deleteProveedor(String cif) {
       // return proveedorList.removeIf(proveedor -> cif.equals(proveedor.getCif()));
        if(Proveedor.eliminarProveedor(cif)){
            proveedorList= Proveedor.getProveedores();
            return true;
        }else{
            return false;
        }

    }

    /**
     * @param cif
     * @param name
     * @return Se cambia el nombre del proveedor mediante el cip
     */
    public boolean cambiarNombreProveedor(String cif, String name) {
        if (Proveedor.editarnombreProveedor(cif, name)) {
            proveedorList=Proveedor.getProveedores();
            return true;
            /*
            for (int i = 0; i < proveedorList.size(); i++) {
                if (cif.equals(proveedorList.get(i).getCif())) {
                    proveedorList.get(i).setName(name);
                    return true;
                } else {
                    return false;
                }
            }
             */

        }
        return false;
    }

    /**
     * @param cif
     * @param direccion
     * @return Cambio de la dirección del proveedor mediante cip
     */
    public boolean cambiarDireccionProveedor(String cif, String direccion) {
        for (int i = 0; i < proveedorList.size(); i++) {
            if (cif.equals(proveedorList.get(i).getCif())) {
                proveedorList.get(i).setDireccion(direccion);
                return true;
            }
        }
        return false;
    }

    /**
     * @param cif
     * @param localidad
     * @return Cambio de la localidad del proveedor a través del cip
     */
    public boolean cambiarLocalidadProveedor(String cif, String localidad) {
        return proveedorList.stream()
                .filter(proveedor -> cif.equals(proveedor.getCif()))
                .findFirst()
                .map(proveedor -> {
                    proveedor.setLocalidad(localidad);
                    return true;
                })
                .orElse(false);
    }

    /**
     * @param cif
     * @param provincia
     * @return Cambio de la provincia del proveedor a través del cip
     */
    public boolean cambiarProvinciaProveedor(String cif, String provincia) {
        for (Proveedor proveedor : proveedorList) {
            if (cif.equals(proveedor.getCif())) {
                proveedor.setProvincia(provincia);
                return true;
            }
        }
        return false;
    }

    /**
     * @param name
     * @param color
     * @param precio
     * @param idcategoria
     * @return Se crea una pieza con sus parámetros y se añade a la lista de piezas
     */
    public boolean nuevaPieza(String name, Color color, double precio, int idcategoria) {
        Pieza pieza = new Pieza(name, color.toString(), precio);
        pieza.setCategoría(getCategoriaById(idcategoria));
        piezaList.add(pieza);
        return true;
    }

    /**
     * @param id
     * @param precio
     * @return Se cambia el precio de la pieza mediante el id
     */
    public boolean cambiarPrecioPieza(int id, double precio) {
        for (Pieza pieza : piezaList) {
            if (id == pieza.getId()) {
                pieza.setPrecio(precio);
                return true;
            }
        }
        return false;
    }

    /**
     * @param id
     * @return Se obtiene una categoría en cuestión mediante el paso de un id por parámetro
     */
    private Categoria getCategoriaById(int id) {
        return categoriasList.stream()
                .filter(categoria -> categoria.getId() == id).
                findFirst().get();
    }

    /**
     * @param cif
     * @param idPieza
     * @param cantidad
     * @return Creación de pedido
     */
    public String nuevoPedido(String cif, int idPieza, int cantidad) {
        Proveedor proveedor = getProveedorByCif(cif);
        if (proveedor != null) {
            Pieza pieza = getPiezaById(idPieza);
            if (pieza != null) {
                Pedido pedido1 = new Pedido(proveedor, pieza);
                pedido1.setCantidad(cantidad);
                pedido1.setFecha(LocalDate.now());
                pedidoList.add(pedido1);
                return "Pedido creado";
            } else {
                return "Error al crear el pedido, pieza no existe";
            }

        } else {
            return "Error al crear el pedido, proveedor no existe ";
        }

    }

    /**
     * @param id
     * @return Comprobar que una pieza existe en la lista de piezas
     */
    private Pieza getPiezaById(int id) {
        for (int i = 0; i < piezaList.size(); i++) {
            if (piezaList.get(i).getId() == id) {
                return piezaList.get(i);
            }

        }
        return null;
    }

    /**
     * @param cif
     * @return Comprobar que un proveedor existe en la lista de proveedores
     */
    private Proveedor getProveedorByCif(String cif) {
        for (Proveedor p : proveedorList) {
            if (cif.equals(p.getCif())) {
                return p;
            }
        }
        return null;
    }

    public boolean comprobarCif(String cif) {
        for (Proveedor p : proveedorList) {
            if (cif.equals(p.getCif())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param idPieza
     * @return Obtener lista de todos los pedidos con un mismo id
     */
    public String getPedidosByPieza(int idPieza) {
        List<Pedido> pedidosByPieza = new ArrayList<>();

        for (Pedido pedido : pedidoList) {
            if (pedido.getPieza().getId() == idPieza) {
                pedidosByPieza.add(pedido);

            }
        }
        if (pedidosByPieza.size() > 0) {
            return pedidosByPieza.toString();
        } else {
            return "No hay pedidos de esta pieza";
        }

    }

    /**
     * @param cif
     * @return Ontener todos los pedidos de los proveedores con un mismo id
     */
    public String getPedidosByProveedor(String cif) {
        List<Proveedor> pedidosByProveedor = new ArrayList<>();
        for (Proveedor proveedor : proveedorList) {
            if (proveedor.getCif().equals(cif)) {
                pedidosByProveedor.add(proveedor);
            }
        }
        if (pedidosByProveedor.size() > 0) {
            return pedidosByProveedor.toString();
        } else {
            return "No hay pedidos de este proveedor";
        }
    }

    public String veerProvedores() {
        return proveedorList.toString();
    }


    @Override
    public String toString() {
        return "AlmacenController{" + "\n" +
                "proveedorList=" + proveedorList + "\n" +
                ", piezaList=" + piezaList + "\n" +
                ", pedidoList=" + pedidoList + "\n" +
                '}';
    }


}



