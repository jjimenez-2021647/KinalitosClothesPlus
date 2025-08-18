<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CRUD_Detalle_Pedido</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/detallepedidoadmin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <nav class="navbar">
            <div class="nav-content">
                <div class="logo">K<span>C</span></div>
                <ul class="menu">
                    <li><a href="Controlador?menu=VistaAdmin">Menu Administrador</a></li>
                    <li><a href="Controlador?menu=Proveedor&accion=Listar">Proveedor</a></li>
                    <li><a href="Controlador?menu=Categoria&accion=Listar">Categoria</a></li>
                    <li><a href="Controlador?menu=MetodoPago&accion=Listar">Metodo P.</a></li>
                    <li><a href="Controlador?menu=Usuarios&accion=Listar">Usuario</a></li>
                    <li><a href="Controlador?menu=Producto&accion=Listar">Producto</a></li>
                    <li><a href="Controlador?menu=Pedido&accion=Listar">Pedido</a></li>
                    <li><a href="Controlador?menu=DetallePedido&accion=Listar">Detalle P.</a></li>
                    <li><a href="Controlador?menu=Factura&accion=Listar">Factura</a></li>
                </ul>
            </div>
        </nav>
        <section>
            <div class="container">
                <h1>Bienvenido al CRUD completo de la entidad <b>Detalle Pedido</b></h1>

                <div class="section">
                    <h2>Agregar o actualizar detalle pedido</h2>
                    <form action="Controlador?menu=DetallePedido" method="POST">
                        <!-- Campo oculto para el código del detalle de pedido, usado en la edición/actualización -->
                        <input type="hidden" name="txtCodigoDetalleP" value="${detallepedido.getCodigoDetalleP()}">
                        <div class="form-row">
                            <div class="form-group">
                                <input type="number" class="entrada_texto" name="txtCantidad" id="txtCantidad" value="${detallepedido.getCantidad()}" min="0" required>
                                <label class="label-input-number">Cantidad</label>
                            </div>
                            <div class="form-group">
                                <input type="number" class="entrada_texto" name="txtSubtotal" id="txtSubtotal" step="0.01" value="${detallepedido.getSubtotal()}" min="0" required>
                                <label class="label-input-number">Subtotal en Quetzales</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtDescripcion" id="txtDescripcion" value="${detallepedido.getDescripcion()}" required>
                                <label class="label-input">Descripción</label>
                            </div>
                            <div class="form-group">
                                <input type="number" class="entrada_texto" name="txtCodigoPedido" id="txtCodigoPedido" value="${detallepedido.getCodigoPedido()}" min="0" required>
                                <label class="label-input-number">Código del Pedido</label>
                            </div>
                            <div class="form-group">
                                <input type="number" class="entrada_texto" name="txtCodigoProducto" id="txtCodigoProducto" value="${detallepedido.getCodigoProducto()}" min="0" required>
                                <label class="label-input-number">Código del Producto</label>
                            </div>
                        </div>
                        <div class="form-row">
                            <button type="submit" class="btn_crear_producto" name="accion" value="Agregar" >
                                <span class="bnt_texto">Crear</span>
                                <span class="btn_icono">
                                    <i class="fa-solid fa-plus"></i>
                                </span>
                            </button>
                            <button type="submit" class="btn_actualizar" name="accion" value="Actualizar">
                                <span class="bnt_texto">Actualizar</span>
                                <span class="btn_icono">
                                    <i class="fa-solid fa-pen-to-square"></i>
                                </span>
                            </button>
                        </div>
                    </form>
                </div>

                <div class="section">
                    <h2>Lista y Buscar</h2>
                    <form action="Controlador?menu=DetallePedido" method="POST" class="search-section">
                        <div class="form-group search-group">
                            <input type="text" class="entrada_texto search-input" name="txtBuscarId" placeholder="">
                            <label class="label-input">Buscar Detalle Pedido.</label>
                            <div class="search-icon">
                                <i class="fa-solid fa-search"></i>
                            </div>
                        </div>

                        <!-- Botón oculto para enviar el formulario al presionar Enter en el campo de búsqueda -->
                        <button type="submit" name="accion" value="Buscar" style="display:none;"></button>

                        <button type="button" class="btn_eliminar"
                                onclick="window.location.href = 'Controlador?menu=DetallePedido&accion=Listar'">
                            <span class="bnt_texto">Cancelar</span>
                            <span class="btn_icono">
                                <i class="fa fa-solid fa-x"></i>
                            </span>
                        </button>
                    </form>
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th scope="col">Código Detalle P.</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Subtotal</th>
                                    <th scope="col">Descripción</th>
                                    <th scope="col">Código Pedido</th>
                                    <th scope="col">Código Producto</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Itera sobre la lista de detalles de pedidos para mostrar cada uno en una fila -->
                                <c:forEach var="detallepedido" items="${detallepedidos}">
                                    <tr>
                                        <td>${detallepedido.getCodigoDetalleP()}</td>
                                        <td>${detallepedido.getCantidad()}</td>
                                        <td>${detallepedido.getSubtotal()}</td>
                                        <td>${detallepedido.getDescripcion()}</td>
                                        <td>${detallepedido.getCodigoPedido()}</td>
                                        <td>${detallepedido.getCodigoProducto()}</td>
                                        <td>
                                            <button
                                                type="button"
                                                class="btn_editar"
                                                onclick="window.location.href = 'Controlador?menu=DetallePedido&accion=Editar&id=${detallepedido.getCodigoDetalleP()}'">
                                                <span class="bnt_texto">Editar</span>
                                                <span class="btn_icono">
                                                    <i class="fa-solid fa-pen-to-square"></i>
                                                </span>
                                            </button>

                                            <button
                                                type="button"
                                                class="btn_eliminar"
                                                onclick="window.location.href = 'Controlador?menu=DetallePedido&accion=Eliminar&id=${detallepedido.getCodigoDetalleP()}'">
                                                <span class="bnt_texto">Eliminar</span>
                                                <span class="btn_icono">
                                                    <i class="fa fa-trash"></i>
                                                </span>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <form class="mensaje_eliminar">
                        <input type="hidden">
                        <div class="message warning">
                            <i class="fa fa-exclamation-triangle"></i>
                            <strong>¡Atención!</strong> Recuerda que vas a eliminar un registro si lo haces se
                            borrará de forma permanente lo que quiere decir que ya nunca lo recuperarás
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
