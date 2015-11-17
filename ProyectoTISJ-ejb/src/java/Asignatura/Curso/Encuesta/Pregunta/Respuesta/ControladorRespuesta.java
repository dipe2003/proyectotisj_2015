package Asignatura.Curso.Encuesta.Pregunta.Respuesta;

import Asignatura.Curso.Encuesta.Encuesta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorRespuesta {
    
    @EJB
    ManejadorRespuesta mResp;
    
    /**
     * Crea una Respuesta y la persiste.  
     * @param ResultadoRespuesta
     * @param PreguntaRespuesta  
     * @param EncuestaRespuesta  
     * @return Devuelve una Respuesta si fue creada, de lo contrario devuelve null.
     */
    public Respuesta CrearRespuesta(int ResultadoRespuesta, Pregunta PreguntaRespuesta, Encuesta EncuestaRespuesta){
        Respuesta resp = new Respuesta(ResultadoRespuesta, PreguntaRespuesta, EncuestaRespuesta);
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
