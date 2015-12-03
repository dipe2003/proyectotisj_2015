
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
    
    public List<Docente> ListarProfesoresDeEstudiante(int idEstudiante){
        return cDoc.ListarProfesoresDeEstudiante(idEstudiante);
    }
    
}
