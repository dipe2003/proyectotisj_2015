
package Asignatura.Curso;


import Asignatura.ControladorAsignatura;
import Asignatura.Curso.Clase.Clase;
import Asignatura.Curso.Clase.ControladorClase;
import Asignatura.Curso.Evaluacion.ControladorEvaluacion;
import Asignatura.Curso.Evaluacion.Evaluacion;
import Asignatura.Curso.Evaluacion.Resultado.ControladorResultado;
import Asignatura.Curso.Evaluacion.Resultado.Resultado;
import Usuario.Docente.ControladorDocente;
import Usuario.Estudiante.ControladorEstudiante;
import Usuario.Estudiante.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeCurso implements Serializable {
    @EJB
    private ControladorCurso cCurso;
    @EJB
    private ControladorDocente cDoc;
    @EJB
    private ControladorAsignatura cAsig;
    @EJB
    private ControladorEstudiante cEst;
    @EJB
    private ControladorClase cClase;
    @EJB
    private ControladorResultado cRes;
    @EJB
    private ControladorEvaluacion cEval;
    
    public FacadeCurso() {}
    
    /**
     * Devuelve el id del curso creado.
     * @param Semestre
     * @param Anio
     * @param IdDocente
     * @param IdAsignatura
     * @param ContratoDocente
     * @return -1 si no se pudo registrar.
     */
    public int RegistrarCurso(int Semestre, int Anio, int IdDocente, int IdAsignatura, String ContratoDocente ){
        Curso curso = cCurso.CrearCurso(Semestre, Anio,cDoc.BuscarDocente(IdDocente), cAsig.BuscarAsignatura(IdAsignatura),ContratoDocente);
        if (curso!=null) {
            return curso.getIdCurso();
        }
        return -1;
    }
    
    /**
     * Devuelve el curso identificado por su id.
     * PRE: existe el curso en la base de datos.
     * @param Id
     * @return
     */
    public Curso BuscarCurso(int Id){
        return cCurso.BuscarCurso(Id);
    }
    
    
    /**
     * Actualiza los datos de curso en la base de datos.
     * @param curso
     * @return -1 si no se pudo actualizar
     */
    public int ModificarCurso(Curso curso){
        return cCurso.ModificarCurso(curso);
    }
    
    /**
     * Lista todos los cursos registrados en la base de datos.
     * @return
     */
    public List<Curso> ListarCurso(){
        return cCurso.ListarCursos();
    }
    
    /**
     * Lista todos los cursos del docente especificado por su id
     * @param IdDocente
     * @return
     */
    public List<Curso> ListarCursosDocente(int IdDocente){
        return cCurso.ListarCursos(IdDocente, true);
    }
    
    /**
     * Lista todos los cursos en los que esta inscripto el estudiante especificado por su id.
     * @param idEstudiante
     * @return
     */
    public List<Curso> ListarCursosEstudiante(int idEstudiante){
        return cCurso.ListarCursos(idEstudiante, false);
    }
    
    /**
     * Agrega el estudiante al curso especificado por su id.
     * @param IdEstudiante
     * @param IdCurso
     */
    public void AgregarEstudianteACurso(int IdEstudiante, int IdCurso){
        cCurso.AgregarEstudianteACurso(cEst.BuscarEstudiante(IdEstudiante), IdCurso);
    }
    
    /**
     * Devuelve todos los anios que tengan cursos regitrados
     * @return
     */
    public List<String> getAniosCursos(){
        return cCurso.getAniosCursos();
    }
    
    
    /**
     * Devuelve la catidad de clases dictadas del curso especificado por su id.
     * @param IdCurso
     * @return
     */
    public int GetCantidadClasesCurso(int IdCurso){
        return cClase.ListarClases(IdCurso).size();
    }
    
    /**
     * Devuelve el total de inasistencias de un estudiante para el curso especificado.
     * @param IdEstudiante
     * @param IdCurso
     * @return
     */
    public int GetInanistenciasEstudianteCurso(int IdEstudiante, int IdCurso)    {
        return cClase.GetInasistenciasEstudianteCurso(IdCurso, IdEstudiante);
    }
    
    /**
     * Devuelve los cursos que se estan dictando actualmente (en el año y semestre actual).
     * @return
     */
    public List<Curso> GetCursosActuales(){
        return cCurso.GetCursosActuales();
    }
    
    public List<Curso> filtrarCursos(String nameDocente, String nameAsignatura, int anioFilter, int semestreFilter, int idAsignatura){
        List<Curso> cursos = cCurso.filtrarCursos(anioFilter, semestreFilter, idAsignatura);
        List<Curso> cursosFiltrados;
        if (((nameDocente == null) || (nameDocente.isEmpty()))&&((nameAsignatura == null) || (nameAsignatura.isEmpty()))){
            cursosFiltrados = cursos;
        }else{
            cursosFiltrados = new ArrayList<>();
            for (Curso item : cursos){
                if (((item.getDocenteCurso().getNombreCompleto().toLowerCase().contains(nameDocente.toLowerCase())))
                        && (item.getAsignaturaCurso().getNombreAsignatura().toLowerCase().contains(nameAsignatura.toLowerCase())))
                    cursosFiltrados.add(item);
            }
        }
        return cursosFiltrados;
    }
    
    /**
     * Remueve el estudiante indicado del curso.
     * @param idCurso
     * @param idEstudiante
     * @return Retorna -1 si no se pudo remover | Retorna el IdCurso si se removio.
     */
    public int removerEstudianteCurso(int idCurso, int idEstudiante){
        Estudiante estudiante = cEst.BuscarEstudiante(idEstudiante);
        Curso curso = cCurso.BuscarCurso(idCurso);
        boolean tieneEvaluacion = false;
        for(Evaluacion eva: curso.getEvaluacionesCurso()){
            int id = 0;
            for(Resultado res: eva.getResultadosEvaluacion()){                
                if(estudiante.getResultadosEstudiante().contains(res)){
                    estudiante.removeResultadoEstudiante(res);
                    id = res.getEvaluacionResultado().getIdEvaluacion();
                    res.setEvaluacionResultado(null);
                    cRes.BorrarResultado(res);
                }
            }
            if(eva.getIdEvaluacion() == id) cEval.BorrarEvaluacion(eva);
        }
        if(tieneEvaluacion == false){
            Iterator<Clase> it = estudiante.getClasesEstudiante().iterator();
            while(it.hasNext()){
                Clase clase = it.next();
                if(clase.getCursoClase().getIdCurso()==idCurso)
                    it.remove();
            }
            estudiante.removeCursoEstudiante(curso);
            return cEst.ModificarInstEstudiante(estudiante);
        }
        return -1;
    }
}
