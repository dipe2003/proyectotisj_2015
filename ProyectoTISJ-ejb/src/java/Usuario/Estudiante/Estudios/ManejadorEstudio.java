
package Usuario.Estudiante.Estudios;

import Usuario.Estudiante.Estudios.Estudio;
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
public class ManejadorEstudio {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearEstudio(Estudio estudio){
        try{
            em.persist(estudio);
            return estudio.getIdEstudio();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarEstudio(Estudio estudio){
        try{
            em.merge(estudio);
            return estudio.getIdEstudio();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarEstudio(Estudio estudio){
        try{
            em.remove(estudio);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Estudio BuscarEstudio(int id){
        try{
            return em.find(Estudio.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Estudio> ListarEstudios(){
        List<Estudio> lista = new ArrayList<>();
        try{
            TypedQuery<Estudio> query = em.createQuery("SELECT e FROM Estudio e", Estudio.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Estudio> ListarEstudios(List<Integer> IdsEstudios){
        List<Estudio> lista = new ArrayList<>();
        try{
            TypedQuery<Estudio> query = em.createQuery("SELECT e FROM Estudio e WHERE e.IdEstudio IN (:Ids)", Estudio.class);
            query.setParameter("Ids", IdsEstudios);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
