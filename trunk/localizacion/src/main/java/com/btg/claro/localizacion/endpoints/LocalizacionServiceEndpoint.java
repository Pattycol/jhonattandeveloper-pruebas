package com.btg.claro.localizacion.endpoints;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btg.claro.localizacion.endpoints.respuestas.LocalizacionRespuesta;
import com.btg.claro.localizacion.endpoints.respuestas.LoginRespuesta;
import com.btg.claro.localizacion.entidades.Sesion;
import com.btg.claro.localizacion.services.LocalizacionService;
import com.btg.claro.localizacion.services.LoginService;
import com.btg.claro.localizacion.util.Constantes;
import com.btg.claro.localizacion.util.Ejecucion;
import com.btg.claro.localizacion.util.ListEjecuciones;
import com.btg.claro.localizacion.util.Ubicacion;

@Service("LocalizacionWebService")
@WebService(name = "LocalizacionService")
public class LocalizacionServiceEndpoint {

    private static Logger log = LoggerFactory.getLogger(LocalizacionServiceEndpoint.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private LocalizacionService localizacionService;

    /*
     * @Resource private WebServiceContext wsContext;
     */

    @WebMethod
    public LoginRespuesta login(@WebParam(name = "nombre") String nombre,
            @WebParam(name = "password") String password) {
        /*
         * MessageContext context=wsContext.getMessageContext();
         * Map<String,Object> requestHeaders=(Map<String,Object>)
         * context.get(MessageContext.HTTP_REQUEST_HEADERS); for(String key :
         * requestHeaders.keySet()){ System.out.println(key + "=" +
         * requestHeaders.get(key)); } HttpServletRequest
         * request=(HttpServletRequest)
         * context.get(MessageContext.SERVLET_REQUEST); // String
         * ip=request.getRemoteAddr();
         */

        LoginRespuesta respuesta = new LoginRespuesta();
        String resultado = loginService.login(nombre, password, "");
        if (resultado == null) {
            respuesta.setError("Su peticion es incorrecta");
        } else if (resultado.equals(Constantes.LOGIN_INCORRECTO)) {
            respuesta.setError("La aplicacion o la contraseña son incorrectos");
        } else if (resultado.equals(Constantes.IP_INCORRECTA)) {
            respuesta
                    .setError("Esta aplicacion no se puede conectar desde la direccion desde la que esta intentando conectarse");
        } else {
            respuesta.setToken(resultado);
        }
        return respuesta;
    }


    @WebMethod
    public LocalizacionRespuesta consultaLocalizacion(@WebParam(name = "token") String token,
            @WebParam(name = "telefono") String telefono) {
        Sesion sesion = null;

        LocalizacionRespuesta respuesta = new LocalizacionRespuesta();
        sesion = localizacionService.validarSesion(token);

        if (sesion != null) {

            boolean resultado = false;
         
            if (!ListEjecuciones.isEjecucion(sesion.getAplicacion().getId())) {
                Ejecucion ejecucion = new Ejecucion(sesion.getAplicacion().getConexiones(), sesion
                        .getAplicacion().getNombre());
                ListEjecuciones.agregarEjecucion(sesion.getAplicacion().getId(), ejecucion);
                log.debug("APLICACION NUEVA: [" + ejecucion.getNombre() + "]");

            }
            
            Ejecucion ejecucion2 = ListEjecuciones.getEjecucion(sesion.getAplicacion().getId());

            Ubicacion ubicacion = null;
            ubicacion = localizacionService.localizarMovil(telefono, sesion, resultado, ejecucion2);

            if (ubicacion != null) {
                respuesta.setUbicacion(ubicacion);
            } else {
                if (resultado) {
                    respuesta.setError("Esta aplicacion no puede consultar por este numero");
                } else {
                    respuesta.setError("No se pudo ubicar el movil " + telefono);
                }
            }


        } else {
            respuesta.setError("El token es invalido");
        }
        return respuesta;
    }

}
