
package Asignatura.Curso.Encuesta;

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
public class ManejadorRespuestaEncuesta {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearRespuestaEncuesta(RespuestaEncuesta respuestaEncuesta){
        try{
            em.persist(respuestaEncuesta);
            return respuestaEncuesta.getIdRespuestaEncuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarRespuestaEncuesta(RespuestaEncuesta respuestaEncuesta){
        try{
            em.merge(respuestaEncuesta);
            return respuestaEncuesta.getIdRespuestaEncuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarRespuestaEncuesta(RespuestaEncuesta respuestaEncuesta){
        try{
            em.remove(respuestaEncuesta);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public RespuestaEncuesta BuscarRespuestaEncuesta(int id){
        try{
            return em.find(RespuestaEncuesta.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<RespuestaEncuesta> ListarRespuestasEncuesta(){
        List<RespuestaEncuesta> lista = new ArrayList<>();
        try{
            TypedQuery<RespuestaEncuesta> query = em.createQuery("SELECT e FROM RespuestaEncuesta e", RespuestaEncuesta.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public int ObtenerRespuestaEncuestaPorPregunta(int IdEncuesta, int IdPregunta){
        try{
            TypedQuery<RespuestaEncuesta> query = em.createQuery(
                    "SELECT respec "
                            + "FROM RespuestaEncuesta respec, Encuesta enc "
                            + "WHERE enc.IdEncuesta = :idencuesta and "
                            + "respec MEMBER OF enc and "
                            + "respec.PreguntaRespuestasEncuesta.IdPregunta = :idpregunta", RespuestaEncuesta.class);
            query.setParameter("idencuesta", IdEncuesta);
            query.setParameter("idpregunta", IdPregunta);
            RespuestaEncuesta resp = query.getSingleResult();
            return resp.getIdRespuestaEncuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return -1;
    }
        
}
    
