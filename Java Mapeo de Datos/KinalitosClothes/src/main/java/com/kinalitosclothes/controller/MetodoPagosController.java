package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.MetodoPagos;
import com.kinalitosclothes.view.MenuMetodoPago;

import javax.persistence.*;
import java.util.List;

public class MetodoPagosController {
    private MenuMetodoPago view;
    private EntityManager em;

    public MetodoPagosController() {
        view = new MenuMetodoPago();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        em = emf.createEntityManager();
    }

    public void iniciar() {
        int op;
        do {
            op = view.mostrarMenuYObtenerOpcion();
            procesarOpcion(op);
        } while (op != 0);
        em.close();
    }

    private void procesarOpcion(int op) {
        switch (op) {
            case 1:
                agregarMetodoPago();
                break;
            case 2:
                listarMetodoPago();
                break;
            case 3:
                eliminarMetodoPago();
                break;
            case 4:
                buscarMetodoPago();
                break;
            case 5:
                editarMetodoPago();
                break;
            case 0:
                view.mostrarMensaje("Saliendo...");
                break;
            default:
                view.mostrarMensaje("Opción no válida.");
        }
    }

    private void agregarMetodoPago() {
        MetodoPagos nuevo = view.obtenerDatosMetodoPago();
        em.getTransaction().begin();
        em.persist(nuevo);
        em.getTransaction().commit();
        view.mostrarMensaje("Método de pago agregado exitosamente.");
    }

    private void listarMetodoPago() {
        TypedQuery<MetodoPagos> query = em.createQuery("SELECT m FROM MetodoPagos m", MetodoPagos.class);
        List<MetodoPagos> lista = query.getResultList();
        view.mostrarMetodoPagos(lista);
    }

    private void eliminarMetodoPago() {
        int id = view.obtenerIdMetodoPago();
        MetodoPagos mp = em.find(MetodoPagos.class, id);
        if (mp != null) {
            view.mostrarMetodoPagos(List.of(mp));
            if (view.confirmarEliminacion().equalsIgnoreCase("SI")) {
                em.getTransaction().begin();
                em.remove(mp);
                em.getTransaction().commit();
                view.mostrarMensaje("Método de pago eliminado.");
            } else {
                view.mostrarMensaje("Eliminación cancelada.");
            }
        } else {
            view.mostrarMensaje("Método de pago no encontrado.");
        }
    }

    private void buscarMetodoPago() {
        int id = view.obtenerIdMetodoPago();
        MetodoPagos mp = em.find(MetodoPagos.class, id);
        if (mp != null) {
            view.mostrarMetodoPagos(List.of(mp));
        } else {
            view.mostrarMensaje("No se encontró ningún método de pago con ese ID.");
        }
    }

    private void editarMetodoPago() {
        int id = view.obtenerIdMetodoPago();
        MetodoPagos mp = em.find(MetodoPagos.class, id);
        if (mp != null) {
            view.mostrarMetodoPagos(List.of(mp));
            MetodoPagos actualizado = view.obtenerDatosActualizados(mp);
            em.getTransaction().begin();
            em.merge(actualizado);
            em.getTransaction().commit();
            view.mostrarMensaje("Método de pago actualizado.");
        } else {
            view.mostrarMensaje("No se encontró ningún método de pago con ese ID.");
        }
    }

    public void mostrarmenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
