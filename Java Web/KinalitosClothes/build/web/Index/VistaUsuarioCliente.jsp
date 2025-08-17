<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li><a href="Controlador?menu=Principal">Inicio</a></li>
                </ul>
            </div>  
        </nav>

        <div class="container">
            <div class="factura-card">
                <div class="factura-header">
                    <h1><span>Usuario</span> Registrado</h1>
                </div>

                <div class="factura-info">
                    <div class="profile-image-section">
                        <div class="profile-image-container">
                            <img src="${pageContext.request.contextPath}/MostrarImagen?id=${usuario.codigoUsuario}" alt="Foto Usuario" />
                        </div>
                        <button class="btn-edit-photo" id="editarFoto">Editar</button>
                    </div>

                    <div class="info-item">
                        <div class="info-label">Código</div>
                        <div class="info-value">${usuario.codigoUsuario}</div>
                    </div>

                    <div class="info-item">
                        <div class="info-label">Nombre</div>
                        <div class="info-value">${usuario.nombreUsuario}</div>
                    </div>

                    <div class="info-item">
                        <div class="info-label">Apellido</div>
                        <div class="info-value">${usuario.apellidoUsuario}</div>
                    </div>

                    <div class="info-item">
                        <div class="info-label">Correo Electrónico</div>
                        <div class="info-value">${usuario.correoUsuario}</div>
                    </div>

                    <div class="info-item">
                        <div class="info-label">Teléfono</div>
                        <div class="info-value">${usuario.telefonoUsuario}</div>
                    </div>

                    <div class="info-item">
                        <div class="info-label">Dirección</div>
                        <div class="info-value"> ${usuario.direccionUsuario}</div>
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
                    <a id="editarUsuario" class="btn btn-generar" href="#">Editar Usuario</a>
                </div>
            </div>

            <!-- Botón Cerrar Sesión -->
            <div class="logout-section">
                <a id="CerrarSesion" class="btn btn-logout" href="Controlador?menu=Index">Cerrar Sesión</a>
            </div>
    </body> 
</html>