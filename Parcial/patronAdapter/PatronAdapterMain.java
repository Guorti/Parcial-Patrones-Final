package Parcial.patronAdapter;

import Parcial.implementacion.AdaptadorDB;
import Parcial.implementacion.AdaptadorWeb;
import Parcial.implementacion.DataEmpleadoRespuesta;
import Parcial.implementacion.DatosEmpleadoSolicitud;
import Parcial.implementacion.InterfaceDataAccessAdaptador;

public class PatronAdapterMain {
    

    public static void main (String[] args) {

        DatosEmpleadoSolicitud request = new DatosEmpleadoSolicitud();
        request.setCodigo("1");

        InterfaceDataAccessAdaptador SolicitudWeb = new AdaptadorWeb();
        DataEmpleadoRespuesta DEresponse = SolicitudWeb.EnvioPeticionDatosEmpleado(request);
        System.out.println("Nombre: " + DEresponse.getNombre() + ", Salario: " + DEresponse.salario);


        request.setCodigo("2");

        InterfaceDataAccessAdaptador SolicitudDB = new AdaptadorDB();
        DEresponse = SolicitudDB.EnvioPeticionDatosEmpleado(request);
        System.out.println("Nombre: " + DEresponse.getNombre() + ", Salario: " + DEresponse.salario);


    }
}
