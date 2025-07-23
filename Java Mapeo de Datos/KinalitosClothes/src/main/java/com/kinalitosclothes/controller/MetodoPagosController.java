package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.*;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class MetodoPagosController {

    public void MetodoPagosController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();
        int op = 1;

        while (op != 0) {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menú de Métodos de Pago");
            System.out.println("Por favor elija una opción:");
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
                    System.out.println("Agregar Método de Pago");
                    leer.nextLine();
                    System.out.println("Ingrese el tipo de método de pago (Tarjeta o Efectivo):");
                    String tipoMetodoPago = leer.nextLine();
                    System.out.println("Ingrese la entidad financiera:");
                    String entidad = leer.nextLine();
                    System.out.println("Ingrese la moneda:");
                    String moneda = leer.nextLine();

                    MetodoPagos nuevoMetodo = new MetodoPagos();
                    nuevoMetodo.setEntidadFinanciaera(entidad);
                    nuevoMetodo.setMoneda(moneda);
                    try {
                        MetodoPagos.TipoMetodoPago tipo = MetodoPagos.TipoMetodoPago.valueOf(tipoMetodoPago);
                        nuevoMetodo.setTipoMetodoPago(tipo);
                        em.getTransaction().begin();
                        em.persist(nuevoMetodo);
                        em.getTransaction().commit();
                        System.out.println("Método de pago agregado exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: tipo de método de pago inválido.");
                    }
                    break;

                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todos los Métodos de Pago");
                    TypedQuery<MetodoPagos> query = em.createQuery("SELECT m FROM MetodoPago m", MetodoPagos.class);
                    List<MetodoPagos> metodos = query.getResultList();

                    for (MetodoPagos m : metodos) {
                        System.out.println("--------------------------------------");
                        System.out.println(m);
                    }
                    break;

                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Método de Pago");
                    System.out.println("Ingrese el ID del método a eliminar:");
                    try {
                        int codigoMetodo = leer.nextInt();
                        leer.nextLine();
                        MetodoPagos metodoEliminar = em.find(MetodoPagos.class, codigoMetodo);
                        if (metodoEliminar != null) {
                            System.out.println("¿Desea eliminar el siguiente método?");
                            System.out.println(metodoEliminar);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.nextLine();

                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                em.remove(metodoEliminar);
                                em.getTransaction().commit();
                                System.out.println("Método de pago eliminado correctamente.");
                            } else {
                                System.out.println("Operación cancelada por el usuario.");
                            }
                        } else {
                            System.out.println("No se encontró ningún método con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID válido.");
                    }
                    break;

                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Buscar Método de Pago");
                    System.out.println("Ingrese el código del método de pago:");
                    int codigo = leer.nextInt();
                    MetodoPagos mp = em.find(MetodoPagos.class, codigo);
                    System.out.println(mp);
                    break;

                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Editar Método de Pago");
                    leer.nextLine();
                    System.out.println("Ingrese el código del método a editar:");
                    try {
                        int codigoMetodo = leer.nextInt();
                        leer.nextLine();

                        MetodoPagos metodoEditar = em.find(MetodoPagos.class, codigoMetodo);
                        if (metodoEditar != null) {
                            System.out.println("Método encontrado:");
                            System.out.println(metodoEditar);

                            System.out.println("Ingrese el nuevo tipo de método de pago (Tarjeta o Efectivo):");
                            String nuevoTipo = leer.nextLine();
                            System.out.println("Ingrese la nueva entidad financiera:");
                            String nuevaEntidad = leer.nextLine();
                            System.out.println("Ingrese la nueva moneda:");
                            String nuevaMoneda = leer.nextLine();

                            if (!nuevoTipo.isEmpty()) {
                                try {
                                    MetodoPagos.TipoMetodoPago tipo = MetodoPagos.TipoMetodoPago.valueOf(nuevoTipo);
                                    metodoEditar.setTipoMetodoPago(tipo);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Tipo inválido. No se actualizó ese campo.");
                                }
                            }

                            if (!nuevaEntidad.isEmpty()) {
                                metodoEditar.setEntidadFinanciaera(nuevaEntidad);
                            }

                            if (!nuevaMoneda.isEmpty()) {
                                metodoEditar.setMoneda(nuevaMoneda);
                            }

                            em.getTransaction().begin();
                            em.merge(metodoEditar);
                            em.getTransaction().commit();
                            System.out.println("Método de pago actualizado exitosamente.");
                        } else {
                            System.out.println("No se encontró ningún método con ese ID.");
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
                    System.out.println("Por favor seleccione una de las opciones válidas.");
            }
        }
    }
}
