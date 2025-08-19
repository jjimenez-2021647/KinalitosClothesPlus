
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Datos Usuario</title>
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/Images/Logo_K.C.png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Styles/UsuarioCliente.css">
    </head>
    <body>
        <nav class="navbar">
            <div class="nav-content">
                <div class="logo">K<span>C</span></div>
                <ul class="menu">
                    <li><a href="Controlador?menu=PrincipalCliente">Inicio</a></li>
                </ul>
            </div>  
        </nav>

        <div class="container">
            <div class="factura-card">
                <div class="factura-header">
                    <h1><span>Usuario</span> Registrado</h1>
                </div>

                <!-- Formulario separado solo para la imagen -->
                <div class="profile-image-section">
                    <div class="profile-image-container">
                        <img src="${pageContext.request.contextPath}/MostrarImagen?id=${usuario.codigoUsuario}" alt="Foto Usuario" />
                    </div>
                    <form action="${pageContext.request.contextPath}/ImagenesActualizar" method="post" enctype="multipart/form-data" class="profile-form">
                        <input type="hidden" name="codigoUsuario" value="${usuario.codigoUsuario}" />
                        <label class="file-input-wrapper">
                            <input type="file" name="imagenUsuario" accept="image/*" required />
                            <div class="file-input-button">
                                <span class="file-input-text">Selecciona tu Imagen</span>
                            </div>
                        </label>
                        <button type="submit" class="btn-edit-photo">Actualizar</button>
                    </form>
                </div>
                <!-- Formulario para editar datos -->
                <form action="Controlador?menu=Usuarios" method="post">
                    <input type="hidden" name="txtCodigoUsuario" value="${usuario.codigoUsuario}">
                    <input type="hidden" name="accion" value="ActualizarLoginC">

                    <div class="factura-info">
                        <div class="info-item">
                            <div class="info-label">Código</div>
                            <div class="info-value">${usuario.codigoUsuario}</div>
                        </div>

                        <div class="info-item">
                            <div class="info-label">Nombre</div>
                            <input type="text" name="txtNombreUsuario" class="info-value" value="${usuario.nombreUsuario}" required />
                        </div>

                        <div class="info-item">
                            <div class="info-label">Apellido</div>
                            <input type="text" name="txtApellidoUsuario" class="info-value" value="${usuario.apellidoUsuario}" required />
                        </div>

                        <div class="info-item">
                            <div class="info-label">Correo Electrónico</div>
                            <input type="email" name="txtCorreoUsuario" class="info-value" value="${usuario.correoUsuario}" required />
                        </div>

                        <div class="info-item">
                            <div class="info-label">Teléfono</div>
                            <input type="text" name="txtTelefonoUsuario" class="info-value" value="${usuario.telefonoUsuario}" />
                        </div>

                        <div class="info-item">
                            <div class="info-label">Dirección</div>
                            <input type="text" name="txtDireccionUsuario" class="info-value" value="${usuario.direccionUsuario}" />
                        </div>

                        <div class="info-item">
                            <div class="info-label">Contraseña</div>
                            <input type="password" class="info-value" value="${usuario.contraseñaUsuario}" readonly />
                        </div>

                        <div class="info-item">
                            <div class="info-label">Tipo Usuario</div>
                            <div class="info-value">
                                <span class="factura-estado estado-emitida">${usuario.tipoUsuario}</span>
                            </div>
                        </div>

                        <div class="info-item">
                            <div class="info-label">Fecha Registro</div>
                            <div class="info-value">${usuario.fechaRegistro}</div>
                        </div>
                    </div>

                    <div class="total-section">
                        <span class="total-label">Estado del Usuario:</span>
                        <span class="total-amount">Activo</span>
                    </div>

                    <div class="actions">
                        <button type="submit" class="btn btn-generar">Editar Usuario</button>
                    </div>
                </form>
            </div>
        </div>
        <!-- Botón Cerrar Sesión -->
        <div class="logout-section">
            <a id="CerrarSesion" class="btn btn-logout" href="Controlador?menu=Index">Cerrar Sesión</a>
        </div>

    </body> 
</html>