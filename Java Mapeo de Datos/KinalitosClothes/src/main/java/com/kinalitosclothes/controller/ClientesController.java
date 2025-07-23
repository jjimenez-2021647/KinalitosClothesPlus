package com.kinalitosclothes.controller;
import com.kinalitosclothes.dominio.Clientes;
import com.kinalitosclothes.dominio.Usuarios;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;
public class ClientesController {
    public void ClientesController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();
        int op = 1;
        while (op != 0) {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menu de Clientes");
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
                    System.out.println("Agregar Cliente");
                    leer.nextLine();
                    System.out.println("Ingrese el nombre del cliente:");
                    String nombre = leer.nextLine();
                    System.out.println("Ingrese el apellido del cliente:");
                    String apellido = leer.nextLine();
                    System.out.println("Ingrese el correo del cliente:");
                    String correo = leer.nextLine();
                    System.out.println("Ingrese el telefono del cliente:");
                    String telefono = leer.nextLine();
                    System.out.println("Ingrese la direccion del cliente:");
                    String direccion = leer.nextLine();
                    System.out.println("Ingrese el codigo de usuario asociado:");
                    int codUsuario = leer.nextInt();
                    Usuarios usuario = em.find(Usuarios.class, codUsuario);
                    if (usuario != null) {
                        Clientes nuevoCliente = new Clientes();
                        nuevoCliente.setNombreCliente(nombre);
                        nuevoCliente.setApellidoCliente(apellido);
                        nuevoCliente.setCorreoCliente(correo);
                        nuevoCliente.setTelefonoCliente(telefono);
                        nuevoCliente.setDireccionCliente(direccion);
                        nuevoCliente.setUsuario(usuario);
                        em.getTransaction().begin();
                        em.persist(nuevoCliente);
                        em.getTransaction().commit();
                        System.out.println("Cliente agregado exitosamente.");
                    } else {
                        System.out.println("No se encontró un usuario con ese código.");
                    }
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todos los Clientes");
                    TypedQuery<Clientes> query = em.createQuery("SELECT c FROM Cliente c", Clientes.class);
                    List<Clientes> clientes = query.getResultList();
                    for (Clientes c : clientes) {
                        System.out.println("--------------------------------------");
                        System.out.println(c);
                    }
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Cliente");
                    System.out.println("Ingrese el ID del cliente a eliminar");
                    try {
                        int codigo = leer.nextInt();
                        leer.nextLine();
                        Clientes clienteEliminar = em.find(Clientes.class, codigo);
                        if (clienteEliminar != null) {
                            System.out.println("¿Desea eliminar el siguiente cliente?");
                            System.out.println(clienteEliminar);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.nextLine();
                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                em.remove(clienteEliminar);
                                em.getTransaction().commit();
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
                    break;
                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Buscar Cliente");
                    System.out.println("Ingrese el código del cliente:");
                    int codigoBuscar = leer.nextInt();
                    Clientes c = em.find(Clientes.class, codigoBuscar);
                    System.out.println(c);
                    break;
                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Editar Cliente");
                    leer.nextLine();
                    System.out.println("Ingrese el código del cliente a editar:");
                    try {
                        int codigoEditar = leer.nextInt();
                        leer.nextLine();
                        Clientes clienteEditar = em.find(Clientes.class, codigoEditar);
                        if (clienteEditar != null) {
                            System.out.println("Cliente encontrado:");
                            System.out.println(clienteEditar);
                            System.out.println("Ingrese el nuevo nombre:");
                            String nuevoNombre = leer.nextLine();
                            System.out.println("Ingrese el nuevo apellido:");
                            String nuevoApellido = leer.nextLine();
                            System.out.println("Ingrese el nuevo correo:");
                            String nuevoCorreo = leer.nextLine();
                            System.out.println("Ingrese el nuevo telefono:");
                            String nuevoTelefono = leer.nextLine();
                            System.out.println("Ingrese la nueva direccion:");
                            String nuevaDireccion = leer.nextLine();
                            if (!nuevoNombre.isEmpty()) {
                                clienteEditar.setNombreCliente(nuevoNombre);
                            }
                            if (!nuevoApellido.isEmpty()) {
                                clienteEditar.setApellidoCliente(nuevoApellido);
                            }
                            if (!nuevoCorreo.isEmpty()) {
                                clienteEditar.setCorreoCliente(nuevoCorreo);
                            }
                            if (!nuevoTelefono.isEmpty()) {
                                clienteEditar.setTelefonoCliente(nuevoTelefono);
                            }
                            if (!nuevaDireccion.isEmpty()) {
                                clienteEditar.setDireccionCliente(nuevaDireccion);
                            }
                            em.getTransaction().begin();
                            em.merge(clienteEditar);
                            em.getTransaction().commit();
                            System.out.println("Cliente actualizado exitosamente.");
                        } else {
                            System.out.println("No se encontró ningún cliente con ese ID.");
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