package Asignatura.Curso.Encuesta;

import Asignatura.Curso.ControladorCurso;
import Asignatura.Curso.Encuesta.Pregunta.ControladorPregunta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.ControladorRespuesta;
import Usuario.Estudiante.ControladorEstudiante;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class FacadeEncuesta {
    
    @EJB
    private ControladorEncuesta cEnc;
    @EJB
    private ControladorPregunta cPreg;
    @EJB
    private ControladorRespuesta cResp;
    @EJB
    private ControladorEstudiante cEst;
    @EJB
    private ControladorCurso cCurso;
    @EJB
    private ControladorRespuestaEncuesta cRespEnc;
    
    /*
    *   Encuesta
    */
    
    /**
     * Crea una Encuesta y la persiste.
     * @param IdCurso
     * @return Devuelve una Encuesta si fue creada, de lo contrario devuelve null.
     */
    public Encuesta CrearEncuesta(int IdCurso){
        return cEnc.CrearEncuesta(cCurso.BuscarCurso(IdCurso));
    }
    
    /**
     * Agrega un estudiante a la encuesta
     * @param IdEstudiante
     * @param IdEncuesta
     * @return Retorna el id de la encuesta. Retorna -1 si no se agrego.
     */
    public int AregarEstudianteEncuesta(int IdEstudiante, int IdEncuesta){
        return cEnc.AgregarEstudianteEncuesta(cEst.BuscarEstudiante(IdEstudiante), cEnc.BuscarEncuesta(IdEncuesta));
    }
    
    /**
     * Agrega preguntas a la encuesta.
     * @param IdEncuesta
     * @param IdPreguntas
     * @return Retorna true si se pudo agregar.
     */
    public boolean AgregarPreguntasEncuesta(int IdEncuesta, List<Integer> IdPreguntas){
        return cEnc.AgregarPreguntasEncuesta(IdEncuesta, IdPreguntas);
    }
    
    /*
    Preguntas
    */
    
    /**
     * Crea una Pregunta y la persiste.
     * @param TextoPregunta
     * @param TipoPregunta
     * @return Devuelve una Pregunta si fue creada, de lo contrario devuelve null.
     */
    public int CrearPregunta(String TextoPregunta, EnumTipoPregunta TipoPregunta){
        return cPreg.CrearPregunta(TextoPregunta, TipoPregunta);
    }
    
    /**
     * Busca una Pregunta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Pregunta BuscarPregunta(int id){
        return cPreg.BuscarPregunta(id);
    }
    
    /**
     * Devuelve una lista de Preguntas desde la base de datos.
     * @return
     */
    public List<Pregunta> ListarPreguntas(){
        return cPreg.ListarPreguntas();
    }
    
    /*
    Respuestas
    */
    
    /**
     * Crea una respuesta para la pregunta especificada.
     * @param ResultadoRespuesta
     * @param IdPregunta
     * @return Retorna el id de la respuesta. Retorna -1 si no se creo.
     */
    public int CrearRespuesta(int ResultadoRespuesta, int IdPregunta){
        return cResp.CrearRespuesta(ResultadoRespuesta, cPreg.BuscarPregunta(IdPregunta));
    }
    
    /**
     * Agrega la respuesta a la encuesta especificada.
     * @param IdRespuesta
     * @param IdRespuestaEncuesta
     * @return Retorn el id de la RespuestaEncuesta. Retorna -1 si no se agrego.
     */
    public int AgregarRespuesta(int IdRespuesta, int IdRespuestaEncuesta){
        return cRespEnc.AgregarRespuestaEncuesta(cResp.BuscarRespuesta(IdRespuesta), cRespEnc.ObtenerRespuestaEncuesta(IdRespuestaEncuesta));
    }
    
    
}
