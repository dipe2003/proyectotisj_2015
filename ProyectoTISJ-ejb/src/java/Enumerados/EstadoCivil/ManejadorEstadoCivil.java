
package Enumerados.EstadoCivil;

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
public class ManejadorEstadoCivil {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
        
    public int CrearEstadoCivil(EstadoCivil estadoCivil){
        try{
            em.persist(estadoCivil);
            return estadoCivil.getIdEstadoCivil();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarEstadoCivil(EstadoCivil estadoCivil){
        try{
            em.merge(estadoCivil);
            return estadoCivil.getIdEstadoCivil();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
        
    public List<EstadoCivil> ListarEstadoCivil(){
        List<EstadoCivil> lista = new ArrayList<>();
        try{
            TypedQuery<EstadoCivil> query = em.createQuery("SELECT e FROM EstadoCivil e", EstadoCivil.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
