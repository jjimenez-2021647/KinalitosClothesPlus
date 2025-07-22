package com.kinalitosclothes.dominio;

import javax.persistence.*;

@Entity
@Table(name = "Empleados")
public class Empleados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoEmpleado;

    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String correoEmpleado;
    private String telefonoEmpleado;
    private String direccionEmpleado;
    private int codigoUsuario;

    // Getters y Setters
    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getApellidoEmpleado() {
        return apellidoEmpleado;
    }

    public void setApellidoEmpleado(String apellidoEmpleado) {
        this.apellidoEmpleado = apellidoEmpleado;
    }

    public String getCorreoEmpleado() {
        return correoEmpleado;
    }

    public void setCorreoEmpleado(String correoEmpleado) {
        this.correoEmpleado = correoEmpleado;
    }

    public String getTelefonoEmpleado() {
        return telefonoEmpleado;
    }

    public void setTelefonoEmpleado(String telefonoEmpleado) {
        this.telefonoEmpleado = telefonoEmpleado;
    }

    public String getDireccionEmpleado() {
        return direccionEmpleado;
    }

    public void setDireccionEmpleado(String direccionEmpleado) {
        this.direccionEmpleado = direccionEmpleado;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public String toString() {
        return "Empleado {" +
                "codigo=" + codigoEmpleado +
                ", nombre='" + nombreEmpleado + ' ' + apellidoEmpleado + '\'' +
                ", correo='" + correoEmpleado + '\'' +
                ", teléfono='" + telefonoEmpleado + '\'' +
                ", dirección='" + direccionEmpleado + '\'' +
                ", usuario='" + codigoUsuario + '\'' +
                '}';
    }
}
