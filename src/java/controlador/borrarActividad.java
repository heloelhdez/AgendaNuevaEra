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
			System.out.println("Llegamos a borrar actividad");
		 	baseDatos query = new baseDatos();
	        ArrayList<actividad> lista = new ArrayList<actividad>();
	        boolean respuesta;
	        
	        String idString= request.getParameter("id");
	        int id = Integer.parseInt(idString);
	      
	        respuesta = query.borrarActividad(id);
	        
	        request.setAttribute("lista", lista);
	        
	        if (respuesta)
	        {
	            RequestDispatcher rd;
	            rd = request.getRequestDispatcher("index.jsp");
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