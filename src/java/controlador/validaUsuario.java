/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;
import modelo.baseDatos;
import modelo.actividad;

/**
 *
 * @author heloel
 */
@WebServlet(name = "validaUsuario", urlPatterns = {"/validaUsuario"})
public class validaUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet validaUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet validaUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	baseDatos query = new baseDatos();
            ArrayList<actividad> actividades = new ArrayList<actividad>();
            boolean respuesta;
            boolean valida;
            String nombre = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasenia");
            
            valida = query.consultaDB(nombre, contrasena);
            
            System.out.println(valida);
            request.setAttribute("user", nombre);
            if(valida){
                String id = query.consultaID(nombre, contrasena);
                request.setAttribute("id", id);
                actividades = query.consultaActividades(id);
                request.setAttribute("actividades", actividades);
                    RequestDispatcher rd;
                    rd = request.getRequestDispatcher("index.jsp");
                    rd.forward(request, response);
            }
            else {
                RequestDispatcher rd;
        	rd = request.getRequestDispatcher("error.html");
        	rd.forward(request, response);
        }
            doGet(request, response);
	
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
