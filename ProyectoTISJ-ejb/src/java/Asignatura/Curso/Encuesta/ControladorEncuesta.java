package Asignatura.Curso.Encuesta;

import Asignatura.Curso.Encuesta.Pregunta.Respuesta.Respuesta;
import Usuario.Estudiante.Estudiante;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEncuesta {
    
    @EJB
    ManejadorEncuesta mEnc;
    
    /**
     * Crea una Encuesta y la persiste.
     * @param FechaEncuesta 
     * @return Devuelve una Encuesta si fue creada, de lo contrario devuelve null.
     */
    public Encuesta CrearEncuesta(Date FechaEncuesta){
        Encuesta enc = new Encuesta(FechaEncuesta);
        if (mEnc.CrearEncuesta(enc)!=-1){
            return enc;
        }
        return null;
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
     * Agrega una respuesta a la encuesta especificada.
     * @param RespuestaEncuesta
     * @param EncuestaRespuesta
     * @return Retorna el id de la encuesta. Retorna -1 si no se agrego.
     */
    public int AgregarRespuestaEncuesta(Respuesta RespuestaEncuesta, Encuesta EncuestaRespuesta){
        EncuestaRespuesta.addRespuestaEncuesta(RespuestaEncuesta);
        return mEnc.ModificarEncuesta(EncuestaRespuesta);
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
}