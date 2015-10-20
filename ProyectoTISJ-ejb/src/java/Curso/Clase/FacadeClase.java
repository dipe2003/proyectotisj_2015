
package Curso.Clase;



import Curso.ControladorCurso;
import Estudiante.Estudiante;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeClase implements Serializable {
    @EJB
    private ControladorCurso cCurso;
    @EJB
    private ControladorClase cClase;
    
    public FacadeClase() {}
       
   /**
    * Registra una nueva clase.
    * @param FechaClase
    * @param TemaClase
    * @return el id de la clase registrada. Retorne -1 si no se registro.
    */
    public int RegistarClase(Date FechaClase, String TemaClase){
        Clase clase = cClase.CrearClase(FechaClase, TemaClase);
        if (clase!=null) {
            return clase.getIdClase();
        }
        return -1;
    }
   /**
    * Registra una nueva clase con todos sus datos.
    * @param FechaClase
    * @param TemaClase
     * @param IdCurso
     * @param EstudiantesCurso
    * @return el id de la clase registrada. Retorne -1 si no se registro.
    */
    public int RegistarClase(Date FechaClase, String TemaClase, int IdCurso, List<Usuario> EstudiantesCurso){
        List<Estudiante> estudiantesCurso = (List<Estudiante>) (ArrayList<?>) EstudiantesCurso;
        Clase clase = cClase.CrearClase(FechaClase, TemaClase);
        if (clase!=null) {
            clase.setCursoClase(cCurso.BuscarCurso(IdCurso));
            clase.setAsistenciasClase(estudiantesCurso);
            cClase.ModificarClase(clase);
            return clase.getIdClase();
        }
        return -1;
    }
    
    /**
     * Devuelve la clase especificada por su id.
     * @param IdClase
     * @return 
     */
    public Clase BuscarClase(int IdClase){
        return cClase.BuscarClase(IdClase);
    }
    
    /**
     * Devuelve la lista de clases que se dictaron para el curso especificado por su id.
     * @param idCurso
     * @return 
     */
    public List<Clase> ListarClases(int idCurso){
        return cClase.ListarClases(idCurso);
    }
    
    /**
     * Agrega un estudiante a la lista de estudiantes que asistieron a la clase especificada por su id.
     * @param EstudianteCurso
     * @param IdClase 
     */
    public void RegistrarAsistenciaEstudiante(Estudiante EstudianteCurso, int IdClase){
        cClase.AgregarEstudianteAClase(EstudianteCurso, IdClase);
    }
    
    /**
     * Agrega una lista de estudiantes que asistieron a la clase especificada por su id.
     * @param EstudiantesCurso
     * @param IdClase 
     */
    public void RegistrarAsistenciaEstudiantes(List<Estudiante> EstudiantesCurso, int IdClase){
        cClase.AgregarEstudiantesClase(EstudiantesCurso, IdClase);
    }

        
}
