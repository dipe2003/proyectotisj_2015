
package Usuario.Estudiante;

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
public class ManejadorEstudiante {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearEstudiante(Estudiante estudiante){
        try{
            em.persist(estudiante);
            return estudiante.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarEstudiante(Estudiante estudiante){
        try{
            em.merge(estudiante);
            return estudiante.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarEstudiante(Estudiante estudiante){
        try{
            em.remove(estudiante);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Estudiante BuscarEstudiante(int id){
        try{
            return em.find(Estudiante.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Estudiante> ListarEstudiantes(){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Estudiante> ListarEstudiantes(List<Integer> IdsEstudiantes){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e WHERE e.IdUsuario IN (:Ids)", Estudiante.class);
            query.setParameter("Ids", IdsEstudiantes);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Integer> ListarEstudios(int IdEstudiante){
        List<Integer> lista = new ArrayList<>();
        try{
            Query query = em.createQuery("SELECT estudio.IdEstudio FROM Estudio estudio, Estudiante e WHERE e.IdUsuario= :IdEstudiante AND estudio.IdEstudio IN e.EstudiosCursadosEstudiante");
            query.setParameter("IdEstudiante", IdEstudiante);
            query.setParameter("IdEstudiante", IdEstudiante);            
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Estudiante> ListarEstudiantesCurso(int IdCurso){
        List<Estudiante> estudiantes = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.IdCurso= :idCurso" , Estudiante.class);
            query.setParameter("idCurso", IdCurso);
            estudiantes = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return estudiantes;
    }
    
    public List<Estudiante> ListarEstudiantesSinCurso(int IdCurso){
        List<Estudiante> estudiantes = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c NOT MEMBER OF e.CursosEstudiante AND c.IdCurso= :idCurso" , Estudiante.class);
            query.setParameter("idCurso", IdCurso);
            estudiantes = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return estudiantes;
    }
        
     /**
     * Todos los Estudiantes que cursan clases en anio y semestre especificados
     * @param SemestreCurso
     * @param AnioCurso
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoSemestreAnio(int SemestreCurso, int AnioCurso){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.AnioCurso= :anioCurso and c.SemestreCurso= :semestreCurso", Estudiante.class);
            query.setParameter("anioCurso", AnioCurso);
            query.setParameter("semestreCurso", SemestreCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los estudiantes que cursan clases en anio y asignatura especificados.
     * @param AnioCurso
     * @param IdAsignatura
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoAsignaturaAnio(int AnioCurso, int IdAsignatura){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.AnioCurso= :anioCurso and c.AsignaturaCurso.IdAsignatura= :idAsignatura", Estudiante.class);
            query.setParameter("anioCurso", AnioCurso);
            query.setParameter("idAsignatura", IdAsignatura);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los estudiantes que cursan clases de asignatura y semestre especificados
     * @param SemestreCurso
     * @param IdAsignatura
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoAsignaturaSemestre(int SemestreCurso, int IdAsignatura){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.SemestreCurso= :semestreCurso and c.AsignaturaCurso.IdAsignatura= :idAsignatura", Estudiante.class);
            query.setParameter("semestreCurso", SemestreCurso);
            query.setParameter("idAsignatura", IdAsignatura);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los estudiantes que cursan clases de la asignatura especificada
     * @param IdAsignatura
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoAsignatura(int IdAsignatura){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.AsignaturaCurso.IdAsignatura= :idAsignatura", Estudiante.class);
            query.setParameter("idAsignatura", IdAsignatura);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los estudiantes que cursan clases en el año especificado
     * @param AnioCurso
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoAnio(int AnioCurso){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.AnioCurso= :anioCurso", Estudiante.class);
            query.setParameter("anioCurso", AnioCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los estudiantes que cursan clases en el semestre especificado
     * @param SemestreCurso
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoSemestre(int SemestreCurso){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.SemestreCurso= :semestreCurso", Estudiante.class);
            query.setParameter("semestreCurso", SemestreCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    /**
     * Todos los estudiantes que cursan clases de asignatura y semestre especificados
     * @param SemestreCurso
     * @param IdAsignatura
     * @param AnioCurso
     * @return 
     */
    public List<Estudiante> ListarEstudiantesCursoAsignaturaSemestre(int SemestreCurso, int IdAsignatura, int AnioCurso){
        List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("SELECT e FROM Estudiante e, Curso c WHERE c MEMBER OF e.CursosEstudiante AND c.SemestreCurso= :semestreCurso and c.AsignaturaCurso.IdAsignatura= :idAsignatura AND c.AnioCurso= :anioCurso", Estudiante.class);
            query.setParameter("semestreCurso", SemestreCurso);
            query.setParameter("idAsignatura", IdAsignatura);
            query.setParameter("anioCurso", AnioCurso);            
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public List<Estudiante> ListarEstudiantesEncuesta(int IdEncuesta){
         List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("select e from Estudiante e, Encuesta enc WHERE e MEMBER OF enc.EstudiantesEncuesta and enc.IdEncuesta= :idencuesta", Estudiante.class);
            query.setParameter("idencuesta", IdEncuesta);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    public List<Estudiante> ListarEstudiantesEncuestaCurso(int IdEncuesta){
         List<Estudiante> lista = new ArrayList<>();
        try{
            TypedQuery<Estudiante> query = em.createQuery("select e from Estudiante e, Curso c, Encuesta enc WHERE e MEMBER OF c.EstudiantesCurso and enc.CursoEncuesta = c and enc.IdEncuesta= :idencuesta", Estudiante.class);
            query.setParameter("idencuesta", IdEncuesta);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
}
    
