package Parcial.dataAccess.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBDatosAPI {

    private static final String DB_URL = "jdbc:postgresql://db.hsyvdqeudhdhgfgxhtqg.supabase.co:5432/postgres";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "parcial"; // Contrasenha definida

    public DBDatosEmpleadoRespuesta sendDatosEmpleadoRequest(DBSolicitudDatosEmpleado request){
        DBDatosEmpleadoRespuesta response = new DBDatosEmpleadoRespuesta();

        try {
            Empleado empleado = obtenerEmpleadoPorId(request.getCodigo());
            response.setNombre(empleado.getNombre());
            response.setSalario(empleado.getSalario());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return response;
    }

    private Empleado obtenerEmpleadoPorId(String id) throws SQLException {
        Empleado empleado = new Empleado();
        
        String query = "SELECT nombre, salario FROM empleados WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, Integer.parseInt(id));
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setSalario(rs.getDouble("salario"));
                }
            }
        }
        
        return empleado;
    }
}