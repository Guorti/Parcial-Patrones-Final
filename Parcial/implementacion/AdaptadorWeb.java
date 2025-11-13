package Parcial.implementacion;

import Parcial.dataAccess.web.WebDatosAPI;
import Parcial.dataAccess.web.WebDatosEmpleadoRespuesta;
import Parcial.dataAccess.web.WebSolicitudDatosEmpleado;

public class AdaptadorWeb implements InterfaceDataAccessAdaptador {

    @Override
    public DataEmpleadoRespuesta EnvioPeticionDatosEmpleado(DatosEmpleadoSolicitud request) {
        WebSolicitudDatosEmpleado WSDErequest = new WebSolicitudDatosEmpleado();
        WSDErequest.setCodigo(request.getCodigo());

        WebDatosAPI api = new WebDatosAPI();
        WebDatosEmpleadoRespuesta WDERespuesta = api.sendDatosEmpleadoRequest(WSDErequest);

        DataEmpleadoRespuesta response = new DataEmpleadoRespuesta();
        response.setNombre(WDERespuesta.getNombre());
        response.setSalario(WDERespuesta.getSalario());

        return response;
    }
    
}
