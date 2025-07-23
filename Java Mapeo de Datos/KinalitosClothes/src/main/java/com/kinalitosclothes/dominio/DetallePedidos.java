package com.kinalitosclothes.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DetallePedidos {
    @Id
    @Column(name = "codigoDetalleP")
    private int codigoDetalleP;
    private int cantidad;
    private double subtotal;
    private String descripcion;
    private int codigoPedido;
    private int codigoProducto;

    // Constructores, getters y setters
    public DetallePedidos() {}

    public DetallePedidos(int cantidad, double subtotal, String descripcion, int codigoPedido, int codigoProducto) {
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.descripcion = descripcion;
        this.codigoPedido = codigoPedido;
        this.codigoProducto = codigoProducto;
    }

    public int getCodigoDetalleP() {
        return codigoDetalleP;
    }

    public void setCodigoDetalleP(int codigoDetalleP) {
        this.codigoDetalleP = codigoDetalleP;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    @Override
    public String toString() {
        return "DetallePedidos{" + "codigoDetalleP=" + codigoDetalleP + ", cantidad=" + cantidad + ", subtotal=" + subtotal + ", descripcion=" + descripcion + ", codigoPedido=" + codigoPedido + ", codigoProducto=" + codigoProducto + '}';
    }

    
}