package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import com.kinalitosclothes.modelo.Categorias.Genero;
import com.kinalitosclothes.modelo.Categorias.RangoEdad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoriasDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int resp;

    // Listar categorías
    public List<Categorias> listar() {
        String sql = "call sp_ListarCategoria();";
        List<Categorias> listaCategorias = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categorias cat = new Categorias();
                cat.setCodigoCategoria(rs.getInt(1));
                cat.setNombreCategoria(rs.getString(2));
                cat.setDescripcionCategoria(rs.getString(3));
                cat.setGenero(Genero.valueOf(rs.getString(4)));
                cat.setRangoEdad(RangoEdad.valueOf(rs.getString(5)));
                listaCategorias.add(cat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCategorias;
    }

    // Agregar categoría
    public int agregar(Categorias categoria) {
        String sql = "call sp_AgregarCategoria(?, ?, ?, ?);";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNombreCategoria());
            ps.setString(2, categoria.getDescripcionCategoria());
            ps.setString(3, categoria.getGenero().name()); // Enum convertido a String
            ps.setString(4, categoria.getRangoEdad().name()); // Enum convertido a String
            ps.executeQuery(); // Igual que FacturasDAO
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;
    }

    public int eliminar(int codigoCategoria) {
        String sql = "call sp_EliminarCategoria(?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCategoria);

            resp = ps.executeUpdate();
            System.out.println("Categoría eliminada. Filas afectadas: " + resp);

        } catch (Exception e) {
            System.out.println("Error al eliminar categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

    public Categorias buscar(int codigoCategoria) {
        String sql = "call sp_BuscarCategoria(?);";
        Categorias categoria = null;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoCategoria);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoria = new Categorias();
                categoria.setCodigoCategoria(rs.getInt("codigoCategoria"));
                categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                categoria.setDescripcionCategoria(rs.getString("descripcionCategoria"));
                categoria.setGenero(Genero.valueOf(rs.getString("genero")));
                categoria.setRangoEdad(RangoEdad.valueOf(rs.getString("rangoEdad")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoria;
    }
    public int actualizar(Categorias cat) {
        String sql = "call sp_EditarCategoria(?, ?, ?, ?, ?);";
        resp = 0;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cat.getCodigoCategoria());
            ps.setString(2, cat.getNombreCategoria());
            ps.setString(3, cat.getDescripcionCategoria());
            ps.setString(4, cat.getGenero().name()); // Convertir enum a String
            ps.setString(5, cat.getRangoEdad().name()); // Convertir enum a String

            resp = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al actualizar Categoria: " + e.getMessage());
            e.printStackTrace();
        }
        return resp;
    }

}