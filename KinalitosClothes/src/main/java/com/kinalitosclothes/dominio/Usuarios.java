package com.josuejimenez.dominio;

import java.util.Date;
import javax.persistence.*;

@NamedStoredProcedureQuery(
    name = "Usuario.eliminar",
    procedureName = "sp_EliminarUsuario",
    parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN, name = "codigoUsuario", type = Integer.class)
    }
)

@Entity
@Table(name = "Usuarios")
public class Usuarios {

    @Id
    @Column(name = "codigoUsuario")
    private int codigoUsuario;
    @Column
    private String nombreUsuario, contraseñaUsuario;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipoUsuario", columnDefinition = "enum('Empleado','Cliente')")
    private TipoUsuarios tipoUsuario;
    @Temporal(TemporalType.DATE)
    @Column
    private Date fechaRegistro;

    public enum TipoUsuarios {
        Empleado, Cliente
    }

    public Usuarios() {
    }

    public Usuarios(int codigoUsuario, String nombreUsuario, String contraseñaUsuario, TipoUsuarios tipoUsuario, Date fechaRegistro) {
        this.codigoUsuario = codigoUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
        this.tipoUsuario = tipoUsuario;
        this.fechaRegistro = fechaRegistro;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public TipoUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuarios tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Usuario{" + "\nCodigo Usuario: " + codigoUsuario + 
                "\nNombre Usuario: " + nombreUsuario + 
                "\nClave de acceso: " + contraseñaUsuario + 
                "\nTipo Usuario: " + tipoUsuario + 
                "\nFecha Registro: " + fechaRegistro + '}';
    }

}
