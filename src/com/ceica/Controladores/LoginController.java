package com.ceica.Controladores;

import com.ceica.Modelos.Usuario;

import java.sql.SQLOutput;
import java.util.Scanner;

public class LoginController {

    public static boolean login(String usuario,String password) {
        return Usuario.getUsuarios(usuario, password);
    }

}
