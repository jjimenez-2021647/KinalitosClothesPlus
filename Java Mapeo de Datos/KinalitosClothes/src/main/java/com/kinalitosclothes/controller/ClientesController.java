package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Clientes;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

public class ClientesController {

    Scanner leer = new Scanner(System.in);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
    EntityManager em = emf.createEntityManager();

    public void agregarCliente() {
        System.out.println("-----------------------------------");
        System.out.println("Agregar Cliente");
        leer.nextLine();
        System.out.println("Ingrese el nombre del Cliente:");
        String nombre = leer.nextLine();
        System.out.println("Ingrese el apellido del Cliente:");
        String apellido = leer.nextLine();
        System.out.println("Ingrese el correo del Cliente:");
        String correo = leer.nextLine();
        System.out.println("Ingrese el teléfono del Cliente:");
        String telefono = leer.nextLine();
        System.out.println("Ingrese la dirección del Cliente:");
        String direccion = leer.nextLine();
        System.out.println("Ingrese el código de usuario asociado:");
        int codigoUsuario = leer.nextInt();

        Clientes nuevoCliente = new Clientes();
        nuevoCliente.setNombreCliente(nombre);
        nuevoCliente.setApellidoCliente(apellido);
        nuevoCliente.setCorreoCliente(correo);
        nuevoCliente.setTelefonoCliente(telefono);
        nuevoCliente.setDireccionCliente(direccion);
        nuevoCliente.setCodigoUsuario(codigoUsuario);

        em.getTransaction().begin();
        em.persist(nuevoCliente);
        em.getTransaction().commit();
        System.out.println("Cliente agregado exitosamente.");
    }

    public void listarClientes() {
        System.out.println("-----------------------------------");
        System.out.println("Lista de todos los Clientes");
        TypedQuery<Clientes> query = em.createQuery("SELECT c FROM Clientes c", Clientes.class);
        List<Clientes> clientes = query.getResultList();

        for (Clientes cliente : clientes) {
            System.out.println("--------------------------------------");
            System.out.println(cliente);
        }
    }

    public void eliminarClientes() {
        System.out.println("-----------------------------------");
        System.out.println("Eliminar Cliente");
        System.out.println("Ingrese el ID del cliente a eliminar:");
        try {
            int codigoCliente = leer.nextInt();
            leer.nextLine();
            Clientes clienteAEliminar = em.find(Clientes.class, codigoCliente);
            if (clienteAEliminar != null) {
                System.out.println("¿El cliente que desea eliminar es el siguiente?");
                System.out.println(clienteAEliminar);
                System.out.println("Escriba 'SI' para confirmar:");
                String confirmar = leer.nextLine();

                if (confirmar.equalsIgnoreCase("SI")) {
                    em.getTransaction().begin();
                    StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("Cliente.eliminar");
                    sp.setParameter("codigoCliente", codigoCliente);
                    sp.execute();
                    em.getTransaction().commit();
                    em.clear();
                    System.out.println("Cliente eliminado correctamente.");
                } else {
                    System.out.println("Operación cancelada por el usuario.");
                }
            } else {
                System.out.println("No se encontró ningún cliente con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un ID válido.");
        }
    }

    public void buscarClientes() {
        System.out.println("-----------------------------------");
        System.out.println("Buscar Cliente");
        System.out.println("Ingrese el código del cliente que desea buscar:");
        int codigo = leer.nextInt();
        Clientes c = em.find(Clientes.class, codigo);
        System.out.println(c);
    }

    public void editarClientes() {
        System.out.println("-----------------------------------");
        System.out.println("Editar Cliente");
        leer.nextLine();
        System.out.println("Ingrese el código del cliente a editar:");
        try {
            int codigoCliente = leer.nextInt();
            leer.nextLine();

            Clientes clienteAEditar = em.find(Clientes.class, codigoCliente);
            if (clienteAEditar != null) {
                System.out.println("Cliente encontrado:");
                System.out.println(clienteAEditar);

                System.out.println("Ingrese el nuevo nombre del Cliente:");
                String nuevoNombre = leer.nextLine();
                System.out.println("Ingrese el nuevo apellido del Cliente:");
                String nuevoApellido = leer.nextLine();
                System.out.println("Ingrese el nuevo correo del Cliente:");
                String nuevoCorreo = leer.nextLine();
                System.out.println("Ingrese el nuevo teléfono del Cliente:");
                String nuevoTelefono = leer.nextLine();
                System.out.println("Ingrese la nueva dirección del Cliente:");
                String nuevaDireccion = leer.nextLine();
                System.out.println("Ingrese el nuevo código de usuario asociado:");
                String nuevoCodigoUsuario = leer.nextLine();

                if (!nuevoNombre.isEmpty()) {
                    clienteAEditar.setNombreCliente(nuevoNombre);
                }
                if (!nuevoApellido.isEmpty()) {
                    clienteAEditar.setApellidoCliente(nuevoApellido);
                }
                if (!nuevoCorreo.isEmpty()) {
                    clienteAEditar.setCorreoCliente(nuevoCorreo);
                }
                if (!nuevoTelefono.isEmpty()) {
                    clienteAEditar.setTelefonoCliente(nuevoTelefono);
                }
                if (!nuevaDireccion.isEmpty()) {
                    clienteAEditar.setDireccionCliente(nuevaDireccion);
                }
                if (!nuevoCodigoUsuario.isEmpty()) {
                    try {
                        int nuevoCodigo = Integer.parseInt(nuevoCodigoUsuario);
                        clienteAEditar.setCodigoUsuario(nuevoCodigo);
                    } catch (NumberFormatException e) {
                        System.out.println("Código de usuario inválido. No se actualizó este campo.");
                    }
                }

                em.getTransaction().begin();
                em.merge(clienteAEditar);
                em.getTransaction().commit();
                System.out.println("Cliente actualizado exitosamente.");
            } else {
                System.out.println("No se encontró ningún cliente con ese ID.");
            }
        } catch (Exception e) {
            System.out.println("Error: Ingrese un ID válido.");
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
