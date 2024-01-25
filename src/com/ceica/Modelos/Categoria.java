package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Categoria extends ModeloBase{
    private int id;
    private String nombre;

    public Categoria() {
    }

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static List<Categoria> getCategorias(){
        List<Categoria> categoriaList = new ArrayList<>();
        Connection con = Conexion.conectar();
        String sql = "select * from categoria";
        try{
            Statement stm = con.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while(respuesta.next()){
                Categoria categoria = new Categoria();
                categoria.setId(respuesta.getInt("idcategoria"));
                categoria.setNombre(respuesta.getString("nombre"));
                categoriaList.add(categoria);
            }
        }
        catch (SQLException e){
            return categoriaList;
        }
        try{
            con.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return categoriaList;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    protected String getNombreTabla() {
        return "categoria";
    }


    public static void main(String[] args) {
        Categoria categoria = new Categoria();

        /*categoria.setNombre("Portatiles");
        String sql = "(nombre) values (?)";
        categoria.insertar(sql,categoria.getNombre());

        categoria.setNombre("Natural");
        categoria.actualizar("set nombre = ?  where idcategoria = ?", "Natural",1);*/

        categoria.borrar("delete from categoria where idcategoria=?", 8);

    }
}
