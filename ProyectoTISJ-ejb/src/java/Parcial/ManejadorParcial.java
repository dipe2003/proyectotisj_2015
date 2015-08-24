
package Parcial;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorParcial {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearParcial(Parcial parcial){
        try{
            em.persist(parcial);
            return parcial.getIdEvaluacion();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarParcial(Parcial parcial){
        try{
            em.merge(parcial);
            return parcial.getIdEvaluacion();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarParcial(Parcial parcial){
        try{
            em.remove(parcial);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Parcial BuscarParcial(int id){
        try{
            return em.find(Parcial.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Parcial> ListarParcials(){
        List<Parcial> lista = new ArrayList<>();
        try{
            TypedQuery<Parcial> query = em.createQuery("SELECT p FROM Parcial p", Parcial.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
