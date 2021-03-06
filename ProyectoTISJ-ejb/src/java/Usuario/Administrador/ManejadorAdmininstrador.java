
package Usuario.Administrador;

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
public class ManejadorAdmininstrador {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearAdministrador(Administrador administrador){
        try{
            em.persist(administrador);
            return administrador.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }        
    }
    
    public int ModificarAdministrador(Administrador administrador){
        try{
            em.merge(administrador);
            return administrador.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarAdministrador(Administrador administrador){
        try{
            em.remove(administrador);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Administrador BuscarAdministrador(int id){
        try{
            return em.find(Administrador.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Administrador> ListarAdministradores(){
        List<Administrador> lista = new ArrayList<>();
        try{
            TypedQuery<Administrador> query = em.createQuery("SELECT a FROM Administrador a", Administrador.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
        
}
    
