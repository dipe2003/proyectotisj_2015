
package Asignatura;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorAsignatura {
    @EJB
    ManejadorAsignatura mAsig;
    
    /**
     * Crea un Asignatura y lo persiste.
     * @param NombreAsignatura
     * @param CreditosAsignatura 
     * @return Devuelve una Asignatura si fue creada, de lo contrario devuelve null.
     */
    public Asignatura CrearAsignatura(String NombreAsignatura, int CreditosAsignatura){
        Asignatura asig = new Asignatura(NombreAsignatura, CreditosAsignatura);
        if (mAsig.CrearAsignatura(asig)!=-1) {
            return asig;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Asignatura en la base de datos.
     * @param asignatura
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarAsignatura(Asignatura asignatura){
        return mAsig.ModificarAsignatura(asignatura);
    }
    
    /**
     * Borra un Asignatura de la base de datos.
     * @param asignatura
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarAsignatura(Asignatura asignatura){
        return mAsig.BorrarAsignatura(asignatura);
    }
    
    /**
     * Busca una Asignatura en la base de datos.
     * @param id
     * @return Devuelve null si la Asignatura no se pudo encontrar.
     */
    public Asignatura BuscarAsignatura(int id){
        return mAsig.BuscarAsignatura(id);
    }
    
    /**
     * Devuelve una lista de Asignaturas desde la base de datos.
     * @return 
     */
    public List<Asignatura> ListarAsignaturas(){
        return mAsig.ListarAsignaturas();
    }
    
    /**
     * Devuelve el nombre de la asignatura especificada por su id.
     * @param IdAsignatura
     * @return 
     */
    public String getNombreAsignatura(int IdAsignatura){
        return mAsig.getNombreAsignatura(IdAsignatura);
    }
    
    public List<Asignatura> ListarAsignaturasCurso(){
        return mAsig.ListarAsignaturasCurso();
    }
}
