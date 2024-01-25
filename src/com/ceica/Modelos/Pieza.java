package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Pieza extends ModeloBase{
    private static int idpieza=0;
    private int id;
    private String name;
    private String color;
    private Categoria categoría;
    private Double precio;

    public Pieza() {
    }

    public Pieza(String name, String color, Double precio) {
        this.id = idpieza++;
        this.name = name;
        this.color = color;
        this.precio = precio;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Categoria getCategoría() {
        return categoría;
    }

    public void setCategoría(Categoria categoría) {
        this.categoría = categoría;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public static List<Pieza> getPiezas() {
        List<Pieza> piezaList = new ArrayList<>();
        Connection con = Conexion.conectar();
        String sql = "select P.idpieza,P.nombre,P.precio,P.color,C.idcategoria,C.nombre as nombre_categoria from pieza as P inner join categoria as C on P.idcategoria=C.idcategoria";
        try{
            Statement stm = con.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while(respuesta.next()){
                Pieza pieza = new Pieza();
                Categoria categoria = new Categoria();
                pieza.setId(respuesta.getInt("idpieza"));
                pieza.setName(respuesta.getString("nombre"));
                pieza.setColor(respuesta.getString("color"));
                pieza.setPrecio(respuesta.getDouble("precio"));
                categoria.setId(respuesta.getInt("idcategoria"));
                categoria.setNombre(respuesta.getString("nombre_categoria"));
                pieza.setCategoría(categoria);
                piezaList.add(pieza);

            }


    } catch (
    SQLException e) {
        //throw new RuntimeException(e);
        return piezaList;
    }
        try{
        con.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);

    }
        return piezaList;

    }

    @Override
    public String toString() {
        return "Pieza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", categoría=" + categoría +
                ", precio=" + precio +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "pieza";
    }
}
