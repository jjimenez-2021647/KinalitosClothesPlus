package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Categorias;
import com.kinalitosclothes.dominio.Categorias.Genero;
import com.kinalitosclothes.dominio.Categorias.RangoEdad;
import com.kinalitosclothes.view.MenuCategorias;

import javax.persistence.*;
import java.util.List;

public class CategoriasController {
    private MenuCategorias view;
    private EntityManager em;

    public CategoriasController() {
        view = new MenuCategorias();
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
                agregarCategoria();
                break;
            case 2:
                listarCategorias();
                break;
            case 3:
                eliminarCategoria();
                break;
            case 4:
                buscarCategoria();
                break;
            case 5:
                editarCategoria();
                break;
            case 0:
                view.mostrarMensaje("Saliendo...");
                break;
            default:
                view.mostrarMensaje("Por favor seleccione una de las opciones válidas");
        }
    }

    private void agregarCategoria() {
        Categorias nuevaCategoria = view.obtenerDatosCategoria();
        em.getTransaction().begin();
        em.persist(nuevaCategoria);
        em.getTransaction().commit();
        view.mostrarMensaje("Categoría agregada exitosamente.");
    }

    private void listarCategorias() {
        TypedQuery<Categorias> query = em.createQuery("SELECT c FROM Categorias c", Categorias.class);
        List<Categorias> categorias = query.getResultList();
        view.mostrarCategorias(categorias);
    }

    private void eliminarCategoria() {
        int codigoCategoria = view.obtenerCodigoCategoria();
        Categorias categoriaAEliminar = em.find(Categorias.class, codigoCategoria);
        if (categoriaAEliminar != null) {
            view.mostrarCategorias(List.of(categoriaAEliminar));
            if (view.confirmarEliminacion().equalsIgnoreCase("SI")) {
                em.getTransaction().begin();
                // Si tienes un StoredProcedure para eliminar, úsalo aquí, si no, elimina directo:
                // StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Categoria.eliminar");
                // spq.setParameter("codigoCategoria", codigoCategoria);
                // spq.execute();
                em.remove(categoriaAEliminar);
                em.getTransaction().commit();
                view.mostrarMensaje("Categoría eliminada correctamente.");
            } else {
                view.mostrarMensaje("Operación cancelada por el usuario.");
            }
        } else {
            view.mostrarMensaje("No se encontró ninguna categoría con ese código.");
        }
    }

    private void buscarCategoria() {
        int codigoCategoria = view.obtenerCodigoCategoria();
        Categorias categoria = em.find(Categorias.class, codigoCategoria);
        if (categoria != null) {
            view.mostrarCategorias(List.of(categoria));
        } else {
            view.mostrarMensaje("No se encontró ninguna categoría con ese código.");
        }
    }

    private void editarCategoria() {
        int codigoCategoriaEditar = view.obtenerCodigoCategoria();
        Categorias categoriaAEditar = em.find(Categorias.class, codigoCategoriaEditar);
        if (categoriaAEditar != null) {
            view.mostrarCategorias(List.of(categoriaAEditar));
            Categorias categoriaActualizada = view.obtenerDatosActualizados(categoriaAEditar);
            em.getTransaction().begin();
            em.merge(categoriaActualizada);
            em.getTransaction().commit();
            view.mostrarMensaje("Categoría actualizada exitosamente.");
        } else {
            view.mostrarMensaje("No se encontró ninguna categoría con ese código.");
        }
    }
}
