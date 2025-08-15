package com.kinalitosclothes.modelo;

import java.sql.Time;
import java.util.Date;

public class Pedidos {
    private int codigoPedido;
    private Time horaPedido;
    private Date fechaPedido;
    private Estado estadoPedido;
    private Double total;
    private int codigoUsuario;
    private int codigoMetodoPago;

    public enum Estado {
        Pendiente, Enviado, Entregado
    }

    public Pedidos() {
    }

    public Pedidos(int codigoPedido, Time horaPedido, Date fechaPedido, Estado estadoPedido, Double total, int codigoUsuario, int codigoMetodoPago) {
        this.codigoPedido = codigoPedido;
        this.horaPedido = horaPedido;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.total = total;
        this.codigoUsuario = codigoUsuario;
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Time getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(Time horaPedido) {
        this.horaPedido = horaPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Estado getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(Estado estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public int getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(int codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }
    
    
}
