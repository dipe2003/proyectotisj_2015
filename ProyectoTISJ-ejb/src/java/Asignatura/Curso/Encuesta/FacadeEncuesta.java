package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Encuesta.Pregunta.ControladorPregunta;
import Asignatura.Curso.Encuesta.Pregunta.EnumTipoPregunta;
import Asignatura.Curso.Encuesta.Pregunta.Pregunta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.ControladorRespuesta;
import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import Usuario.Estudiante.ControladorEstudiante;
import java.util.Date;
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
    private ControladorPregunta cPre;
    @EJB
    private ControladorRespuesta cResp;
    @EJB
    private ControladorEstudiante cEst;
    
    /*
    *   Encuesta
    */
    
    /**
     * Crea una Encuesta y la persiste.
     * @param FechaEncuesta
     * @return Devuelve una Encuesta si fue creada, de lo contrario devuelve null.
     */
    public Encuesta CrearEncuesta(Date FechaEncuesta){
        return cEnc.CrearEncuesta(FechaEncuesta);
    }
    
    /**
     * Agrega una respuesta a la encuesta especificada.
     * @param IdRespuesta
     * @param IdEncuesta
     * @return Retorna el id de la encuesta. Retorna -1 si no se agrego.
     */
    public int AgregarRespuestaEncuesta(int IdRespuesta, int IdEncuesta){
        return cEnc.AgregarRespuestaEncuesta(cResp.BuscarRespuesta(IdRespuesta), cEnc.BuscarEncuesta(IdEncuesta));
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
     * Modifica los datos de una Encuesta en la base de datos.
     * @param encuesta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEncuesta(Encuesta encuesta){
        return cEnc.ModificarEncuesta(encuesta);
    }
    
    /**
     * Borra los datos de una Encuesta en la base de datos.
     * @param encuesta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEncuesta(Encuesta encuesta){
        return cEnc.BorrarEncuesta(encuesta);
    }
    
    /**
     * Busca una Encuesta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Encuesta BuscarEncuesta(int id){
        return cEnc.BuscarEncuesta(id);
    }
    
    /**
     * Devuelve una lista de Encuestas desde la base de datos.
     * @return
     */
    public List<Encuesta> ListarEncuestas(){
        return cEnc.ListarEncuestas();
    }
    
    /*
    *   Pregunta
    */
    
    /**
     * Crea una Pregunta y la persiste.
     * @param TextoPregunta
     * @param TipoPregunta
     * @return Devuelve una Pregunta si fue creada, de lo contrario devuelve null.
     */
    public Pregunta CrearPregunta(String TextoPregunta, EnumTipoPregunta TipoPregunta){
        return cPre.CrearPregunta(TextoPregunta, TipoPregunta);
    }
    
    /**
     * Modifica los datos de una Pregunta en la base de datos.
     * @param pregunta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarPregunta(Pregunta pregunta){
        return cPre.ModificarPregunta(pregunta);
    }
    
    /**
     * Borra los datos de una Pregunta en la base de datos.
     * @param pregunta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarPregunta(Pregunta pregunta){
        return cPre.BorrarPregunta(pregunta);
    }
    
    /**
     * Busca una Pregunta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Pregunta BuscarPregunta(int id){
        return cPre.BuscarPregunta(id);
    }
    
    /**
     * Devuelve una lista de Preguntas desde la base de datos.
     * @return
     */
    public List<Pregunta> ListarPreguntas(){
        return cPre.ListarPreguntas();
    }
    
    /*
    *   Respuesta
    */
    
    /**
     * Crea una Respuesta y la persiste.
     * @param ResultadoRespuesta
     * @param IdPregunta
     * @param IdEncuesta
     * @return Devuelve una Respuesta si fue creada, de lo contrario devuelve null.
     */
    public Respuesta CrearRespuesta(int ResultadoRespuesta, int IdPregunta, int IdEncuesta){
        return cResp.CrearRespuesta(ResultadoRespuesta, cPre.BuscarPregunta(IdPregunta), cEnc.BuscarEncuesta(IdEncuesta));
    }
    
    /**
     * Modifica los datos de una Respuesta en la base de datos.
     * @param respuesta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarRespuesta(Respuesta respuesta){
        return cResp.ModificarRespuesta(respuesta);
    }
    
    /**
     * Borra los datos de una Respuesta en la base de datos.
     * @param respuesta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarRespuesta(Respuesta respuesta){
        return cResp.BorrarRespuesta(respuesta);
    }
    
    /**
     * Busca una Respuesta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Respuesta BuscarRespuesta(int id){
        return cResp.BuscarRespuesta(id);
    }
    
    /**
     * Devuelve una lista de Respuestas desde la base de datos.
     * @return
     */
    public List<Respuesta> ListarRespuestas(){
        return cResp.ListarRespuestas();
    }
}
