package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorRespuestaEncuesta {
    @EJB
    private ManejadorRespuestaEncuesta mRespEnc;
    
    /**
     * Crea una clase que contiene las preguntas que corresponden a la encuesta.
     * @param PreguntaEncuesta
     * @param EncuestaRespuestas
     * @return Retorna el id de la clase. Retorna -1 si no se pudo crear.
     */
    public int CrearRespuestaEncuesta(Pregunta PreguntaEncuesta, Encuesta EncuestaRespuestas){
        RespuestaEncuesta respEncuesta = new RespuestaEncuesta(PreguntaEncuesta, EncuestaRespuestas);
        return mRespEnc.CrearRespuestaEncuesta(respEncuesta);
    }
    
    /**
     * Agrega la respuesta a la encuesta especificada.
     * @param RespuestaEncuesta
     * @param respuestaEncuesta
     * @return Retorn el id de la RespuestaEncuesta. Retorna -1 si no se agrego.
     */
    public int AgregarRespuestaEncuesta(Respuesta RespuestaEncuesta, RespuestaEncuesta respuestaEncuesta){
        respuestaEncuesta.addRespuestaEncuesta(RespuestaEncuesta);
        return mRespEnc.ModificarRespuestaEncuesta(respuestaEncuesta);
    }
    
    /**
     * Busca la RespuestaEncuesta de la base de datos.
     * @param IdRespuestaEncuesta
     * @return 
     */
    public RespuestaEncuesta ObtenerRespuestaEncuesta(int IdRespuestaEncuesta){
        return mRespEnc.BuscarRespuestaEncuesta(IdRespuestaEncuesta);
    }
    
    /**
     * Devuelve una RespuestaEncuesta
     * @param IdEncuesta
     * @param IdPregunta
     * @return 
     */
    public int ObtenerRespuestaEncuestaPorPregunta(int IdEncuesta, int IdPregunta){
        return mRespEnc.ObtenerRespuestaEncuestaPorPregunta(IdEncuesta, IdPregunta);
    }
   
}
