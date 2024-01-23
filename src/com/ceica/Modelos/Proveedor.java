package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Proveedor {
    private int id;
    private String name;
    private String cif;
    private String direccion;
    private String localidad;
    private String provincia;

    public Proveedor() {
    }

    public Proveedor(String cif, String name) {
        this.cif = cif;
        this.name = name;

    }

    public static boolean insertar(Proveedor p) {
        Connection  con = Conexion.conectar();
        String sql = "insert into provedor(nombre,direccion,localidad,provincia,cif) " +
                " values (?,?,?,?,?) ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,p.getName());
            pst.setString(2,p.getDireccion());
            pst.setString(3,p.getLocalidad());
            pst.setString(4,p.getProvincia());
            pst.setString(5,p.getCif());
            if(pst.executeUpdate()<0){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean editarnombreProveedor(String cif, String name) {
        Connection con = Conexion.conectar();
        String sql = "update provedor set nombre=? where cif=?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,name);
            pst.setString(2,cif);
            if(pst.executeUpdate()>0){
                con.close();
                return true;
            }else{
                con.close();
                return false;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    public static boolean eliminarProveedor(String cif) {
        Connection con = Conexion.conectar();
        String sql = "delete from provedor where cif = ?";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,cif);
            if(pst.executeUpdate()>0){
                con.close();
                return true;
            }else{
                con.close();
                return false;
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public static List<Proveedor> getProveedores()  {
        List<Proveedor> proveedorList = new ArrayList<>();
        Connection con = Conexion.conectar();
        String sql = "select * from provedor";
        try {
            Statement stm = con.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while(respuesta.next()){
                Proveedor proveedor = new Proveedor();
                proveedor.setId(respuesta.getInt("idprovedor"));
                proveedor.setCif(respuesta.getString("cif"));
                proveedor.setName(respuesta.getString("nombre"));
                proveedor.setDireccion(respuesta.getString("direccion"));
                proveedor.setLocalidad(respuesta.getString("localidad"));
                proveedor.setProvincia(respuesta.getString("provincia"));
                proveedorList.add(proveedor);
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return proveedorList;
        }
        try{
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return proveedorList;
    }
    @Override
    public String toString() {
        return "Proveedor{" + "\n"+
                "id=" + id + "\n"+
                ", name='" + name + '\'' + "\n"+
                ", cif='" + cif + '\'' + "\n"+
                ", direccion='" + direccion + '\'' + "\n"+
                ", localidad='" + localidad + '\'' + "\n"+
                ", provincia='" + provincia + '\'' + "\n"+
                '}';
    }
}
