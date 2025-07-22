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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el nombre del producto:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese el precio del producto:");
        double precio = Double.parseDouble(scanner.nextLine());

        System.out.println("Ingrese la talla del producto:");
        String talla = scanner.nextLine();

        System.out.println("Ingrese el stock del producto:");
        int stock = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el código del proveedor:");
        int codProveedor = Integer.parseInt(scanner.nextLine());

        System.out.println("Ingrese el código de la categoría:");
        int codCategoria = Integer.parseInt(scanner.nextLine());

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
        TypedQuery<Productos> query = em.createQuery("SELECT p FROM Productos p", Productos.class);
        return query.getResultList();
    }

    public void eliminarProducto(int idProducto) {
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
        return em.find(Productos.class, idProducto);
    }

    public void editarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el ID del producto a actualizar:");
        int idProducto = Integer.parseInt(scanner.nextLine());

        Productos producto = em.find(Productos.class, idProducto);
        if (producto == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.println("Ingrese el nuevo nombre del producto:");
        producto.setNombreProducto(scanner.nextLine());

        System.out.println("Ingrese la nueva descripción del producto:");
        producto.setDescripcionProducto(scanner.nextLine());

        System.out.println("Ingrese el nuevo precio del producto:");
        producto.setPrecioProducto(Double.parseDouble(scanner.nextLine()));

        System.out.println("Ingrese la nueva talla del producto:");
        producto.setTalla(scanner.nextLine());

        System.out.println("Ingrese el nuevo stock del producto:");
        producto.setStock(Integer.parseInt(scanner.nextLine()));

        System.out.println("Ingrese el nuevo código del proveedor:");
        producto.setCodigoProveedor(Integer.parseInt(scanner.nextLine()));

        System.out.println("Ingrese el nuevo código de la categoría:");
        producto.setCodigoCategoria(Integer.parseInt(scanner.nextLine()));

        em.getTransaction().begin();
        em.merge(producto);
        em.getTransaction().commit();

        System.out.println("Producto actualizado correctamente.");
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
