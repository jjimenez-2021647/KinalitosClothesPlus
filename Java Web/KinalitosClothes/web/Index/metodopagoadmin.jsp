<%-- Document : vistaproductoadmin Created on : 22 jul 2025, 13:12:45 Author : PC --%> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CRUD_MetodoPago</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/metodopagoadmin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <nav class="navbar">
            <div class="nav-content">
                <div class="logo">K<span>C</span></div>
                <ul class="menu">
                    <li><a href="Controlador?menu=VistaAdmin">Menu Administrador</a></li>
                    <li><a href="Controlador?menu=Proveedor&accion=Listar">Proveedor</a></li>
                    <li><a href="Controlador?menu=Categoria">Categoria</a></li>
                    <li><a href="#">Metodo P.</a></li>
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
                <h1>Bienvenido al CRUD completo de la entidad <b>Método Pago</b></h1>

                <div class="section">
                    <h2>Agregar o actualizar método pago</h2>
                    <form action="Controlador?menu=MetodoPago" method="POST">
                        <input type="hidden" name="txtCodigoMetodoPago" value="${metodopago.getCodigoMetodoPago()}">

                        <div class="form-row">
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtTipoMetodoPago" id="txtTipoMetodoPago" value="${metodopago.getTipoMetodoPago()}" required>
                                <label class="label-input">Tipo Método Pago</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtEntidadFinanciera" id="txtEntidadFinanciera" value="${metodopago.getEntidadFinanciaera()}" required>
                                <label class="label-input">Entidad Financiera</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtMoneda" id="txtMoneda" value="${metodopago.getMoneda()}" required>
                                <label class="label-input">Moneda</label>
                            </div>
                        </div>

                        <div class="form-row">
                            <button type="submit" class="btn_crear_producto" value="Agregar" name="accion">
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
                    <form action="Controlador?menu=MetodoPago" method="Post" class="search-section">
                        <div class="form-group search-group">
                            <input type="text" class="entrada_texto search-input" name="txtBuscarId" placeholder="">
                            <label class="label-input">Buscar Metodo Pago.</label>
                            <div class="search-icon">
                                <i class="fa-solid fa-search"></i>
                            </div>
                        </div>

                        <button type="submit" name="accion" value="Buscar" style="display:none;"></button>

                        <button type="button" class="btn_eliminar" 
                                onclick="window.location.href = 'Controlador?menu=MetodoPago&accion=Listar'">
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
                                    <th scope="col">Código Método Pago</th>
                                    <th scope="col">Tipo</th>
                                    <th scope="col">Entidad Financiera</th>
                                    <th scope="col">Moneda</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="metodopago" items="${metodos}">
                                    <tr>
                                        <td>${metodopago.getCodigoMetodoPago()}</td>
                                        <td>${metodopago.getTipoMetodoPago()}</td>
                                        <td>${metodopago.getEntidadFinanciaera()}</td>
                                        <td>${metodopago.getMoneda()}</td>
                                        <td>
                                            <button
                                                type="button"
                                                class="btn_editar"
                                                onclick="window.location.href = 'Controlador?menu=MetodoPago&accion=Editar&id=${metodopago.getCodigoMetodoPago()}'">
                                                <span class="bnt_texto">Editar</span>
                                                <span class="btn_icono">
                                                    <i class="fa-solid fa-pen-to-square"></i>
                                                </span>
                                            </button>

                                            <button
                                                type="button"
                                                class="btn_eliminar"
                                                onclick="window.location.href = 'Controlador?menu=MetodoPago&accion=Eliminar&id=${metodopago.getCodigoMetodoPago()}'">
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
                            <strong>¡Atención!</strong> Recuerda que si eliminas un registro se borrará de forma permanente y no podrás recuperarlo.
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>