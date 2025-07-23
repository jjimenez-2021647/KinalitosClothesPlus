<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>K.C - Inicio</title>
    <link rel="icon" type="image/x-icon" href="../Images/Logo_K.C.png">
    <link rel="stylesheet" href="../Styles/menu.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@900&display=swap" rel="stylesheet">
</head>

<body>
    <!-- SECTOR 1: NAVBAR Y HERO -->
    <nav class="navbar">
        <div class="nav-content">
            <div class="logo">K<span>C</span></div>
            <ul class="menu">
                <li><a href="#">INICIO</a></li>
                <li><a href="conocenos.jsp">NOSOTROS</a></li>
                <li><a href="vistaproducto.jsp">TIENDA</a></li>
                <li><a href="vistaadmin.jsp">ADMINISTRACION</a></li>
                <li><a href="mispedidos.jsp">MIS PEDIDOS</a></li>
            </ul>
            <div class="iconos">
                <a href="#"><i class="fa fa-search"></i></a>
                <a href="#"><i class="fa fa-shopping-cart"></i></a>
                <a href="index.jsp"><i class="fa fa-user"></i></a>
            </div>
        </div>
    </nav>
    <section class="hero">
        <div class="hero-content">
            <span class="descuento">hasta 30% de descuento</span>
            <h1>LA MEJOR ROPA URBANA</h1>
            <h2>PARA TU ESTILO ÃNICO</h2>
            <a href="#" class="btn-coleccion">VER COLECCIÃN</a>
        </div>
    </section>
    <!-- SECTOR 2: OFERTAS DESTACADAS -->
    <section class="ofertas-destacadas">
        <div class="oferta">
            <img src="../Images/ropa1.jpg" alt="Oferta Laptop">
            <div class="oferta-info">
                <h3>OFERTA DEL DÃA</h3>
                <p>Hasta 45% de descuento en camicetas</p>
                <a href="#" class="btn-oferta">COMPRAR AHORA</a>
            </div>
        </div>
        <div class="oferta">
            <img src="../Images/ropa2.jpg" alt="Oferta Reloj">
            <div class="oferta-info">
                <h3>EL MÃS VENDIDO</h3>
                <p>Precio desde $100</p>
                <a href="#" class="btn-oferta">COMPRAR AHORA</a>
            </div>
        </div>
    </section>
    <!-- SECTOR 3: NUEVOS PRODUCTOS -->
    <section class="nuevos-productos-titulo">
        <h2>NUEVOS PRODUCTOS</h2>
        <a href="#" class="btn-ver-todo">VER TODO</a>
    </section>
    <section class="acciones-imagenes-con-flechas">
        <button class="carrusel-flecha flecha-izq" aria-label="Anterior">
            <div class="button flecha-personalizada">
                <div class="line one">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line two">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line three">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line four">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line five">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line six">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line seven">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
            </div>
        </button>
        <section class="acciones-imagenes">
            <div class="accion-imagen">
                <img src="../Images/ropap3.jpg" alt="Producto 1">
                <div class="iconos-acciones">
                    <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                    <button title="Guardar"><i class="fa fa-heart"></i></button>
                    <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
                </div>
            </div>
            <div class="accion-imagen">
                <img src="../Images/ropap4.jpg" alt="Producto 2">
                <div class="iconos-acciones">
                    <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                    <button title="Guardar"><i class="fa fa-heart"></i></button>
                    <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
                </div>
            </div>
            <div class="accion-imagen">
                <img src="../Images/ropap5.jpg" alt="Producto 3">
                <div class="iconos-acciones">
                    <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                    <button title="Guardar"><i class="fa fa-heart"></i></button>
                    <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
                </div>
            </div>
            <div class="accion-imagen">
                <img src="../Images/ropap6.jpg" alt="Producto 4">
                <div class="iconos-acciones">
                    <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                    <button title="Guardar"><i class="fa fa-heart"></i></button>
                    <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
                </div>
            </div>
        </section>
        <button class="carrusel-flecha flecha-der" aria-label="Siguiente">
            <div class="button flecha-personalizada" style="transform: rotate(180deg)">
                <div class="line one">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line two">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line three">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line four">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line five">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line six">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
                <div class="line seven">
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                    <div class="round"></div>
                </div>
            </div>
        </button>
    </section>
    <!-- SECTOR 4: RESEÃAS Y BANNER -->
    <section class="resenas-clientes">
        <h2 class="titulo-resenas">RESEÃAS DE NUESTROS CLIENTES</h2>
        <div class="resenas-lista">
            <div class="resena">
                <p>"Â¡La calidad de la ropa es excelente y el envÃ­o fue muy rÃ¡pido! Sin duda volverÃ© a comprar."</p>
                <span class="cliente">ANA PÃREZ</span>
            </div>
            <div class="resena">
                <p>"Me encantÃ³ la variedad de estilos y los precios accesibles. Â¡Muy recomendable!"</p>
                <span class="cliente">CARLOS RAMÃREZ</span>
            </div>
            <div class="resena">
                <p>"El servicio al cliente fue muy atento y la ropa llegÃ³ en perfecto estado."</p>
                <span class="cliente">MARÃA LÃPEZ</span>
            </div>
        </div>
        <div class="resenas-puntos">
            <span class="punto activo"></span>
            <span class="punto"></span>
            <span class="punto"></span>
        </div>
    </section>
    <section class="banner-imagen">
        <img src="../Images/fondoh.jpg" alt="Banner Tienda" class="banner-img">
    </section>
    <!-- SECTOR 5: PRODUCTOS DESTACADOS Y ARTÃCULOS DE MODA -->
