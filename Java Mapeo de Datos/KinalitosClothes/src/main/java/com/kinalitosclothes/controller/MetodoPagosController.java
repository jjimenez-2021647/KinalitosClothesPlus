package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.MetodoPagos;
import com.kinalitosclothes.dominio.MetodoPagos.TipoMetodoPago;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class MetodoPagosController {

    Scanner leer = new Scanner(System.in);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
    EntityManager em = emf.createEntityManager();

    public void agregarMetodoPago() {
        System.out.println("-----------------------------------");
        System.out.println("Agregar Método de Pago");
        leer.nextLine();

        System.out.println("Ingrese el tipo de método de pago (Tarjeta/Efectivo):");
        String tipo = leer.nextLine();
        TipoMetodoPago tipoMetodo = null;
        try {
            tipoMetodo = TipoMetodoPago.valueOf(tipo);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de método de pago inválido.");
            return;
        }

        System.out.println("Ingrese la entidad financiera:");
        String entidad = leer.nextLine();
        System.out.println("Ingrese la moneda:");
        String moneda = leer.nextLine();

        MetodoPagos nuevoMetodo = new MetodoPagos(tipoMetodo, entidad, moneda);

        em.getTransaction().begin();
        em.persist(nuevoMetodo);
        em.getTransaction().commit();
        System.out.println("Método de pago agregado exitosamente.");
    }

    public void listarMetodoPagos() {
        System.out.println("-----------------------------------");
        System.out.println("Lista de todos los Métodos de Pago");
        TypedQuery<MetodoPagos> query = em.createQuery("SELECT m FROM MetodoPagos m", MetodoPagos.class);
        List<MetodoPagos> lista = query.getResultList();

        for (MetodoPagos mp : lista) {
            System.out.println("--------------------------------------");
            System.out.println(mp);
        }
    }

    public void eliminarMetodoPago() {
        System.out.println("-----------------------------------");
        System.out.println("Eliminar Método de Pago");
        System.out.println("Ingrese el código del método de pago a eliminar:");

        try {
            int codigo = leer.nextInt();
            leer.nextLine();

            MetodoPagos metodo = em.find(MetodoPagos.class, codigo);
            if (metodo != null) {
                System.out.println("¿El método que desea eliminar es el siguiente?");
                System.out.println(metodo);
                System.out.println("Escriba 'SI' para confirmar:");
                String confirmar = leer.nextLine();

                if (confirmar.equalsIgnoreCase("SI")) {
                    em.getTransaction().begin();
                    StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("MetodoPagos.eliminar");
                    sp.setParameter("codigoMetodoPago", codigo);
                    sp.execute();
                    em.getTransaction().commit();
                    em.clear();
                    System.out.println("Método de pago eliminado correctamente.");
                } else {
                    System.out.println("Operación cancelada por el usuario.");
                }
            } else {
                System.out.println("No se encontró ningún método de pago con ese código.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un código válido.");
        }
    }

    public void buscarMetodoPago() {
        System.out.println("-----------------------------------");
        System.out.println("Buscar Método de Pago");
        System.out.println("Ingrese el código del método de pago a buscar:");
        int codigo = leer.nextInt();
        MetodoPagos metodo = em.find(MetodoPagos.class, codigo);
        System.out.println(metodo);
    }

    public void editarMetodoPago() {
        System.out.println("-----------------------------------");
        System.out.println("Editar Método de Pago");
        leer.nextLine();
        System.out.println("Ingrese el código del método de pago a editar:");

        try {
            int codigo = leer.nextInt();
            leer.nextLine();

            MetodoPagos metodo = em.find(MetodoPagos.class, codigo);
            if (metodo != null) {
                System.out.println("Método encontrado:");
                System.out.println(metodo);

                System.out.println("Ingrese el nuevo tipo de método de pago (Tarjeta/Efectivo):");
                String nuevoTipo = leer.nextLine();
                System.out.println("Ingrese la nueva entidad financiera:");
                String nuevaEntidad = leer.nextLine();
                System.out.println("Ingrese la nueva moneda:");
                String nuevaMoneda = leer.nextLine();

                if (!nuevoTipo.isEmpty()) {
                    try {
                        metodo.setTipoMetodoPago(TipoMetodoPago.valueOf(nuevoTipo));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo inválido. No se actualizó este campo.");
                    }
                }
                if (!nuevaEntidad.isEmpty()) {
                    metodo.setEntidadFinanciaera(nuevaEntidad);
                }
                if (!nuevaMoneda.isEmpty()) {
                    metodo.setMoneda(nuevaMoneda);
                }

                em.getTransaction().begin();
                em.merge(metodo);
                em.getTransaction().commit();
                System.out.println("Método de pago actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún método de pago con ese código.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un código válido.");
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
