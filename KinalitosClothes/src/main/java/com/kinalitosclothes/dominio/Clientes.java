package com.kinalitosclothes.dominio;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
 
@NamedStoredProcedureQuery(
    name = "Cliente.eliminar",
    procedureName = "sp_EliminarCliente",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigocliente", type = Integer.class)
    }
)


@Entity
@Table(name = "Clientes")
public class Clientes {
 
    @Id
    @Column(name = "codigoCliente")
    private int codigoCliente;
    @Column
    private String nombreCliente, apellidoCliente, correoCliente, telefonoCliente, direccionCliente;
 
    @ManyToOne
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    private Usuarios usuario;
 
    public Clientes() {
    }
 
    public Clientes(int codigoCliente, String nombreCliente, String apellidoCliente, String correoCliente, String telefonoCliente, String direccionCliente, Usuarios usuario) {
        this.codigoCliente = codigoCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.direccionCliente = direccionCliente;
        this.usuario = usuario;
    }
 
    public int getCodigoCliente() {
        return codigoCliente;
    }
 
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
 
    public String getNombreCliente() {
        return nombreCliente;
    }
 
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
 
    public String getApellidoCliente() {
        return apellidoCliente;
    }
 
    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }
 
    public String getCorreoCliente() {
        return correoCliente;
    }
 
    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }
 
    public String getTelefonoCliente() {
        return telefonoCliente;
    }
 
    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
 
    public String getDireccionCliente() {
        return direccionCliente;
    }
 
    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }
 
    public Usuarios getUsuario() {
        return usuario;
    }
 
    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
 
    @Override
    public String toString() {
        return "Cliente{" + 
                "\nCodigo Cliente: " + codigoCliente + 
                "\nNombre Cliente: " + nombreCliente + 
                "\nApellido Cliente: " + apellidoCliente + 
                "\nCorreo Cliente: " + correoCliente + 
                "\nTelefono Cliente: " + telefonoCliente + 
                "\nDireccion Cliente: " + direccionCliente + 
                "\nUsuario Asociado: " + usuario.getCodigoUsuario() + 
                '}';
    }
}