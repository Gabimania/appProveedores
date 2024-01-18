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
        System.out.println("-------------------------------");
        almacen.cambiarNombreProveedor("345", "Leche");
        System.out.println(almacen.toString());
        almacen.cambiarDireccionProveedor("345", "Jaja");
        almacen.cambiarLocalidadProveedor("345", "Cangas");
        almacen.cambiarProvinciaProveedor("345", "Lugo");
        System.out.println(almacen.toString());
        almacen.nuevaPieza("tornillos", "gris", 4.5, 1);


        almacen.cambiarPrecioPieza(0,10.10);

        almacen.nuevaPieza("ladrillo", "rojo", 3, 2);
        almacen.cambiarPrecioPieza(1, 300);
        System.out.println(almacen.toString());






    }
}