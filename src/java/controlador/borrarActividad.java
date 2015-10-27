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

@WebServlet("/borrarActividad")
public class borrarActividad extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public borrarActividad() { super(); }

    public String formateaHora(String fecha){
 		return fecha.substring(11,16);
 	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 baseDatos query = new baseDatos();
	        ArrayList<actividad> lista = new ArrayList<actividad>();
	        boolean respuesta;
	        
	        String idString= request.getParameter("id");
	        int id = Integer.parseInt(idString);
	      
	        request.setAttribute("lista", lista);

	        
		doGet(request, response);
	}
}