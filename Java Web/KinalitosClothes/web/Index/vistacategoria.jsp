<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE-edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CRUD_Categorias</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/VistaCategoriasAdmin.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    </head>
    <body>
        <nav class="navbar">
            <div class="nav-content">
                <div class="logo">K<span>C</span></div>
                <ul class="menu">
                    <li><a href="Controlador?menu=VistaAdmin">Menu Administrador</a></li>
                    <li><a href="Controlador?menu=Proveedor&accion=Listar">Proveedor</a></li>
                    <li><a href="#">Categoria</a></li>
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
                <h1>Bienvenido al CRUD completo de la entidad <b>Categorias</b></h1>

                <!-- Apartado para crear o actualizar categoría -->
                <div class="section">
                    <h2>Agregar o modificar Categoria</h2>
                    <form action="Controlador?menu=Categoria" method="POST">
                        <input type="hidden" name="txtCodigoCategoria" value="${categoria.getCodigoCategoria()}">
                        <div class="form-row">
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtNombreCategoria" 
                                       id="nombreCategoria" value="${categoria.getNombreCategoria()}" required>
                                <label class="label-input">Nombre Categoria</label>
                            </div>
                            <div class="form-group">
                                <input type="text" class="entrada_texto" name="txtDescripcionCategoria" 
                                       id="descripcionCategoria" value="${categoria.getDescripcionCategoria()}" required>
                                <label class="label-input">Descripcion Categoria</label>
                            </div>
                            <div class="form-group">
                                <select class="entrada_texto" name="txtGenero" id="genero" required>
                                    <option value="" disabled ${categoria.getGenero() == null ? "selected" : ""}></option>
                                    <option value="Hombre" ${categoria.getGenero() == 'Hombre' ? "selected" : ""}>Hombre</option>
                                    <option value="Mujer" ${categoria.getGenero() == 'Mujer' ? "selected" : ""}>Mujer</option>
                                    <option value="Unisex" ${categoria.getGenero() == 'Unisex' ? "selected" : ""}>Unisex</option>
                                </select>
                                <label class="label-input">Genero</label>
                            </div>
                            <div class="form-group">
                                <select class="entrada_texto" name="txtRangoEdad" id="rangoEdad" required>
                                    <option value="" disabled ${categoria.getRangoEdad() == null ? "selected" : ""}></option>
                                    <option value="Infantil" ${categoria.getRangoEdad() == 'Infantil' ? "selected" : ""}>Infantil</option>
                                    <option value="Juvenil" ${categoria.getRangoEdad() == 'Juvenil' ? "selected" : ""}>Juvenil</option>
                                    <option value="Adultos" ${categoria.getRangoEdad() == 'Adultos' ? "selected" : ""}>Adultos</option>
                                </select>
                                <label class="label-input">Rango Edad</label>
                            </div>
                        </div>

                        <div class="form-row">
                            <button type="submit" class="btn_actualizar" name="accion" value="Agregar">
                                <span class="bnt_texto">Crear Categoria</span>
                                <span class="btn_icono"><i class="fa-solid fa-plus"></i></span>
                            </button>
                            <button type="submit" class="btn_actualizar" name="accion" value="Actualizar">
                                <span class="bnt_texto">Actualizar</span>
                                <span class="btn_icono"><i class="fa-solid fa-pen-to-square"></i></span>
                            </button>
                        </div>
                    </form>
                </div>

                <!-- Buscar y listar -->
                <div class="section">
                    <h2>Listar</h2>
                    <form action="Controlador?menu=Categoria" method="post" class="search-section">
                        <div class="form-group search-group">
                            <input type="text" class="search-input" name="txtBuscarCategoria" required>
                            <label class="label-input">Buscar Categoria...</label>
                            <div class="search-icon">
                                <i class="fa-solid fa-search"></i>
                            </div>
                            <button type="submit" name="accion" value="Buscar" style="display:none;"></button>
                        </div>
                        <button type="button" class="btn_eliminar" 
                                onclick="window.location.href = 'Controlador?menu=Categoria&accion=Listar'">
                            <span class="bnt_texto">Cancelar</span>
                            <span class="btn_icono"><i class="fa fa-solid fa-x"></i></span>
                        </button>
                    </form>

                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Codigo Categoria</th>
                                    <th>Nombre Categoria</th>
                                    <th>Descripcion Categoria</th>
                                    <th>Genero</th>
                                    <th>Rango Edad</th>
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="categoria" items="${categorias}">
                                    <tr>
                                        <td>${categoria.getCodigoCategoria()}</td>
                                        <td>${categoria.getNombreCategoria()}</td>
                                        <td>${categoria.getDescripcionCategoria()}</td>
                                        <td>${categoria.getGenero()}</td>
                                        <td>${categoria.getRangoEdad()}</td>
                                        <td>
                                            <div class="botonesTabla">
                                                <button type="button" class="btn_editar" 
                                                        onclick="window.location.href = 'Controlador?menu=Categoria&accion=Editar&id=${categoria.getCodigoCategoria()}'">
                                                    <span class="bnt_texto">Editar</span>
                                                    <span class="btn_icono"><i class="fa-solid fa-pen-to-square"></i></span>
                                                </button>
                                                <button type="button" class="btn_eliminar" 
                                                        onclick="window.location.href = 'Controlador?menu=Categoria&accion=Eliminar&id=${categoria.getCodigoCategoria()}'">
                                                    <span class="bnt_texto">Eliminar</span>
                                                    <span class="btn_icono"><i class="fa fa-trash"></i></span>
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
                            <strong>Atencion!</strong> Recuerda que vas a eliminar un registro; si lo haces, se borrará de forma permanente.
                        </div>
                    </form>
                </div>
            </div>
        </section>

    </body>
</html>
