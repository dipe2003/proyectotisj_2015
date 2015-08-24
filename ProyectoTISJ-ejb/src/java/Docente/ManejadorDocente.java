
package Docente;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorDocente {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearDocente(Docente docente){
        try{
            em.persist(docente);
            return docente.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarDocente(Docente docente){
        try{
            em.merge(docente);
            return docente.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarDocente(Docente docente){
        try{
            em.remove(docente);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Docente BuscarDocente(int id){
        try{
            return em.find(Docente.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Docente> ListarDocentes(){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d", Docente.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
