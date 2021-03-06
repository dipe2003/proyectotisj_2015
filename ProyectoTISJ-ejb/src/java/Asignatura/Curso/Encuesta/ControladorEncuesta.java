package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Encuesta.Pregunta.ControladorPregunta;
import Usuario.Estudiante.Estudiante;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEncuesta {
    
    @EJB
    private ManejadorEncuesta mEnc;
    @EJB
    private ControladorRespuestaEncuesta cRespEnc;
    @EJB
    private ControladorPregunta cPreg;
    
    /**
     * Crea una Encuesta y la persiste.
     * @param CursoEncuesta
     * @return Devuelve el id de la Encuesta si fue creada, de lo contrario devuelve -1.
     */
    public int CrearEncuesta(Curso CursoEncuesta){
        Encuesta enc = new Encuesta();
        if(mEnc.CrearEncuesta(enc)!=-1){
            enc.setCursoEncuesta(CursoEncuesta);
            return mEnc.ModificarEncuesta(enc);
        }
        return -1;
    }
    
    /**
     * Modifica los datos de una Encuesta en la base de datos.
     * @param encuesta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEncuesta(Encuesta encuesta){
        return mEnc.ModificarEncuesta(encuesta);
    }
    
    /**
     * Borra los datos de una Encuesta en la base de datos.
     * @param encuesta
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEncuesta(Encuesta encuesta){
        return mEnc.BorrarEncuesta(encuesta);
    }
    
    /**
     * Busca una Encuesta en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Encuesta BuscarEncuesta(int id){
        return mEnc.BuscarEncuesta(id);
    }
    
    /**
     * Devuelve una lista de Encuestas desde la base de datos.
     * @return
     */
    public List<Encuesta> ListarEncuestas(){
        return mEnc.ListarEncuestas();
    }
    
    /**
     * Agrega un estudiante a la encuesta
     * @param EstudianteEncuesta
     * @param EncuestaEstudiante
     * @return Retorna el id de la encuesta. Retorna -1 si no se agrego.
     */
    public int AgregarEstudianteEncuesta(Estudiante EstudianteEncuesta, Encuesta EncuestaEstudiante){
        EncuestaEstudiante.addEstudianteEncuesta(EstudianteEncuesta);
        return mEnc.ModificarEncuesta(EncuestaEstudiante);
    }
    
    /**
     * Agrega preguntas a la encuesta. 
     * Crea una RespuestaEncuesta para cada pregunta que agrega y la agrega a la lista en encuesta.
     * @param IdEncuesta
     * @param IdPreguntas
     * @return Retorna true si se pudo agregar.
     */
    public boolean AgregarPreguntasEncuesta(int IdEncuesta, List<Integer> IdPreguntas){
        Encuesta encuesta = mEnc.BuscarEncuesta(IdEncuesta);
        try{
            for (int i = 0; i < IdPreguntas.size(); i++) {
                cRespEnc.CrearRespuestaEncuesta(cPreg.BuscarPregunta(IdPreguntas.get(i)), encuesta);
            }
            return true;
        }catch(Exception ex){}
        return false;
    }
    
    /**
     * Devuelve una lista de encuestas que hayan sido respondidas por el estudiante 
     * @param IdEstudiante Id del estudiante
     * @return Retorna una lista con Encuestas. Retorna una lista vacia si no hay encuestas respondidas por el estudiante o
     * si los cursos a los que pertenece el estudiante no tienen encuestas se obtienen una lista vacia.
     */
    public List<Encuesta> ListarEncuestasRespondidas(int IdEstudiante){
        return mEnc.ListarEncuestasRespondidas(IdEstudiante);
    }
    
    /**
     * Devuelve una lista de encuestas que se hayan emitido para los cualquier curso del estudiante.
     * @param IdEstudiante Id del estudiante
     * @return Retorna una lista con encuestas. Retorna una lista vacia si no hay encuestas en los cursos del estudiante o si el
     * estudiante no tiene cursos asignados.
     */
    public List<Encuesta> ListarEncuestasCursosEstudiante(int IdEstudiante){
        return mEnc.ListarEncuestasCursosEstudiante(IdEstudiante);
    }
    
    /**
     * Devuelve la encuesta indicada por su id.
     * @param IdEncuesta
     * @return 
     */
    public Encuesta GetEncuesta(int IdEncuesta){
        return mEnc.BuscarEncuesta(IdEncuesta);
    }
    
    /**
     * Devuelve las encuestas que el estudiante tiene sin responder.
     * @param idEstudiante
     * @return Retorna una lista de encuesta. Retorna una lista vacía si no hay encuestas.
     */
    public List<Encuesta> ListarEncuestasEstudianteSinResponder(int idEstudiante){
        List<Encuesta> encuestasSinResponder = new ArrayList<>();
        List<Encuesta> escuestasRespondidas = ListarEncuestasRespondidas(idEstudiante);
        List<Encuesta> escuestasEstudiante = ListarEncuestasCursosEstudiante(idEstudiante);
        for (Encuesta item : escuestasEstudiante){
            if (!PerteneceEcuesta(escuestasRespondidas, item)) encuestasSinResponder.add(item);
        }
        return encuestasSinResponder;
    }
    
    private boolean PerteneceEcuesta(List<Encuesta> lista, Encuesta item){
        for (Encuesta i : lista){
            if (i.getIdEncuesta() == item.getIdEncuesta()) return true;
        }
        return false;
    }
    
    public List<Encuesta> getEncuestaPorAnioYSemestre(int Anio, int Semestre){
         return mEnc.getEncuestaPorAnioYSemestre(Anio, Semestre);
    }
    
    public List<Encuesta> filtrarEncuestas(int anioFilter, int semestreFilter, int idAsignatura){
        return mEnc.filtrarEncuestas(anioFilter, semestreFilter, idAsignatura);
    }
    
}
