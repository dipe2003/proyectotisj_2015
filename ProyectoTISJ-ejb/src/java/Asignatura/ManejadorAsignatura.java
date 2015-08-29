
package Asignatura;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@ManagedBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorAsignatura {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearAsignatura(Asignatura asignatura){
        try{
            em.persist(asignatura);
            return asignatura.getIdAsignatura();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarAsignatura(Asignatura asignatura){
        try{
            em.merge(asignatura);
            return asignatura.getIdAsignatura();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarAsignatura(Asignatura asignatura){
        try{
            em.remove(asignatura);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Asignatura BuscarAsignatura(int id){
        try{
            return em.find(Asignatura.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Asignatura> ListarAsignaturas(){
        List<Asignatura> lista = new ArrayList<>();
        try{
            TypedQuery<Asignatura> query = em.createQuery("SELECT a FROM Asignatura a", Asignatura.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
