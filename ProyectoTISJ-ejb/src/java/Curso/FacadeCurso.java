
package Curso;


import Asignatura.ControladorAsignatura;
import Docente.ControladorDocente;
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
    public List<Curso> ListarCurso(int IdDocente){
        return cCurso.ListarCursos(IdDocente);
    }
    
}
