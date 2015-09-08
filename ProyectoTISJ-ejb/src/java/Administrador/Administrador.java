
package Administrador;

import Usuario.Usuario;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario implements Serializable{

    public Administrador() {
        
    }

    public Administrador(String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String ImagenUsuario) {
        super(NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
    }
    
    
}
