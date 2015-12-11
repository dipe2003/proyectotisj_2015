
package Usuario;

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
public class ManejadorUsuario {
    @PersistenceContext(unitName = "ProyectoTISJ_PU")
    private EntityManager em ;
    
    public int CrearUsuario(Usuario usuario){
        try{
            em.persist(usuario);
            return usuario.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int ModificarUsuario(Usuario usuario){
        try{
            em.merge(usuario);
            return usuario.getIdUsuario();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public int BorrarUsuario(Usuario usuario){
        try{
            em.remove(usuario);
            return 1;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return -1;
        }
    }
    
    public Usuario BuscarUsuarioPorId(int id){
        try{
            return em.find(Usuario.class, id);
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public List<Usuario> BuscarUsuarioLogin(int Cedula, String Password){
        List<Usuario> Usuarios = new ArrayList<>();
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.CedulaUsuario= :cedula AND u.PasswordUsuario= :pass", Usuario.class);
            query.setParameter("pass", Password);
            query.setParameter("cedula", Cedula);
            Usuarios = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return Usuarios;
    }
    
    public List<Usuario> BuscarUsuarioPorCedula(int Cedula){
        List<Usuario> Usuarios = new ArrayList<>();
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.CedulaUsuario= :cedula", Usuario.class);
            query.setParameter("cedula", Cedula);
            Usuarios = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return Usuarios;
    }
    
    public List<Usuario> ListarUsuarios(){
        List<Usuario> lista = new ArrayList<>();
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.ApellidoUsuario", Usuario.class);
            lista = query.getResultList();
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return lista;
    }
    
     public boolean ExisteUsuario(int Cedula){
        try{
            Query query = em.createQuery("SELECT u.CedulaUsuario FROM Usuario u WHERE u.CedulaUsuario= :cedula");
            query.setParameter("cedula", Cedula);
            if (!query.getResultList().isEmpty()) return true;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }
    
}

