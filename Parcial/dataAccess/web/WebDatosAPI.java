package Parcial.dataAccess.web;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WebDatosAPI {

    private static final String API_URL = "https://hsyvdqeudhdhgfgxhtqg.supabase.co/rest/v1/empleados";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImhzeXZkcWV1ZGhkaGdmZ3hodHFnIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTc2MzA0NDgzNSwiZXhwIjoyMDc4NjIwODM1fQ.7bO3oLAIox9DXtQ7TQg_7VWu3iZ1Rh7taJnfG3Rywiw";


    public WebDatosEmpleadoRespuesta sendDatosEmpleadoRequest(WebSolicitudDatosEmpleado request){
        WebDatosEmpleadoRespuesta response = new WebDatosEmpleadoRespuesta();

        try {
            Empleado empleado = obtenerEmpleadoPorId(request.getCodigo());
            response.setNombre(empleado.getNombre());
            response.setSalario(empleado.getSalario());
        } catch (IOException | InterruptedException e) {

            e.printStackTrace();
        }

        
        return response;
    }

    public static Empleado obtenerEmpleadoPorId(String id) throws IOException, InterruptedException {

    String url = API_URL + "?id=eq." + id + "&select=*";
    
    HttpClient client = HttpClient.newHttpClient();
    
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("apikey", API_KEY)
            .header("Authorization", "Bearer " + API_KEY)
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .GET()
            .build();
    
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
    
    return parsearEmpleado(response.body());
}

    private static Empleado parsearEmpleado(String json) {
        Empleado empleado = new Empleado();
        
        // Remover corchetes y llaves
        json = json.trim();
        if (json.startsWith("[")) {
            json = json.substring(1, json.length() - 1); // Remover [ ]
        }
        json = json.trim();
        if (json.startsWith("{")) {
            json = json.substring(1, json.length() - 1); // Remover { }
        }
        
        // Dividir por comas
        String[] campos = json.split(",");
        
        for (String campo : campos) {
            String[] partes = campo.split(":");
            String clave = partes[0].trim().replace("\"", "");
            String valor = partes[1].trim().replace("\"", "");
            
            if (clave.equals("nombre")) {
                empleado.setNombre(valor);
            } else if (clave.equals("salario")) {
                empleado.setSalario(Double.parseDouble(valor));
            }
        }
        
        return empleado;
    }
    
}
