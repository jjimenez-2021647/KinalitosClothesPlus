package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Productos;
import java.util.List;
import java.util.Scanner;
import javax.persistence.*;

public class ProductosController {

    public void ProductosController() {
        Scanner leer = new Scanner(System.in);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dominio");
        EntityManager em = emf.createEntityManager();
        int op = 1;

        while (op != 0) {
            System.out.println("-----------------------------------");
            System.out.println("Bienvenido al menu de Producto");
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

                    System.out.println("Ingrese el nombre para su Producto");
                    String nombreProducto = leer.nextLine();
                    System.out.println("Ingresar la descripcion del Producto");
                    String descripcionProducto = leer.nextLine();
                    System.out.println("Ingrese el precio del Producto");
                    double precioProducto = leer.nextDouble();
                    System.out.println("Ingrese el tipo de talla");
                    String talla = leer.nextLine();
                    System.out.println("Ingrese la cantidad de stock del Producto");
                    int stock = leer.nextInt();
                    System.out.println("Ingrese el codigo del proveedor");
                    int codigoProveedor = leer.nextInt();
                    System.out.println("Ingrese el codigo de la Categoria");
                    int codigoCategoria = leer.nextInt();

                    Productos nuevoProducto = new Productos();
                    nuevoProducto.setNombreProducto(nombreProducto);
                    nuevoProducto.setDescripcionProducto(descripcionProducto);
                    nuevoProducto.setPrecioProducto(precioProducto);
                    nuevoProducto.setTalla(talla);
                    nuevoProducto.setStock(stock);
                    nuevoProducto.setCodigoProveedor(codigoProveedor);
                    nuevoProducto.setCodigoCategoria(codigoCategoria);
                    em.getTransaction().begin();
                    em.persist(nuevoProducto);
                    em.getTransaction().commit();
                    System.out.println("Se guardo correctamente el producto");
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    System.out.println("Lista de todos los Productos");
                    TypedQuery<Productos> query = em.createQuery("Select u From Productos u", Productos.class);
                    List<Productos> productos = query.getResultList();

                    for (Productos producto : productos) {
                        System.out.println("--------------------------------------");
                        System.out.println(producto);
                    }
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    System.out.println("Eliminar Producto");
                    System.out.println("Ingrese el ID del producto a eliminar");
                    try {
                        int codigoProducto = leer.nextInt();
                        leer.nextLine();
                        Productos productoAEliminar = em.find(Productos.class, codigoProducto);
                        if (productoAEliminar != null) {
                            // Confirmar eliminación
                            System.out.println("¿El Producto que desea eliminar es el siguiente?");
                            Productos u = em.find(Productos.class, codigoProducto);
                            System.out.println(u);
                            System.out.println("Escriba 'SI' para confirmar:");
                            String confirmar = leer.nextLine();

                            if (confirmar.equalsIgnoreCase("SI")) {
                                em.getTransaction().begin();
                                em.remove(productoAEliminar);
                                em.getTransaction().commit();
                                System.out.println("Producto eliminado correctamente.");
                            } else {
                                System.out.println("Operación cancelada.");
                            }
                        } else {
                            System.out.println("No se encontró ningún producto con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese un ID válido.");
                    }
                    break;
                case 4:
                    System.out.println("-----------------------------------");
                    System.out.println("Menu Buscar");
                    System.out.println("Escriba el codigo de producto que desea buscar");
                    int codigo = leer.nextInt();
                    Productos u = em.find(Productos.class, codigo);
                    System.out.println(u);
                    break;
                case 5:
                    System.out.println("-----------------------------------");
                    System.out.println("Ingrese el código del producto a editar:");
                    try {
                        int codigoProducto = leer.nextInt();
                        leer.nextLine(); // consumir salto de línea

                        Productos productoAEditar = em.find(Productos.class, codigoProducto);
                        if (productoAEditar != null) {
                            System.out.println("Producto encontrado:");
                            System.out.println(productoAEditar);

                            System.out.println("Ingrese el nuevo nombre del producto:");
                            String nuevoNombreProducto = leer.nextLine();

                            System.out.println("Ingrese la nueva descripción del producto:");
                            String nuevaDescripcionProducto = leer.nextLine();

                            System.out.println("Ingrese el nuevo precio del producto:");
                            double nuevoPrecioProducto = leer.nextDouble();
                            leer.nextLine(); // consumir salto de línea

                            System.out.println("Ingrese la nueva talla del producto:");
                            String nuevaTalla = leer.nextLine();

                            System.out.println("Ingrese el nuevo stock del producto:");
                            int nuevoStock = leer.nextInt();

                            System.out.println("Ingrese el nuevo código del proveedor:");
                            int nuevoCodigoProveedor = leer.nextInt();

                            System.out.println("Ingrese el nuevo código de la categoría:");
                            int nuevoCodigoCategoria = leer.nextInt();

                            productoAEditar.setNombreProducto(nuevoNombreProducto);
                            productoAEditar.setDescripcionProducto(nuevaDescripcionProducto);
                            productoAEditar.setPrecioProducto(nuevoPrecioProducto);
                            productoAEditar.setTalla(nuevaTalla);
                            productoAEditar.setStock(nuevoStock);
                            productoAEditar.setCodigoProveedor(nuevoCodigoProveedor);
                            productoAEditar.setCodigoCategoria(nuevoCodigoCategoria);
                            em.getTransaction().begin();
                            em.merge(productoAEditar);
                            em.getTransaction().commit();

                            System.out.println("Producto actualizado exitosamente.");
                        } else {
                            System.out.println("No se encontró ningún producto con ese ID.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: Ingrese datos válidos.");
                        leer.nextLine(); 
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
