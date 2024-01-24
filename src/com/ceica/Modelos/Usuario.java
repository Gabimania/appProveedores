package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String usuario;
    private String password;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static boolean getUsuarios(String usuarios, String password) {
        Connection con = Conexion.conectar();
        String sql = "select * from usuarios where usuarios = ? and password = ?";
        try {
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, usuarios);
            stm.setString(2, password);
            ResultSet respuesta = stm.executeQuery();
            if (respuesta.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
