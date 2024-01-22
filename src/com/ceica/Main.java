package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Controladores.LoginController;
import com.ceica.Modelos.Categoria;
import com.ceica.Modelos.Color;

import java.sql.SQLOutput;
import java.util.Scanner;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String usuario, password;
        AlmacenController almacen = new AlmacenController();
        System.out.println("Bienvenido a AppAlmacén");
        System.out.println("Pulsa enter para empezar");
        Scanner leer = new Scanner(System.in);
        leer.nextLine();
        do {
            System.out.println("Login para empezar");
            System.out.println("Introduce tu usuario");
            usuario = leer.nextLine();
            System.out.println("Introduce la contraseña");
            password = leer.nextLine();
            if (LoginController.login(usuario, password)) {
                System.out.println("Estoy en AppAlmacen");
                menuPrincipalAlmacen(leer, almacen);

                leer.nextLine();
            } else {
                System.out.println("Usuario o contraseña incorrecta");
            }

        } while (true);
    }

    private static void menuPrincipalAlmacen(Scanner leer, AlmacenController almacen) {
        String op = "";
        String menuPrincipal = """
                1.Proveedores
                2.Piezas
                3.Pedidos
                4.Salir
                """;
        do {
            System.out.println(menuPrincipal);
            op = leer.nextLine();
            switch (op) {
                case "1":
                    subMenuProveedores(leer, almacen);
                    break;
                case "2":
                    subMenuPedidos(leer,almacen);
                    break;
                case "3":
                    break;
                case "4":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (!"4".equals(op));

    }

    private static void subMenuProveedores(Scanner leer, AlmacenController almacen) {
        String op;
        String menuProveedores = """
                1.Nuevo proveedor
                2.Cambiar nombre del proveedor
                3.Ver proveedores
                4.Eliminar proveedor
                5.Volver al menú principal
                """;
        do {
            System.out.println(menuProveedores);
            op = leer.nextLine();
            switch (op) {
                case "1":
                    System.out.println("Introduce el nombre del proveedor: ");
                    String nombre = leer.nextLine();
                    System.out.println("Introduce el cif del proveedor: ");
                    String cif = leer.nextLine();
                    System.out.println("Introduce su dirección: ");
                    String direccion = leer.nextLine();
                    System.out.println("Introduce la localidad");
                    String localidad = leer.nextLine();
                    System.out.println("Introduce la provincia");
                    String provincia = leer.nextLine();
                    almacen.nuevoProveedor(nombre, cif, direccion, localidad, provincia);
                    break;
                case "2":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    if (almacen.comprobarCif(cif)) {
                        System.out.println("Introduce el nuevo nombre del proveedor: ");
                        nombre = leer.nextLine();
                        almacen.cambiarNombreProveedor(cif, nombre);
                    } else {
                        System.out.println("El cif de este proveedor no existe en nuestra base de datos");
                    }
                    break;
                case "3":
                    System.out.println(almacen.veerProvedores());
                    break;
                case "4":
                    System.out.println("Introduce el cif del proveedor: ");
                    cif = leer.nextLine();
                    if (almacen.comprobarCif(cif)) {
                        almacen.deleteProveedor(cif);
                    } else {
                        System.out.println("El cif de este proveedor no existe en nuestra base de datos");
                    }
                    almacen.deleteProveedor(cif);
                    break;
                case "5":
                    System.out.println("Volviendo al menu principal: ");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (!"5".equals(op));
    }

    private static void subMenuPedidos(Scanner leer, AlmacenController almacen ){
        String op;
        String menuPedidos = """
                1.Nueva Pieza
                2.Cambiar precio de la pieza
                3.Realizar un nuevo pedido
                4.Obtener pedido por pieza
                5.Volver al menu principañ
                """;

        do{
            System.out.println(menuPedidos);
            op= leer.nextLine();
            switch (op){
                case "1":
                    System.out.println("Introduce el nombre de la pieza: ");
                    String nombre= leer.nextLine() ;
                    System.out.println("Introduce el color de la pieza: 1. Rojo  2. Verde 3. Azul 4. Amarillo 5. Blanco 6. Negro");
                    String opcion= leer.nextLine();
                    Color color = Color.VERDE;
                    switch (opcion){
                        case "1":
                            color =Color.ROJO;
                            break;
                        case "2":
                            color = Color.VERDE;
                            break;
                        case "3":
                            color = Color.AZUL;
                            break;
                        case "4":
                            color = Color.AMARILLO;
                            break;
                        case "5":
                            color = Color.BLANCO;
                            break;
                        case "6":
                            color = Color.NEGRO;
                            break;
                        default:
                            System.out.println("Opción no válida");
                    }
                    System.out.println("Introduce el precio de la pieza");
                    double precio = leer.nextDouble();
                    System.out.println("Introduce la categoría: 1.metal 2.madera 3.plastico");
                    int categoria = leer.nextInt();
                    leer.nextLine();
                    almacen.nuevaPieza(nombre,color,precio,categoria);
                    break;
                case "2":
                    System.out.println("Introduce el id de la pieza");
                    int id = leer.nextInt();
                    leer.nextLine();
                    System.out.println("Introduce el nuevo precio de la pieza");
                    precio = leer.nextDouble();
                    leer.nextLine();
                    almacen.cambiarPrecioPieza(id,precio);
                    break;
                case "3":
                    System.out.println("Introduce el cif del proveedor");
                    String cif = leer.nextLine();
                    System.out.println("Introduce el id de la pieza: ");
                    id = leer.nextInt();
                    leer.nextLine();
                    System.out.println("Introduce la cantidad que deseas del producto: ");
                    int cantidad = leer.nextInt();
                    leer.nextLine();
                    almacen.nuevoPedido(cif,id,cantidad);
                    break;
                case "4":
                    System.out.println("Introduce el id de la pieza");
                    int pieza = leer.nextInt();
                    leer.nextLine();
                    System.out.println(almacen.toString());
                    System.out.println(almacen.getPedidosByPieza(pieza));
                    break;
                case "5":
                    System.out.println("Volviendo al menu principal");
                    break;
                default:
                    System.out.println("Opción no válida");

            }


        }while(!op.equals("4"));


    }

}
