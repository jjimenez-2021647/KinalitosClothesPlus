package com.kinalitosclothes.modelo;

import com.kinalitosclothes.config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DetallePedidosDAO
 * Gestiona las operaciones de acceso a datos (CRUD) para la entidad DetallePedidos.
 * Utiliza JDBC para interactuar con la base de datos a través de procedimientos almacenados.
 */
public class DetallePedidosDAO {

    // Instancia de la clase de conexión a la base de datos
    Conexion cn = new Conexion();
    Connection con; // Objeto de conexión a la base de datos
    PreparedStatement ps; // Objeto para ejecutar sentencias SQL precompiladas
    ResultSet rs; // Objeto para almacenar el resultado de las consultas
    int resp; // Variable para almacenar el número de filas afectadas en operaciones de actualización

    /**
     * Lista todos los detalles de pedidos existentes en la base de datos.
     * @return Una lista de objetos DetallePedidos.
     */
    public List<DetallePedidos> listar() {
        // Llamada al procedimiento almacenado para listar detalles de pedidos
        String sql = "call sp_ListarDetallePedido();";
        List<DetallePedidos> listaDetalles = new ArrayList<>();
        try {
            con = cn.Conexion(); 
            ps = con.prepareCall(sql); 
            rs = ps.executeQuery(); 
            while (rs.next()) {
                DetallePedidos dp = new DetallePedidos();
                dp.setCodigoDetalleP(rs.getInt(1)); 
                dp.setCantidad(rs.getInt(2)); 
                dp.setSubtotal(rs.getDouble(3)); 
                dp.setDescripcion(rs.getString(4));
                dp.setCodigoPedido(rs.getInt(5));
                dp.setCodigoProducto(rs.getInt(6)); 
                listaDetalles.add(dp); 
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción en caso de error
        } finally {
            // Cerrar recursos (ResultSet, PreparedStatement, Connection)
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listaDetalles; // Devolver la lista de detalles de pedidos
    }

    /**
     * Agrega un nuevo detalle de pedido a la base de datos.
     * @param dp El objeto DetallePedidos a agregar.
     * @return El número de filas afectadas (normalmente 1 si la inserción fue exitosa).
     */
    public int agregar(DetallePedidos dp) {
        String sql = "CALL sp_AgregarDetallePedido(?, ?, ?, ?, ?)"; 

        try {
            con = cn.Conexion(); 
            ps = con.prepareStatement(sql); 
            ps.setInt(1, dp.getCantidad());
            ps.setDouble(2, dp.getSubtotal());
            ps.setString(3, dp.getDescripcion());
            ps.setInt(4, dp.getCodigoPedido());    // Parámetro 4
            ps.setInt(5, dp.getCodigoProducto());  // Parámetro 5

            // *** CAMBIO CRÍTICO: Usar executeUpdate() para operaciones de modificación ***
            resp = ps.executeUpdate(); // Ejecuta la inserción y almacena el número de filas afectadas

        } catch (SQLException e) {
            // Captura cualquier error de SQL que ocurra durante la operación
            System.err.println("Error SQL al agregar DetallePedido: " + e.getMessage());
            e.printStackTrace(); // Imprime la traza completa del error para depuración
            resp = 0; // Asegurarse de que el resultado sea 0 en caso de error
        } finally {
            // Cerrar recursos para evitar fugas de memoria y conexiones abiertas
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos en DetallePedidosDAO.agregar: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return resp; // Devuelve el número de filas afectadas
    }

    /**
     * Elimina un detalle de pedido de la base de datos por su código.
     * @param codigoDetalleP El código del detalle de pedido a eliminar.
     * @return El número de filas afectadas (normalmente 1 si la eliminación fue exitosa).
     */
    public int eliminar(int codigoDetalleP) {
        // Llamada al procedimiento almacenado para eliminar un detalle de pedido
        String sql = "call sp_EliminarDetallePedido(?);";
        resp = 0; // Inicializar la respuesta
        try {
            con = cn.Conexion(); // Obtener la conexión
            ps = con.prepareStatement(sql); // Preparar la sentencia
            ps.setInt(1, codigoDetalleP); // Establecer el parámetro del código
            resp = ps.executeUpdate(); // Ejecutar la actualización
            System.out.println("Detalle de Pedido eliminado. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al eliminar Detalle de Pedido: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la excepción
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resp; // Devolver el resultado de la operación
    }

    /**
     * Busca un detalle de pedido en la base de datos por su código.
     * @param codigoDetalleP El código del detalle de pedido a buscar.
     * @return El objeto DetallePedidos encontrado, o null si no se encuentra.
     */
    public DetallePedidos buscar(int codigoDetalleP) {
        // Llamada al procedimiento almacenado para buscar un detalle de pedido
        String sql = "call sp_BuscarDetallePedido(?);";
        DetallePedidos detallePedido = null; // Objeto para almacenar el detalle de pedido encontrado
        try {
            con = cn.Conexion(); // Obtener la conexión
            ps = con.prepareStatement(sql); // Preparar la sentencia
            ps.setInt(1, codigoDetalleP); // Establecer el parámetro del código
            rs = ps.executeQuery(); // Ejecutar la consulta
            // Si se encuentra un resultado, mapearlo a un objeto DetallePedidos
            if (rs.next()) {
                detallePedido = new DetallePedidos();
                detallePedido.setCodigoDetalleP(rs.getInt(1));
                detallePedido.setCantidad(rs.getInt(2));
                detallePedido.setSubtotal(rs.getDouble(3));
                detallePedido.setDescripcion(rs.getString(4));
                detallePedido.setCodigoPedido(rs.getInt(5));
                detallePedido.setCodigoProducto(rs.getInt(6));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Imprimir la traza de la excepción
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return detallePedido; // Devolver el detalle de pedido encontrado (o null)
    }

    /**
     * Actualiza un detalle de pedido existente en la base de datos.
     * @param dp El objeto DetallePedidos con los datos actualizados.
     * @return El número de filas afectadas (normalmente 1 si la actualización fue exitosa).
     */
    public int actualizar(DetallePedidos dp) {
        // Llamada al procedimiento almacenado para editar un detalle de pedido
        String sql = "call sp_EditarDetallePedido(?, ?, ?, ?, ?, ?);";
        resp = 0; // Inicializar la respuesta
        try {
            con = cn.Conexion(); // Obtener la conexión
            ps = con.prepareStatement(sql); // Preparar la sentencia
            // Establecer los parámetros para el procedimiento almacenado
            ps.setInt(1, dp.getCodigoDetalleP());
            ps.setInt(2, dp.getCantidad());
            ps.setDouble(3, dp.getSubtotal());
            ps.setString(4, dp.getDescripcion());
            ps.setInt(5, dp.getCodigoPedido());
            ps.setInt(6, dp.getCodigoProducto());
            resp = ps.executeUpdate(); // Ejecutar la actualización
            System.out.println("Detalle de Pedido actualizado. Filas afectadas: " + resp);
        } catch (Exception e) {
            System.out.println("Error al actualizar Detalle de Pedido: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la excepción
        } finally {
            // Cerrar recursos
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resp; // Devolver el resultado de la operación
    }
}
