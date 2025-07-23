package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Clientes;
import com.kinalitosclothes.view.MenuClientes;

import javax.persistence.*;
import java.util.List;

public class ClientesController {
    private MenuClientes view;
    private EntityManager em;

    public ClientesController() {
        view = new MenuClientes();
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
                agregarCliente();
                break;
            case 2:
                listarClientes();
                break;
            case 3:
                eliminarCliente();
                break;
            case 4:
                buscarCliente();
                break;
            case 5:
                editarCliente();
                break;
            case 0:
                view.mostrarMensaje("Saliendo...");
                break;
            default:
                view.mostrarMensaje("Por favor seleccione una de las opciones válidas");
        }
    }

    private void agregarCliente() {
        Clientes nuevoCliente = view.obtenerDatosCliente();
        em.getTransaction().begin();
        em.persist(nuevoCliente);
        em.getTransaction().commit();
        view.mostrarMensaje("Cliente agregado exitosamente.");
    }

    private void listarClientes() {
        TypedQuery<Clientes> query = em.createQuery("SELECT c FROM Clientes c", Clientes.class);
        List<Clientes> clientes = query.getResultList();
        view.mostrarClientes(clientes);
    }

    private void eliminarCliente() {
        int codigoCliente = view.obtenerIdCliente();
        Clientes clienteAEliminar = em.find(Clientes.class, codigoCliente);
        if (clienteAEliminar != null) {
            view.mostrarClientes(List.of(clienteAEliminar));
            if (view.confirmarEliminacion().equalsIgnoreCase("SI")) {
                em.getTransaction().begin();
                StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("Cliente.eliminar");
                spq.setParameter("codigoCliente", codigoCliente);
                spq.execute();
                em.getTransaction().commit();
                view.mostrarMensaje("Cliente eliminado correctamente.");
            } else {
                view.mostrarMensaje("Operación cancelada por el usuario.");
            }
        } else {
            view.mostrarMensaje("No se encontró ningún cliente con ese ID.");
        }
    }

    private void buscarCliente() {
        int codigoCliente = view.obtenerIdCliente();
        Clientes cliente = em.find(Clientes.class, codigoCliente);
        if (cliente != null) {
            view.mostrarClientes(List.of(cliente));
        } else {
            view.mostrarMensaje("No se encontró ningún cliente con ese ID.");
        }
    }

    private void editarCliente() {
        int codigoClienteEditar = view.obtenerIdCliente();
        Clientes clienteAEditar = em.find(Clientes.class, codigoClienteEditar);
        if (clienteAEditar != null) {
            view.mostrarClientes(List.of(clienteAEditar));
            Clientes clienteActualizado = view.obtenerDatosActualizados(clienteAEditar);
            em.getTransaction().begin();
            em.merge(clienteActualizado);
            em.getTransaction().commit();
            view.mostrarMensaje("Cliente actualizado exitosamente.");
        } else {
            view.mostrarMensaje("No se encontró ningún cliente con ese ID.");
        }
    }
}
