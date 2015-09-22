
package Administrativo;

import Enumerados.EstadoCivil.EstadoCivil;
import Estudiante.EnumSexo;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

@Entity
public class Administrativo extends Usuario implements Serializable{

    public Administrativo() {
    }

    public Administrativo(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String ImagenUsuario, 
            int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, String DepartamentoUsuario, String LocalidadUsuario, 
            String TelefonoUsuario, String CelularUsuario, EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario, 
            EnumSexo SexoUsuario) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, ImagenUsuario, CedulaUsuario, CredencialCivicaUsuario, DomicilioUsuario, 
                DepartamentoUsuario, LocalidadUsuario, TelefonoUsuario, CelularUsuario, EstadoCivilUsuario, FechaNacimientoUsuario, LugarNacimientoUsuario, 
                SexoUsuario);
    }

    

 
    
}
