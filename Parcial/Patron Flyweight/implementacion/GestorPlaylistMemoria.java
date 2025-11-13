package implementacion;

import dataAccess.WebPlaylistAPI;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GestorPlaylistMemoria {
    
    private static final int MAX_PLAYLISTS_EN_MEMORIA = 1000000; // Límite de playlists en memoria
    private WebPlaylistAPI webAPI;
    
    public GestorPlaylistMemoria() {
        this.webAPI = new WebPlaylistAPI();
    }
    
    public void gestionarMemoria(List<ListaReproduccion> todasLasListas) {
        System.out.println("\n=== INICIANDO GESTIÓN DE MEMORIA ===");
        System.out.println("Total de playlists: " + todasLasListas.size());
        
        // Simular uso aleatorio de playlists
        simularUsoDePlaylists(todasLasListas);
        
        // Ordenar por uso (de menor a mayor)
        Collections.sort(todasLasListas, new Comparator<ListaReproduccion>() {
            @Override
            public int compare(ListaReproduccion p1, ListaReproduccion p2) {
                return Integer.compare(p1.getVecesUtilizada(), p2.getVecesUtilizada());
            }
        });
        
        System.out.println("\nPlaylists ordenadas por uso (menor a mayor)");
        
        // Si excede el límite, enviar las menos usadas a BD
        if (todasLasListas.size() > MAX_PLAYLISTS_EN_MEMORIA) {
            int cantidadAEnviarBD = todasLasListas.size() - MAX_PLAYLISTS_EN_MEMORIA;
            System.out.println("\nEnviando " + cantidadAEnviarBD + " playlists menos usadas a la BD...");
            
            for (int i = 0; i < cantidadAEnviarBD; i++) {
                ListaReproduccion playlist = todasLasListas.get(i);
                webAPI.insertarPlaylist(playlist.getNombreLista());
                
                // Mostrar progreso cada 10%
                if (i % (cantidadAEnviarBD / 10) == 0) {
                    System.out.println("Progreso: " + (i * 100 / cantidadAEnviarBD) + "%");
                }
            }
            
            // Remover de memoria las enviadas a BD
            todasLasListas.subList(0, cantidadAEnviarBD).clear();
            System.out.println("\nPlaylists enviadas a BD exitosamente");
        }
        
        System.out.println("\nPlaylists en memoria: " + todasLasListas.size());
        System.out.println("=== GESTIÓN DE MEMORIA COMPLETADA ===\n");
    }
    
    private void simularUsoDePlaylists(List<ListaReproduccion> listas) {
        System.out.println("\nSimulando uso de playlists...");
        java.util.Random random = new java.util.Random();
        
        // Simular que algunas playlists se usan más que otras
        for (int i = 0; i < listas.size() / 2; i++) {
            int index = random.nextInt(listas.size());
            int usos = random.nextInt(10) + 1; // Entre 1 y 10 usos
            for (int j = 0; j < usos; j++) {
                listas.get(index).incrementarUso();
            }
        }
        
        System.out.println("Simulación de uso completada");
    }
    
    public void mostrarEstadisticas(List<ListaReproduccion> listas) {
        System.out.println("\n=== ESTADÍSTICAS DE USO ===");
        
        // Calcular estadísticas
        int totalUsos = 0;
        int maxUsos = 0;
        int minUsos = Integer.MAX_VALUE;
        
        for (ListaReproduccion playlist : listas) {
            int usos = playlist.getVecesUtilizada();
            totalUsos += usos;
            if (usos > maxUsos) maxUsos = usos;
            if (usos < minUsos) minUsos = usos;
        }
        
        double promedioUsos = listas.isEmpty() ? 0 : (double) totalUsos / listas.size();
        
        System.out.println("Playlists en memoria: " + listas.size());
        System.out.println("Usos totales: " + totalUsos);
        System.out.println("Promedio de usos: " + String.format("%.2f", promedioUsos));
        System.out.println("Máximo de usos: " + maxUsos);
        System.out.println("Mínimo de usos: " + minUsos);
        
        // Mostrar top 5 más usadas
        System.out.println("\nTop 5 playlists más utilizadas:");
        Collections.sort(listas, new Comparator<ListaReproduccion>() {
            @Override
            public int compare(ListaReproduccion p1, ListaReproduccion p2) {
                return Integer.compare(p2.getVecesUtilizada(), p1.getVecesUtilizada());
            }
        });
        
        for (int i = 0; i < Math.min(5, listas.size()); i++) {
            ListaReproduccion p = listas.get(i);
            System.out.println((i + 1) + ". " + p.getNombreLista() + " - Usos: " + p.getVecesUtilizada());
        }
        
        System.out.println("=========================\n");
    }
}
