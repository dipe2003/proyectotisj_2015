
package Docente;

import Usuario.Usuario;
import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Docente extends Usuario implements Serializable{
    private String ContratoDocente;

    public Docente() {
    }

    public Docente(String ContratoDocente, String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario) {
        super(NickUsuario, NombreUsuario, CorreoUsuario, PasswordUsuario, CedulaUsuario);
        this.ContratoDocente = ContratoDocente;
    }

    public String getContratoDocente() {return ContratoDocente;}

    public void setContratoDocente(String ContratoDocente) {this.ContratoDocente = ContratoDocente;}
    
    
}
