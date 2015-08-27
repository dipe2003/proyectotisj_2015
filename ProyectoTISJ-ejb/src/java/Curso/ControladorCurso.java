package Curso;

import Docente.Docente;
import Docente.ManejadorDocente;
import java.util.List;
import javax.ejb.EJB;

public class ControladorCurso {
    
    @EJB
    ManejadorDocente mDoc;
    
    /**
     * Crea un Docente y lo persiste.
     * @param ContratoDocente 
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @return Devuelve un Docente si fue creado, de lo contrario devuelve null.
     */
    public Docente CrearDocente(String ContratoDocente, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario){
        Docente doc = new Docente(ContratoDocente, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
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