<section class="nuevos-productos-titulo">
    <h2>PRODUCTOS DESTACADOS</h2>
    <a href="#" class="btn-ver-todo">VER TODO</a>
</section>
<section class="acciones-imagenes-con-flechas">
    <button class="carrusel-flecha flecha-izq" aria-label="Anterior">
        <div class="button flecha-personalizada">
            <div class="line one"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line two"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line three"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line four"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line five"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line six"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line seven"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
        </div>
    </button>
    <section class="acciones-imagenes">
        <div class="accion-imagen">
            <img src="../Images/ropasec2.jpg" alt="Producto Destacado 1">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="accion-imagen">
            <img src="../Images/ropasec2.3.jpg" alt="Producto Destacado 2">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="accion-imagen">
            <img src="../Images/ropasec2.4.jpg" alt="Producto Destacado 3">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="accion-imagen">
            <img src="../Images/ropasec2.5.jpg" alt="Producto Destacado 4">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
    </section>
    <button class="carrusel-flecha flecha-der" aria-label="Siguiente">
        <div class="button flecha-personalizada" style="transform: rotate(180deg)">
            <div class="line one"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line two"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line three"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line four"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line five"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line six"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line seven"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
        </div>
    </button>
</section>
<section class="nuevos-productos-titulo">
    <h2>ARTÃCULOS DE MODA</h2>
    <a href="#" class="btn-ver-todo">VER TODO</a>
</section>
<section class="acciones-imagenes-con-flechas">
    <button class="carrusel-flecha flecha-izq" aria-label="Anterior">
        <div class="button flecha-personalizada">
            <div class="line one"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line two"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line three"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line four"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line five"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line six"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line seven"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
        </div>
    </button>
    <section class="acciones-imagenes">
        <div class="accion-imagen">
            <img src="../Images/ropasec3.jpg" alt="Producto Destacado 1">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="accion-imagen">
            <img src="../Images/ropasec3.2.jpg" alt="Producto Destacado 2">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="accion-imagen">
            <img src="../Images/ropasec3.4.jpg" alt="Producto Destacado 3">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
        <div class="accion-imagen">
            <img src="../Images/ropasec3.3.jpg" alt="Producto Destacado 4">
            <div class="iconos-acciones">
                <button title="Agregar al carrito"><i class="fa fa-shopping-cart"></i></button>
                <button title="Guardar"><i class="fa fa-heart"></i></button>
                <button title="MÃ¡s informaciÃ³n"><i class="fa fa-times"></i></button>
            </div>
        </div>
    </section>
    <button class="carrusel-flecha flecha-der" aria-label="Siguiente">
        <div class="button flecha-personalizada" style="transform: rotate(180deg)">
            <div class="line one"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line two"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line three"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line four"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line five"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line six"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
            <div class="line seven"><div class="round"></div><div class="round"></div><div class="round"></div><div class="round"></div></div>
        </div>
    </button>
</section>
<!-- SecciÃ³n de suscripciÃ³n y pie de pÃ¡gina debajo de las imÃ¡genes/artÃ­culos -->
<section class="suscripcion-seccion">
    <h2 class="titulo-suscripcion">SUSCRÃBETE</h2>
    <form class="form-suscripcion">
        <input type="email" placeholder="Escribe tu correo electrÃ³nico aquÃ­..." required>
        <button type="submit">SUSCRIBIRME</button>
    </form>
</section>

<footer class="footer">
    <div class="footer-contenido">
        <div class="footer-logo">
            <span>KinalitosClothes</span>
        </div>
        <div class="footer-links">
            <ul>
                <li><strong>ACERCA DE</strong></li>
                <li><a href="#">Nosotros</a></li>
                <li><a href="#">Tienda</a></li>
                <li><a href="#">Ofertas</a></li>
                <li><a href="#">ArtÃ­culos</a></li>
            </ul>
            <ul>
                <li><strong>PEDIDOS</strong></li>
                <li><a href="#">Rastrear pedido</a></li>
                <li><a href="#">PolÃ­tica de devoluciones</a></li>
                <li><a href="#">EnvÃ­os</a></li>
                <li><a href="#">Contacto</a></li>
            </ul>
            <ul>
                <li><strong>REDES SOCIALES</strong></li>
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Instagram</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">YouTube</a></li>
                <li><a href="#">Pinterest</a></li>
            </ul>
            <ul>
                <li><strong>Â¿DUDAS O SOPORTE?</strong></li>
                <li><a href="mailto:soporte@KinalitosClothes.com">soporte@KinalitosClothes.com</a></li>
                <li>Â¿Necesitas ayuda? LlÃ¡manos:</li>
                <li>+52 55 1234 5678</li>
            </ul>
        </div>
    </div>
    <div class="footer-copy">
        Â© Todos los derechos reservados por KinalitosClothes
    </div>
</footer>
</body>

</html>