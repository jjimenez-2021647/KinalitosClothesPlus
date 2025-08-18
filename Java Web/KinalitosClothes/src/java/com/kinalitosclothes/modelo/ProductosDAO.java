package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductosDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    public List listar() {
        String sql = "call sp_ListarProducto();";
        List<Productos> listaProductos = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos pr = new Productos();
                pr.setCodigoProducto(rs.getInt(1));
                pr.setNombreProducto(rs.getString(2));
                pr.setDescripcionProducto(rs.getString(3));
                pr.setPrecioProducto(rs.getDouble(4));
                pr.setTalla(rs.getString(5));
                pr.setStock(rs.getInt(6));
                pr.setCodigoProveedor(rs.getInt(7));
                pr.setCodigoCategoria(rs.getInt(8));
                listaProductos.add(pr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaProductos;
    }

    public int agregar(Productos pro) {
        String sql = "call sp_AgregarProducto(?, ?, ?, ?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getNombreProducto());
            ps.setString(2, pro.getDescripcionProducto());
            ps.setDouble(3, pro.getPrecioProducto());
            ps.setString(4, pro.getTalla());
            ps.setInt(5, pro.getStock());
            ps.setInt(6, pro.getCodigoProveedor());
            ps.setInt(7, pro.getCodigoCategoria());
            ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resp;
    }

    public int actualizar(Productos pro) {
        String sql = "call sp_EditarProducto(?, ?, ?, ?, ?, ?, ?, ?)";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pro.getCodigoProducto());
            ps.setString(2, pro.getNombreProducto());
            ps.setString(3, pro.getDescripcionProducto());
            ps.setDouble(4, pro.getPrecioProducto());
            ps.setString(5, pro.getTalla());
            ps.setInt(6, pro.getStock());
            ps.setInt(7, pro.getCodigoProveedor());
            ps.setInt(8, pro.getCodigoCategoria());
            resp = ps.executeUpdate(); 
            System.out.println("Producto actualizado. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int codigoProducto) {
        String sql = "call sp_EliminarProducto(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoProducto);

            resp = ps.executeUpdate();
            System.out.println("Producto eliminado. Filas afectadas: " + resp);

        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public Productos buscar(int codigoProducto) {
        String sql = "call sp_BuscarProducto(?);";
        Productos producto = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoProducto);
            rs = ps.executeQuery();

            if (rs.next()) {
                producto = new Productos();
                producto.setCodigoProducto(rs.getInt(1));
                producto.setNombreProducto(rs.getString(2));
                producto.setDescripcionProducto(rs.getString(3));
                producto.setPrecioProducto(rs.getDouble(4));
                producto.setTalla(rs.getString(5));
                producto.setStock(rs.getInt(6));
                producto.setCodigoProveedor(rs.getInt(7));
                producto.setCodigoCategoria(rs.getInt(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return producto;
    }

}