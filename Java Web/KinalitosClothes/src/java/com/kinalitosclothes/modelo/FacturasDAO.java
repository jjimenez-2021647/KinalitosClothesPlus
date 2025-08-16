package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FacturasDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List listar() {
        String sql = "call sp_ListarFactura();";
        List<Facturas> listaFacturas = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Facturas Fac = new Facturas();
                Fac.setCodigoFactura(rs.getInt(1));
                Fac.setFechaEmision(rs.getDate(2));
                Fac.setDescuentoAplicado(rs.getDouble(3));
                Fac.setTotalFactura(rs.getDouble(4));
                Fac.setEstadoFactura(Facturas.EstadoFactura.valueOf(rs.getString(5)));
                Fac.setFormaEntrega(Facturas.FormaEntrega.valueOf(rs.getString(6)));
                Fac.setCodigoPedido(rs.getInt(7));
                Fac.setCodigoUsuario(rs.getInt(8));
                listaFacturas.add(Fac);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaFacturas;
    }

    public int agregar(Facturas Fac) {
        String sql = "call sp_AgregarFactura(?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, Fac.getEstadoFactura().name());
            ps.setString(2, Fac.getFormaEntrega().name());
            ps.setInt(3, Fac.getCodigoPedido());
            ps.setInt(4, Fac.getCodigoUsuario());
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int codigoFactura) {
        String sql = "call sp_EliminarFactura(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoFactura);

            resp = ps.executeUpdate();
            System.out.println("Factura eliminado. Filas afectadas: " + resp);

        } catch (Exception e) {
            System.out.println("Error al eliminar Factura: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public Facturas buscar(int codigoFactura) {
        String sql = "call sp_BuscarFactura(?);"; 
        Facturas factura = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoFactura);
            rs = ps.executeQuery();

            if (rs.next()) {
                factura = new Facturas();
                factura.setCodigoFactura(rs.getInt("codigoFactura"));
                factura.setFechaEmision(rs.getDate("fechaEmision"));
                factura.setDescuentoAplicado(rs.getDouble("descuentoAplicado"));
                factura.setTotalFactura(rs.getDouble("totalFactura"));
                factura.setEstadoFactura(Facturas.EstadoFactura.valueOf(rs.getString("estadoFactura")));
                factura.setFormaEntrega(Facturas.FormaEntrega.valueOf(rs.getString("formaEntrega")));
                factura.setCodigoPedido(rs.getInt("codigoPedido"));
                factura.setCodigoUsuario(rs.getInt("codigoUsuario"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return factura;
    }
}
