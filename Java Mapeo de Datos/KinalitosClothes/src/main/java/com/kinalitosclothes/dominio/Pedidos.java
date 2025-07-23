package com.kinalitosclothes.dominio;

import java.sql.Time;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table (name= "Pedidos")
public class Pedidos {
    
    @Id
    @Column (name = "codigoPedido")
    private int codigoPedido;
    @Column(name = "horaPedido")
    private Time hora;;
    @Temporal(TemporalType.DATE)
    @Column
    private Date fechaPedido;
    @Enumerated(EnumType.STRING)    
    @Column(name = "estadoPedido", columnDefinition = "enum('Pendiente','Enviado','Entregado')")
    private Pedidos.EstadoPedido estadoPedido;
    @Column 
    private Double total;
    @Column 
    private int codigoCliente;
    @Column 
    private int codigoMetodoPago;
    
    public enum EstadoPedido {
        Pendiente, Enviado, Entregado
    }
    
    public Pedidos() {
    }

    public Pedidos(int codigoPedido, Time hora, Date fechaPedido, EstadoPedido estadoPedido, Double total, int codigoCliente, int codigoMetodoPago) {
        this.codigoPedido = codigoPedido;
        this.hora = hora;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.total = total;
        this.codigoCliente = codigoCliente;
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public int getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(int codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    

    @Override
    public String toString() {
        return "Pedidos{" + "codigoPedido=" + codigoPedido + 
                ", hora=" + hora + 
                ", fechaPedido=" + fechaPedido + 
                ", estadoPedido=" + estadoPedido + 
                ", total=" + total + 
                ", codigoCliente=" + codigoCliente + 
                ", codigoMetodoPago=" + codigoMetodoPago + '}';
    }

    
}
