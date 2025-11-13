
/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Flyweight
 * Tipo de Clase: Java
 */
package implementacion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fabrizio Bolaño
 */
public class ListaReproduccion {
    private String NombreLista;
    private List<Cancion> Canciones = new ArrayList<>();
    private int vecesUtilizada = 0;
    
    public ListaReproduccion(String NombreLista) {
        this.NombreLista = NombreLista;
    }

    public String getNombreLista() {
        return NombreLista;
    }

    public void setNombreLista(String NombreLista) {
        this.NombreLista = NombreLista;
    }

    public List<Cancion> getCanciones() {
        return Canciones;
    }

    public void setCanciones(List<Cancion> Canciones) {
        this.Canciones = Canciones;
    }
    
    public void addCancion(String NombreCancion) {
        Canciones.add(FabricaCanciones.CrearItem(NombreCancion));
    }
    
    public void ImprimirLista() {
        String out = "\nPlayList > " + NombreLista;
        for (Cancion playItem : Canciones) {
            out += "\n\t" + playItem.toString();
        }
        System.out.println(out);
    }
    
    public void incrementarUso() {
        this.vecesUtilizada++;
    }
    
    public int getVecesUtilizada() {
        return vecesUtilizada;
    }
    
    public String getCancionesComoTexto() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Canciones.size(); i++) {
            sb.append(Canciones.get(i).getNombreCancion());
            if (i < Canciones.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
    
    
}


