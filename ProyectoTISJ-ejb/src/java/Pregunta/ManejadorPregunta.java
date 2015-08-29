
package Pregunta;

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
public class ManejadorPregunta {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearPregunta(Pregunta pregunta){
        try{
            em.persist(pregunta);
            return pregunta.getIdPregunta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarPregunta(Pregunta pregunta){
        try{
            em.merge(pregunta);
            return pregunta.getIdPregunta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarPregunta(Pregunta pregunta){
        try{
            em.remove(pregunta);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Pregunta BuscarPregunta(int id){
        try{
            return em.find(Pregunta.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Pregunta> ListarPreguntas(){
        List<Pregunta> lista = new ArrayList<>();
        try{
            TypedQuery<Pregunta> query = em.createQuery("SELECT p FROM Pregunta p", Pregunta.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
