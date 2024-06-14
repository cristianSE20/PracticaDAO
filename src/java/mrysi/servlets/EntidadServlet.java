package mrysi.servlets;

import mrysi.beans.Entidad;
import mrysi.beans.EntidadDAOImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/EntidadServlet")
public class EntidadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private EntidadDAOImp entidadDAO;

    public void init() {
        entidadDAO = new EntidadDAOImp();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEntidad(request, response);
                    break;
                case "delete":
                    deleteEntidad(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateEntidad(request, response);
                    break;
                default:
                    listEntidad(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void listEntidad(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Entidad> listEntidad = entidadDAO.readAll();
        request.setAttribute("listEntidad", listEntidad);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Entidad existingEntidad = entidadDAO.read(id);
        request.setAttribute("entidad", existingEntidad);
        request.getRequestDispatcher("form.jsp").forward(request, response);
    }

    private void insertEntidad(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombre = request.getParameter("nombreEntidad");
        Entidad newEntidad = new Entidad(0, nombre);  // 0 porque la BD debe generar el idEntidad
        entidadDAO.insert(newEntidad);
        response.sendRedirect("EntidadServlet?action=list");
    }

    private void updateEntidad(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("idEntidad"));
        String nombre = request.getParameter("nombreEntidad");

        Entidad entidad = new Entidad(id, nombre);
        entidadDAO.update(entidad);
        response.sendRedirect("EntidadServlet?action=list");
    }

    private void deleteEntidad(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        entidadDAO.delete(id);
        response.sendRedirect("EntidadServlet?action=list");
    }
}