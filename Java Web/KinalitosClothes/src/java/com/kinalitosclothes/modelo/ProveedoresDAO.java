package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List<Proveedores> listar() {
        String sql = "call sp_ListarProveedor();";
        List<Proveedores> listaProveedores = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedores prov = new Proveedores();
                prov.setCodigoProveedor(rs.getInt("codigoProveedor"));
                prov.setNombreProveedor(rs.getString("nombreProveedor"));
                prov.setTelefonoProveedor(rs.getString("telefonoProveedor"));
                prov.setCorreoProveedor(rs.getString("correoProveedor"));
                prov.setPaisProveedor(rs.getString("paisProveedor"));
                listaProveedores.add(prov);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProveedores;
    }

    public int agregar(Proveedores prov) {
        String sql = "call sp_AgregarProveedor(?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getNombreProveedor());
            ps.setString(2, prov.getTelefonoProveedor());
            ps.setString(3, prov.getCorreoProveedor());
            ps.setString(4, prov.getPaisProveedor());
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int codigoProveedor) {
        String sql = "call sp_EliminarProveedor(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoProveedor);

            resp = ps.executeUpdate();
            System.out.println("Proveedor eliminado. Filas afectadas: " + resp);

        } catch (Exception e) {
            System.out.println("Error al eliminar Proveedor: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public Proveedores buscar(int codigoProveedor) {
        String sql = "call sp_BuscarProveedor(?);";
        Proveedores prov = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoProveedor);
            rs = ps.executeQuery();

            if (rs.next()) {
                prov = new Proveedores();
                prov.setCodigoProveedor(rs.getInt("codigoProveedor"));
                prov.setNombreProveedor(rs.getString("nombreProveedor"));
                prov.setTelefonoProveedor(rs.getString("telefonoProveedor"));
                prov.setCorreoProveedor(rs.getString("correoProveedor"));
                prov.setPaisProveedor(rs.getString("paisProveedor"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prov;
    }
}