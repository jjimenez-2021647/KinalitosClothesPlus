package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Proveedores;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ProveedoresController {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
    private static final Scanner sc = new Scanner(System.in);

    public static void menuProveedores() {
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
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarProveedor();
                case 2 -> listarProveedores();
                case 3 -> buscarProveedor();
                case 4 -> editarProveedor();
                case 5 -> eliminarProveedor();
                case 6 -> System.out.println("-*-*- Saliendo del menú...");
                default -> System.out.println("*******Opción no válida*******");
            }

        } while (opcion != 6);
    }

    public static void agregarProveedor() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        

        System.out.print("");
        System.out.print("");
        System.out.print("| Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("| Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("| Correo: ");
        String correo = sc.nextLine();
        System.out.print("| País: ");
        String pais = sc.nextLine();

        try {
            tx.begin();
            Query query = em.createNativeQuery("CALL sp_AgregarProveedor(?, ?, ?, ?)");
            query.setParameter(1, nombre);
            query.setParameter(2, telefono);
            query.setParameter(3, correo);
            query.setParameter(4, pais);
            query.executeUpdate();
            tx.commit();
            System.out.println("------- Proveedor agregado correctamente.");
        } catch (Exception e) {
            tx.rollback();
            System.err.println("******** Error al agregar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void listarProveedores() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Object[]> lista = em.createNativeQuery("CALL sp_ListarProveedor()").getResultList();
            System.out.println("\n*** Lista de Proveedores ***");
            System.out.print("");
            for (Object[] p : lista) {
                System.out.print("");
                System.out.println("Código: " + p[0] +     ", Nombre: " + p[1] +     ", Tel: " + p[2] +     ", Correo: " + p[3] +     ", País: " + p[4]);
                System.out.print("");
            }
        } finally {
            em.close();
        }
    }

    public static void buscarProveedor() {
        EntityManager em = emf.createEntityManager();
        System.out.print("");
        System.out.print("");
        System.out.print("|  Ingrese el código del proveedor a buscar: ");
        int codigo = sc.nextInt();

        try {
            Query query = em.createNativeQuery("CALL sp_BuscarProveedor(?)");
            query.setParameter(1, codigo);
            List<Object[]> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("******* Proveedor no encontrado. *******");
            } else {
                Object[] p = result.get(0);
                System.out.print("");
                System.out.println("| Código: " + p[0] + ",   |Nombre: " + p[1] + ",   |Tel: " + p[2] + ",   |Correo: " + p[3] + ",   |País: " + p[4]);
            }
        } finally {
            em.close();
        }
    }

    public static void editarProveedor() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.print("| Ingrese el código del proveedor a editar: ");
        int codigo = sc.nextInt(); sc.nextLine();
        System.out.print("| Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("| Nuevo teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("| Nuevo correo: ");
        String correo = sc.nextLine();
        System.out.print("| Nuevo país: ");
        String pais = sc.nextLine();

        try {
            tx.begin();
            Query query = em.createNativeQuery("CALL sp_EditarProveedor(?, ?, ?, ?, ?)");
            query.setParameter(1, codigo);
            query.setParameter(2, nombre);
            query.setParameter(3, telefono);
            query.setParameter(4, correo);
            query.setParameter(5, pais);
            query.executeUpdate();
            tx.commit();
            System.out.println("-------------- Proveedor editado correctamente.");
        } catch (Exception e) {
            tx.rollback();
            System.err.println("************* Error al editar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void eliminarProveedor() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.print("|  Ingrese el código del proveedor a eliminar: ");
        int codigo = sc.nextInt();

        try {
            tx.begin();
            Query query = em.createNativeQuery("CALL sp_EliminarProveedor(?)");
            query.setParameter(1, codigo);
            query.getSingleResult();
            tx.commit();
            System.out.println("----------- Proveedor eliminado correctamente.");
        } catch (Exception e) {
            tx.rollback();
            System.err.println("Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
        }
    }


}