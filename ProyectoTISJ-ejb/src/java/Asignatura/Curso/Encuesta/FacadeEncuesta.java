package Asignatura.Curso.Encuesta;

import Asignatura.Curso.ControladorCurso;
import Asignatura.Curso.Encuesta.Pregunta.ControladorPregunta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.ControladorRespuesta;
import Usuario.Estudiante.ControladorEstudiante;
import Usuario.Estudiante.Estudiante;
import java.util.ArrayList;
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
     * @return Devuelve la id de la Encuesta si fue creada, de lo contrario devuelve -1.
     */
    public int CrearEncuesta(int IdCurso){
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
    
    /**
     * Dvuelve todas las encuestas registradas.
     * @return
     */
    public List<Encuesta> ListarEncuestas(){
        return cEnc.ListarEncuestas();
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
    
    /**
     * Crea una respuesta para la encuesta y la agrega a la misma.
     * @param ResultadoRespuesta
     * @param IdPregunta
     * @param IdEncuesta
     * @return Retorna el id de la respuesta creada. Retorna -1 si no se pudo agregar.
     */
    public int ResponderPreguntaEncuesta(int ResultadoRespuesta, int IdPregunta, int IdEncuesta){
        int IdRespuesta = cResp.CrearRespuesta(ResultadoRespuesta, cPreg.BuscarPregunta(IdPregunta));
        int IdRespuestaEncuesta = cRespEnc.ObtenerRespuestaEncuestaPorPregunta(IdEncuesta, IdPregunta);
        if(IdRespuesta != -1 && IdRespuestaEncuesta != -1) return cRespEnc.AgregarRespuestaEncuesta(cResp.BuscarRespuesta(IdRespuesta), cRespEnc.ObtenerRespuestaEncuesta(IdRespuestaEncuesta));
        return -1;
    }
    
    /**
     * Estudiantes encuestas
     */
    
    
    /**
     * Obtiene los estudiantes que tienen encuestas pendientes para responder.
     * @param idEncuesta
     * @return Retorna una lista de estudiantes. Retorna una lista vacia si no hay encuestas pendientes.
     */
    public List<Estudiante> getEstudianteSinRespuesta(int idEncuesta){
        List<Estudiante> estudiantes= new ArrayList<>();
        List<Estudiante> estudiantesEncuesta = cEst.getEstudiantesEncuesta(idEncuesta);
        List<Estudiante> estudiantesCursoEncuesta = cEst.getEstudiantesCursoEncuesta(idEncuesta);
        for (Estudiante item : estudiantesCursoEncuesta){
            if (!perteneceEstudiante(estudiantesEncuesta, item.getIdUsuario())) estudiantes.add(item);
        }
        return estudiantes;
    }
    
    private boolean perteneceEstudiante(List<Estudiante> estudiantes, int idEstudiante){
        for (Estudiante item : estudiantes){
            if (item.getIdUsuario() == idEstudiante) return true;
        }
        return false;
    }
    
    /**
     * Devuelve las encuestas que el estudiante tiene sin responder.
     * @param idEstudiante
     * @return Retorna una lista de encuesta. Retorna una lista vacía si no hay encuestas.
     */
    public List<Encuesta> getEncuestasEstudianteSinResponder(int idEstudiante){
        return cEnc.ListarEncuestasEstudianteSinResponder(idEstudiante);
    }
    
    /**
     * Devuelve la encuesta indicada por su id.
     * @param IdEncuesta
     * @return
     */
    public Encuesta GetEncuestaPorId(int IdEncuesta){
        return cEnc.BuscarEncuesta(IdEncuesta);
    }
    
}
