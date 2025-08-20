vista jsp de producto
<%-- Document : vistaproductoadmin Created on : 22 jul 2025, 13:12:45 Author : PC --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta https-equiv=" X-UA-Compatible" content="IE-edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CRUD_Producto</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/vistaproductoadmin.css">
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
                    <li><a href="#">Producto</a></li>
                    <li><a href="Controlador?menu=Pedido&accion=Listar">Pedido</a></li>
                    <li><a href="Controlador?menu=DetallePedido&accion=Listar">Detalle P.</a></li>
                    <li><a href="Controlador?menu=Factura&accion=Listar">Factura</a></li>
                </ul>
            </div>
        </nav>

        <section>
            <div class="container">
                <h1>Bienvenido al CRUD completo de la entidad <b>Producto</b></h1>

                <div class="section">
                    <h2>Agregar o actualizar producto</h2>
                    <form action="Controlador?menu=Producto" method="POST">
                        <!--guarda el codigo del ptoducto para que se ejecute el editar y actualizar y asi no se tiene que editar porque se guarda con el mismo id que seleccionamos-->
                        <input type="hidden" name="txtCodigoProducto" value="${producto.getCodigoProducto()}">
                        <div class="form-row">
                            <div class="form-group">
                                <input type="text" class="entrada_texto"  name="txtNombreProducto" id="txtNombreProducto" value="${producto.getNombreProducto()}" required>
                                <label class="label-input">Nombre Producto</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtDescripcion"  id="txtDescripcion" value="${producto.getDescripcionProducto()}" required>
                                <label class="label-input">Descripcion</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" id="txtPrecio" name="txtPrecio" value="${producto.getPrecioProducto()}" required>
                                <label class="label-input">Precio en Quetzales</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtTalla" id="txtTalla" value="${producto.getTalla()}" required>
                                <label class="label-input">Talla</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtStock"id="txtStock" value="${producto.getStock()}" required>
                                <label class="label-input">Stock</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtCodigoProveedor"id="txtCodigoProveedor" value="${producto.getCodigoProveedor()}" required>
                                <label class="label-input">Codigo del Proveedor</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtCodigoCategoria" id="txtCodigoCategoria" value="${producto.getCodigoCategoria()}" required>
                                <label class="label-input">Codigo de la Categoria</label>
                            </div>

                        </div>
                        <div class="form-row">
                            <button type="submit" class="btn_crear_producto" value="Agregar" name="accion">
                                <span class="bnt_texto">Crear Producto</span>
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
                    <h2>Lista y Buscar</h2>
                    <form action="Controlador?menu=Producto" method="POST" class="search-section">
                        <div class="form-group search-group">
                            <input type="text" class="entrada_texto search-input" name="txtBuscarId" placeholder="">
                            <label class="label-input">Buscar Producto.</label>
                            <div class="search-icon">
                                <i class="fa-solid fa-search"></i>
                            </div>
                        </div>

                        <button type="submit" name="accion" value="Buscar" style="display:none;"></button>

                        <button type="button" class="btn_eliminar" 
                                onclick="window.location.href = 'Controlador?menu=Producto&accion=Listar'">
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
                                    <th scope="col">Codigo del Producto</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Descripción</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Talla</th>
                                    <th scope="col">Stock</th>
                                    <th scope="col">Codigo Proveedor</th>
                                    <th scope="col">Codigo Categoria</th>
                                    <th scope="col">Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="productos" items="${productos}"><tr> 
                                        <td>${productos.getCodigoProducto()}</td>
                                        <td>${productos.getNombreProducto()}</td>
                                        <td>${productos.getDescripcionProducto()}</td>
                                        <td>${productos.getPrecioProducto()}</td>
                                        <td>${productos.getTalla()}</td>
                                        <td>${productos.getStock()}</td>
                                        <td>${productos.getCodigoProveedor()}</td>
                                        <td>${productos.getCodigoCategoria()}</td>
                                        <td>
                                            <button 
                                                type="button" 
                                                class="btn_editar" 
                                                onclick="window.location.href = 'Controlador?menu=Producto&accion=Editar&id=${productos.getCodigoProducto()}'">
                                                <span class="bnt_texto">Editar</span>
                                                <span class="btn_icono">
                                                    <i class="fa-solid fa-pen-to-square"></i>
                                                </span>
                                            </button>

                                            <button 
                                                type="button" 
                                                class="btn_eliminar" 
                                                onclick="window.location.href = 'Controlador?menu=Producto&accion=Eliminar&id=${productos.getCodigoProducto()}'">
                                                <span class="bnt_texto">Eliminar</span>
                                                <span class="btn_icono">
                                                    <i class="fa fa-trash"></i>
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