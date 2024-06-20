package com.mycompany.calcetinesintegridad;

import java.util.Random;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    
    public static void main (String args[]) throws IOException {
        
               
        Gestor a = new Gestor();
        String opcion = "0";
        String Usuarios;
        String Integridad;
        String texto1;
        String texto2;
        Scanner scan = new Scanner(System.in);
        int Nivel; //1=(Administrador) 2(Asistente) 3(Vendedor)

        Integridad =(a.Integridad("Usuarios.txt","Integridad_Usuarios.txt"));
        
        if( Integridad.equals("no") ) {
            System.out.println("Se ha detectado un problema en la integridad del sistema, que deseas hacer?");
            System.out.println("¿Que deseas hacer?:");
            System.out.print("1. Eliminar el dato\n");
            System.out.print("2. Marcar el original como perdida de integridad\n");
            System.out.print("3. Regenerar la integridad\n");
            String opcionIntegridad = scan.nextLine();
            
            if(opcionIntegridad.equals("1")){
                  System.out.println(a.Regenerar("Usuarios.txt","Integridad_Usuarios.txt"));
            }if(opcionIntegridad.equals("2")){
                  System.out.println(a.RegenerarYMarcar("Usuarios.txt","Integridad_Usuarios.txt"));
            }if(opcionIntegridad.equals("3")){
                System.out.println(a.Regenerar("Usuarios.txt","Integridad_Usuarios.txt"));
            }
        }
        
        
        System.out.print("Hola! Introduce tu nombre\n" );
        System.out.println(a.leerNombres("Usuarios.txt"));
        String nombre = scan.nextLine();
        System.out.print("Hola " + nombre +"\n");
        Nivel =(a.Nivel("Usuarios.txt",nombre));

        System.out.println("Tienes nivel: " + Nivel +"\n");
        
        while (Nivel == 3) { // El gestor es un vendedor
        System.out.print("\nSeleccione una:\n");
        System.out.print("1.Listar productos: \n");
        opcion = scan.nextLine();

            if (opcion.equals("1")){
                System.out.print("Lista de productos\n");
                break;
            }else{
                System.out.print("Esa no es una opcion\n");
            }
        }

        while (Nivel == 2){ // El gestor es un asistente
            System.out.print("\nSeleccione una:\n");
            System.out.print("1.Listar productos: \n");
            System.out.print("2.Agregar producto: \n");
            System.out.print("3.Modificar venta: \n");
            System.out.print("4.Desactivar producto: \n");
            System.out.print("5.Crear venta: \n");
            opcion = scan.nextLine();
            
            if (opcion.equals("1")||opcion.equals("2")||opcion.equals("3")||opcion.equals("4")||opcion.equals("5")){
                break;
            }else {
                System.out.print("Esa no es una opcion\n");
            }
        }
        
        while (Nivel == 1){ // El gestor es un administrador
        System.out.print("\nSeleccione una:\n");
        System.out.print("1.Listar productos: \n"); 
        System.out.print("2.Agregar producto: \n"); 
        System.out.print("3.Modificar venta: \n"); 
        System.out.print("4.Desactivar producto: \n"); 
        System.out.print("5.Crear venta: \n"); 
        System.out.print("6.Eliminar venta: \n"); 
        System.out.print("7.Agregar usuario: \n"); 
        opcion = scan.nextLine();
        
            if (opcion.equals("1")||opcion.equals("2")||opcion.equals("3")||opcion.equals("4")||opcion.equals("5")||opcion.equals("6")||opcion.equals("7")){
                break;
            }else {
                System.out.print("Esa no es una opcion\n");
            }
        }
    

        if (Nivel < 4){
            if ("1".equals(opcion)) { // Listar productos
                System.out.println(a.leer("Productos.txt"));
            }
        }
        
        if (Nivel < 3) {
            if ("2".equals(opcion)) { // Agregar productos
                String estampado = "";
                String color = "";
                int precio;
                String activo = "";
                System.out.print("Introduce el estampado del calcetin: ");
                estampado = scan.nextLine();
                System.out.print("Introduce el color del calcetin: ");
                color = scan.nextLine();
                System.out.print("Introduce el precio del calcetin: ");
                precio = Integer.parseInt(scan.nextLine());
                System.out.print("¿Se encuentra activo? Si / No");
                activo = scan.nextLine();

                Random r = new Random();
                int idProducto = r.nextInt(9000) + 1000;

                String nuevo = idProducto + ";" + estampado + ";" + color + ";" + precio + ";" + activo;
                System.out.print("El producto: ");
                System.out.println(a.Agregar("Productos.txt",nuevo));
                System.out.print(" ha sido creado.\n");
            }
        
            if ("3".equals(opcion)) { // Modificar venta
                System.out.print("\nIntroduce el identificador de la venta que deseas modificar: \n");
                System.out.println(a.leer("Ventas.txt"));
                int idVenta;
                idVenta = Integer.parseInt(scan.nextLine());

                System.out.print("¿Como deseas modificarla?\n");

                System.out.print("¿Quieres cambiar el nombre? Si / No\n");
                String cambiarNombre = scan.nextLine();
                String nombreAntiguo = "";
                String nombreNuevo = "";
                String productoAntiguo = "";
                String productoNuevo = "";

                if( cambiarNombre.equals("si") || cambiarNombre.equals("Si") ) {
                    nombreAntiguo =  a.leerVentas("Ventas.txt",idVenta,1);
                    System.out.print("Introduce el nuevo nombre: ");
                    nombreNuevo = scan.nextLine();
                } else if ( cambiarNombre.equals("no") || cambiarNombre.equals("No") ) {
                    nombreAntiguo =  a.leerVentas("Ventas.txt",idVenta,1);
                    nombreNuevo = nombreAntiguo;
                }

                System.out.print("¿Quieres cambiar el producto? Si / No\n");
                String cambiarCalcetin = scan.nextLine();
                String calcetinNuevo = "";
                if( cambiarCalcetin.equals("si") || cambiarCalcetin.equals("Si") ) {
                    productoAntiguo =  a.leerVentas("Ventas.txt",idVenta,2);
                    System.out.print("Introduce el nuevo producto: ");
                    productoNuevo = scan.nextLine();
                } else if ( cambiarCalcetin.equals("no") || cambiarCalcetin.equals("No") ) {
                    productoAntiguo =  a.leerVentas("Ventas.txt",idVenta,2);
                    productoNuevo = productoAntiguo;
                }

                String antiguo = "";
                String nuevo = "";

                antiguo = idVenta + ";" + nombreAntiguo + ";" + productoAntiguo;
                nuevo = idVenta + ";" + nombreNuevo + ";" + productoNuevo;

                System.out.print("La venta: ' " + antiguo + " ' ha sido modificada a:");
                System.out.println(a.Modificar("Ventas.txt",antiguo,nuevo));
            }

            if ("5".equals(opcion)) { // Crear venta
                System.out.print("\nIntroduce el nombre del comprador: \n");
                String nombreComprador = scan.nextLine();

                System.out.print("\nIntroduce el estampado del producto: \n");
                String estampadoProducto = scan.nextLine();

                Random r = new Random();
                int idVenta = r.nextInt(9000) + 1000;

                String nuevo = idVenta + ";" + nombreComprador + ";" + estampadoProducto;
                System.out.print("La venta: ");
                System.out.println(a.Agregar("Ventas.txt",nuevo));
                System.out.print(" ha sido creada.");
            }
        
            if ("4".equals(opcion)) { // Desactivar productos
                System.out.print("\nIntroduce el identificador del producto: \n");
                int idProducto = Integer.parseInt(scan.nextLine());

                String estampado = "";
                String color = "";
                String precio = "";
                String activo = "";

                estampado =  a.leerVentas("Productos.txt",idProducto,1);
                color =  a.leerVentas("Productos.txt",idProducto,2);
                precio =  a.leerVentas("Productos.txt",idProducto,3);
                activo =  a.leerVentas("Productos.txt",idProducto,4);

                String antiguo = idProducto + ";" + estampado + ";" + color + ";" + precio + ";" + activo;

                if( activo.equals("si") || activo.equals("Si") ) {
                    System.out.print("El producto se encuentra: " + activo + "\n"); // activado
                    System.out.print("¿Quieres desactivarlo? Si / No ");
                    String ActivoNuevo = scan.nextLine();
                    if( ActivoNuevo.equals("si") || activo.equals("Si") ) {
                        activo = "No";
                    } else {
                        activo = "Si";
                    }

                } else {
                    System.out.print("El producto se encuentra: " + activo + "\n"); // desactivado
                    System.out.print("¿Quieres activarlo? Si / No ");
                    String ActivoNuevo = scan.nextLine();
                    if( ActivoNuevo.equals("si") || activo.equals("Si") ) {
                        activo = "Si";
                    } else {
                        activo = "No";
                    }
                }
                    String nuevo = idProducto + ";" + estampado + ";" + color + ";" + precio + ";" + activo;

                    System.out.print("El estado del producto: ");
                    System.out.println(a.Modificar("Productos.txt",antiguo,nuevo));
                    System.out.print(" ha sido modoficado.");
            }
        
        }

        if (Nivel < 2){
            if ("6".equals(opcion)) { // Eliminar venta
                System.out.print("\nIntroduce el identificador de la venta que deseas eliminar: \n");
                System.out.println(a.leer("Ventas.txt"));
                int idVenta;
                idVenta = Integer.parseInt(scan.nextLine());

                String borrar = "";
                String nombreAntiguo = "";;
                String productoAntiguo = "";
                nombreAntiguo =  a.leerVentas("Ventas.txt",idVenta,1);
                productoAntiguo =  a.leerVentas("Ventas.txt",idVenta,2);

                borrar = idVenta + ";" + nombreAntiguo + ";" + productoAntiguo;
                System.out.print("La venta:  ");
                System.out.println(a.Eliminar("Ventas.txt",borrar));
                System.out.print(" ha sido eliminada.");
            }   

            if ("7".equals(opcion)) { // Crear usuario
                System.out.print("\nQue usuario deseas crear(Nombre-Nivel): \n");
                System.out.print("\nIntroduce el nombre del usuario: ");
                String nombreUsuario = scan.nextLine();
                System.out.print("\nIntroduce el cargo a asignar:\n");
                System.out.println("1- Administrador");
                System.out.println("2- Asistente");
                System.out.println("3- Vendedor");
                int nivelUser = Integer.parseInt(scan.nextLine());
                String nuevo = nombreUsuario + "-" + nivelUser;
                System.out.println("El usuario ");
                System.out.println(a.Agregar("Usuarios.txt",nuevo));
                System.out.println(a.ModificarIntegridad("Usuarios.txt","Integridad_Usuarios.txt")); // Crear usuario llama a ModificarIntegridad
                System.out.print("El usuario ha sido creado.");
            }
        }
       
        Integridad =(a.Integridad("Usuarios.txt","Integridad_Usuarios.txt"));
        
        if( Integridad.equals("no") ) {
            System.out.println("Se ha detectado un problema en la integridad del sistema, que deseas hacer?");
            System.out.println("¿Que deseas hacer?:");
            System.out.print("1. Eliminar el dato\n");
            System.out.print("2. Marcar el original como perdida de integridad\n");
            System.out.print("3. Regenerar la integridad\n");
            String opcionIntegridad = scan.nextLine();
            
            if(opcionIntegridad.equals("1")){
                  System.out.println(a.Regenerar("Usuarios.txt","Integridad_Usuarios.txt"));
            }if(opcionIntegridad.equals("2")){
                  System.out.println(a.RegenerarYMarcar("Usuarios.txt","Integridad_Usuarios.txt"));
            }if(opcionIntegridad.equals("3")){
                System.out.println(a.Regenerar("Usuarios.txt","Integridad_Usuarios.txt"));
            }
        }
    
    } // MAIN
    
} // CLASS
