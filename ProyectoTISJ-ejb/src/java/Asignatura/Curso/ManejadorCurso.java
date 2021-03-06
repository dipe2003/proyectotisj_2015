
package Asignatura.Curso;

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
            TypedQuery<Curso> query = em.createQuery("SELECT c FROM Curso c ORDER BY c.AnioCurso DESC", Curso.class);
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
            query = em.createQuery("SELECT c FROM Curso c WHERE c.DocenteCurso.IdUsuario= :IdDocente ORDER BY c.AnioCurso DESC", Curso.class);
            query.setParameter("IdDocente", IdUsuario);
        }else{
            query = em.createQuery("SELECT c FROM Curso c, Estudiante es WHERE es.IdUsuario= :idEstudiante and c MEMBER OF es.CursosEstudiante ", Curso.class);
            query.setParameter("idEstudiante", IdUsuario);
        }
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Integer> ListarEstudiantesCurso(int IdCurso){
        List<Integer> lista = new ArrayList<>();
        Query query = em.createQuery("SELECT e.IdUsuario FROM Estudiante e, Curso c WHERE c.IdCurso= :IdCurso");
        query.setParameter("IdCurso", IdCurso);
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Integer> getAniosCursos(){
        List<Integer> lista = new ArrayList<>();
        Query query = em.createQuery("SELECT DISTINCT c.AnioCurso FROM Curso c");
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Curso> FiltrarCursos(int anioFilter, int semestreFilter){
        List<Curso> lista = new ArrayList<>();
        TypedQuery<Curso> query;
            query = em.createQuery("SELECT c FROM Curso c WHERE c.AnioCurso= :Anio AND c.SemestreCurso= :Semestre ", Curso.class);
            query.setParameter("Anio", anioFilter);
            query.setParameter("Semestre", semestreFilter);
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Curso> FiltrarCursos(int anioFilter){
        List<Curso> lista = new ArrayList<>();
        TypedQuery<Curso> query;
            query = em.createQuery("SELECT c FROM Curso c WHERE c.AnioCurso= :Anio", Curso.class);
            query.setParameter("Anio", anioFilter);;
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Curso> filtrarCursos(int anioFilter, int semestreFilter, int idAsignatura){
       List<Curso> lista = new ArrayList<>();
       String SELECT = "SELECT DISTINCT c ";
       String FROM = "FROM Curso c ";
       String WHERE = "WHERE c.IdCurso = c.IdCurso ";
       if (anioFilter!=0) WHERE += "and c.AnioCurso= :Anio ";
       if (semestreFilter!=0) WHERE += "and c.SemestreCurso= :Semestre ";
       if (idAsignatura!=0){
           FROM += ", Asignatura a ";
           WHERE += "and c.AsignaturaCurso.IdAsignatura = a.IdAsignatura and a.IdAsignatura= :idAsig ";
       }
       String consulta = SELECT + FROM + WHERE;
        TypedQuery<Curso> query;
            query = em.createQuery(consulta, Curso.class);
            if (anioFilter!=0) query.setParameter("Anio", anioFilter);
            if (semestreFilter!=0) query.setParameter("Semestre", semestreFilter);
            if (idAsignatura!=0) query.setParameter("idAsig", idAsignatura);
        try{
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }

    
}

