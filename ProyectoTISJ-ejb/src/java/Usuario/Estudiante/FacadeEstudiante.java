
package Usuario.Estudiante;

import Asignatura.Curso.ControladorCurso;
import Enumerados.TipoDeEstudio.ControladorTipoEstudio;
import Enumerados.TipoDeEstudio.TipoEstudio;
import Usuario.Estudiante.Estudios.ControladorEstudio;
import Usuario.Estudiante.Estudios.Estudio;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeEstudiante implements Serializable {
    @EJB
    private ControladorEstudiante cEst;
    @EJB
    private ControladorEstudio cEstudio;
    @EJB
    private ControladorTipoEstudio cTEstudio;
    @EJB
    private ControladorCurso cCurso;    
    
    public FacadeEstudiante() {}

    /**
     * Agrega el estudio especificado al estudiante indicado por Id
     * @param IdTipoDeEstudio
     * @param Orientacion
     * @param idEstudiante 
     */
    public void agregarEstudiosEstudiante(int IdTipoDeEstudio, String Orientacion, int idEstudiante){
        TipoEstudio tipo = cTEstudio.getTipoEstudio(IdTipoDeEstudio);
        Estudiante estudiante = cEst.BuscarEstudiante(idEstudiante);
        Estudio estudio = cEstudio.CrearEstudio(tipo, Orientacion);
        estudiante.addEstudioCursado(estudio);
        cEst.ModificarEstudiante(estudiante);
        cEstudio.ModificarEstudio(estudio);
    }
    
    /**
     * Devuelve una lista con todos los estudiantes registrados en el sistema.
     * @return 
     */
    public List<Estudiante> ListarEstudiates(){
        return cEst.ListarEstudiantes();
    }
    
      /**
     * Devuelve una lista con todos los estudiantes de un curso especificado por su id.
     * @param IdCurso
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCurso(int IdCurso){
        return cEst.ListarEstudiantesCurso(IdCurso);
    }
    
    /**
     * Devuelve una lista con los estudios cursados y la orientacion del estudiante especificado por su id.
     * @param IdEstudiante
     * @return 
     */
    public List<String> ListarEstudiosOrientacionCursados(int IdEstudiante){
        return cEstudio.ListarEstudiosOrientacion(cEst.ListarEstudiosOrientacionCursados(IdEstudiante));        
    }
    
        /**
     * Devuelve los estudiantes que contestaron la encuesta.
     * @param IdEncuesta
     * @return 
     */
    public List<Estudiante> getEstudiantesEncuesta(int IdEncuesta){
        return cEst.getEstudiantesEncuesta(IdEncuesta);
    }
    
    /**
     * Devuelve los estudiantes que deben contestar la encuesta
     * @param IdEncuesta
     * @return 
     */
    public List<Estudiante> getEstudiantesCursoEncuesta(int IdEncuesta){
        return cEst.getEstudiantesCursoEncuesta(IdEncuesta);
    }
    
    public List<Estudiante> ListarCompanierosDeClases(int idEstudiante){
        return cEst.ListarCompanierosDeClases(idEstudiante);
    }
    
    public List<Estudiante> ListarAlumnosDocente(int idDocente){
        return cEst.ListarAlumnosDocente(idDocente);
    }
    
    public Estudiante BuscarEstudiante(int id){
        return cEst.BuscarEstudiante(id);
    }
    
 }
