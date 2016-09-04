
package Usuario.Estudiante;

import Enumerados.TipoDeEstudio.ControladorTipoEstudio;
import Enumerados.TipoDeEstudio.TipoEstudio;
import Usuario.Estudiante.Estudios.ControladorEstudio;
import Usuario.Estudiante.Estudios.Estudio;
import java.io.Serializable;
import java.util.ArrayList;
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
        cEst.ModificarInstEstudiante(estudiante);
        cEstudio.ModificarEstudio(estudio);
    }
    
    /**
     * Comprueba si el estudio es nuevo y lo agrega al estudiante. Si el estudio ya pertenece al estudiante lo actualiza o lo elimina.
     * La combinacion de idtipo estudio y orientacion definen si se actualiza o elimina.
     * @param IdTipoDeEstudio
     * @param Orientacion Si esta vacio y el tipo de estudio ya existe se elimina. De lo contrario se actualiza con la nueva orientacion.
     * @param IdEstudiante
     */
    public void actualizarEstudioEstudiante(int IdTipoDeEstudio, String Orientacion, int IdEstudiante){
        Estudiante estudiante = cEst.BuscarEstudiante(IdEstudiante);
        List<Estudio> estudios = estudiante.getEstudiosCursadosEstudiante();
        List<Integer> tiposEstudio = new ArrayList<>();
        for(Estudio estudio: estudios) tiposEstudio.add(estudio.getTipoDeEstudio().getIdTipoEstudio());
        if(!tiposEstudio.contains(IdTipoDeEstudio)){
            if(!Orientacion.isEmpty())agregarEstudiosEstudiante(IdTipoDeEstudio, Orientacion, IdEstudiante);
        }else{
            for (int i = 0; i < estudios.size(); i++) {
                if(estudios.get(i).getTipoDeEstudio().getIdTipoEstudio() == IdTipoDeEstudio){
                    if(!Orientacion.isEmpty() && !Orientacion.equals(estudios.get(i).getOrientacionEstudio())){
                        // se actualiza el estudio
                        estudios.get(i).setOrientacionEstudio(Orientacion);
                        cEst.ModificarInstEstudiante(estudiante);
                        cEstudio.ModificarEstudio(estudios.get(i));
                    }else{
                        if(Orientacion.isEmpty()){
                            // Se elimina el estudio.
                            cEstudio.BorrarEstudio(estudios.get(i).getIdEstudio());
                            estudiante.getEstudiosCursadosEstudiante().remove(estudios.get(i));
                            cEst.ModificarInstEstudiante(estudiante);
                        }
                    }
                }
            }
        }        
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
