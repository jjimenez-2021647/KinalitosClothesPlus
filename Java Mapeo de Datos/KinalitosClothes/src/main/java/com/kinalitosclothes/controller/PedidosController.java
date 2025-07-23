package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Pedidos;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class PedidosController {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public PedidosController() {
        emf = Persistence.createEntityManagerFactory("dominio");
        em = emf.createEntityManager();
    }

    public void agregarPedido() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese la hora del pedido (HH:mm:ss):");
        String horaPedidoStr = leer.nextLine();

        Time horaPedido = null;
        try {
            horaPedido = Time.valueOf(horaPedidoStr);
        } catch (Exception e) {
            System.out.println("Formato de hora incorrecto. Usa HH:mm:ss");
        }
        System.out.println("Ingrese la fecha del pedido (yyyy-MM-dd):");
        String fechaPedidoStr = leer.nextLine();
        Date fechaPedido = null;
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            fechaPedido = formatoFecha.parse(fechaPedidoStr);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto. Usa yyyy-MM-dd");
        }

        System.out.println("Ingrese el estado del Pedido:");
        String estadoPedido = leer.nextLine();

        System.out.println("Ingrese el total del Pedido:");
        Double total = leer.nextDouble();

        System.out.println("Ingres el codigo del Cliente:");
        int codigoCliente = leer.nextInt();
        leer.nextLine();

        System.out.println("Ingrese el codigo del Metodo de Pago:");
        int codigoMetodoPago = leer.nextInt();
        leer.nextLine();

        Pedidos nuevoPedido = new Pedidos();
        nuevoPedido.setHora(horaPedido);
        nuevoPedido.setFechaPedido(fechaPedido);
        // Convertir String a Enum
        try {
            Pedidos.EstadoPedido estadoP = Pedidos.EstadoPedido.valueOf(estadoPedido);
            nuevoPedido.setEstadoPedido(estadoP);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: estado de pedido inválido.");
        }
        nuevoPedido.setTotal(total);
        nuevoPedido.setCodigoCliente(codigoCliente);
        nuevoPedido.setCodigoMetodoPago(codigoMetodoPago);

        em.getTransaction().begin();
        em.persist(nuevoPedido);
        em.getTransaction().commit();
        System.out.println("Pedido agregado exitosamente.");
    }

    public List<Pedidos> listarPedidos() {
        TypedQuery<Pedidos> query = em.createQuery("Select u From Pedidos u", Pedidos.class);
        List<Pedidos> pedidos = query.getResultList();

        for (Pedidos pedido : pedidos) {
            System.out.println("--------------------------------------");
            System.out.println(pedido);
        }

        return query.getResultList();
    }

    public void eliminarPedido(int idPedido) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el id del pedido a eliminar:");
        idPedido = leer.nextInt();
        leer.nextLine();
        Pedidos pedido = em.find(Pedidos.class, idPedido);
        if (pedido != null) {
            em.getTransaction().begin();
            em.remove(pedido);
            em.getTransaction().commit();
            System.out.println("Pedido eliminado correctamente.");
        } else {
            System.out.println("Pedido no encontrado con el ID proporcionado.");
        }
    }

    public Pedidos buscarPedidos(int idPedido) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el id del pedido a buscar:");
        idPedido = leer.nextInt();
        Pedidos u = em.find(Pedidos.class, idPedido);
        System.out.println(u);
        return u;
    }

    public void editarPedido() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese el codigo del pedido a editar");
        try {
            int codigoPedido = leer.nextInt();
            leer.nextLine();

            Pedidos pedidoEditar = em.find(Pedidos.class, codigoPedido);

            if (pedidoEditar != null) {
                System.out.println("Pedido encontrado:");
                Pedidos P = em.find(Pedidos.class, codigoPedido);
                System.out.println(P);

                System.out.println("Ingrese la nueva hora del pedido: Usa HH:mm:ss");
                String horaPedidoStrEditar = leer.nextLine();
                Time horaPedidoEditar = null;
                try {
                    horaPedidoEditar = Time.valueOf(horaPedidoStrEditar);
                } catch (Exception e) {
                    System.out.println("Formato de hora incorrecto. Usa HH:mm:ss");
                }

                System.out.println("Ingrese la nueva fecha del pedido:");
                String fechaPedidoStrEditar = leer.nextLine();
                Date fechaPedidoEditar = null;
                try {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    fechaPedidoEditar = formatoFecha.parse(fechaPedidoStrEditar);
                } catch (Exception e) {
                    System.out.println("Formato de fecha incorrecto. Usa yyyy-MM-dd");
                }

                System.out.println("Ingrese el nuevo estado del Pedido:");
                String estadoPedidoEditar = leer.nextLine();

                System.out.println("Ingrese el nuevo total del pedido:");
                Double totalEditar = leer.nextDouble();
                leer.nextLine();

                System.out.println("Ingrese el nuevo codigo del Cliente:");
                int codigoClienteEditar = leer.nextInt();
                leer.nextLine();

                System.out.println("Ingrese el nuevo codigo del Metodo de Pago:");
                int codigoMetodoPagoEditar = leer.nextInt();
                leer.nextLine();

                pedidoEditar.setHora(horaPedidoEditar);
                pedidoEditar.setFechaPedido(fechaPedidoEditar);
                // Convertir String a Enum
                try {
                    Pedidos.EstadoPedido estadoPE = Pedidos.EstadoPedido.valueOf(estadoPedidoEditar);
                    pedidoEditar.setEstadoPedido(estadoPE);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: estado de pedido inválido.");
                }
                pedidoEditar.setTotal(totalEditar);
                pedidoEditar.setCodigoCliente(codigoClienteEditar);
                pedidoEditar.setCodigoMetodoPago(codigoMetodoPagoEditar);

                // Guardar cambios
                em.getTransaction().begin();
                em.merge(pedidoEditar);
                em.getTransaction().commit();
                System.out.println("Pedido actualizado exitosamente.");

            } else {
                System.out.println("No se encontró ningún pedido con ese ID.");
            }

        } catch (Exception e) {
            System.out.println("Error: Ingrese un ID válido.");
        }
    }

    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
