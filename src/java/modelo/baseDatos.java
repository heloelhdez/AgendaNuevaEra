package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.actividad;
public class baseDatos
{
	 public Connection getConnection()
	    {
	        Connection conn = null;
	        try
	        {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost:3306/" + "agenda";
	            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	            conn = DriverManager.getConnection(url, "ArtRey", "flux");
	        }
	        catch (java.lang.ClassNotFoundException e)
	        {
	            System.out.println("MySQL JDBC Driver not found ... " + e);
	        }
	        catch (java.sql.SQLException e)
	        {
	            System.out.println("Connection couldn't be established due " + e);
	        }
	        return conn;
	    }

	    public boolean insertaActividad(String nombre, int estado, int avance, String descripcion, String diaini, String horaini, String diafin, String horafin, int borrado, int idUsuario)
	    {
	        int inserta = 0;
	        String insercion = " insert into actividad (nombre, estado, avance, descripcion, dia_ini, hora_ini, dia_fin, hora_fin, borrado)" + " values ('" + nombre+ "', '" +estado+ "', '" +avance+ "', '" +descripcion+ "', '" +diaini+ "', '" +horaini+ "', '" +diafin+ "', '" +horafin+ "', '" +borrado+ "')";
	                       
                Connection conn = getConnection();
	        System.out.println("Valor de Conexion: " + conn);
            System.out.println("En base: hora: "+horaini);
                int insertaAct = 0;
                String ultimoId = null;
	        try
	        {
	            Statement stmt = (Statement) conn.createStatement();
                    insertaAct = stmt.executeUpdate(insercion);
                    
                    String masId = "SELECT MAX(id) AS id FROM actividad;";
                    PreparedStatement stmt2 = conn.prepareStatement(masId);
                    ResultSet rs = stmt2.executeQuery();
                    while (rs.next()) {
                        ultimoId = rs.getString("id");
                    }
                    System.out.println("utimoId: "+ultimoId );
                    String insertaAU = "insert into usuario_actividad(id_actividad, id_usuario)"+" values ('"+ultimoId+"', '"+idUsuario+"');";
	            inserta = stmt.executeUpdate(insertaAU);
                }
	        catch (java.sql.SQLException e) 
	        {
	            System.out.println("DB problem : " + e);
	        }
	        finally
	        {
	            try { conn.close(); }
	            catch (java.sql.SQLException e) 
	            {
	                System.out.println("DB problem : " + e);
	            }
	        }

	        if (inserta > 0)
	            return true;
	        else 
	        	return false;
	    }
            
            public boolean modificaActividad(String nombre, int estado, int avance, String descripcion, String diaini, String horaini, String diafin, String horafin, int borrado, int idUsuario, int id)
	    {
	        int inserta = 0;
	        String insercion = "update actividad SET nombre='" + nombre+ "', estado=" +estado+ ", avance=" +avance+ ", descripcion='" +descripcion+ "', dia_ini='" +diaini+ "', hora_ini='" +horaini+ "', dia_fin='" +diafin+ "', hora_fin='" +horafin+ "', borrado=" +borrado+ " where id="+id+"";
	                       
                Connection conn = getConnection();
	        System.out.println("Valor de Conexion: " + conn);
                int insertaAct = 0;
                String ultimoId = null;
	        try
	        {
	            Statement stmt = (Statement) conn.createStatement();
                    insertaAct = stmt.executeUpdate(insercion);
                    
                }
	        catch (java.sql.SQLException e) 
	        {
	            System.out.println("DB problem : " + e);
	        }
	        finally
	        {
	            try { conn.close(); }
	            catch (java.sql.SQLException e) 
	            {
	                System.out.println("DB problem : " + e);
	            }
	        }

	        if (inserta > 0)
	            return true;
	        else 
	        	return false;
	    }
            
