package modelo;
import java.util.Date;
import java.util.ArrayList;

public class actividad {
	private int status;
	private int avance;
	private int borrado;
	private String descripcion;
	private String nombre;
	private String dia_ini;
	private String hora_ini;
        private String dia_fin;
        private String hora_fin;
        private int id;
        
        public int getID(){
            return id;
        }
        public void setID(int id){
            this.id=id;
        }
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAvance() {
		return avance;
	}
	public void setAvance(int avance) {
		this.avance = avance;
	}
	public int isBorrado() {
		return borrado;
	}
	public void setBorrado(int borrado) {
		this.borrado = borrado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHora_Ini() {
		return hora_ini;
	}
	public void setHora_Ini(String inicio) {
		this.hora_ini = inicio;
	}
        public String getHora_Fin() {
		return hora_fin;
	}
	public void setHora_Fin(String inicio) {
		this.hora_fin = inicio;
	}
	public String getDia_Ini() {
		return dia_ini;
	}
	public void setDia_Ini(String fin) {
		this.dia_ini = fin;
	}
        public String getDia_Fin() {
		return dia_fin;
	}
	public void setDia_Fin(String fin) {
		this.dia_fin = fin;
	}
	
	public actividad(int id, int status, int avance, int borrado, String descripcion, String nombre, String dia_ini, String hora_ini, String dia_fin, String hora_fin){
		this.status = status;
		this.avance = avance;
		this.borrado = borrado;
		this.descripcion = descripcion;
		this.nombre = nombre;
		this.dia_ini = dia_ini;
                this.hora_ini= hora_ini;
                this.dia_fin = dia_fin;
                this.hora_fin = hora_fin;
	}
	
}
