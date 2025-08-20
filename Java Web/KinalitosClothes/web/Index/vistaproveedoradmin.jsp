<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CRUD_Proveedor</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/vistaproveedoradmin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>

    <body>
        <nav class="navbar">
            <div class="nav-content">
                <div class="logo">K<span>C</span></div>
                <ul class="menu">
                    <li><a href="Controlador?menu=VistaAdmin">Menu Administrador</a></li>
                    <li><a href="#">Proveedor</a></li>
                    <li><a href="Controlador?menu=Categoria">Categoria</a></li>
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
                <h1>Bienvenido al CRUD completo de la Entidad <b>Proveedor</b></h1>
                <!--apartado para crear el proveedor-->
                <div class="section">
                    <h2>Agregar o actualizar proveedor</h2>
                    <form action="Controlador?menu=Proveedor" method="POST">
                        <div class="form-row">
                            <input type="hidden" name="txtCodigoProveedor" value="${proveedor.getCodigoProveedor()}">
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtNombreProveedor" id="txtNombreProveedor" value="${proveedor.getNombreProveedor()}" required>
                                <label class="label-input">Nombre Proveedor</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtTelefonoProveedor" id="txtTelefonoProveedor" value="${proveedor.getTelefonoProveedor()}" required>
                                <label class="label-input">Telefono Proveedor</label>
                            </div>
                            <div class="form-group">
                                <input type="email" class="entrada_texto" name="txtCorreoProveedor" id="txtCorreoProveedor" value="${proveedor.getCorreoProveedor()}" required>
                                <label class="label-input">Correo Proveedor</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtPaisProveedor" id="txtPaisProveedor" value="${proveedor.getPaisProveedor()}" required>
                                <label class="label-input">Pais Proveedor</label>
                            </div>
                            <button type="submit" class="btn_actualizar" name="accion" value="Agregar">
                                <span class="bnt_texto">Crear Proveedor</span>
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

                <!-- el buscar y listar -->
                <div class="section">
                    <h2>Listar</h2>

                    <form action="Controlador?menu=Proveedor" method="post" class="search-section">
                        <div class="form-group search-group">
                            <input type="text" class="entrada_texto search-input" name="txtBuscarId" placeholder="">
                            <label class="label-input">Buscar Proveedor..</label>
                            <div class="search-icon">
                                <i class="fa-solid fa-search"></i>
                            </div>
                        </div>
                        <!-- Botón oculto para enviar el formulario al presionar Enter -->
                        <button type="submit" name="accion" value="Buscar" style="display:none;"></button>

                        <button type="button" class="btn_eliminar" 
                                onclick="window.location.href = 'Controlador?menu=Proveedor&accion=Listar'">
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
                                    <th>Codigo del Proveedor</th>
                                    <th>Nombre</th>
                                    <th>Telefono</th>
                                    <th>Correo</th>
                                    <th>Pais</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="proveedor" items="${proveedores}">
                                    <tr>
                                        <td>${proveedor.getCodigoProveedor()}</td>
                                        <td>${proveedor.getNombreProveedor()}</td>
                                        <td>${proveedor.getTelefonoProveedor()}</td>
                                        <td>${proveedor.getCorreoProveedor()}</td>
                                        <td>${proveedor.getPaisProveedor()}</td>
                                        <td>
                                            <div class="botonesTabla">
                                                <button type="button" class="btn_editar" onclick="window.location.href = 'Controlador?menu=Proveedor&accion=Editar&id=${proveedor.getCodigoProveedor()}'">
                                                    <span class="bnt_texto">Editar</span>
                                                    <span class="btn_icono">
                                                        <i class="fa-solid fa-pen-to-square"></i>
                                                    </span>
                                                </button>
                                                <button type="button" class="btn_eliminar" name="btnEliminarProveedor" id="btnEliminarProveedor"
                                                        onclick="window.location.href = 'Controlador?menu=Proveedor&accion=Eliminar&id=${proveedor.getCodigoProveedor()}'">
                                                    <span class="bnt_texto">Eliminar</span>
                                                    <span class="btn_icono">
                                                        <i class="fa fa-trash"></i>
                                                    </span>
                                            </div>
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
                            <strong>¡Atención!</strong> Recuerda que vas a eliminar un registro si lo haces se borrará de forma permanente lo que quiere decir que ya nunca lo recuperarás
                        </div>
                    </form>
                </div>
            </div>
        </section>