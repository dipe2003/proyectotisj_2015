
package Estudiante;

import java.util.List;
import javax.ejb.EJB;


public class ControladorEstudiante {
    @EJB
    ManejadorEstudiante mEst;
    
    /**
     * Crea un Estudiante y lo persiste.
     * @param FormInscripcion 
     * @param NickUsuario
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaIdentidadUsuario
     * @return Devulve un Estudiante si fue creado, de lo contrario devuelve null.
     */
    public Estudiante CrearEstudiante(String FormInscripcion, String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario,  int CedulaIdentidadUsuario){
        Estudiante est = new Estudiante(FormInscripcion, NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario,  CedulaIdentidadUsuario);
        if (mEst.CrearEstudiante(est)!=-1) {
            return est;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Estudiante en la base de datos.
     * @param estudiante
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEstudiante(Estudiante estudiante){
        return mEst.ModificarEstudiante(estudiante);
    }
    
    /**
     * Borra un Estudiante de la base de datos.
     * @param estudiante
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEstudiante(Estudiante estudiante){
        return mEst.BorrarEstudiante(estudiante);
    }
    
    /**
     * Busca un Estudiante en la base de datos.
     * @param id
     * @return Devuelve null si el Estudiante no se pudo encontrar.
     */
    public Estudiante BuscarEstudiante(int id){
        return mEst.BuscarEstudiante(id);
    }
    
    /**
     * Devuelve una lista de Estudiantes desde la base de datos.
     * @return 
     */
    public List<Estudiante> ListarEstudiantees(){
        return mEst.ListarEstudiantes();
    }
}
