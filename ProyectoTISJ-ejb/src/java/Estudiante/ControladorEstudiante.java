
package Estudiante;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEstudiante {
    @EJB
    ManejadorEstudiante mEst;
    
    /**
     * Crea un Estudiante y lo persiste
     * @param SexoEstudiante
     * @param LugarNcimientoEstudiante
     * @param FechaNacimientoEstudiante
     * @param LugarNacimietoEstudiante
     * @param EstadoCivilEstudiante
     * @param CredencialCivicaEstudiante
     * @param DomicilioEstudiante
     * @param DepartamentoEstudiante
     * @param LocalidadEstudiante
     * @param TelefonoEstudiante
     * @param CelularEstudiante
     * @param FormInscripcion
     * @param NombreUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param CedulaUsuario
     * @param ImagenUsuario
     * @return Devulve un Estudiante si fue creado, de lo contrario devuelve null.
     */
    public Estudiante CrearEstudiante(EnumSexo SexoEstudiante, String LugarNcimientoEstudiante, Date FechaNacimientoEstudiante, String LugarNacimietoEstudiante, 
            EnumEstadoCivil EstadoCivilEstudiante, String CredencialCivicaEstudiante, String DomicilioEstudiante, String DepartamentoEstudiante, 
            String LocalidadEstudiante, String TelefonoEstudiante, String CelularEstudiante, String FormInscripcion, String NombreUsuario, 
            String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String ImagenUsuario) {
        Estudiante est = new Estudiante(SexoEstudiante, LugarNcimientoEstudiante, FechaNacimientoEstudiante, LugarNacimietoEstudiante, EstadoCivilEstudiante, 
                CredencialCivicaEstudiante, DomicilioEstudiante, DepartamentoEstudiante, LocalidadEstudiante, TelefonoEstudiante, CelularEstudiante, FormInscripcion, 
                NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);        
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
