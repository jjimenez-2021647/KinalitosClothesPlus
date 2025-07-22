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

    public void PedidosController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();
        int op = 1;

        while (op != 0) {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menu de Pedidos");
            System.out.println("Por favor elija una opcion:");
            System.out.println("1. Agregar");
            System.out.println("2. Listar");
            System.out.println("3. Eliminar");
            System.out.println("4. Buscar");
            System.out.println("5. Editar");
            System.out.println("0. Salir");
            System.out.println("-----------------------------------");
            op = leer.nextInt();
            switch (op) {
                case 1:
                    System.out.println("-----------------------------------");
                    System.out.println("Agregar Pedidos");
                    leer.nextLine();
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
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todas los Pedidos");
                    TypedQuery<Pedidos> query = em.createQuery("Select u From Pedidos u", Pedidos.class);
                    List<Pedidos> pedidos = query.getResultList();

                    for (Pedidos pedido : pedidos) {
                        System.out.println("--------------------------------------");
                        System.out.println(pedido);
                    }
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Pedido");
                    System.out.println("Ingrese el ID del pedido a eliminar");
                    try {
                        int codigoPedido = leer.nextInt();
                        leer.nextLine();
                        Pedidos pedidoEliminar = em.find(Pedidos.class, codigoPedido);
                        if (pedidoEliminar != null) {
                            // Confirmar eliminación
                            System.out.println("¿El pedido que desea eliminar es el siguiente?");
                            Pedidos u = em.find(Pedidos.class, codigoPedido);
                            System.out.println(u);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.nextLine();

                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                em.remove(pedidoEliminar);
                                em.getTransaction().commit();
                                System.out.println("Pedidos eliminado correctamente.");
                            } else {
                                System.out.println("Operación cancelada por el usuario.");
                            }
                        } else {
                            System.out.println("No se encontró ningún pedido con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID válido.");
                    }
                    break;
                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Menu Buscar");
                    System.out.println("Escriba el codigo del pedido que desea buscar");
                    int codigo = leer.nextInt();
                    Pedidos u = em.find(Pedidos.class, codigo);
                    System.out.println(u);
                    break;
                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Editar Pedidos");
                    leer.nextLine();
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
                    break;
                case 0:
                    System.out.println("-----------------------------------");
                    System.out.println("Saliendo...");
                    emf.close();
                    em.close();
                    break;
                default:
                    System.out.println("Por favor seleecione una de las opciones validas");
            }
        }
    }
}
