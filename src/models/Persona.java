
package models;

import java.io.Serializable;


public class Persona implements Serializable {
    
    
    
    private String nombre;
    private String apellido;
    private int dni;
    private Genero genero;

    public Persona(String nombre, String apellido, int dni, Genero genero) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    
    
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", genero=" + genero + '}';
    }

    
    public String toCSV(){
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.nombre).append(";");
        sb.append(this.apellido).append(";");
        sb.append(this.dni).append(";");
        sb.append(this.genero);
        
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.dni;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        return this.dni == other.dni;
    }
    
    
}
