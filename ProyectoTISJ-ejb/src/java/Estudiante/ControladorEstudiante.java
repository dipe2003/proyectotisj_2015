
package Estudiante;

import Enumerados.EstadoCivil.EstadoCivil;
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
     * Crea un estudiante y los persiste.
     * @param FormInscripcion
     * @param NombreUsuario
     * @param ApellidoUsuario
     * @param CorreoUsuario
     * @param PasswordUsuario
     * @param ImagenUsuario
     * @param CedulaUsuario del tipo 12345672 (sin puntos ni guiones)
     * @param CredencialCivicaUsuario
     * @param DomicilioUsuario
     * @param DepartamentoUsuario
     * @param LocalidadUsuario
     * @param TelefonoUsuario
     * @param CelularUsuario
     * @param EstadoCivilUsuario
     * @param FechaNacimientoUsuario
     * @param LugarNacimientoUsuario
     * @param SexoUsuario
     * @return null si no se creo el estudiante
     */
    public Estudiante CrearEstudiante(String FormInscripcion, String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, 
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, 
            String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, 
            String LugarNacimientoUsuario, EnumSexo SexoUsuario) {
        Estudiante est = new Estudiante(FormInscripcion, NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario, 
                CredencialCivicaUsuario, DomicilioUsuario, DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario, 
                FechaNacimientoUsuario, LugarNacimientoUsuario, SexoUsuario);        
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
    public List<Estudiante> ListarEstudiantes(){
        return mEst.ListarEstudiantes();
    }
}
