package com.kinalitosclothes.view;

import com.kinalitosclothes.dominio.Clientes;

import java.util.List;
import java.util.Scanner;

public class MenuClientes {
    private Scanner scanner;

    public MenuClientes() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenuYObtenerOpcion() {
        System.out.println("-----------------------------------");
        System.out.println("Bienvenido al menú de Clientes");
        System.out.println("Por favor elija una opción:");
        System.out.println("1. Agregar");
        System.out.println("2. Listar");
        System.out.println("3. Eliminar");
        System.out.println("4. Buscar");
        System.out.println("5. Editar");
        System.out.println("0. Salir");
        System.out.println("-----------------------------------");
        return scanner.nextInt();
    }

    public Clientes obtenerDatosCliente() {
        System.out.println("Agregar Cliente");
        System.out.println("Ingrese el código del cliente:");
        int codigoCliente = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese el nombre del cliente:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del cliente:");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el correo del cliente:");
        String correo = scanner.nextLine();
        System.out.println("Ingrese el teléfono del cliente:");
        String telefono = scanner.nextLine();
        System.out.println("Ingrese la dirección del cliente:");
        String direccion = scanner.nextLine();
        System.out.println("Ingrese el código del usuario asociado:");
        int codigoUsuario = scanner.nextInt();

        return new Clientes(codigoCliente, nombre, apellido, correo, telefono, direccion, codigoUsuario);
    }

    public void mostrarClientes(List<Clientes> clientes) {
        System.out.println("Lista de todos los Clientes");
        for (Clientes cliente : clientes) {
            System.out.println("--------------------------------------");
            System.out.println(cliente);
        }
    }

    public int obtenerIdCliente() {
        System.out.println("Ingrese el ID del cliente");
        return scanner.nextInt();
    }

    public String confirmarEliminacion() {
        System.out.println("¿El cliente que desea eliminar es el siguiente?");
        System.out.println("Escriba 'SI' para confirmar:");
        return scanner.next();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public Clientes obtenerDatosActualizados(Clientes cliente) {
        scanner.nextLine(); // Consumir salto de línea pendiente
        System.out.println("Ingrese el nuevo nombre del cliente:");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Ingrese el nuevo apellido del cliente:");
        String nuevoApellido = scanner.nextLine();
        System.out.println("Ingrese el nuevo correo del cliente:");
        String nuevoCorreo = scanner.nextLine();
        System.out.println("Ingrese el nuevo teléfono del cliente:");
        String nuevoTelefono = scanner.nextLine();
        System.out.println("Ingrese la nueva dirección del cliente:");
        String nuevaDireccion = scanner.nextLine();
        System.out.println("Ingrese el nuevo código de usuario:");
        int nuevoCodigoUsuario = scanner.nextInt();

        cliente.setNombreCliente(nuevoNombre);
        cliente.setApellidoCliente(nuevoApellido);
        cliente.setCorreoCliente(nuevoCorreo);
        cliente.setTelefonoCliente(nuevoTelefono);
        cliente.setDireccionCliente(nuevaDireccion);
        cliente.setCodigoUsuario(nuevoCodigoUsuario);

        return cliente;
    }
}
