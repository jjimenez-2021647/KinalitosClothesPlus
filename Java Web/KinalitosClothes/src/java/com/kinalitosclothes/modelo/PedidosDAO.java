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
}
