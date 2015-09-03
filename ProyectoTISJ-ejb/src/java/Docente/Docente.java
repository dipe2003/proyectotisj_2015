
package Docente;

import Usuario.Usuario;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Docente extends Usuario implements Serializable{

    public Docente() {}

    public Docente(String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario) {
        super(NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
    }    
    
}
