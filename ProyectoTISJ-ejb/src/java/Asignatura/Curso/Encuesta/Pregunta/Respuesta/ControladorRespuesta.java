package Asignatura.Curso.Encuesta.Pregunta.Respuesta;

import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorRespuesta {
    
    @EJB
    private ManejadorRespuesta mResp;
    
    /**
     * Crea una Respuesta y la persiste.
     * @param ResultadoRespuesta
     * @param PreguntaRespuesta
     * @return Devuelve una Respuesta si fue creada, de lo contrario devuelve null.
     */
    public int CrearRespuesta(int ResultadoRespuesta, Pregunta PreguntaRespuesta){
        Respuesta resp = new Respuesta(ResultadoRespuesta, PreguntaRespuesta);
        return mResp.CrearRespuesta(resp);
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
