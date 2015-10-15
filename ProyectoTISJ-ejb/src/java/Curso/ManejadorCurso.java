
package Curso;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@ManagedBean
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorCurso {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearCurso(Curso curso){
        try{
            em.persist(curso);
            return curso.getIdCurso();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int ModificarCurso(Curso curso){
        try{
            em.merge(curso);
            return curso.getIdCurso();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarCurso(Curso curso){
        try{
            em.remove(curso);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Curso BuscarCurso(int id){
        try{
            return em.find(Curso.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Curso> ListarCursos(){
        List<Curso> lista = new ArrayList<>();
        try{
            TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c", Curso.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Curso> ListarCursos(int IdUsuario, boolean docente){
        List<Curso> lista = new ArrayList<>();
        TypedQuery<Curso> query;
        if (docente) {
            query = em.createQuery("SELECT c FROM Curso c WHERE c.DocenteCurso.IdUsuario= :IdDocente", Curso.class);
            query.setParameter("IdDocente", IdUsuario);
        }else{
            query = em.createQuery("SELECT c FROM Curso c WHERE c.EstudiantesCurso.IdUsuario= :idEstudiante", Curso.class);
            query.setParameter("idEstudiante", IdUsuario);
        }
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
}

