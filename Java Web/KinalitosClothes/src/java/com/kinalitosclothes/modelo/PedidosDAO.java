package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class PedidosDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List listar() {
        String sql = "call sp_ListarPedido();";
        List<Pedidos> listaPedidos = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pedidos ped = new Pedidos();
                ped.setCodigoPedido(rs.getInt(1));
                ped.setHoraPedido(rs.getTime(2));
                ped.setFechaPedido(rs.getDate(3));
                ped.setEstadoPedido(Pedidos.Estado.valueOf(rs.getString(4)));
                ped.setTotal(rs.getDouble(5));
                ped.setCodigoUsuario(rs.getInt(6));
                ped.setCodigoMetodoPago(rs.getInt(7));
                listaPedidos.add(ped);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }

    //Agregar
    public int agregar(Pedidos pe) {
        String sql = "call sp_AgregarPedido(?, ?, ?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setTime(1, pe.getHoraPedido());
            java.util.Date fechaUtil = pe.getFechaPedido(); 
            java.sql.Date fechaSQL = new java.sql.Date(fechaUtil.getTime());
            ps.setDate(2, fechaSQL);
            ps.setString(3, pe.getEstadoPedido().name());
            ps.setDouble(4, pe.getTotal());
            ps.setInt(5, pe.getCodigoUsuario());
            ps.setInt(6, pe.getCodigoMetodoPago());
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }
    
    //Eliminar
    public int eliminar(int codigoPedido) {
        String sql = "call sp_EliminarPedido(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoPedido);

            resp = ps.executeUpdate();
            System.out.println("Pedido eliminado. Filas afectadas: " + resp);

        } catch (Exception e) {
            System.out.println("Error al eliminar pedido: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }
    
    public Pedidos buscar(int codigoPedido) {
        String sql = "call sp_BuscarPedido(?);"; 
        Pedidos pedido = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoPedido);
            rs = ps.executeQuery();

            if (rs.next()) {
                pedido = new Pedidos();
                pedido.setCodigoPedido(rs.getInt(1));
                pedido.setHoraPedido(rs.getTime(2));
                pedido.setFechaPedido(rs.getDate(3));
                pedido.setEstadoPedido(Pedidos.Estado.valueOf(rs.getString(4)));
                pedido.setTotal(rs.getDouble(5));
                pedido.setCodigoUsuario(rs.getInt(6));
                pedido.setCodigoMetodoPago(rs.getInt(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pedido;
    }
}
