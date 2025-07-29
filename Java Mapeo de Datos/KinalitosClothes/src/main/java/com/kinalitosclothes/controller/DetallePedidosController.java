package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.DetallePedidos;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class DetallePedidosController {
    private Scanner leer = new Scanner(System.in);
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
    private EntityManager em = emf.createEntityManager();

    public void iniciar() {
        int op;
        do {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menú de Detalle de Pedidos");
            System.out.println("Por favor elija una opción:");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("4. Buscar");
            System.out.println("5. Editar");
            System.out.println("0. Salir");
            System.out.println("-----------------------------------");
            op = leer.nextInt();
            procesarOpcion(op);
        } while (op != 0);
        salir();
    }

    public void procesarOpcion(int op) {
        switch (op) {
            case 1:
                agregarDetallePedidos();
                break;
            case 2:
                listarDetallePedidos();
                break;
            case 3:
                eliminarDetallePedidos();
                break;
            case 4:
                buscarDetallePedidos();
                break;
            case 5:
                editarDetallePedidos();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Por favor seleccione una de las opciones válidas");
        }
    }

    public void agregarDetallePedidos() {
        System.out.println("-----------------------------------");
        System.out.println("Agregar Detalle de Pedido");
        leer.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese la cantidad:");
        int cantidad = leer.nextInt();
        System.out.println("Ingrese el subtotal:");
        double subtotal = leer.nextDouble();
        leer.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese la descripción:");
        String descripcion = leer.nextLine();
        System.out.println("Ingrese el código del pedido:");
        int codigoPedido = leer.nextInt();
        System.out.println("Ingrese el código del producto:");
        int codigoProducto = leer.nextInt();

        DetallePedidos nuevoDetalle = new DetallePedidos(cantidad, subtotal, descripcion, codigoPedido, codigoProducto);
        em.getTransaction().begin();
        em.persist(nuevoDetalle);
        em.getTransaction().commit();
        System.out.println("Detalle de pedido agregado exitosamente.");
    }

    public void listarDetallePedidos() {
        System.out.println("-----------------------------------");
        System.out.println("Lista de todos los Detalles de Pedidos");
        TypedQuery<DetallePedidos> query = em.createQuery("SELECT d FROM DetallePedidos d", DetallePedidos.class);
        List<DetallePedidos> detalles = query.getResultList();
        for (DetallePedidos detalle : detalles) {
            System.out.println("--------------------------------------");
            System.out.println(detalle);
        }
    }

    public void eliminarDetallePedidos() {
        System.out.println("-----------------------------------");
        System.out.println("Eliminar Detalle de Pedido");
        System.out.println("Ingrese el ID del detalle de pedido a eliminar:");
        try {
            int codigoDetalleP = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer
            DetallePedidos detalleAEliminar = em.find(DetallePedidos.class, codigoDetalleP);
            if (detalleAEliminar != null) {
                System.out.println("¿El detalle de pedido que desea eliminar es el siguiente?");
                System.out.println(detalleAEliminar);
                System.out.println("Escriba 'SI' para confirmar:");
                String confirmar = leer.nextLine();
                if (confirmar.equalsIgnoreCase("SI")) {
                    em.getTransaction().begin();
                    em.remove(detalleAEliminar);
                    em.getTransaction().commit();
                    System.out.println("Detalle de pedido eliminado correctamente.");
                } else {
                    System.out.println("Operación cancelada por el usuario.");
                }
            } else {
                System.out.println("No se encontró ningún detalle de pedido con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un ID válido.");
        }
    }

    public void buscarDetallePedidos() {
        System.out.println("-----------------------------------");
        System.out.println("Buscar Detalle de Pedido");
        System.out.println("Ingrese el ID del detalle de pedido que desea buscar:");
        int codigoDetalleP = leer.nextInt();
        DetallePedidos detalle = em.find(DetallePedidos.class, codigoDetalleP);
        if (detalle != null) {
            System.out.println(detalle);
        } else {
            System.out.println("No se encontró ningún detalle de pedido con ese ID.");
        }
    }

    public void editarDetallePedidos() {
        System.out.println("-----------------------------------");
        System.out.println("Editar Detalle de Pedido");
        leer.nextLine(); // Limpiar el buffer
        System.out.println("Ingrese el ID del detalle de pedido a editar:");
        try {
            int codigoDetalleP = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer
            DetallePedidos detalleAEditar = em.find(DetallePedidos.class, codigoDetalleP);
            if (detalleAEditar != null) {
                System.out.println("Detalle de pedido encontrado:");
                System.out.println(detalleAEditar);
                System.out.println("Ingrese la nueva cantidad:");
                String nuevaCantidad = leer.nextLine();
                System.out.println("Ingrese el nuevo subtotal:");
                String nuevoSubtotal = leer.nextLine();
                System.out.println("Ingrese la nueva descripción:");
                String nuevaDescripcion = leer.nextLine();
                System.out.println("Ingrese el nuevo código del pedido:");
                String nuevoCodigoPedido = leer.nextLine();
                System.out.println("Ingrese el nuevo código del producto:");
                String nuevoCodigoProducto = leer.nextLine();

                if (!nuevaCantidad.isEmpty()) {
                    detalleAEditar.setCantidad(Integer.parseInt(nuevaCantidad));
                }
                if (!nuevoSubtotal.isEmpty()) {
                    detalleAEditar.setSubtotal(Double.parseDouble(nuevoSubtotal));
                }
                if (!nuevaDescripcion.isEmpty()) {
                    detalleAEditar.setDescripcion(nuevaDescripcion);
                }
                if (!nuevoCodigoPedido.isEmpty()) {
                    detalleAEditar.setCodigoPedido(Integer.parseInt(nuevoCodigoPedido));
                }
                if (!nuevoCodigoProducto.isEmpty()) {
                    detalleAEditar.setCodigoProducto(Integer.parseInt(nuevoCodigoProducto));
                }

                em.getTransaction().begin();
                em.merge(detalleAEditar);
                em.getTransaction().commit();
                System.out.println("Detalle de pedido actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún detalle de pedido con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un ID válido.");
        }
    }

    public void salir() {
        System.out.println("-----------------------------------");
        System.out.println("Saliendo...");
        System.out.println("-----------------------------------");
        emf.close();
        em.close();
    }
}
