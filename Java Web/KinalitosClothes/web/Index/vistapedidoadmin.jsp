<%-- Document : vistaproductoadmin Created on : 22 jul 2025, 13:12:45 Author : PC --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta https-equiv=" X-UA-Compatible" content="IE-edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CRUD_Pedidos</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/vistapedidoadmin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>

    <body>
        <nav class="navbar">
            <div class="nav-content">
                <div class="logo">K<span>C</span></div>
                <ul class="menu">
                    <li><a href="Controlador?menu=VistaAdmin">Menu Administrador</a></li>
                </ul>
            </div>
        </nav>

        <section>
            <div class="container">
                <h1>Bienvenido al CRUD completo de la entidad <b>Pedidos</b></h1>

                <!--apartado y actualizar para crear el Pedidos-->
                <div class="section">
                    <h2>Agregar o actualizar pedidos</h2>
                    <form action="Controlador?menu=Pedido" method="POST">
                        <div class="form-row">
                            <div class="form-group">
                                <input type="time" class="entrada_texto" value="${pedido.getHoraPedido()}" name="txtHoraPedido" id="txtHoraPedido" required>
                            </div>
                            <div class="form-group">
                                <input type="date" class="entrada_texto" value="${pedido.getFechaPedido()}" name="txtFechaPedido" id="txtFechaPedido" required>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" value="${pedido.getEstadoPedido()}" name="txtEstadoPedido" id="txtEstadoPedido" required>
                                <label class="label-input">Estado Pedido</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" value="${pedido.getTotal()}" name="txtTotal" min="0" placeholder="0"
                                       required id="numTotal">
                                <label class="label-input-number">Total</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" value="${pedido.getCodigoUsuario()}" name="txtCodigoUsuario" min="0" placeholder="0"
                                       required id="numCodigoCliente">
                                <label class="label-input-number">Codigo de Usuario</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" value="${pedido.getCodigoMetodoPago()}" name="txtCodigoMetodoPago" min="0" placeholder="0"
                                       required id="numCodigoMetodoPago">
                                <label class="label-input-number">Codigo de Metodo Pago</label>
                            </div>

                            <button type="submit" class="btn_crear_producto" value="Agregar" name="accion">
                                <span class="bnt_texto">Crear Pedido</span>
                                <span class="btn_icono">
                                    <i class="fa-solid fa-plus"></i>
                                </span>
                            </button>

                            <button type="submit" class="btn_actualizar">
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
                    <h2>Lista</h2>
                    
                    <!-- el buscar y listar -->
                <div class="section">
                    <h2>Lista</h2>

                    <form action="Controlador?menu=Pedido" method="post" class="search-section">
                        <div class="form-group search-group">
                            <input type="text" class="entrada_texto search-input" name="txtBuscarId" placeholder="">
                            <label class="label-input">Buscar Factura..</label>
                            <div class="search-icon">
                                <i class="fa-solid fa-search"></i>
                            </div>
                        </div>
                        <!-- Botón oculto para enviar el formulario al presionar Enter -->
                        <button type="submit" name="accion" value="Buscar" style="display:none;"></button>

                        <button type="button" class="btn_eliminar" 
                                onclick="window.location.href = 'Controlador?menu=Pedido&accion=Listar'">
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
                                    <th scope="col">Codigo del Pedido</th>
                                    <th scope="col">Hora</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Total</th>
                                    <th scope="col">Codigo de Cliente</th>
                                    <th scope="col">Codigo del Metodo Pago</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="pedidos" items="${pedidos}">
                                    <tr>
                                        <td>${pedidos.getCodigoPedido()}</td>
                                        <td>${pedidos.getHoraPedido()}</td>
                                        <td>${pedidos.getFechaPedido()}</td>
                                        <td>${pedidos.getEstadoPedido()}</td>
                                        <td>${pedidos.getTotal()}</td>
                                        <td>${pedidos.getCodigoUsuario()}</td>
                                        <td>${pedidos.getCodigoMetodoPago()}</td>
                                        <td>
                                            <div class="botonesTabla">
                                                <button type="button" class="btn_editar" id="btnEditarRegistro">
                                                    <span class="bnt_texto">Editar</span>
                                                    <span class="btn_icono">
                                                        <i class="fa-solid fa-pen-to-square"></i>
                                                    </span>
                                                </button>

                                                <button type="button" class="btn_eliminar"
                                                        onclick="window.location.href = 'Controlador?menu=Pedido&accion=Eliminar&id=${pedidos.getCodigoPedido()}'">
                                                    <span class="bnt_texto">Eliminar</span>
                                                    <span class="btn_icono">
                                                        <i class="fa fa-trash"></i></i>
                                                    </span>
                                                </button>
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
                            <strong>¡Atención!</strong> Recuerda que vas a eliminar un registro si lo haces se
                            borrara de
                            forma permanente lo que quiere decir que ya nunca lo recuperaras
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>

</html>