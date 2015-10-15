
package Docente;

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
public class ManejadorDocente {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearDocente(Docente docente){
        try{
            em.persist(docente);
            return docente.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarDocente(Docente docente){
        try{
            em.merge(docente);
            return docente.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarDocente(Docente docente){
        try{
            em.remove(docente);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Docente BuscarDocente(int id){
        try{
            return em.find(Docente.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Docente> ListarDocentes(){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d", Docente.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public String getNombreDocente(int id){
        try{
            return em.find(Docente.class, id).getNombreCompleto();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    /**
     * Todos los docentes que dictan clases en anio y semestre especificados
     * @param SemestreCurso
     * @param AnioCurso
     * @return 
     */
    public List<Docente> ListarDocentesCursoSemestreAnio(int SemestreCurso, int AnioCurso){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.AnioCurso= :anioCurso and c.SemestreCurso= :semestreCurso", Docente.class);
            query.setParameter("anioCurso", AnioCurso);
            query.setParameter("semestreCurso", SemestreCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los docentes que dictan clases en anio y asignatura especificados.
     * @param AnioCurso
     * @param IdAsignatura
     * @return 
     */
    public List<Docente> ListarDocentesCursoAsignaturaAnio(int AnioCurso, int IdAsignatura){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.AnioCurso= :anioCurso and c.AsignaturaCurso= :idAsignatura", Docente.class);
            query.setParameter("anioCurso", AnioCurso);
            query.setParameter("idAsignatura", IdAsignatura);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los docentes que dictan clases de asignatura y semestre especificados
     * @param SemestreCurso
     * @param IdAsignatura
     * @return 
     */
    public List<Docente> ListarDocentesCursoAsignaturaSemestre(int SemestreCurso, int IdAsignatura){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.SemestreCurso= :semestreCurso and c.AsignaturaCurso= :idAsignatura", Docente.class);
            query.setParameter("semestreCurso", SemestreCurso);
            query.setParameter("idAsignatura", IdAsignatura);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los docentes que dictan clases de la asignatura especificada
     * @param IdAsignatura
     * @return 
     */
    public List<Docente> ListarDocentesCursoAsignatura(int IdAsignatura){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.AsignaturaCurso= :idAsignatura", Docente.class);
            query.setParameter("idAsignatura", IdAsignatura);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los docentes que dictan clases en el año especificado
     * @param AnioCurso
     * @return 
     */
    public List<Docente> ListarDocentesCursoAnio(int AnioCurso){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.AnioCurso= :anioCurso", Docente.class);
            query.setParameter("anioCurso", AnioCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los docentes que dictan clases en el semestre especificado
     * @param SemestreCurso
     * @return 
     */
    public List<Docente> ListarDocentesCursoSemestre(int SemestreCurso){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.SemestreCurso= :semestreCurso", Docente.class);
            query.setParameter("semestreCurso", SemestreCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los docentes que dictan clases de asignatura y semestre especificados
     * @param SemestreCurso
     * @param IdAsignatura
     * @param AnioCurso
     * @return 
     */
    public List<Docente> ListarDocentesCursoAsignaturaSemestre(int SemestreCurso, int IdAsignatura, int AnioCurso){
        List<Docente> lista = new ArrayList<>();
        try{
            TypedQuery<Docente> query = em.createQuery("SELECT d FROM Docente d, Curso c WHERE c.SemestreCurso= :semestreCurso and c.AsignaturaCurso= :idAsignatura AND c.AnioCurso= :anioCurso", Docente.class);
            query.setParameter("semestreCurso", SemestreCurso);
            query.setParameter("idAsignatura", IdAsignatura);
            query.setParameter("anioCurso", AnioCurso);            
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
