
package Usuario.Docente;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@Stateless
@SessionScoped
public class FacadeDocente implements Serializable {

    @EJB
    private ControladorDocente cDoc;

    
    public FacadeDocente() {}
    
    /**
     * Devuelve una lista con todos los docentes registrados en el sistema.
     * @return 
     */
    public List<Docente> ListarDocentes(){
        return cDoc.ListarDocentes();
    }
    
    /**
     * Devuelve el nombre del docente especificado por su id.
     * @param IdDocente
     * @return 
     */
    public String BuscarNombreDocente(int IdDocente){
        return cDoc.getNombreDocente(IdDocente);
    }
    
    /**
     * Devuelve una lista de los docentes de cursos a los que pertenece el estudiante.
     * @param idEstudiante
     * @return 
     */
    public List<Docente> ListarProfesoresDeEstudiante(int idEstudiante){
        return cDoc.ListarProfesoresDeEstudiante(idEstudiante);
    }
    
    /**
     * Devuelve el docente del curso indicado por su id.
     * @param idCurso
     * @return 
     */
    public Docente getDocenteCurso(int idCurso){
        return cDoc.getDocenteCurso(idCurso);
    }
    
    /**
     * Devuelve el docente inicado por su id.
     * @param IdDocente
     * @return 
     */
    public Docente GetDocente(int IdDocente){
        return cDoc.BuscarDocente(IdDocente);
    }
    
}