	    public boolean borrarActividad(int id)
	    {
	        int inserta = 0;
	        String insercion = "update actividad set borrado=1 where id="+id+";";
	        Connection conn = getConnection();
	        System.out.println("Valor de Conexion: " + conn);
	        try
	        {
	            Statement stmt = (Statement) conn.createStatement();
	            inserta = stmt.executeUpdate(insercion);
	        }
	        catch (java.sql.SQLException e) 
	        {
	            System.out.println("DB problem : " + e);
	        }
	        finally
	        {
	            try { conn.close(); }
	            catch (java.sql.SQLException e) 
	            {
	                System.out.println("DB problem : " + e);
	            }
	        }
	        if (inserta > 0)
	            return true;
	        else 
	        	return false;
	    }
	    public boolean insertaUsuario(String nombre, String contra)
	    {
	        
              
                int inserta = 0;
                
                 String insercion = " insert into usuario (nombre, contrasena)" + " values ('" + nombre+ "', '" +contra+ "')";
	        Connection conn = getConnection();
	        System.out.println("Valor de Conexion: " + conn);

	        try
	        {
	            Statement stmt = (Statement) conn.createStatement();
	            inserta = stmt.executeUpdate(insercion);
	        }
	        catch (java.sql.SQLException e) 
	        {
	            System.out.println("DB problem : " + e);
	        }
	        finally
	        {
	            try { conn.close(); }
	            catch (java.sql.SQLException e) 
	            {
	                System.out.println("DB problem : " + e);
	            }
	        }

	        if (inserta > 0)
	            return true;
	        else 
	        	return false;
	    	
         }
         public boolean consultaDB(String usuario, String passwd) {
    	
    	int activo = 0;
        String cL1 = "select * from usuario where ";
        String cL2 = "nombre='" + usuario + "' and contrasena='" + passwd + "' and activo='" + activo + "';";
        String consultaLogin = cL1 + cL2;
        
        
        Connection conn = getConnection();
        System.out.println("Valor de Conexion: " + conn);
        int existe=0;
    	
        try
	        {
	           
        	PreparedStatement stmt = conn.prepareStatement(consultaLogin);
        	ResultSet rs = stmt.executeQuery();
        	
        	while (rs.next()) {
                String id = rs.getString("id");
                String us = rs.getString("nombre");
                String pass = rs.getString("contrasena");
                existe++;

	        }
                }
        catch (java.sql.SQLException e) 
        {
            System.out.println("DB problem : " + e);
        }
        finally
        {
            try { conn.close(); }
            catch (java.sql.SQLException e) 
            {
                System.out.println("DB problem : " + e);
            }
        }

	        if (existe > 0)
	            return true;
	        else 
	        	return false;

    }
         
     public boolean borraUsuario(String nombre, String contra)
	    {
	        
                
                int inserta = 0;
                int activar =1; 
                 String insercion = " UPDATE usuario SET activo='"+activar+"' WHERE nombre='" + nombre+ "'";
	        Connection conn = getConnection();
	        System.out.println("Valor de Conexion: " + conn);

	        try
	        {
	            Statement stmt = (Statement) conn.createStatement();
	            inserta = stmt.executeUpdate(insercion);
	        }
	        catch (java.sql.SQLException e) 
	        {
	            System.out.println("DB problem : " + e);
	        }
	        finally
	        {
	            try { conn.close(); }
	            catch (java.sql.SQLException e) 
	            {
	                System.out.println("DB problem : " + e);
	            }
	        }

	        if (inserta > 0)
	            return true;
	        else 
	        	return false;
	    	
         }
	  
