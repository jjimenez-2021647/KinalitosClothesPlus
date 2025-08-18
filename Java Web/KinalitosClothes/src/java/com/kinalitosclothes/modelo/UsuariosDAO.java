package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public Usuarios validar(String correoUsuario, String contraseñaUsuario) {
        //instanciar el objeto de la entidad Empleado
        Usuarios usuarios = new Usuarios();
        //agregar una variable de tipo Select * from Usuarios where nombreUsuario = ? and contraseñaUsuario = ?" String para muestra de consulta sql
        String sql = "call sp_BuscarUsuariosNC(?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, correoUsuario);
            ps.setString(2, contraseñaUsuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuarios.setCodigoUsuario(rs.getInt("codigoUsuario"));
                usuarios.setNombreUsuario(rs.getString("nombreUsuario"));
                usuarios.setApellidoUsuario(rs.getString("apellidoUsuario"));
                usuarios.setCorreoUsuario(rs.getString("correoUsuario"));
                usuarios.setTelefonoUsuario(rs.getString("telefonoUsuario"));
                usuarios.setDireccionUsuario(rs.getString("direccionUsuario"));
                usuarios.setContraseñaUsuario(rs.getString("contraseñaUsuario"));
                usuarios.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(rs.getString("tipoUsuario")));
                usuarios.setFechaRegistro(rs.getDate("fechaRegistro"));
            }
        } catch (Exception e) {
            System.out.println("El usuario o contraseña son incorrectos");
            e.printStackTrace();
        }
        return usuarios;
    }

    public List listar() {
        String sql = "call sp_ListarUsuarios();";
        List<Usuarios> listaUsuarios = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuarios us = new Usuarios();
                us.setCodigoUsuario(rs.getInt(1));
                us.setNombreUsuario(rs.getString(2));
                us.setApellidoUsuario(rs.getString(3));
                us.setCorreoUsuario(rs.getString(4));
                us.setTelefonoUsuario(rs.getString(5));
                us.setDireccionUsuario(rs.getString(6));
                us.setContraseñaUsuario(rs.getString(7));
                us.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(rs.getString(8)));
                us.setFechaRegistro(rs.getDate(9));
                listaUsuarios.add(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    public int agregar(Usuarios us) {
        String sql = "call sp_AgregarUsuario( ?, ?, ?, ?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, us.getNombreUsuario());
            ps.setString(2, us.getApellidoUsuario());
            ps.setString(3, us.getCorreoUsuario());
            ps.setString(4, us.getTelefonoUsuario());
            ps.setString(5, us.getDireccionUsuario());
            ps.setString(6, us.getContraseñaUsuario());
            ps.setString(7, us.getTipoUsuario().name());
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int codigoUsuario) {
        String sql = "call sp_EliminarUsuario(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoUsuario);

            resp = ps.executeUpdate();
            System.out.println("Usuario eliminado. Filas afectadas: " + resp);

        } catch (Exception e) {
            System.out.println("Error al eliminar Usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public List<Usuarios> buscarPorNombre(String nombre) {
        String sql = "call sp_BuscarUsuariosCon(?);";
        List<Usuarios> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuarios us = new Usuarios();
                us.setCodigoUsuario(rs.getInt(1));
                us.setNombreUsuario(rs.getString(2));
                us.setApellidoUsuario(rs.getString(3));
                us.setCorreoUsuario(rs.getString(4));
                us.setTelefonoUsuario(rs.getString(5));
                us.setDireccionUsuario(rs.getString(6));
                us.setContraseñaUsuario(rs.getString(7));
                us.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(rs.getString(8)));
                us.setFechaRegistro(rs.getDate(9));
                lista.add(us);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Usuarios imagenCodigo(int codigoUsuario) {
        Usuarios usuario = null;
        String sql = "call sp_BuscarUsuariosImagen(?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoUsuario);
            rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuarios();
                usuario.setCodigoUsuario(rs.getInt(1));
                usuario.setNombreUsuario(rs.getString(2));
                usuario.setApellidoUsuario(rs.getString(3));
                usuario.setCorreoUsuario(rs.getString(4));
                usuario.setTelefonoUsuario(rs.getString(5));
                usuario.setDireccionUsuario(rs.getString(6));
                usuario.setContraseñaUsuario(rs.getString(7));
                usuario.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(rs.getString(8)));
                usuario.setFechaRegistro(rs.getDate(9));
                usuario.setImagenUsuario(rs.getBytes(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public boolean actualizarImagen(int codigoUsuario, byte[] imagen) {
        String sql = "{call sp_AgregarImagenUsuario(?, ?)}";
        try (Connection con = cn.Conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, codigoUsuario);
            cs.setBytes(2, imagen);
            return cs.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuarios buscarPorCodigo(int codigoUsuario) {
        String sql = "call sp_BuscarUsuarios(?);";
        Usuarios us = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoUsuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                us = new Usuarios();
                us.setCodigoUsuario(rs.getInt(1));
                us.setNombreUsuario(rs.getString(2));
                us.setApellidoUsuario(rs.getString(3));
                us.setCorreoUsuario(rs.getString(4));
                us.setTelefonoUsuario(rs.getString(5));
                us.setDireccionUsuario(rs.getString(6));
                us.setContraseñaUsuario(rs.getString(7));
                us.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(rs.getString(8)));
                us.setFechaRegistro(rs.getDate(9));
                us.setImagenUsuario(rs.getBytes(10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return us;
    }

    public int actualizar(Usuarios usuario) {
        String sql = "call sp_EditarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getCodigoUsuario());
            ps.setString(2, usuario.getNombreUsuario());
            ps.setString(3, usuario.getApellidoUsuario());
            ps.setString(4, usuario.getCorreoUsuario());
            ps.setString(5, usuario.getTelefonoUsuario());
            ps.setString(6, usuario.getDireccionUsuario());
            ps.setString(7, usuario.getContraseñaUsuario());
            ps.setString(8, usuario.getTipoUsuario().name()); // Enum convertido a String
            ps.setDate(9, new java.sql.Date(usuario.getFechaRegistro().getTime()));

            resp = ps.executeUpdate();
            System.out.println("Usuario actualizado. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al actualizar Usuario: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception ex) {
            }
        }
        return resp;
    }

    public int registrarLogin(Usuarios usuario) {
        String sql = "call sp_RegistroLogin(?, ?, ?, ?);";
        int resp = 0;
        try (Connection con = cn.Conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setString(1, usuario.getCorreoUsuario());
            cs.setString(2, usuario.getContraseñaUsuario());
            cs.setString(3, usuario.getTipoUsuario().name());
            cs.registerOutParameter(4, java.sql.Types.INTEGER); // Filas afectadas
            cs.execute();
            resp = cs.getInt(4);
            System.out.println("Usuario registrado en login. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al registrar usuario en login: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public int actualizarLogin(Usuarios usuario) {
        String sql = "call sp_EditarUsuarioLogin(?, ?, ?, ?, ?, ?)";
        int resp = 0;
        try (Connection con = cn.Conexion(); CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, usuario.getCodigoUsuario());
            cs.setString(2, usuario.getNombreUsuario());
            cs.setString(3, usuario.getApellidoUsuario());
            cs.setString(4, usuario.getCorreoUsuario());
            cs.setString(5, usuario.getTelefonoUsuario());
            cs.setString(6, usuario.getDireccionUsuario());

            resp = cs.executeUpdate();
            System.out.println("Usuario actualizado en login. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario en login: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

}
