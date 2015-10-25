package modelo;

public class Usuario {
        int id;
	private String nombre;
	private String contraseña;
        
        public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contraseña;
	}
	public void setContraseña(String contrasena) {
		this.contraseña = contraseña;
	}
	
	public Usuario(int id,String nombre, String contrasena){
		this.id=id;
                this.nombre = nombre;
		this.contraseña = contrasena;
	}
}