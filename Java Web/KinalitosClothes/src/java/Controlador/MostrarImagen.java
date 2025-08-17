package Controlador;

import com.kinalitosclothes.modelo.Usuarios;
import com.kinalitosclothes.modelo.UsuariosDAO;
import java.io.IOException;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de usuario no especificado");
            return;
        }

        try {
            int idUsuario = Integer.parseInt(idStr);
            Usuarios usuario = usuariosDAO.imagenCodigo(idUsuario);

            if (usuario == null || usuario.getImagenUsuario() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
                return;
            }

            // Configurar el tipo de contenido (puede ser "image/jpeg" o "image/png" según tu BD)
            response.setContentType("image/jpeg");
            response.setContentLength(usuario.getImagenUsuario().length);

            try (OutputStream os = response.getOutputStream()) {
                os.write(usuario.getImagenUsuario());
                os.flush();
            }

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener la imagen");
        }
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
