
package Asignatura;

import Asignatura.Curso.ControladorCurso;
import Asignatura.Curso.Curso;
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
public class FacadeAsignatura implements Serializable {
    @EJB
    private ControladorAsignatura cAsig;
    @EJB
    private ControladorCurso cCurso;
    
    
    public FacadeAsignatura() {}
       
    /**
     * Devuelve el id de la asignatura registrada.
     * @param NombreAsignatura 
     * @param Creditos
     * @return -1 si no se pudo registrar.
     */
    public int RegistrarAsignatura(String NombreAsignatura, int Creditos){
        Asignatura asignatura = cAsig.CrearAsignatura(NombreAsignatura, Creditos);
        if (asignatura!=null) {
            return asignatura.getIdAsignatura();
        }
        return -1;
    }
   
    /**
     * Devuelve la asignatura identificada por su id.
     * PRE: existe la asignatura en la base de datos.
     * @param Id
     * @return 
     */
    public Asignatura BuscarAsignatura(int Id){
        return cAsig.BuscarAsignatura(Id);
    }
    
        
    /**
     * Actualiza los datos de una asignatura en la base de datos.
     * @param asignatura 
     * @return -1 si no se pudo actualizar
     */
    public int ModificarAsignatura(Asignatura asignatura){
        return cAsig.ModificarAsignatura(asignatura);
    }
    
    /**
     * Lista todas las asignauras registradas en la base de datos.
     * @return 
     */
    public List<Asignatura> ListarAsignaturas(){
        return cAsig.ListarAsignaturas();
    }
    
    /**
     * Devuelve el nombre de la asignatura especificada por su id.
     * @param IdAsignatura
     * @return 
     */
    public String BuscarNombreAsignatura(int IdAsignatura){
        return cAsig.getNombreAsignatura(IdAsignatura);
    }
    
    /**
     * Retorna una lista de los nombres de las asignaturas del docente de Id idDocente
     * @param idDocente
     * @return 
     */
    public List<String> AsignaturasDocente(int idDocente){
        List<String> AsignaturasDocente = new ArrayList<>();
        List<Curso> CursosDocente = cCurso.ListarCursos(idDocente, true);
        for (Curso curso : CursosDocente){
            if (!AsignaturasDocente.contains(curso.getAsignaturaCurso().getNombreAsignatura())) AsignaturasDocente.add(curso.getAsignaturaCurso().getNombreAsignatura());
        }
        return AsignaturasDocente;
    }
    
    public List<Asignatura> ListarAsignaturasCurso(){
        return cAsig.ListarAsignaturasCurso();
    }

}