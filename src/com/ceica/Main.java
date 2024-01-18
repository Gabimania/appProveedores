package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Modelos.Color;



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
        almacen.nuevaPieza("tornillos", Color.VERDE, 4.5, 1);


        almacen.cambiarPrecioPieza(0,10.10);

        almacen.nuevaPieza("ladrillo", Color.AMARILLO, 3, 2);
        almacen.cambiarPrecioPieza(1, 300);
        System.out.println(almacen.toString());

        System.out.println(almacen.nuevoPedido("345",1,50));
        System.out.println(almacen.getPedidosByPieza(1));
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println(almacen.getPedidosByProveedor("345"));







    }
}