
package Laboratorio;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorLaboratorio {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearLaboratorio(Laboratorio laboratorio){
        try{
            em.persist(laboratorio);
            return laboratorio.getIdEvaluacion();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarLaboratorio(Laboratorio laboratorio){
        try{
            em.merge(laboratorio);
            return laboratorio.getIdEvaluacion();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarLaboratorio(Laboratorio laboratorio){
        try{
            em.remove(laboratorio);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Laboratorio BuscarLaboratorio(int id){
        try{
            return em.find(Laboratorio.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Laboratorio> ListarLaboratorios(){
        List<Laboratorio> lista = new ArrayList<>();
        try{
            TypedQuery<Laboratorio> query = em.createQuery("SELECT l FROM Laboratorio l", Laboratorio.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
