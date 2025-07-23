package com.kinalitosclothes.controller;

import com.kinalitosclothes.dominio.Productos;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ProductosController {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ProductosController() {
        emf = Persistence.createEntityManagerFactory("dominio");
        em = emf.createEntityManager();
    }

    public void agregarProducto() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese el nombre del producto:");
        String nombre = leer.nextLine();

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = leer.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = leer.nextDouble();

        System.out.println("Ingrese la talla del producto:");
        String talla = leer.nextLine();

        System.out.println("Ingrese el stock del producto:");
        int stock = Integer.parseInt(leer.nextLine());

        System.out.println("Ingrese el código del proveedor:");
        int codProveedor = leer.nextInt();

        System.out.println("Ingrese el código de la categoría:");
        int codCategoria = leer.nextInt();

        Productos nuevoProducto = new Productos();
        nuevoProducto.setNombreProducto(nombre);
        nuevoProducto.setDescripcionProducto(descripcion);
        nuevoProducto.setPrecioProducto(precio);
        nuevoProducto.setTalla(talla);
        nuevoProducto.setStock(stock);
        nuevoProducto.setCodigoProveedor(codProveedor);
        nuevoProducto.setCodigoCategoria(codCategoria);

        em.getTransaction().begin();
        em.persist(nuevoProducto);
        em.getTransaction().commit();

        System.out.println("Producto guardado correctamente.");
    }

    public List<Productos> listarProductos() {

        TypedQuery<Productos> query = em.createQuery("Select u From Productos u", Productos.class);
        List<Productos> productos = query.getResultList();

        for (Productos producto : productos) {
            System.out.println("--------------------------------------");
            System.out.println(producto);
        }
        return query.getResultList();
    }

    public void eliminarProducto(int idProducto) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el id del producto a eliminar:");
        idProducto = leer.nextInt();
        leer.nextLine();
        Productos producto = em.find(Productos.class, idProducto);
        if (producto != null) {
            em.getTransaction().begin();
            em.remove(producto);
            em.getTransaction().commit();
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Producto no encontrado con el ID proporcionado.");
        }
    }

    public Productos buscarProducto(int idProducto) {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el id del producto a buscar:");
        idProducto = leer.nextInt();
        Productos u = em.find(Productos.class, idProducto);
        System.out.println(u);
        return u;
    }

    public void editarProducto() {
        Scanner leer = new Scanner(System.in);

        System.out.println("Ingrese el ID del producto a actualizar:");
        int idProducto = leer.nextInt();
        leer.nextLine();

        Productos productoEditar = em.find(Productos.class, idProducto);
        if (productoEditar != null) {
            System.out.println("producto encontrado:");
            Productos P = em.find(Productos.class, idProducto);
            System.out.println(P);

            System.out.println("Ingrese el nuevo nombre del producto:");
            String nombre = leer.nextLine();

            System.out.println("Ingrese la nuevo descripción del producto:");
            String descripcion = leer.nextLine();

            System.out.println("Ingrese el nuevo precio del producto:");
            double precio = leer.nextDouble();
            leer.nextLine();

            System.out.println("Ingrese la nueva talla del producto:");
            String talla = leer.nextLine();

            System.out.println("Ingrese el nuevo stock del producto:");
            int stock = Integer.parseInt(leer.nextLine());

            System.out.println("Ingrese el nuevo código del proveedor:");
            int codProveedor = leer.nextInt();

            System.out.println("Ingrese el nuevo código de la categoría:");
            int codCategoria = leer.nextInt();

            productoEditar.setNombreProducto(nombre);
            productoEditar.setDescripcionProducto(descripcion);
            productoEditar.setPrecioProducto(precio);
            productoEditar.setTalla(talla);
            productoEditar.setStock(stock);
            productoEditar.setCodigoProveedor(codProveedor);
            productoEditar.setCodigoCategoria(codCategoria);

            em.getTransaction().begin();
            em.merge(productoEditar);
            em.getTransaction().commit();

            System.out.println("Producto actualizado correctamente.");

        } else {
            System.out.println("No se encontró ningún producto con ese ID.");

        }

    }

    public void cerrar() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
