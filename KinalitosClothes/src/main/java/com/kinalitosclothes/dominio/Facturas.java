package com.josuejimenez.dominio;

import java.util.Date;
import javax.persistence.*;

@NamedStoredProcedureQuery(
    name = "Factura.eliminar",
    procedureName = "sp_EliminarFactura",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigoFactura", type = Integer.class)
    }
)

@Entity
@Table(name = "Facturas")
public class Facturas {

    @Id
    @Column(name = "codigoFactura")
    private int codigoFactura;
    @Temporal(TemporalType.DATE)
    @Column
    private Date fechaEmision;
    @Column
    private Double descuentoAplicado;
    @Column
    private Double totalFactura;
    @Enumerated(EnumType.STRING)
    @Column(name = "estadoFactura", columnDefinition = "enum('Emitida','Anulada','Pagada')")
    private EstadoFactura estadoFactura;
    @Enumerated(EnumType.STRING)
    @Column(name = "formaEntrega", columnDefinition = "enum('Fisica','Electronica')")
    private FormaEntrega  formaEntrega;
    @Column
    private int codigoPedido;
    @Column
    private int codigoEmpleado;

    public enum EstadoFactura {
        Emitida, Anulada, Pagada
    }

    public enum FormaEntrega {
        Fisica, Electronica
    }

    public Facturas() {
    }

    public Facturas(int codigoFactura, Date fechaEmision, Double descuentoAplicado, Double totalFactura, EstadoFactura estadoFactura, FormaEntrega formaEntrega, int codigoPedido, int codigoEmpleado) {
        this.codigoFactura = codigoFactura;
        this.fechaEmision = fechaEmision;
        this.descuentoAplicado = descuentoAplicado;
        this.totalFactura = totalFactura;
        this.estadoFactura = estadoFactura;
        this.formaEntrega = formaEntrega;
        this.codigoPedido = codigoPedido;
        this.codigoEmpleado = codigoEmpleado;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getDescuentoAplicado() {
        return descuentoAplicado;
    }

    public void setDescuentoAplicado(Double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public Double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(Double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public EstadoFactura getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public FormaEntrega getFormaEntrega() {
        return formaEntrega;
    }

    public void setFormaEntrega(FormaEntrega formaEntrega) {
        this.formaEntrega = formaEntrega;
    }

    public int getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public String toString() {
        return "Facturas{" + "\nCodigo Factura: " + codigoFactura + 
                "\nFecha Emision: " + fechaEmision + 
                "\nDescuento Aplicado: " + descuentoAplicado + 
                "\nTotal Factura: " + totalFactura + 
                "\nEstado Factura: " + estadoFactura + 
                "\nForma Entrega: " + formaEntrega + 
                "\nCodigo Pedido: " + codigoPedido + 
                "\nCodigo Empleado: " + codigoEmpleado + '}';
    }

    
    
}
