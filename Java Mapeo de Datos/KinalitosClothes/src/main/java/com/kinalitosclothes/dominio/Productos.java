package com.kinalitosclothes.dominio;

import javax.persistence.*;

@Entity
@Table(name = "Productos")
public class Productos {

    @Id
    @Column(name = "codigoProducto")
    private int codigoProducto;
    @Column
    private String nombreProducto;
    @Column
    private String descripcionProducto;
    @Column
    private double precioProducto;
    @Column
    private String talla;
    @Column
    private int stock;
    @Column
    private int codigoProveedor;
    @Column
    private int codigoCategoria;

    public Productos() {

    }

    public Productos(int codigoProducto, String nombreProducto, String descripcionProducto, double precioProducto, String talla, int stock, int codigoProveedor, int codigoCategoria) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.talla = talla;
        this.stock = stock;
        this.codigoProveedor = codigoProveedor;
        this.codigoCategoria = codigoCategoria;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCodigoProveedor() {
        return codigoProveedor;
    }

    public void setCodigoProveedor(int codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    public int getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    @Override
    public String toString() {
        return "Productos{" + "codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", descripcionProducto=" + descripcionProducto + ", precioProducto=" + precioProducto + ", talla=" + talla + ", stock=" + stock + ", codigoProveedor=" + codigoProveedor + ", codigoCategoria=" + codigoCategoria + '}';
    }
    
    

}
