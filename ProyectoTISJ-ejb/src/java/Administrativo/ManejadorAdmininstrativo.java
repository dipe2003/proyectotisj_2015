
package Administrativo;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@TransactionManagement(TransactionManagementType.CONTAINER)
public class ManejadorAdmininstrativo {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearAdministrativo(Administrativo administrativo){
        try{
            em.persist(administrativo);
            return administrativo.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarAdministrativo(Administrativo administrativo){
        try{
            em.merge(administrativo);
            return administrativo.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarAdministrativo(Administrativo administrativo){
        try{
            em.remove(administrativo);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Administrativo BuscarAdministrativo(int id){
        try{
            return em.find(Administrativo.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Administrativo> ListarAdministrativos(){
        List<Administrativo> lista = new ArrayList<>();
        try{
            TypedQuery<Administrativo> query = em.createQuery("SELECT a FROM Administrativo a", Administrativo.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
