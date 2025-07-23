package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Facturas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

public class FacturasController {

    public void FacturasController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();
        int op = 1;

        while (op != 0) {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menu de Facturas");
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
                    System.out.println("Agregar Facturas");
                    leer.nextLine();
                    System.out.println("Ingrese el estado de la Factura");
                    String estadoFac = leer.nextLine();

                    System.out.println("Ingrese la forma de entrega que prefiere");
                    String formaEntrega = leer.nextLine();

                    System.out.println("Ingrese el codigo del pedido al que pertenece");
                    int codigoPedido = leer.nextInt();

                    System.out.println("Ingrese el codigo del Empleado al que pertenece");
                    int codigoEmpleado = leer.nextInt();
                    leer.nextLine();
                    try {
                        Facturas nuevaFactura = new Facturas();
                        Facturas.EstadoFactura estadoF = Facturas.EstadoFactura.valueOf(estadoFac);
                        Facturas.FormaEntrega formaE = Facturas.FormaEntrega.valueOf(formaEntrega);

                        nuevaFactura.setCodigoPedido(codigoPedido);
                        nuevaFactura.setCodigoEmpleado(codigoEmpleado);
                        nuevaFactura.setEstadoFactura(estadoF);
                        nuevaFactura.setFormaEntrega(formaE);

                        em.getTransaction().begin();
                        em.persist(nuevaFactura);
                        em.getTransaction().commit();

                        System.out.println("Factura agregada exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: Estado o forma de Entrega no válidos.");
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número entero válido para el código del pedido o empleado.");
                        leer.nextLine();
                    }
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todas las Facturas");
                    TypedQuery<Facturas> query = em.createQuery("Select f From Facturas f", Facturas.class);
                    List<Facturas> facturas = query.getResultList();

                    for (Facturas factura : facturas) {
                        System.out.println("--------------------------------------");
                        System.out.println(factura);
                    }
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Factura");
                    System.out.println("Ingrese el ID de la Factura a eliminar");
                    try {
                        int codigoFactura = leer.nextInt();
                        leer.nextLine();
                        Facturas facturaAEliminar = em.find(Facturas.class, codigoFactura);
                        if (facturaAEliminar != null) {
                            // Confirmar eliminación
                            System.out.println("¿La Factura que desea eliminar es la siguiente?");
                            Facturas f = em.find(Facturas.class, codigoFactura);
                            System.out.println(f);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.nextLine();

                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("Factura.eliminar");
                                sp.setParameter("codigoFactura", codigoFactura);
                                sp.execute();
                                em.getTransaction().commit();
                                em.clear();
                                System.out.println("Factura eliminada correctamente.");
                            } else {
                                System.out.println("Operación cancelada por la Factura.");
                            }
                        } else {
                            System.out.println("No se encontró ninguna Factura con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID válido.");
                    }
                    break;
                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Menu Buscar");
                    System.out.println("Escriba el codigo de la Factura que desea buscar");
                    int codigo = leer.nextInt();
                    Facturas f = em.find(Facturas.class, codigo);
                    System.out.println(f);
                    break;
                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Editar Factura");
                    leer.nextLine();
                    System.out.println("Ingrese el codigo de la Factura a editar");
                    try {
                        int codigoFactura = leer.nextInt();
                        leer.nextLine();

                        Facturas FacturaAEditar = em.find(Facturas.class, codigoFactura);
                        if (FacturaAEditar != null) {
                            System.out.println("Factura encontrada:");
                            Facturas Fac = em.find(Facturas.class, codigoFactura);
                            System.out.println(Fac);
                            System.out.println("Ingrese la nueva fecha para la emision de la factura (formato: yyyy-MM-dd):");
                            String fechaEmision = leer.nextLine();
                            if (!fechaEmision.isEmpty()) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date nuevaFecha = sdf.parse(fechaEmision);
                                    FacturaAEditar.setFechaEmision(nuevaFecha);
                                } catch (ParseException e) {
                                    System.out.println("Formato de fecha inválido. No se actualizó este campo.");
                                }
                            }
                            System.out.println("Ingrese el nuevo descuento aplicado");
                            Double descuentoAplicado = leer.nextDouble();
                            leer.nextLine();
                            System.out.println("Ingrese el nuevo total para la factura");
                            Double totalFactura = leer.nextDouble();
                            leer.nextLine();

                            System.out.println("Ingrese el nuevo estado de la Factura");
                            String estadoFacE = leer.nextLine();

                            System.out.println("Ingrese la nueva forma de entrega que prefiere");
                            String formaEntregaE = leer.nextLine();

                            System.out.println("Ingrese el nuevo codigo del pedido al que pertenece");
                            int codigoPedidoE = leer.nextInt();
                            leer.nextLine();
                            System.out.println("Ingrese el nuevo codigo del Empleado al que pertenece");
                            int codigoEmpleadoE = leer.nextInt();
                            leer.nextLine();

                            try {
                                Facturas.EstadoFactura estadoF = Facturas.EstadoFactura.valueOf(estadoFacE);
                                Facturas.FormaEntrega formaE = Facturas.FormaEntrega.valueOf(formaEntregaE);

                                FacturaAEditar.setDescuentoAplicado(descuentoAplicado);
                                FacturaAEditar.setTotalFactura(totalFactura);
                                FacturaAEditar.setCodigoPedido(codigoPedidoE);
                                FacturaAEditar.setCodigoEmpleado(codigoEmpleadoE);
                                FacturaAEditar.setEstadoFactura(estadoF);
                                FacturaAEditar.setFormaEntrega(formaE);

                                em.getTransaction().begin();
                                em.persist(FacturaAEditar);
                                em.getTransaction().commit();

                                System.out.println("Factura Editada exitosamente.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Error: Estado o forma de Entrega no válidos.");
                            } catch (InputMismatchException e) {
                                System.out.println("Error: Debe ingresar un número entero válido para el código del pedido o empleado.");
                                leer.nextLine();
                            }
                        } else {
                            System.out.println("No se encontró ningún usuario con ese ID.");
                        }

                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID válido.");
                    }
                    break;

                case 0:
                    System.out.println("-----------------------------------");
                    System.out.println("Saliendo...");
                    System.out.println("-----------------------------------");
                    emf.close();
                    em.close();
                    break;
                default:
                    System.out.println("Por favor seleecione una de las opciones validas");
            }
        }

    }
}
