package com.ceica;

import com.ceica.Controladores.AlmacenController;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AlmacenController almacen = new AlmacenController();
        almacen.nuevoProveedor("345", "Carne", "avenida X", "Vigo", "Pontevedra");
        System.out.println(almacen.toString());
        almacen.nuevoProveedor("543", "Chapela", "Lugo 10", "Santiago", "Coruña");
        System.out.println(almacen.toString());
        almacen.deleteProveedor("543");
        System.out.println("-------------------------------");
        System.out.println(almacen.toString());


    }
}