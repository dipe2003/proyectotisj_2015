
package Enumerados.TipoDeEstudio;

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
public class ManejadorTipoEstudio {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
        
    public int CrearTipoDeEstudio(TipoEstudio TipoDeEstudio){
        try{
            em.persist(TipoDeEstudio);
            return TipoDeEstudio.getIdTipoEstudio();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarTipoEstudio(TipoEstudio TipoDeEstudio){
        try{
            em.merge(TipoDeEstudio);
            return TipoDeEstudio.getIdTipoEstudio();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
        
    public List<TipoEstudio> ListarTipoDeEstudios(){
        List<TipoEstudio> lista = new ArrayList<>();
        try{
            TypedQuery<TipoEstudio> query = em.createQuery("SELECT e FROM TipoEstudio e", TipoEstudio.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
