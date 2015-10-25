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

@WebServlet("/modificaActividad")
public class modificaActividad extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public modificaActividad() { super(); }
    
    public String formateaHora(String fecha){
 		return fecha.substring(11,16);
 	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 baseDatos query = new baseDatos();
	        ArrayList<actividad> lista = new ArrayList<actividad>();
	        boolean respuesta;
	        String nombre = request.getParameter("nombre");	        
	        //TODO: quitar esto    String estado = request.getParameter("estado");
	        String avance = request.getParameter("avance");
	        String descripcion = request.getParameter("descripcion");
	        String diaini = request.getParameter("diaInicio");
	        String horaini = formateaHora(request.getParameter("horaInicio"));
	        String diafina = request.getParameter("diaFin");
	        String horafina = formateaHora(request.getParameter("horaFin"));
                int id=Integer.parseInt(request.getParameter("id"));
	           System.out.println("Nombre: "+nombre);
	        System.out.println(horaini);
	        
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
	        int borr = 0;
	        
	        //TODO: quitar esto    int est =Integer.parseInt(estado);
	        int est=0;
	        int avan=0;
	        
	        switch(avance){
	        	case "No iniciada":
	        		avan=0;
	        		break;
	        	case "En curso":
	        		avan=1;
	        		break;
	        	case "Terminada":
	        		avan=2;
	        		break;
	        }     
	        	      
	        respuesta = query.modificaActividad(nombre, est, avan, descripcion, diaini, horaini, diafina, horafina, borr, idUsuario, id);

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
