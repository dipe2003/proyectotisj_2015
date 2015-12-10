
package Asignatura.Curso.Evaluacion;

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
public class ManejadorEvaluacion {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearEvaluacion(Evaluacion evaluacion){
        try{
            em.persist(evaluacion);
            return evaluacion.getIdEvaluacion();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int ModificarEvaluacion(Evaluacion evaluacion){
        try{
            em.merge(evaluacion);
            return evaluacion.getIdEvaluacion();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarEvaluacion(Evaluacion evaluacion){
        try{
            em.remove(evaluacion);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Evaluacion BuscarEvaluacion(int id){
        try{
            return em.find(Evaluacion.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Evaluacion> ListarEvaluaciones(){
        List<Evaluacion> lista = new ArrayList<>();
        try{
            TypedQuery<Evaluacion> query = em.createQuery("SELECT e FROM Evaluacion e", Evaluacion.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Evaluacion> getEvaluacionesPorCurso(int idCurso){
        List<Evaluacion> lista = new ArrayList<>();
        try{
            TypedQuery<Evaluacion> query = em.createQuery("SELECT e FROM Evaluacion e WHERE e.CursoEvaluacion.IdCurso= :idCurso", Evaluacion.class);
            query.setParameter("idCurso", idCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
}

