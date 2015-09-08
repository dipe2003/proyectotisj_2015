
package Administrativo;

import Usuario.Usuario;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Administrativo extends Usuario implements Serializable{

    public Administrativo() {
    }

    public Administrativo(String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String ImagenUsuario) {
        super(NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario, ImagenUsuario);
    }
    
}
