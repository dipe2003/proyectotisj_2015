
package Administrador;

import Usuario.Usuario;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario implements Serializable{

    public Administrador() {
        
    }

    public Administrador(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario) {
        super(NombreUsuario, ApellidoUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
    }
    
    
}
