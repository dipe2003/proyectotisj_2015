
package Respuesta;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorRespuesta {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearRespuesta(Respuesta respuesta){
        try{
            em.persist(respuesta);
            return respuesta.getIdRespuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarRespuesta(Respuesta respuesta){
        try{
            em.merge(respuesta);
            return respuesta.getIdRespuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarRespuesta(Respuesta respuesta){
        try{
            em.remove(respuesta);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Respuesta BuscarRespuesta(int id){
        try{
            return em.find(Respuesta.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Respuesta> ListarRespuestas(){
        List<Respuesta> lista = new ArrayList<>();
        try{
            TypedQuery<Respuesta> query = em.createQuery("SELECT r FROM Respuesta r", Respuesta.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
