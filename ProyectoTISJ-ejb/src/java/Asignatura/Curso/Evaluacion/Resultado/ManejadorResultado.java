
package Asignatura.Curso.Evaluacion.Resultado;

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
public class ManejadorResultado {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearResultado(Resultado resultado){
        try{
            em.persist(resultado);
            return resultado.getIdResultado();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarResultado(Resultado resultado){
        try{
            em.merge(resultado);
            return resultado.getIdResultado();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarResultado(Resultado resultado){
        try{
            em.remove(resultado);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Resultado BuscarResultado(int id){
        try{
            return em.find(Resultado.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Resultado> ListarResultados(){
        List<Resultado> lista = new ArrayList<>();
        try{
            TypedQuery<Resultado> query = em.createQuery("FROM Resultado e", Resultado.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
