package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Categorias;
import com.kinalitosclothes.dominio.Categorias.Genero;
import com.kinalitosclothes.dominio.Categorias.RangoEdad;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;

public class CategoriasController {

    Scanner leer = new Scanner(System.in);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
    EntityManager em = emf.createEntityManager();

    public void agregarCategoria() {
        System.out.println("-----------------------------------");
        System.out.println("Agregar Categoría");

        System.out.println("Ingrese el código de la categoría:");
        int codigo = leer.nextInt();
        leer.nextLine();
        System.out.println("Ingrese el nombre de la categoría:");
        String nombre = leer.nextLine();
        System.out.println("Ingrese la descripción de la categoría:");
        String descripcion = leer.nextLine();

        System.out.println("Seleccione el género (Hombre, Mujer, Unisex):");
        Genero genero = Genero.valueOf(leer.nextLine());

        System.out.println("Seleccione el rango de edad (Infantil, Juvenil, Adultos):");
        RangoEdad rangoEdad = RangoEdad.valueOf(leer.nextLine());

        Categorias nuevaCategoria = new Categorias();
        nuevaCategoria.setCodigoCategoria(codigo);
        nuevaCategoria.setNombreCategoria(nombre);
        nuevaCategoria.setDescripcionCategoria(descripcion);
        nuevaCategoria.setGenero(genero);
        nuevaCategoria.setRangoEdad(rangoEdad);

        em.getTransaction().begin();
        em.persist(nuevaCategoria);
        em.getTransaction().commit();
        System.out.println("Categoría agregada exitosamente.");
    }

    public void listarCategorias() {
        System.out.println("-----------------------------------");
        System.out.println("Lista de Categorías:");
        TypedQuery<Categorias> query = em.createQuery("SELECT c FROM Categorias c", Categorias.class);
        List<Categorias> categorias = query.getResultList();
        for (Categorias c : categorias) {
            System.out.println("--------------------------------------");
            System.out.println(c);
        }
    }

    public void eliminarCategoria() {
        System.out.println("-----------------------------------");
        System.out.println("Eliminar Categoría");
        System.out.println("Ingrese el código de la categoría a eliminar:");
        try {
            int codigo = leer.nextInt();
            leer.nextLine();
            Categorias c = em.find(Categorias.class, codigo);
            if (c != null) {
                System.out.println("¿Está seguro de eliminar esta categoría?");
                System.out.println(c);
                System.out.println("Escriba 'SI' para confirmar:");
                String confirmacion = leer.nextLine();
                if (confirmacion.equalsIgnoreCase("SI")) {
                    em.getTransaction().begin();
                    em.remove(c);
                    em.getTransaction().commit();
                    System.out.println("Categoría eliminada correctamente.");
                } else {
                    System.out.println("Operación cancelada.");
                }
            } else {
                System.out.println("No se encontró ninguna categoría con ese código.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un código válido.");
        }
    }

    public void buscarCategoria() {
        System.out.println("-----------------------------------");
        System.out.println("Buscar Categoría");
        System.out.println("Ingrese el código de la categoría:");
        int codigo = leer.nextInt();
        leer.nextLine();
        Categorias c = em.find(Categorias.class, codigo);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    public void editarCategoria() {
        System.out.println("-----------------------------------");
        System.out.println("Editar Categoría");
        System.out.println("Ingrese el código de la categoría a editar:");
        try {
            int codigo = leer.nextInt();
            leer.nextLine();
            Categorias c = em.find(Categorias.class, codigo);
            if (c != null) {
                System.out.println("Categoría encontrada:");
                System.out.println(c);

                System.out.println("Nuevo nombre (enter para no cambiar):");
                String nombre = leer.nextLine();
                if (!nombre.isEmpty()) c.setNombreCategoria(nombre);

                System.out.println("Nueva descripción (enter para no cambiar):");
                String descripcion = leer.nextLine();
                if (!descripcion.isEmpty()) c.setDescripcionCategoria(descripcion);

                System.out.println("Nuevo género (Hombre, Mujer, Unisex o enter para no cambiar):");
                String gen = leer.nextLine();
                if (!gen.isEmpty()) c.setGenero(Genero.valueOf(gen));

                System.out.println("Nuevo rango de edad (Infantil, Juvenil, Adultos o enter para no cambiar):");
                String rango = leer.nextLine();
                if (!rango.isEmpty()) c.setRangoEdad(RangoEdad.valueOf(rango));

                em.getTransaction().begin();
                em.merge(c);
                em.getTransaction().commit();
                System.out.println("Categoría actualizada exitosamente.");
            } else {
                System.out.println("No se encontró ninguna categoría con ese código.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese datos válidos.");
        }
    }

    public void salir() {
        System.out.println("-----------------------------------");
        System.out.println("Saliendo del menú de Categorías...");
        System.out.println("-----------------------------------");
        em.close();
        emf.close();
    }
}
