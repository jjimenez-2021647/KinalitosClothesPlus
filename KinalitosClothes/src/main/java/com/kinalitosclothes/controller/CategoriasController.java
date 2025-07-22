package com.josuejimenez.controller;
 
import com.josuejimenez.dominio.Categorias;

import java.util.List;

import java.util.Scanner;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;

import javax.persistence.TypedQuery;
 
public class CategoriasController {
 
    public void CategoriasController() {

        Scanner leer = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");

        EntityManager em = emf.createEntityManager();

        int op = 1;
 
        while (op != 0) {

            System.out.println("-----------------------------------");

            System.out.println("Bienvenido al menu de Categorias");

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

                    System.out.println("Agregar Categorias");

                    leer.nextLine();

                    System.out.println("Ingrese el nombre para su Categorias");

                    String nombreCategorias = leer.nextLine();

                    System.out.println("Ingrese la descripccion para su Categorias");

                    String descripccionCategorias = leer.nextLine();

                    System.out.println("Ingrese el genero de Categorias");

                    String generoCategorias = leer.nextLine();

                    System.out.println("Ingrese el rango de edad de Categorias");

                    String rangoEdadCategorias = leer.nextLine();

                    Categorias nuevaCategoria = new Categorias();

                    nuevaCategoria.setNombreCategoria(nombreCategorias);

                    nuevaCategoria.setDescripcionCategoria(descripccionCategorias);

                    // Convertir String a Enum

                    try {

                        Categorias.Genero generoC = Categorias.Genero.valueOf(generoCategorias);

                        nuevaCategoria.setGenero(generoC);

                        Categorias.RangoEdad rangoE = Categorias.RangoEdad.valueOf(rangoEdadCategorias);

                        nuevaCategoria.setRangoEdad(rangoE);

                        em.getTransaction().begin();

                        em.persist(nuevaCategoria);

                        em.getTransaction().commit();

                        System.out.println("Categoria agregada exitosamente.");

                    } catch (IllegalArgumentException e) {

                        System.out.println("Error: tipo de categoria inválido.");

                    }

                    break;

                case 2:

                    System.out.println("-----------------------------------");

                    System.out.println("Lista de todas las Categorias");

                    TypedQuery<Categorias> query = em.createQuery("Select c From Categorias c", Categorias.class);

                    List<Categorias> categorias = query.getResultList();
 
                    for (Categorias categoria : categorias) {

                        System.out.println("--------------------------------------");

                        System.out.println(categoria);

                    }

                    break;

                case 3:

                    System.out.println("-----------------------------------");

                    System.out.println("Eliminar Categoria");

                    System.out.println("Ingrese el ID de la categoria a eliminar");

                    try {

                        int codigoCategoria = leer.nextInt();

                        leer.nextLine();

                        Categorias categoriaAEliminar = em.find(Categorias.class, codigoCategoria);

                        if (categoriaAEliminar != null) {

                            // Confirmar eliminación

                            System.out.println("¿La categoria que desea eliminar es el siguiente?");

                            Categorias c = em.find(Categorias.class, codigoCategoria);

                            System.out.println(c);

                            System.out.println("Escriba 'SI' para confirmar:");

                            String confirmar = leer.nextLine();
 
                            if (confirmar.equalsIgnoreCase("SI")) {

                                em.getTransaction().begin();

                                em.remove(categoriaAEliminar);

                                em.getTransaction().commit();

                                System.out.println("Categoria eliminado correctamente.");

                            } else {

                                System.out.println("Operación cancelada por el usuario.");

                            }

                        } else {

                            System.out.println("No se encontró ningúna Categoria con ese ID.");

                        }

                    } catch (Exception e) {

                        System.out.println("Error: Ingrese un ID válido.");

                    }

                    break;

                case 4:

                    System.out.println("-----------------------------------");

                    System.out.println("Menu Buscar");

                    System.out.println("Escriba el codigo de la categoria que desea buscar");

                    int codigo = leer.nextInt();

                    Categorias c = em.find(Categorias.class, codigo);

                    System.out.println(c);

                    break;

                case 5:

                    System.out.println("-----------------------------------");

                    System.out.println("Editar Categorias");

                    leer.nextLine();

                    System.out.println("Ingrese el codigo de la categoria a editar");

                    try {

                        int codigoCategoria = leer.nextInt();

                        leer.nextLine();
 
                        Categorias categoriaAEditar = em.find(Categorias.class, codigoCategoria);

                        if (categoriaAEditar != null) {

                            System.out.println("Categoria encontrado:");

                            Categorias C = em.find(Categorias.class, codigoCategoria);

                            System.out.println(C);

                            System.out.println("Ingrese el nuevo nombre para su Categorias");

                            String nombreCategoriasE = leer.nextLine();

                            System.out.println("Ingrese la nueva descripccion para su Categorias");

                            String descripccionCategoriasE = leer.nextLine();

                            System.out.println("Ingrese el nuevo genero de Categorias");

                            String generoCategoriasE = leer.nextLine();

                            System.out.println("Ingrese el nuevo rango de edad de Categorias");

                            String rangoEdadCategoriasE = leer.nextLine();

                            Categorias nuevaCategoriaE = new Categorias();

                            categoriaAEditar.setNombreCategoria(nombreCategoriasE);

                            categoriaAEditar.setDescripcionCategoria(descripccionCategoriasE);

                            // Convertir String a Enum

                            try {

                                Categorias.Genero generoC = Categorias.Genero.valueOf(generoCategoriasE);

                                categoriaAEditar.setGenero(generoC);

                                Categorias.RangoEdad rangoE = Categorias.RangoEdad.valueOf(rangoEdadCategoriasE);

                                categoriaAEditar.setRangoEdad(rangoE);

                                em.getTransaction().begin();

                                em.merge(categoriaAEditar);

                                em.getTransaction().commit();

                                System.out.println("Categoria editada exitosamente.");

                            } catch (IllegalArgumentException e) {

                                System.out.println("Error: tipo de categoria inválido.");

                            }

                        } else {

                            System.out.println("No se encontró ninguna categoria con ese ID.");

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

                    System.out.println("Por favor seleecione una de las opciones validas");
 
            }

        }

    }

}

 