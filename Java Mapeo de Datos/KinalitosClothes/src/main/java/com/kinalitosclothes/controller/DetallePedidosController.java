package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.DetallePedidos;
import com.kinalitosclothes.view.MenuDetallePedido;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DetallePedidosController {
    private MenuDetallePedido view;
    private EntityManager em;

    public DetallePedidosController() {
        view = new MenuDetallePedido();
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
                view.mostrarMensaje("Saliendo...");
                break;
            default:
                view.mostrarMensaje("Por favor seleccione una de las opciones válidas");
        }
    }

    private void agregarDetallePedidos() {
        DetallePedidos nuevoDetalle = view.obtenerDatosDetallePedidos();
        em.getTransaction().begin();
        em.persist(nuevoDetalle);
        em.getTransaction().commit();
        view.mostrarMensaje("Detalle de pedido agregado exitosamente.");
    }

    private void listarDetallePedidos() {
        TypedQuery<DetallePedidos> query = em.createQuery("SELECT d FROM DetallePedidos d", DetallePedidos.class);
        List<DetallePedidos> detalles = query.getResultList();
        view.mostrarDetallePedidos(detalles);
    }

    private void eliminarDetallePedidos() {
        int codigoDetalleP = view.obtenerIdDetallePedidos();
        DetallePedidos detalleAEliminar = em.find(DetallePedidos.class, codigoDetalleP);
        if (detalleAEliminar != null) {
            view.mostrarDetallePedidos(List.of(detalleAEliminar));
            if (view.confirmarEliminacion().equalsIgnoreCase("SI")) {
                em.getTransaction().begin();
                em.remove(detalleAEliminar);
                em.getTransaction().commit();
                view.mostrarMensaje("Detalle de pedido eliminado correctamente.");
            } else {
                view.mostrarMensaje("Operación cancelada por el usuario.");
            }
        } else {
            view.mostrarMensaje("No se encontró ningún detalle de pedido con ese ID.");
        }
    }

    private void buscarDetallePedidos() {
        int codigoDetalleP = view.obtenerIdDetallePedidos();
        DetallePedidos detalle = em.find(DetallePedidos.class, codigoDetalleP);
        if (detalle != null) {
            view.mostrarDetallePedidos(List.of(detalle));
        } else {
            view.mostrarMensaje("No se encontró ningún detalle de pedido con ese ID.");
        }
    }

    private void editarDetallePedidos() {
        int codigoDetallePEditar = view.obtenerIdDetallePedidos();
        DetallePedidos detalleAEditar = em.find(DetallePedidos.class, codigoDetallePEditar);
        if (detalleAEditar != null) {
            view.mostrarDetallePedidos(List.of(detalleAEditar));
            DetallePedidos detalleActualizado = view.obtenerDatosActualizados(detalleAEditar);
            em.getTransaction().begin();
            em.merge(detalleActualizado);
            em.getTransaction().commit();
            view.mostrarMensaje("Detalle de pedido actualizado exitosamente.");
        } else {
            view.mostrarMensaje("No se encontró ningún detalle de pedido con ese ID.");
        }
    }
}