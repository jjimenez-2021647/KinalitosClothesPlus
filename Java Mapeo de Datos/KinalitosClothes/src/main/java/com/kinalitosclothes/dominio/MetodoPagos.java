package com.kinalitosclothes.dominio;

import javax.persistence.*;


@NamedStoredProcedureQuery(
    name = "MetodoPagos.eliminar",
    procedureName = "sp_EliminarMetodoPago",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigoMetodoPago", type = Integer.class)
    }
)   

@Entity
@Table(name = "MetodoPagos")
public class MetodoPagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoMetodoPago")
    private int codigoMetodoPago;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoMetodoPago", columnDefinition = "enum('Tarjeta','Efectivo')", nullable = false)
    private TipoMetodoPago tipoMetodoPago;

    @Column(name = "entidadFinanciaera", nullable = false, length = 200)
    private String entidadFinanciaera;

    @Column(name = "moneda", nullable = false, length = 150)
    private String moneda;

    public enum TipoMetodoPago {
        Tarjeta, Efectivo
    }

    public MetodoPagos() {
    }

    public MetodoPagos(int codigoMetodoPago, TipoMetodoPago tipoMetodoPago, String entidadFinanciaera, String moneda) {
        this.codigoMetodoPago = codigoMetodoPago;
        this.tipoMetodoPago = tipoMetodoPago;
        this.entidadFinanciaera = entidadFinanciaera;
        this.moneda = moneda;
    }

    public int getCodigoMetodoPago() {
        return codigoMetodoPago;
    }

    public void setCodigoMetodoPago(int codigoMetodoPago) {
        this.codigoMetodoPago = codigoMetodoPago;
    }

    public TipoMetodoPago getTipoMetodoPago() {
        return tipoMetodoPago;
    }

    public void setTipoMetodoPago(TipoMetodoPago tipoMetodoPago) {
        this.tipoMetodoPago = tipoMetodoPago;
    }

    public String getEntidadFinanciaera() {
        return entidadFinanciaera;
    }

    public void setEntidadFinanciaera(String entidadFinanciaera) {
        this.entidadFinanciaera = entidadFinanciaera;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    

    @Override
    public String toString() {
        return "MetodoPago{" +
                "\nCodigo Metodo Pago: " + codigoMetodoPago +
                "\nTipo Metodo Pago: " + tipoMetodoPago +
                "\nEntidad Financiera: " + entidadFinanciaera +
                "\nMoneda: " + moneda +
                '}';
    }
}
