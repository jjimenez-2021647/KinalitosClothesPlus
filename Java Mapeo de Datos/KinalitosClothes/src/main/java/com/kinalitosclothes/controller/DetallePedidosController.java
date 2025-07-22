package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.DetallePedidos;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DetallePedidosController {
    public void DetallePedidosController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();

        int op = 1;
        while (op != 0) {
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

            op = leer.nextInt();
            leer.nextLine(); // Consumir el salto de línea

            switch (op) {
                case 1:
                    System.out.println("-----------------------------------");
                    System.out.println("Agregar Detalle de Pedido");
                    System.out.println("Ingrese la cantidad");
                    int cantidad = leer.nextInt();
                    System.out.println("Ingrese el subtotal");
                    double subtotal = leer.nextDouble();
                    leer.nextLine(); // Consumir el salto de línea
                    System.out.println("Ingrese la descripcion");
                    String descripcion = leer.nextLine();
                    System.out.println("Ingrese el codigo del pedido");
                    int codigoPedido = leer.nextInt();
                    System.out.println("Ingrese el codigo del producto");
                    int codigoProducto = leer.nextInt();

                    DetallePedidos nuevoDetalle = new DetallePedidos(cantidad, subtotal, descripcion, codigoPedido, codigoProducto);

                    em.getTransaction().begin();
                    em.persist(nuevoDetalle);
                    em.getTransaction().commit();
                    System.out.println("Detalle de pedido agregado exitosamente.");
                    break;

                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todos los Detalles de Pedidos");
                    TypedQuery<DetallePedidos> query = em.createQuery("SELECT d FROM DetallePedidos d", DetallePedidos.class);
                    List<DetallePedidos> detalles = query.getResultList();
                    for (DetallePedidos detalle : detalles) {
                        System.out.println("--------------------------------------");
                        System.out.println(detalle);
                    }
                    break;

                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Detalle de Pedido");
                    System.out.println("Ingrese el ID del detalle de pedido a eliminar");
                    try {
                        int codigoDetalleP = leer.nextInt();
                        DetallePedidos detalleAEliminar = em.find(DetallePedidos.class, codigoDetalleP);
                        if (detalleAEliminar != null) {
                            System.out.println("¿El detalle de pedido que desea eliminar es el siguiente?");
                            System.out.println(detalleAEliminar);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.next();
                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                em.remove(detalleAEliminar);
                                em.getTransaction().commit();
                                System.out.println("Detalle de pedido eliminado correctamente.");
                            } else {
                                System.out.println("Operacion cancelada por el usuario.");
                            }
                        } else {
                            System.out.println("No se encontro ningun detalle de pedido con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID valido.");
                    }
                    break;

                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Buscar Detalle de Pedido");
                    System.out.println("Escriba el codigo del detalle de pedido que desea buscar");
                    int codigoDetalleP = leer.nextInt();
                    DetallePedidos detalle = em.find(DetallePedidos.class, codigoDetalleP);
                    System.out.println(detalle);
                    break;

                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Editar Detalle de Pedido");
                    System.out.println("Ingrese el codigo del detalle de pedido a editar");
                    try {
                        int codigoDetallePEditar = leer.nextInt();
                        DetallePedidos detalleAEditar = em.find(DetallePedidos.class, codigoDetallePEditar);
                        if (detalleAEditar != null) {
                            System.out.println("Detalle de pedido encontrado:");
                            System.out.println(detalleAEditar);
                            System.out.println("Ingrese la nueva cantidad");
                            int nuevaCantidad = leer.nextInt();
                            System.out.println("Ingrese el nuevo subtotal");
                            double nuevoSubtotal = leer.nextDouble();
                            leer.nextLine(); // Consumir el salto de línea
                            System.out.println("Ingrese la nueva descripcion");
                            String nuevaDescripcion = leer.nextLine();
                            System.out.println("Ingrese el nuevo codigo del pedido");
                            int nuevoCodigoPedido = leer.nextInt();
                            System.out.println("Ingrese el nuevo codigo del producto");
                            int nuevoCodigoProducto = leer.nextInt();

                            detalleAEditar.setCantidad(nuevaCantidad);
                            detalleAEditar.setSubtotal(nuevoSubtotal);
                            detalleAEditar.setDescripcion(nuevaDescripcion);
                            detalleAEditar.setCodigoPedido(nuevoCodigoPedido);
                            detalleAEditar.setCodigoProducto(nuevoCodigoProducto);

                            em.getTransaction().begin();
                            em.merge(detalleAEditar);
                            em.getTransaction().commit();
                            System.out.println("Detalle de pedido actualizado exitosamente.");
                        } else {
                            System.out.println("No se encontro ningun detalle de pedido con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID valido.");
                    }
                    break;

                case 0:
                    System.out.println("-----------------------------------");
                    System.out.println("Saliendo...");
                    em.close();
                    emf.close();
                    break;

                default:
                    System.out.println("Por favor seleccione una de las opciones validas");
            }
        }
    }
}