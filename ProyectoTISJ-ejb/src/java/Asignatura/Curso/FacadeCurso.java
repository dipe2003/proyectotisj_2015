
package Asignatura.Curso;


import Asignatura.ControladorAsignatura;
import Asignatura.Curso.Clase.ControladorClase;
import Usuario.Docente.ControladorDocente;
import Usuario.Estudiante.ControladorEstudiante;
import java.io.Serializable;
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
}
