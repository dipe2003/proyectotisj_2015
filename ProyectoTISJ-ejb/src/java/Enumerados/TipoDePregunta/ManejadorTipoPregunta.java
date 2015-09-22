
package Enumerados.TipoDePregunta;

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
public class ManejadorTipoPregunta {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
        
    public int CrearTipoDePregunta(TipoPregunta TipoDePregunta){
        try{
            em.persist(TipoDePregunta);
            return TipoDePregunta.getIdTipoPregunta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarTipoPregunta(TipoPregunta TipoDePregunta){
        try{
            em.merge(TipoDePregunta);
            return TipoDePregunta.getIdTipoPregunta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
        
    public List<TipoPregunta> ListarTiposDePreguntas(){
        List<TipoPregunta> lista = new ArrayList<>();
        try{
            TypedQuery<TipoPregunta> query = em.createQuery("SELECT p FROM TipoPregunta p", TipoPregunta.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
