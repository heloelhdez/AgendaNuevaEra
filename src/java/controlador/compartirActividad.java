package controlador;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.baseDatos;
import modelo.actividad;

@WebServlet("/compartirActividad")
public class compartirActividad extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public compartirActividad() { super(); }
    
    public String formateaHora(String fecha){
 		return fecha.substring(11,16);
 	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		baseDatos query = new baseDatos();
	        ArrayList<actividad> lista = new ArrayList<actividad>();
	        boolean respuesta;
	        int idActividad = Integer.parseInt(request.getParameter("id"));	        
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
	        int borr = 0;
	        
	           
	        	      
	        respuesta = query.comparteActUsuario(idActividad, idUsuario);

	        request.setAttribute("lista", lista);

	        if (respuesta)
	        {
	            RequestDispatcher rd;
	            rd = request.getRequestDispatcher("calendario.jsp");
	            rd.forward(request, response);
	        } 
	        else
	        {
	            RequestDispatcher rd;
	            rd = request.getRequestDispatcher("error.html");
	            rd.forward(request, response);
	        }
		doGet(request, response);
	}
}