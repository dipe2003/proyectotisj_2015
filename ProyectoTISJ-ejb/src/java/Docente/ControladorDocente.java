package Docente;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorDocente {
    
    @EJB
    ManejadorDocente mDoc;
    
    /**
     * Crea un Docente y lo persiste.
     * @param NickUsuario
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @param ImagenUsuario
     * @return Devuelve un Docente si fue creado, de lo contrario devuelve null.
     */
    public Docente CrearDocente(String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String ImagenUsuario){
        Docente doc = new Docente(NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
        if (mDoc.CrearDocente(doc)!=-1){
            return doc;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Docente en la base de datos.
     * @param docente
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarDocente(Docente docente){
        return mDoc.ModificarDocente(docente);
    }
    
    /**
     * Borra los datos de un Docente en la base de datos.
     * @param docente
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarDocente(Docente docente){
        return mDoc.BorrarDocente(docente);
    }
    
    /**
     * Busca un Docente en la base de datos.
     * @param id
     * @return Devuelve null si no se pudo encontrar.
     */
    public Docente BuscarDocente(int id){
        return mDoc.BuscarDocente(id);
    }
    
    /**
     * Devuelve una lista de Docentes desde la base de datos.
     * @return 
     */
    public List<Docente> ListarDocentes(){
        return mDoc.ListarDocentes();
    }
    
}
