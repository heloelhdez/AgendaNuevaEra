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
import modelo.Usuario;

@WebServlet("/nuevoUsuario")
public class nuevoUsuario extends HttpServlet 
{
    private static final long serialVersionUID = 1L;
       
    public nuevoUsuario() { super(); }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
         baseDatos query = new baseDatos();
            ArrayList<Usuario> lista = new ArrayList<Usuario>();
            boolean respuesta;
            boolean valida;
            String nombre = request.getParameter("usuario");
            String contrasena = request.getParameter("contrasenia");
            
            valida = query.consultaDB(nombre, contrasena);
            System.out.println(valida);
            if(!valida){
            //boolean hayUsuario = query.hayUsuario(nombre, contrasena);
            
            //if(hayUsuario){
            
              //Este le habla al metodo de abajo que tengo en modelo/baseDatos
                respuesta = query.insertaUsuario(nombre, contrasena);
    
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
                    rd = request.getRequestDispatcher("error.html");
                    rd.forward(request, response);
                }
            doGet(request, response);
        //}
            }
            else {
                RequestDispatcher rd;
        	rd = request.getRequestDispatcher("error.html");
        	rd.forward(request, response);
        }
    }
  
}       
