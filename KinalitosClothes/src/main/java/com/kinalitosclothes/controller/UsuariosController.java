package com.josuejimenez.controller;

import com.josuejimenez.dominio.Usuarios;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

public class UsuariosController {

    public void UsuariosController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();
        int op = 1;

        while (op != 0) {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menu de Usuarios");
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
                    System.out.println("Agregar Usuarios");
                    leer.nextLine();
                    System.out.println("Ingrese el nombre para su Usuario");
                    String nombreUsuario = leer.nextLine();
                    System.out.println("Ingrese la clave de acceso para su Usuario");
                    String claveAccesoUsuario = leer.nextLine();
                    System.out.println("Ingrese el tipo de usuario");
                    String tipoUsuario = leer.nextLine();
                    Usuarios nuevoUsuario = new Usuarios();
                    nuevoUsuario.setNombreUsuario(nombreUsuario);
                    nuevoUsuario.setContraseñaUsuario(claveAccesoUsuario);
                    // Convertir String a Enum
                    try {
                        Usuarios.TipoUsuarios tipoU = Usuarios.TipoUsuarios.valueOf(tipoUsuario);
                        nuevoUsuario.setTipoUsuario(tipoU);
                        em.getTransaction().begin();
                        em.persist(nuevoUsuario);
                        em.getTransaction().commit();
                        System.out.println("Usuario agregado exitosamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: tipo de usuario inválido.");
                    }
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todas los Usuarios");
                    TypedQuery<Usuarios> query = em.createQuery("Select u From Usuarios u", Usuarios.class);
                    List<Usuarios> usuarios = query.getResultList();

                    for (Usuarios usuario : usuarios) {
                        System.out.println("--------------------------------------");
                        System.out.println(usuario);
                    }
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Usuario");
                    System.out.println("Ingrese el ID del usuario a eliminar");
                    try {
                        int codigoUsuario = leer.nextInt();
                        leer.nextLine();
                        Usuarios usuarioAEliminar = em.find(Usuarios.class, codigoUsuario);
                        if (usuarioAEliminar != null) {
                            // Confirmar eliminación
                            System.out.println("¿El usuario que desea eliminar es el siguiente?");
                            Usuarios u = em.find(Usuarios.class, codigoUsuario);
                            System.out.println(u);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.nextLine();

                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("Usuario.eliminar");
                                sp.setParameter("codigoUsuario", codigoUsuario);
                                sp.execute();
                                em.getTransaction().commit();
                                em.clear();
                                System.out.println("Usuario eliminado correctamente.");
                            } else {
                                System.out.println("Operación cancelada por el usuario.");
                            }
                        } else {
                            System.out.println("No se encontró ningún usuario con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID válido.");
                    }
                    break;
                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Menu Buscar");
                    System.out.println("Escriba el codigo de usuario que desea buscar");
                    int codigo = leer.nextInt();
                    Usuarios u = em.find(Usuarios.class, codigo);
                    System.out.println(u);
                    break;
                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Editar Usuarios");
                    leer.nextLine();
                    System.out.println("Ingrese el codigo del usuario a editar");
                    try {
                        int codigoUsuario = leer.nextInt();
                        leer.nextLine();

                        Usuarios UsuarioAEditar = em.find(Usuarios.class, codigoUsuario);
                        if (UsuarioAEditar != null) {
                            System.out.println("Usuario encontrado:");
                            Usuarios U = em.find(Usuarios.class, codigoUsuario);
                            System.out.println(U);

                            System.out.println("Ingrese el nuevo nombre de su Usuario");
                            String nuevoNombre = leer.nextLine();
                            System.out.println("Ingrese la nueva clave de acceso para su Usuario");
                            String nuevaClaveUsuario = leer.nextLine();
                            System.out.println("Ingrese el nuevo tipo usuario");
                            String nuevoTipoUsuario = leer.nextLine();
                            System.out.println("Ingrese la nueva fecha de registro (formato: yyyy-MM-dd):");
                            String fechaRegistro = leer.nextLine();
                            if (!fechaRegistro.isEmpty()) {
                                try {
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date nuevaFecha = sdf.parse(fechaRegistro);
                                    UsuarioAEditar.setFechaRegistro(nuevaFecha);
                                } catch (ParseException e) {
                                    System.out.println("Formato de fecha inválido. No se actualizó este campo.");
                                }
                            }
                            if (!nuevoNombre.isEmpty()) {
                                UsuarioAEditar.setNombreUsuario(nuevoNombre);
                            }

                            if (!nuevaClaveUsuario.isEmpty()) {
                                UsuarioAEditar.setContraseñaUsuario(nuevaClaveUsuario);
                            }

                            if (!nuevoTipoUsuario.isEmpty()) {
                                try {
                                    Usuarios.TipoUsuarios tipoU = Usuarios.TipoUsuarios.valueOf(nuevoTipoUsuario);
                                    UsuarioAEditar.setTipoUsuario(tipoU);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Tipo de usuario inválido. No se actualizó este campo.");
                                }
                            }
                            // Guardar cambios
                            em.getTransaction().begin();
                            em.merge(UsuarioAEditar);
                            em.getTransaction().commit();
                            System.out.println("Usuario actualizado exitosamente.");

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
