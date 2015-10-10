
package Estudiante;

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
public class ManejadorEstudiante {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearEstudiante(Estudiante estudiante){
        try{
            em.persist(estudiante);
            return estudiante.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarEstudiante(Estudiante estudiante){
        try{
            em.merge(estudiante);
            return estudiante.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarEstudiante(Estudiante estudiante){
        try{
            em.remove(estudiante);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Estudiante BuscarEstudiante(int id){
        try{
            return em.find(Estudiante.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Estudiante> ListarEstudiantes(){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Estudiante> ListarEstudiantes(List<Integer> IdsEstudiantes){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.IdUsuario IN :(Ids)", Estudiante.class);
            query.setParameter("Ids", IdsEstudiantes);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
