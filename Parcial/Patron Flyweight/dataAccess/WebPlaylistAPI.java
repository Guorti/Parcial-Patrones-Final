package dataAccess;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WebPlaylistAPI {

    private static final String API_URL = "https://yehcxcdgwigufnhrvafk.supabase.co/rest/v1/canciones";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InllaGN4Y2Rnd2lndWZuaHJ2YWZrIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc2MzA2ODYxMiwiZXhwIjoyMDc4NjQ0NjEyfQ.6m12o6KSeDu4cPzwIVKydHh9tfdScUBljRblNe9Q7iY";

    public void insertarPlaylist(String nombrePlaylist) {
        try {
            String jsonBody = "{ \"nombre\": \"" + nombrePlaylist + "\" }";
            
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("apikey", API_KEY)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .header("Prefer", "return=minimal")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 201) {
                System.out.println("Playlist almacenada en BD: " + nombrePlaylist);
            } else {
                System.out.println("Error al insertar playlist: " + response.statusCode());
            }
            
        } catch (IOException | InterruptedException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
