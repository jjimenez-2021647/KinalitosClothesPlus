package Controlador;

import com.kinalitosclothes.modelo.*;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        Usuarios usuarios = new Usuarios();
        UsuariosDAO usuariosDao = new UsuariosDAO();
        Facturas facturas = new Facturas();
        FacturasDAO facturasDao = new FacturasDAO();
        PedidosDAO pedidoDAO = new PedidosDAO();
        Pedidos pedido = new Pedidos();
        int codUsuario;

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Index/Principal.jsp").forward(request, response);
        } else if (menu.equals("Index")) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Listar":
                    List listaUsuarios = usuariosDao.listar();
                    request.setAttribute("usuarios", listaUsuarios);
                    break;
                case "Buscar":
                    String nombreBuscar = request.getParameter("txtBuscarNombre");
                    List<Usuarios> listaBusqueda;
                    if (nombreBuscar == null || nombreBuscar.isEmpty()) {
                        listaBusqueda = usuariosDao.listar(); // lista todo si no hay texto
                    } else {
                        listaBusqueda = usuariosDao.buscarPorNombre(nombreBuscar);
                    }
                    request.setAttribute("usuarios", listaBusqueda);
                    request.getRequestDispatcher("/Index/VistaUsuarioAdmin.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String nombreUsuario = request.getParameter("txtNombreUsuario");
                    String apellidoUsuario = request.getParameter("txtApellidoUsuario");
                    String correoUsuario = request.getParameter("txtCorreoUsuario");
                    String telefonoUsuario = request.getParameter("txtTelefonoUsuario");
                    String direccionUsuario = request.getParameter("txtDireccionUsuario");
                    String contraseñaUsuario = request.getParameter("txtpassword");
                    String tipoUsuario = request.getParameter("txtTipoUsuario");
                    usuarios.setNombreUsuario(nombreUsuario);
                    usuarios.setApellidoUsuario(apellidoUsuario);
                    usuarios.setCorreoUsuario(correoUsuario);
                    usuarios.setTelefonoUsuario(telefonoUsuario);
                    usuarios.setDireccionUsuario(direccionUsuario);
                    usuarios.setContraseñaUsuario(contraseñaUsuario);
                    usuarios.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(tipoUsuario));
                    usuariosDao.agregar(usuarios);
                    if (usuarios != null) {
                        request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    } else {
                        System.out.println("No sale");
                    }
                    break;
                case "Editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Usuarios usuarioEditar = usuariosDao.buscarPorCodigo(idEditar);
                    request.setAttribute("usuario", usuarioEditar);
                    request.setAttribute("usuarios", usuariosDao.listar());
                    request.getRequestDispatcher("/Index/VistaUsuarioAdmin.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    int codigoUsuario = Integer.parseInt(request.getParameter("txtCodigoUsuario"));
                    String nuevoNombre = request.getParameter("txtNombreUsuario");
                    String nuevoApellido = request.getParameter("txtApellidoUsuario");
                    String nuevoCorreo = request.getParameter("txtCorreoUsuario");
                    String nuevoTelefono = request.getParameter("txtTelefonoUsuario");
                    String nuevaDireccion = request.getParameter("txtDireccionUsuario");
                    String nuevaContraseña = request.getParameter("txtpassword");
                    String tipoUsuarioS = request.getParameter("txtTipoUsuario");
                    String fechaRegistroStr = request.getParameter("fechaRegistro");

                    Usuarios usuario = new Usuarios();
                    usuario.setCodigoUsuario(codigoUsuario);
                    usuario.setNombreUsuario(nuevoNombre);
                    usuario.setApellidoUsuario(nuevoApellido);
                    usuario.setCorreoUsuario(nuevoCorreo);
                    usuario.setTelefonoUsuario(nuevoTelefono);
                    usuario.setDireccionUsuario(nuevaDireccion);
                    usuario.setContraseñaUsuario(nuevaContraseña);
                    usuario.setTipoUsuario(Usuarios.TipoUsuarios.valueOf(tipoUsuarioS));

                    if (fechaRegistroStr != null && !fechaRegistroStr.isEmpty()) {
                        java.util.Date fechaRegistro = java.sql.Date.valueOf(fechaRegistroStr);
                        usuario.setFechaRegistro(fechaRegistro);
                    }

                    int filasActualizadas = usuariosDao.actualizar(usuario);
                    System.out.println("Filas actualizadas: " + filasActualizadas);

                    request.setAttribute("usuarios", usuariosDao.listar());
                    request.getRequestDispatcher("/Index/VistaUsuarioAdmin.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    String idEliminar = request.getParameter("id");
                    if (idEliminar != null && !idEliminar.trim().isEmpty()) {
                        try {
                            int codigo = Integer.parseInt(idEliminar);

                            int resultado = usuariosDao.eliminar(codigo);

                            if (resultado > 0) {
                                request.setAttribute("mensaje", "Usuario eliminado exitosamente");
                            } else {
                                request.setAttribute("error", "Error al eliminar el Usuario");
                            }

                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "ID de Usuario inválido");
                        }

                        response.sendRedirect("Controlador?menu=Usuarios&accion=Listar");
                        return;
                    }
                    break;
                default:
                    System.out.println("No se encontro");
            }
            request.getRequestDispatcher("/Index/VistaUsuarioAdmin.jsp").forward(request, response);
        } else if (menu.equals("Proveedor")) {
            request.getRequestDispatcher("Index/vistaproveedoradmin.jsp").forward(request, response);
        } else if (menu.equals("Categoria")) {
            request.getRequestDispatcher("Index/vistacategoria.jsp").forward(request, response);
        } else if (menu.equals("MetodoPago")) {
            request.getRequestDispatcher("Index/metodopagoadmin.jsp").forward(request, response);

        } else if (menu.equals("Producto")) {
            Productos productos = new Productos();
            ProductosDAO productosDAO = new ProductosDAO();
            switch (accion) {

                case "Listar":
                    List listaProductos = productosDAO.listar();
                    request.setAttribute("productos", listaProductos);
                    break;
                case "Agregar":
                    String NombreProducto = request.getParameter("txtNombreProducto");
                    String DescripcionProducto = request.getParameter("txtDescripcion");
                    String PrecioProducto = request.getParameter("txtPrecio");
                    double PrecioProductoC = Double.parseDouble(PrecioProducto);
                    String TallaProducto = request.getParameter("txtTalla");
                    String StockProducto = request.getParameter("txtStock");
                    int StockProductoC = Integer.parseInt(StockProducto);
                    String CodigoProveedor = request.getParameter("txtCodigoProveedor");
                    int CodigoProveedorC = Integer.parseInt(CodigoProveedor);
                    String CodigoCategoria = request.getParameter("txtCodigoCategoria");
                    int CodigoCategoriaC = Integer.parseInt(CodigoCategoria);
                    productos.setNombreProducto(NombreProducto);
                    productos.setDescripcionProducto(DescripcionProducto);
                    productos.setPrecioProducto(PrecioProductoC);
                    productos.setTalla(TallaProducto);
                    productos.setStock(StockProductoC);
                    productos.setCodigoProveedor(CodigoProveedorC);
                    productos.setCodigoCategoria(CodigoCategoriaC);
                    productosDAO.agregar(productos);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);

                    break;

                case "Editar":
                    break;
                case "Actualizar":
                    break;
                case "Eliminar":
                    String idEliminar = request.getParameter("id");
                    if (idEliminar != null && !idEliminar.trim().isEmpty()) {
                        try {
                            int codigo = Integer.parseInt(idEliminar);

                            int resultado = productosDAO.eliminar(codigo);

                            if (resultado > 0) {
                                request.setAttribute("mensaje", "Producto eliminado exitosamente");
                            } else {
                                request.setAttribute("error", "Error al eliminar el producto");
                            }

                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "ID de producto inválido");
                        }

                        response.sendRedirect("Controlador?menu=Producto&accion=Listar");
                        return;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("/Index/vistaproductoadmin.jsp").forward(request, response);
        } else if (menu.equals("Pedido")) {
            switch (accion) {
                case "Listar":
                    List listapedidos = pedidoDAO.listar();
                    request.setAttribute("pedidos", listapedidos);
                    break;
                case "Agregar":
                    int codigoUsuario = 0;
                    int codigoMetodoP = 0;
                    String horaStr = request.getParameter("txtHoraPedido");
                    if (horaStr.length() == 5) {
                        horaStr += ":00";
                    }
                    Time Hora = Time.valueOf(horaStr);
                    String fechaStr = request.getParameter("txtFechaPedido");
                    Date Fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
                    String Estado = request.getParameter("txtEstadoPedido");
                    Double Total = Double.valueOf(request.getParameter("txtTotal"));
                    String codUsuarioStr = request.getParameter("txtCodigoUsuario");
                    String codMetodoStr = request.getParameter("txtCodigoMetodoPago"); // corregido
                    try {
                        codigoUsuario = Integer.parseInt(codUsuarioStr);
                        codigoMetodoP = Integer.parseInt(codMetodoStr);
                    } catch (NumberFormatException e) {
                        System.out.println("El código de usuario o método de pago no es un número válido.");
                        return;
                    }
                    pedido.setHoraPedido(Hora);
                    pedido.setFechaPedido(Fecha);
                    pedido.setEstadoPedido(Pedidos.Estado.Pendiente);
                    pedido.setTotal(Total);
                    pedido.setCodigoUsuario(codigoUsuario);
                    pedido.setCodigoMetodoPago(codigoMetodoP);
                    pedidoDAO.agregar(pedido);
                    request.getRequestDispatcher("Controlador?menu=Pedido&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    break;
                case "Actualizar":
                    break;
                case "Eliminar":
                    break;
                default:
                    System.out.println("No se encontro");
            }
            request.getRequestDispatcher("Index/vistapedidoadmin.jsp").forward(request, response);
        } else if (menu.equals("DetallePedido")) {
            request.getRequestDispatcher("Index/vistadetallepedidoadmin.jsp").forward(request, response);
        } else if (menu.equals("Factura")) {
            switch (accion) {
                case "Listar":
                    List listaFacturas = facturasDao.listar();
                    request.setAttribute("facturas", listaFacturas);
                    break;
                case "Buscar":
                    String codigoFac = request.getParameter("txtBuscarId");
                    List<Facturas> listaFacturasB = new ArrayList<>();
                    if (codigoFac != null && !codigoFac.trim().isEmpty()) {
                        try {
                            int codigoF = Integer.parseInt(codigoFac);
                            Facturas facturaEncontrada = facturasDao.buscar(codigoF);

                            if (facturaEncontrada != null) {
                                listaFacturasB.add(facturaEncontrada);
                            } else {
                                request.setAttribute("error", "Factura no encontrada");
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "ID de Factura inválido");
                        }
                    } else {
                        listaFacturasB = facturasDao.listar();
                    }

                    request.setAttribute("facturas", listaFacturasB);
                    request.getRequestDispatcher("/Index/VistaFacturaAdmin.jsp").forward(request, response);

                    break;
                case "Agregar":
                    String estadoFactura = request.getParameter("txtEstadoFactura");
                    String formaEntrega = request.getParameter("txtFormaEntrega");
                    String codigoPedido = request.getParameter("txtCodigoPedido");
                    String codigoUsuario = request.getParameter("txtCodigoUsuarios");
                    int codigoP = Integer.parseInt(codigoPedido);
                    int codigoU = Integer.parseInt(codigoUsuario);
                    facturas.setEstadoFactura(Facturas.EstadoFactura.valueOf(estadoFactura));
                    facturas.setFormaEntrega(Facturas.FormaEntrega.valueOf(formaEntrega));
                    facturas.setCodigoPedido(codigoP);
                    facturas.setCodigoUsuario(codigoU);
                    facturasDao.agregar(facturas);
                    if (facturas != null) {
                        request.getRequestDispatcher("Controlador?menu=Factura&accion=Listar").forward(request, response);
                    } else {
                        System.out.println("No sale");
                    }
                    break;
                case "Editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Facturas facturaEditar = facturasDao.buscar(idEditar);
                    request.setAttribute("factura", facturaEditar);
                    request.setAttribute("facturas", facturasDao.listar());
                    request.getRequestDispatcher("/Index/VistaFacturaAdmin.jsp").forward(request, response);
                    break;
                case "Actualizar":
                    int codigoFactura = Integer.parseInt(request.getParameter("txtCodigoFactura"));
                    java.sql.Date fechaEmision = java.sql.Date.valueOf(request.getParameter("txtFechaEmision"));
                    double descuentoAplicado = Double.parseDouble(request.getParameter("txtDescuentoAplicado"));
                    double totalFactura = Double.parseDouble(request.getParameter("txtTotalFactura"));
                    String eFac = request.getParameter("txtEstadoFactura");
                    String fEntrega = request.getParameter("txtFormaEntrega");
                    int codPe = Integer.parseInt(request.getParameter("txtCodigoPedido"));
                    int codUs = Integer.parseInt(request.getParameter("txtCodigoUsuario"));

                    facturas.setCodigoFactura(codigoFactura);
                    facturas.setFechaEmision(fechaEmision);
                    facturas.setDescuentoAplicado(descuentoAplicado);
                    facturas.setTotalFactura(totalFactura);
                    facturas.setEstadoFactura(Facturas.EstadoFactura.valueOf(eFac));
                    facturas.setFormaEntrega(Facturas.FormaEntrega.valueOf(fEntrega));
                    facturas.setCodigoPedido(codPe);
                    facturas.setCodigoUsuario(codUs);

                    int filas = facturasDao.actualizar(facturas);

                    System.out.println("Filas actualizadas: " + filas);

                    request.setAttribute("facturas", facturasDao.listar());
                    request.getRequestDispatcher("/Index/VistaFacturaAdmin.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    String idEliminar = request.getParameter("id");
                    if (idEliminar != null && !idEliminar.trim().isEmpty()) {
                        try {
                            int codigo = Integer.parseInt(idEliminar);

                            int resultado = facturasDao.eliminar(codigo);

                            if (resultado > 0) {
                                request.setAttribute("mensaje", "Factura eliminado exitosamente");
                            } else {
                                request.setAttribute("error", "Error al eliminar el Factura");
                            }

                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "ID de producto inválido");
                        }

                        response.sendRedirect("Controlador?menu=Factura&accion=Listar");
                        return;
                    }
                    break;
                default:
                    System.out.println("No se encontro");
            }
            request.getRequestDispatcher("Index/VistaFacturaAdmin.jsp").forward(request, response);
        } else if (menu.equals("VistaAdmin")) {
            request.getRequestDispatcher("Index/vistaadmin.jsp").forward(request, response);
        } else if (menu.equals("MetodoPagoC")) {
            request.getRequestDispatcher("Index/MetodoPago.jsp").forward(request, response);
        } else if (menu.equals("VistaFacturaC")) {
            request.getRequestDispatcher("Index/VistaFacturaCliente.jsp").forward(request, response);
        } else if (menu.equals("VistaUsuarioCliente")) {
            HttpSession session = request.getSession();
            System.out.println("Código usuario en sesión: " + session.getAttribute("codigoUsuario"));

            String idParam = request.getParameter("id");
            int idUsuario = 0;
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    idUsuario = Integer.parseInt(idParam);
                } catch (NumberFormatException e) {
                    request.setAttribute("mensajeError", "Debe proporcionar un ID de usuario válido.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
                Usuarios usuario = usuariosDao.imagenCodigo(idUsuario);
                request.setAttribute("usuario", usuario);
                request.getRequestDispatcher("Index/VistaUsuarioCliente.jsp").forward(request, response);
            } else {
                request.setAttribute("mensajeError", "Debe proporcionar un ID de usuario.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else if (menu.equals(
                "Conocenos")) {
            request.getRequestDispatcher("Index/conocenos.jsp").forward(request, response);
        } else if (menu.equals(
                "Hombre")) {
            request.getRequestDispatcher("Index/hombre.jsp").forward(request, response);
        } else if (menu.equals(
                "MisPedidos")) {
            request.getRequestDispatcher("Index/mispedidos.jsp").forward(request, response);
        } else if (menu.equals(
                "Mujer")) {
            request.getRequestDispatcher("Index/mujer.jsp").forward(request, response);
        } else if (menu.equals(
                "VistaCategoria")) {
            request.getRequestDispatcher("Index/vistacategoria.jsp").forward(request, response);
        } else if (menu.equals(
                "VistaDetallePedido")) {
            request.getRequestDispatcher("Index/vistadetallepedido.jsp").forward(request, response);
        } else if (menu.equals(
                "VistaProducto")) {
            request.getRequestDispatcher("Index/vistaproducto.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);

        } catch (ParseException ex) {
            Logger.getLogger(Controlador.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
