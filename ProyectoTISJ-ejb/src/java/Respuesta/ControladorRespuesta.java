package Respuesta;

import Pregunta.Pregunta;
import java.util.List;
import javax.ejb.EJB;

public class ControladorRespuesta {
    
    @EJB
    ManejadorRespuesta mResp;
    
    /**
     * Crea una Respuesta y la persiste.
     * @param IdRespuesta  
     * @param PreguntaRespuesta  
     * @param RespultadoRespuesta  
     * @return Devuelve una Respuesta si fue creada, de lo contrario devuelve null.
     */
    public Respuesta CrearRespuesta(int IdRespuesta, int RespultadoRespuesta, Pregunta PreguntaRespuesta){
        Respuesta resp = new Respuesta(IdRespuesta, RespultadoRespuesta, PreguntaRespuesta);
        if (mResp.CrearRespuesta(resp)!=-1){
            return resp;
        }
        return null;
    }
    
    /**
     * Modifica los datos de una Respuesta en la base de datos.
     * @param respuesta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarRespuesta(Respuesta respuesta){
        return mResp.ModificarRespuesta(respuesta);
    }
    
    /**
     * Borra los datos de una Respuesta en la base de datos.
     * @param respuesta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarRespuesta(Respuesta respuesta){
        return mResp.BorrarRespuesta(respuesta);
    }
    
    /**
     * Busca una Respuesta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Respuesta BuscarRespuesta(int id){
        return mResp.BuscarRespuesta(id);
    }
    
    /**
     * Devuelve una lista de Respuestas desde la base de datos.
     * @return 
     */
    public List<Respuesta> ListarRespuestas(){
        return mResp.ListarRespuestas();
    }
    
}
