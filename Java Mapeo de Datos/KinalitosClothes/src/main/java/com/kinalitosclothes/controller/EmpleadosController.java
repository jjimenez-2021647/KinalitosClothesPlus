package com.kinalitosclothes.controller;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class EmpleadosController {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
    private static final Scanner sc = new Scanner(System.in);

    public static void menuEmpleados() {
        int opcion;
        do {
            System.out.println("|   *** MENÚ DE EMPLEADOS ***  | ");
            System.out.println("|  1. Agregar Empleado          | ");
            System.out.println("|  2. Listar Empleados          | ");
            System.out.println("|  3. Buscar Empleado           | ");
            System.out.println("|  4. Editar Empleado           | ");
            System.out.println("|  5. Eliminar Empleado         | ");
            System.out.println("|  6. Salir                     | ");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarEmpleado();
                case 2 -> listarEmpleados();
                case 3 -> buscarEmpleado();
                case 4 -> editarEmpleado();
                case 5 -> eliminarEmpleado();
                case 6 -> System.out.println("-*-*- Saliendo del menú...");
                default -> System.out.println("******* Opción no válida *******");
            }
        } while (opcion != 6);
    }

    public static void agregarEmpleado() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.print("| Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("| Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("| Correo: ");
        String correo = sc.nextLine();
        System.out.print("| Teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("| Dirección: ");
        String direccion = sc.nextLine();
        System.out.print("| Código de Usuario: ");
        int codigoUsuario = sc.nextInt();

        try {
            tx.begin();
            Query query = em.createNativeQuery("CALL sp_AgregarEmpleado(?, ?, ?, ?, ?, ?)");
            query.setParameter(1, nombre);
            query.setParameter(2, apellido);
            query.setParameter(3, correo);
            query.setParameter(4, telefono);
            query.setParameter(5, direccion);
            query.setParameter(6, codigoUsuario);
            query.executeUpdate();
            tx.commit();
            System.out.println(" Empleado agregado correctamente.");
        } catch (Exception e) {
            tx.rollback();
            System.err.println(" Error al agregar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void listarEmpleados() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Object[]> lista = em.createNativeQuery("CALL sp_ListarEmpleados()").getResultList();
            System.out.println("\n*** Lista de Empleados ***");
            for (Object[] e : lista) {
                System.out.println("Código: " + e[0] + ", Nombre: " + e[1] + " " + e[2] + ", Correo: " + e[3] + ", Tel: " + e[4] + ", Dirección: " + e[5] + ", Usuario: " + e[6]);
            }
        } finally {
            em.close();
        }
    }

    public static void buscarEmpleado() {
        EntityManager em = emf.createEntityManager();
        System.out.print("| Ingrese el código del empleado a buscar: ");
        int codigo = sc.nextInt();

        try {
            Query query = em.createNativeQuery("CALL sp_BuscarEmpleado(?)");
            query.setParameter(1, codigo);
            List<Object[]> result = query.getResultList();
            if (result.isEmpty()) {
                System.out.println("✘ Empleado no encontrado.");
            } else {
                Object[] e = result.get(0);
                System.out.println("Código: " + e[0] + ", Nombre: " + e[1] + " " + e[2] + ", Correo: " + e[3] + ", Tel: " + e[4] + ", Dirección: " + e[5] + ", Usuario: " + e[6]);
            }
        } finally {
            em.close();
        }
    }

    public static void editarEmpleado() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.print("| Código del empleado a editar: ");
        int codigo = sc.nextInt(); sc.nextLine();
        System.out.print("| Nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("| Nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("| Nuevo correo: ");
        String correo = sc.nextLine();
        System.out.print("| Nuevo teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("| Nueva dirección: ");
        String direccion = sc.nextLine();
        System.out.print("| Nuevo código de usuario: ");
        int usuario = sc.nextInt();

        try {
            tx.begin();
            Query query = em.createNativeQuery("CALL sp_EditarEmpleado(?, ?, ?, ?, ?, ?, ?)");
            query.setParameter(1, codigo);
            query.setParameter(2, nombre);
            query.setParameter(3, apellido);
            query.setParameter(4, correo);
            query.setParameter(5, telefono);
            query.setParameter(6, direccion);
            query.setParameter(7, usuario);
            query.executeUpdate();
            tx.commit();
            System.out.println(" Empleado editado correctamente.");
        } catch (Exception e) {
            tx.rollback();
            System.err.println(" Error al editar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public static void eliminarEmpleado() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        System.out.print("| Código del empleado a eliminar: ");
        int codigo = sc.nextInt();

        try {
            tx.begin();
            Query query = em.createNativeQuery("CALL sp_EliminarEmpleado(?)");
            query.setParameter(1, codigo);
            query.getSingleResult();
            tx.commit();
            System.out.println(" Empleado eliminado correctamente.");
        } catch (Exception e) {
            tx.rollback();
            System.err.println(" Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
