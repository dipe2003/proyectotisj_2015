
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
public class ManejadorEncuesta {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearEncuesta(Encuesta encuesta){
        try{
            em.persist(encuesta);
            return encuesta.getIdEncuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int ModificarEncuesta(Encuesta encuesta){
        try{
            em.merge(encuesta);
            return encuesta.getIdEncuesta();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarEncuesta(Encuesta encuesta){
        try{
            em.remove(encuesta);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Encuesta BuscarEncuesta(int id){
        try{
            return em.find(Encuesta.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Encuesta> ListarEncuestas(){
        List<Encuesta> lista = new ArrayList<>();
        try{
            TypedQuery<Encuesta> query = em.createQuery("SELECT e FROM Encuesta e", Encuesta.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Encuesta> ListarEncuestasRespondidas(int IdEstudiante){
        List<Encuesta> lista = new ArrayList<>();
        try{
            TypedQuery<Encuesta> query = em.createQuery("SELECT e FROM Encuesta e, Estudiante es WHERE es.IdUsuario= :idestudiante and es MEMBER OF e.EstudiantesEncuesta", Encuesta.class);
            query.setParameter("idestudiante", IdEstudiante);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Encuesta> ListarEncuestasCursosEstudiante(int IdEstudiante){
        List<Encuesta> lista = new ArrayList<>();
        try{
            TypedQuery<Encuesta> query = em.createQuery("SELECT e FROM Encuesta e, Curso c, Estudiante es WHERE es.IdUsuario= :idestudiante and es MEMBER OF c.EstudiantesCurso and e.IdEncuesta = c.EncuestaCurso.IdEncuesta", Encuesta.class);
            query.setParameter("idestudiante", IdEstudiante);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
}

