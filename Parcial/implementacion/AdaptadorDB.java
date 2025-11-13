package Parcial.implementacion;

import Parcial.dataAccess.db.DBDatosAPI;
import Parcial.dataAccess.db.DBDatosEmpleadoRespuesta;
import Parcial.dataAccess.db.DBSolicitudDatosEmpleado;


public class AdaptadorDB implements InterfaceDataAccessAdaptador{

    @Override
    public DataEmpleadoRespuesta EnvioPeticionDatosEmpleado(DatosEmpleadoSolicitud request) {

        DBSolicitudDatosEmpleado DBSDErequest = new DBSolicitudDatosEmpleado();
        
        DBSDErequest.setCodigo(request.getCodigo());

        DBDatosAPI api = new DBDatosAPI();

        DBDatosEmpleadoRespuesta DBDERespuesta = api.sendDatosEmpleadoRequest(DBSDErequest);

        DataEmpleadoRespuesta response = new DataEmpleadoRespuesta();

        response.setNombre(DBDERespuesta.getNombre());

        response.setSalario(DBDERespuesta.getSalario());

        return response;
        
    }
    
}
