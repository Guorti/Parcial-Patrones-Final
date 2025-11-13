
/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Flyweight
 * Tipo de Clase: Java
 */

package implementacion;

/**
 *
 * @author Fabrizio Bolaño
 */
public class Cancion {
    private Long id;
    private String NombreCancion;
    private byte[] Cancion = new byte[1000000];
    
     public Cancion(Long id, String NombreCancion) {
        this.id = id;
        this.NombreCancion = NombreCancion;
    }

    public Cancion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCancion() {
        return NombreCancion;
    }

    public void setNombreCancion(String NombreCancion) {
        this.NombreCancion = NombreCancion;
    }

    @Override
    public String toString() {
        return "Canciòn{" + "id=" + id + ", Tema=" + NombreCancion + '}';
    }
    
    
}
