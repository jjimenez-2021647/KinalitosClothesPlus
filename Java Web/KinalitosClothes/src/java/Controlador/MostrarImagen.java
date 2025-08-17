package Controlador;

import com.kinalitosclothes.modelo.Usuarios;
import com.kinalitosclothes.modelo.UsuariosDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MostrarImagen")
public class MostrarImagen extends HttpServlet {

    private UsuariosDAO usuariosDAO = new UsuariosDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam == null) {
            return;
        }

        int idUsuario = Integer.parseInt(idParam);
        UsuariosDAO usuariosDao = new UsuariosDAO();
        Usuarios usuario = usuariosDao.imagenCodigo(idUsuario);

        if (usuario != null && usuario.getImagenUsuario() != null) {
            // Imagen del usuario
            response.setContentType("image/jpeg");
            response.getOutputStream().write(usuario.getImagenUsuario());
        } else {
            // Imagen por defecto si no existe
            InputStream defaultImg = getServletContext().getResourceAsStream("/Images/Cliente.jpeg");
            if (defaultImg != null) {
                response.setContentType("image/jpeg");
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = defaultImg.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
                defaultImg.close();
            }
        }
        response.getOutputStream().close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Si quieres que POST también sirva la imagen
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para mostrar imagen de usuario desde DB";
    }
}
