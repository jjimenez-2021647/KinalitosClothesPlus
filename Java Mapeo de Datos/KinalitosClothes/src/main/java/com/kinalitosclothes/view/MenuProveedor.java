package com.kinalitosclothes.view;

import com.kinalitosclothes.controller.ProveedoresController;

import java.util.Scanner;

public class MenuProveedor {

    private static final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("|   *** MENÚ DE PROVEEDORES ***  | ");
            System.out.println("|  1. Agregar Proveedor          | ");
            System.out.println("|  2. Listar Proveedores         | ");
            System.out.println("|  3. Buscar Proveedor           | ");
            System.out.println("|  4. Editar Proveedor           | ");
            System.out.println("|  5. Eliminar Proveedor         | ");
            System.out.println("|  6. Salir                      | ");
            System.out.println("");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> ProveedoresController.agregarProveedor();
                case 2 -> ProveedoresController.listarProveedores();
                case 3 -> ProveedoresController.buscarProveedor();
                case 4 -> ProveedoresController.editarProveedor();
                case 5 -> ProveedoresController.eliminarProveedor();
                case 6 -> System.out.println("-*-*- Saliendo del menú...");
                default -> System.out.println("*******Opción no válida*******");
            }

        } while (opcion != 6);
    }
}
