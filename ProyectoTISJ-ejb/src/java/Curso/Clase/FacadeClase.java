
package Curso.Clase;



import Curso.ControladorCurso;
import Estudiante.ControladorEstudiante;
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
    @EJB
    private ControladorEstudiante cEst;
    
    public FacadeClase() {}
       
   /**
    * Registra una nueva clase.
    * @param FechaClase
    * @param TemaClase
     * @param IdCurso
    * @return el id de la clase registrada. Retorne -1 si no se registro.
    */
    public int RegistarClase(Date FechaClase, String TemaClase, int IdCurso){
        Clase clase = cClase.CrearClase(FechaClase, TemaClase, cCurso.BuscarCurso(IdCurso));
        if (clase!=null) {
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
     * @param IdEstudianteClase
     * @param IdClase 
     */
    public void RegistrarAsistenciaEstudiante(int IdEstudianteClase, int IdClase){
        cClase.AgregarEstudianteAClase(cEst.BuscarEstudiante(IdEstudianteClase), IdClase);
        //cEst.ModificarEstudiante(EstudianteCurso);
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
