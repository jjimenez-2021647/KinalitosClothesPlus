package com.kinalitosclothes.view;

import com.kinalitosclothes.dominio.Categorias;
import com.kinalitosclothes.dominio.Categorias.Genero;
import com.kinalitosclothes.dominio.Categorias.RangoEdad;

import java.util.List;
import java.util.Scanner;

public class MenuCategorias {
    private Scanner scanner;

    public MenuCategorias() {
        scanner = new Scanner(System.in);
    }

    public int mostrarMenuYObtenerOpcion() {
        System.out.println("-----------------------------------");
        System.out.println("Bienvenido al menú de Categorías");
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

    public Categorias obtenerDatosCategoria() {
        System.out.println("Agregar Categoría");
        System.out.println("Ingrese el código de la categoría:");
        int codigoCategoria = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.println("Ingrese el nombre de la categoría:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese la descripción de la categoría:");
        String descripcion = scanner.nextLine();

        System.out.println("Seleccione el género (Hombre, Mujer, Unisex):");
        Genero genero = null;
        while (genero == null) {
            try {
                genero = Genero.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Opción inválida. Intente de nuevo (Hombre, Mujer, Unisex):");
            }
        }

        System.out.println("Seleccione el rango de edad (Infantil, Juvenil, Adultos):");
        RangoEdad rangoEdad = null;
        while (rangoEdad == null) {
            try {
                rangoEdad = RangoEdad.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Opción inválida. Intente de nuevo (Infantil, Juvenil, Adultos):");
            }
        }

        return new Categorias(codigoCategoria, nombre, descripcion, genero, rangoEdad);
    }

    public void mostrarCategorias(List<Categorias> categorias) {
        System.out.println("Lista de Categorías");
        for (Categorias categoria : categorias) {
            System.out.println("--------------------------------------");
            System.out.println(categoria);
        }
    }

    public int obtenerCodigoCategoria() {
        System.out.println("Ingrese el código de la categoría");
        return scanner.nextInt();
    }

    public String confirmarEliminacion() {
        System.out.println("¿La categoría que desea eliminar es la siguiente?");
        System.out.println("Escriba 'SI' para confirmar:");
        return scanner.next();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public Categorias obtenerDatosActualizados(Categorias categoria) {
        scanner.nextLine(); // Consumir salto de línea pendiente
        System.out.println("Ingrese el nuevo nombre de la categoría:");
        String nuevoNombre = scanner.nextLine();
        System.out.println("Ingrese la nueva descripción de la categoría:");
        String nuevaDescripcion = scanner.nextLine();

        System.out.println("Seleccione el nuevo género (Hombre, Mujer, Unisex):");
        Genero nuevoGenero = null;
        while (nuevoGenero == null) {
            try {
                nuevoGenero = Genero.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Opción inválida. Intente de nuevo (Hombre, Mujer, Unisex):");
            }
        }

        System.out.println("Seleccione el nuevo rango de edad (Infantil, Juvenil, Adultos):");
        RangoEdad nuevoRangoEdad = null;
        while (nuevoRangoEdad == null) {
            try {
                nuevoRangoEdad = RangoEdad.valueOf(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                System.out.println("Opción inválida. Intente de nuevo (Infantil, Juvenil, Adultos):");
            }
        }

        categoria.setNombreCategoria(nuevoNombre);
        categoria.setDescripcionCategoria(nuevaDescripcion);
        categoria.setGenero(nuevoGenero);
        categoria.setRangoEdad(nuevoRangoEdad);

        return categoria;
    }
}
