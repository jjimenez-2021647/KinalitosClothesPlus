package com.kinalitosclothes.view;

import java.util.List;
import java.util.Scanner;

import com.kinalitosclothes.dominio.DetallePedidos;

public class MenuDetallePedido {
    private Scanner scanner;

    public MenuDetallePedido() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenuYObtenerOpcion() {
        System.out.println("-----------------------------------");
        System.out.println("Bienvenido al menu de Detalle de Pedidos");
        System.out.println("Por favor elija una opcion:");
        System.out.println("1. Agregar");
        System.out.println("2. Listar");
        System.out.println("3. Eliminar");
        System.out.println("4. Buscar");
        System.out.println("5. Editar");
        System.out.println("0. Salir");
        System.out.println("-----------------------------------");
        return scanner.nextInt();
    }

    public DetallePedidos obtenerDatosDetallePedidos() {
        System.out.println("Agregar Detalle de Pedido");
        System.out.println("Ingrese la cantidad");
        int cantidad = scanner.nextInt();
        System.out.println("Ingrese el subtotal");
        double subtotal = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la descripcion");
        String descripcion = scanner.nextLine();
        System.out.println("Ingrese el codigo del pedido");
        int codigoPedido = scanner.nextInt();
        System.out.println("Ingrese el codigo del producto");
        int codigoProducto = scanner.nextInt();
        return new DetallePedidos(cantidad, subtotal, descripcion, codigoPedido, codigoProducto);
    }

    public void mostrarDetallePedidos(List<DetallePedidos> detalles) {
        System.out.println("Lista de todos los Detalles de Pedidos");
        for (DetallePedidos detalle : detalles) {
            System.out.println("--------------------------------------");
            System.out.println(detalle);
        }
    }

    public int obtenerIdDetallePedidos() {
        System.out.println("Ingrese el ID del detalle de pedido");
        return scanner.nextInt();
    }

    public String confirmarEliminacion() {
        System.out.println("¿El detalle de pedido que desea eliminar es el siguiente?");
        System.out.println("Escriba 'SI' para confirmar:");
        return scanner.next();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public DetallePedidos obtenerDatosActualizados(DetallePedidos detalle) {
        System.out.println("Ingrese la nueva cantidad");
        int nuevaCantidad = scanner.nextInt();
        System.out.println("Ingrese el nuevo subtotal");
        double nuevoSubtotal = scanner.nextDouble();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese la nueva descripcion");
        String nuevaDescripcion = scanner.nextLine();
        System.out.println("Ingrese el nuevo codigo del pedido");
        int nuevoCodigoPedido = scanner.nextInt();
        System.out.println("Ingrese el nuevo codigo del producto");
        int nuevoCodigoProducto = scanner.nextInt();

        detalle.setCantidad(nuevaCantidad);
        detalle.setSubtotal(nuevoSubtotal);
        detalle.setDescripcion(nuevaDescripcion);
        detalle.setCodigoPedido(nuevoCodigoPedido);
        detalle.setCodigoProducto(nuevoCodigoProducto);

        return detalle;
    }
}