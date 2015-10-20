
package Curso.Clase;

import java.io.IOException;
import java.sql.SQLException;
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
public class ManejadorClase {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearClase(Clase clase){
        try{
            em.persist(clase);
            return clase.getIdClase();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int ModificarClase(Clase clase){
        try{
            em.merge(clase);
            return clase.getIdClase();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarClase(Clase clase){
        try{
            em.remove(clase);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Clase BuscarClase(int id){
        try{
            return em.find(Clase.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Clase> ListarClases(){
        List<Clase> lista = new ArrayList<>();
        try{
            TypedQuery<Clase> query = em.createQuery("SELECT c FROM Clase c", Clase.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }    
    public List<Clase> ListarClases(int IdCurso){
        List<Clase> lista = new ArrayList<>();
        try{
            TypedQuery<Clase> query = em.createQuery("SELECT c FROM Clase c WHERE c.CursoClase.IdCurso= :idCurso", Clase.class);
            query.setParameter("idCurso", IdCurso);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
    public int GetAsistenciasEstudianteCurso(int IdCurso, int IdEstudiante){
        Query query = em.createQuery("SELECT COUNT(c.FechaClase) FROM Clase c, Estudiante e WHERE e MEMBER OF c.AsistenciasClase AND c.CursoClase= :idCurso");
        try{
            return (int) query.getSingleResult();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());}
        return -1;
    }
}

