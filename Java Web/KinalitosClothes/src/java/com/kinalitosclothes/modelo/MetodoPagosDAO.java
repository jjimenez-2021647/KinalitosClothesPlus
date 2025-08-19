package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase MetodoPagosDAO
 * Gestiona las operaciones de acceso a datos (CRUD) para la entidad MetodoPagos.
 * Utiliza JDBC para interactuar con la base de datos a través de procedimientos almacenados.
 */
public class MetodoPagosDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    /**
     * Lista todos los métodos de pago existentes en la base de datos.
     * @return Una lista de objetos MetodoPagos.
     */
    public List<MetodoPagos> listar() {
        String sql = "call sp_ListarMetodoPago();";
        List<MetodoPagos> listaMetodos = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MetodoPagos mp = new MetodoPagos();
                mp.setCodigoMetodoPago(rs.getInt(1));
                mp.setTipoMetodoPago(MetodoPagos.TipoMetodo.valueOf(rs.getString(2)));
                mp.setEntidadFinanciaera(rs.getString(3));
                mp.setMoneda(rs.getString(4));
                listaMetodos.add(mp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaMetodos;
    }

    /**
     * Agrega un nuevo método de pago a la base de datos.
     * @param mp El objeto MetodoPagos a agregar.
     * @return El número de filas afectadas.
     */
    public int agregar(MetodoPagos mp) {
        String sql = "call sp_AgregarMetodoPago(?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, mp.getTipoMetodoPago().name());
            ps.setString(2, mp.getEntidadFinanciaera());
            ps.setString(3, mp.getMoneda());
            resp = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resp;
    }

    /**
     * Elimina un método de pago de la base de datos por su código.
     * @param codigoMetodo El código del método de pago a eliminar.
     * @return El número de filas afectadas.
     */
    public int eliminar(int codigoMetodo) {
        String sql = "call sp_EliminarMetodoPago(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoMetodo);
            resp = ps.executeUpdate();
            System.out.println("Método de Pago eliminado. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al eliminar Método de Pago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resp;
    }

    /**
     * Busca un método de pago en la base de datos por su código.
     * @param codigoMetodo El código del método de pago a buscar.
     * @return El objeto MetodoPagos encontrado, o null si no se encuentra.
     */
    public MetodoPagos buscar(int codigoMetodo) {
        String sql = "call sp_BuscarMetodoPago(?);";
        MetodoPagos metodo = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoMetodo);
            rs = ps.executeQuery();
            if (rs.next()) {
                metodo = new MetodoPagos();
                metodo.setCodigoMetodoPago(rs.getInt(1));
                metodo.setTipoMetodoPago(MetodoPagos.TipoMetodo.valueOf(rs.getString(2)));
                metodo.setEntidadFinanciaera(rs.getString(3));
                metodo.setMoneda(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return metodo;
    }

    /**
     * Actualiza un método de pago existente en la base de datos.
     * @param mp El objeto MetodoPagos con los datos actualizados.
     * @return El número de filas afectadas.
     */
    public int actualizar(MetodoPagos mp) {
        String sql = "call sp_EditarMetodoPago(?, ?, ?, ?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mp.getCodigoMetodoPago());
            ps.setString(2, mp.getTipoMetodoPago().name());
            ps.setString(3, mp.getEntidadFinanciaera());
            ps.setString(4, mp.getMoneda());
            resp = ps.executeUpdate();
            System.out.println("Método de Pago actualizado. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al actualizar Método de Pago: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resp;
    }
}