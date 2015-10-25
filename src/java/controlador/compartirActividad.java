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
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{   
                System.out.println("joseramon");
		baseDatos query = new baseDatos();
	        ArrayList<actividad> lista = new ArrayList<>();
	        boolean respuesta;
                int idUsuario;
	        int idActividad = Integer.parseInt(request.getParameter("id"));	        
                String usuario = request.getParameter("nombreUsuario");
	        int borr = 0;
	        System.out.println("joseramon");
	        idUsuario = Integer.parseInt(query.consultaIDUsuario(usuario));
	        System.out.println("Jose Ramon quiere: "+idUsuario);
	        respuesta = query.comparteActUsuario(idActividad, idUsuario);

	        request.setAttribute("lista", lista);

	        if (respuesta)
	        {
	            RequestDispatcher rd;
	            rd = request.getRequestDispatcher("index.html");
	            rd.forward(request, response);
	        } 
	        else
	        {
	            RequestDispatcher rd;
	            rd = request.getRequestDispatcher("errorCompartiendo.html");
	            rd.forward(request, response);
	        }
		doGet(request, response);
	}
}