     public String consultaID(String usuario, String passwd) {
    	String id="0";
    	int activo = 0;
        String cL1 = "select * from usuario where ";
        String cL2 = "nombre='" + usuario + "' and contrasena='" + passwd + "' and activo='" + activo + "';";
        String consultaLogin = cL1 + cL2;
        
        
        Connection conn = getConnection();
        System.out.println("Valor de Conexion: " + conn);
        int existe=0;
    	
        try
	        {
	           
        	PreparedStatement stmt = conn.prepareStatement(consultaLogin);
        	ResultSet rs = stmt.executeQuery();
        	
        	while (rs.next()) {
                 id = rs.getString("id");
                String us = rs.getString("nombre");
                String pass = rs.getString("contrasena");
                existe++;

	        }
                }
        catch (java.sql.SQLException e) 
        {
            System.out.println("DB problem : " + e);
        }
        finally
        {
            try { conn.close(); }
            catch (java.sql.SQLException e) 
            {
                System.out.println("DB problem : " + e);
            }
        }

	       return id;

    }
     public boolean comparteActUsuario(int idActividad, int idUsuario)
	    {
	        
              
                int inserta = 0;
                
                String insercion = " insert into usuario_actividad (id_actividad, id_usuario)" + " values ('" + idActividad+ "', '" +idUsuario+ "')";
	        Connection conn = getConnection();
	        System.out.println("Valor de Conexion: " + conn);

	        try
	        {
	            Statement stmt = (Statement) conn.createStatement();
	            inserta = stmt.executeUpdate(insercion);
	        }
	        catch (java.sql.SQLException e) 
	        {
	            System.out.println("DB problem : " + e);
	        }
	        finally
	        {
	            try { conn.close(); }
	            catch (java.sql.SQLException e) 
	            {
	                System.out.println("DB problem : " + e);
	            }
	        }

	        if (inserta > 0)
	            return true;
	        else 
	        	return false;
	    	
         }
     public String consultaIDACtividad(String usuario, String passwd) {
    	String id="0";
    	int activo = 0;
        String cL1 = "select * from usuario where ";
        String cL2 = "nombre='" + usuario + "' and contrasena='" + passwd + "' and activo='" + activo + "';";
        String consultaLogin = cL1 + cL2;
        
        
        Connection conn = getConnection();
        System.out.println("Valor de Conexion: " + conn);
        int existe=0;
    	
        try
	        {
	           
        	PreparedStatement stmt = conn.prepareStatement(consultaLogin);
        	ResultSet rs = stmt.executeQuery();
        	
        	while (rs.next()) {
                 id = rs.getString("id");
                String us = rs.getString("nombre");
                String pass = rs.getString("contrasena");
                existe++;

	        }
                }
        catch (java.sql.SQLException e) 
        {
            System.out.println("DB problem : " + e);
        }
        finally
        {
            try { conn.close(); }
            catch (java.sql.SQLException e) 
            {
                System.out.println("DB problem : " + e);
            }
        }

	       return id;

    }    
     public ArrayList<actividad> consultaActividades(String ident) {
	
         int id = Integer.parseInt(ident);
         
	ArrayList<actividad> actividades = new ArrayList<actividad>();
    
    String cL1 = "select * from actividad,usuario_actividad where borrado=0 and id=id_actividad and id_usuario='"+id+"'";
    String consultaLogin = cL1;
    
    
    Connection conn = getConnection();
    System.out.println("Valor de Conexion: " + conn);
	
    try {
    	
    	PreparedStatement stmt = conn.prepareStatement(consultaLogin);
    	ResultSet rs = stmt.executeQuery();
    	
    	while (rs.next()) {
            int idActividad = Integer.parseInt(rs.getString("id"));
            String nombre = rs.getString("nombre");
            int estado = Integer.parseInt(rs.getString("estado"));
            int avance = Integer.parseInt(rs.getString("avance"));
            String descripcion = rs.getString("descripcion");
            String dia_ini = rs.getString("dia_ini");
            String hora_ini = rs.getString("hora_ini");
            String dia_fin = rs.getString("dia_fin");
            String hora_fin = rs.getString("hora_fin");
            int borrado = Integer.parseInt(rs.getString("borrado"));
            
            actividades.add(new actividad(idActividad, estado, avance, borrado, descripcion, nombre, dia_ini, hora_ini, dia_fin, hora_fin));

            //lista.add(new Mensajes(id, nombre, pais));

        }

        
    } catch (java.sql.SQLException e) {
    	System.out.println("DB problem : " + e);
    } finally {
        try {
            conn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("DB problem : " + e);
        }
    }
	
    return actividades;


}
      public ArrayList<Usuario> consultaUsuarios() {
	
         
	ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    
    String cL1 = "select * from usuario where activo=0";
    String consultaLogin = cL1;
    
    
    Connection conn = getConnection();
    System.out.println("Valor de Conexion: " + conn);
	
    try {
    	
    	PreparedStatement stmt = conn.prepareStatement(consultaLogin);
    	ResultSet rs = stmt.executeQuery();
    	
    	while (rs.next()) {
            int id = Integer.parseInt(rs.getString("id"));
            String nombre = rs.getString("nombre");
            String contrasena = rs.getString("contrasena");
            usuarios.add(new Usuario(id, nombre, contrasena));
            

            //lista.add(new Mensajes(id, nombre, pais));

        }

        
    } catch (java.sql.SQLException e) {
    	System.out.println("DB problem : " + e);
    } finally {
        try {
            conn.close();
        } catch (java.sql.SQLException e) {
            System.out.println("DB problem : " + e);
        }
    }
	
    return usuarios;


}
      public String consultaIDUsuario(String usuario) {
    	String id="0";
    	int activo = 0;
        String cL1 = "select * from usuario where ";
        String cL2 = "nombre='" + usuario + "' and activo='" + activo + "';";
        String consultaLogin = cL1 + cL2;
        
        Connection conn = getConnection();
        System.out.println("Valor de Conexion: " + conn);
        int existe=0;
    	
        try
	        {
	           
        	PreparedStatement stmt = conn.prepareStatement(consultaLogin);
        	ResultSet rs = stmt.executeQuery();
        	
        	while (rs.next()) {
                 id = rs.getString("id");
                String us = rs.getString("nombre");
                existe++;

	        }
                }
        catch (java.sql.SQLException e) 
        {
            System.out.println("DB problem : " + e);
        }
        finally
        {
            try { conn.close(); }
            catch (java.sql.SQLException e) 
            {
                System.out.println("DB problem : " + e);
            }
        }

	       return id;

    }
            
}