package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
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
    
   
}
