
package Usuario;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdUsuario;
    private String NickUsuario;
    private String NombreUsuario;
    private String CorreoUsuario;
    private String PasswordUsuario;
    private String ImagenUsuario;
    private int CedulaUsuario;
    
    public Usuario() { }

    public Usuario(String NickUsuario, String NombreUsuario, String CorreoUsuario, String PasswordUsuario, int CedulaUsuario, String ImagenUsuario) {
        this.NickUsuario = NickUsuario;
        this.NombreUsuario = NombreUsuario;
        this.CorreoUsuario = CorreoUsuario;
        this.PasswordUsuario = PasswordUsuario;
        this.CedulaUsuario = CedulaUsuario;  
        this.ImagenUsuario = ImagenUsuario;
    }

    public int getIdUsuario() { return IdUsuario; }

    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}

    public String getNickUsuario() {return NickUsuario;}

    public void setNickUsuario(String NickUsuario) {this.NickUsuario = NickUsuario;}

    public String getNombreUsuario() {return NombreUsuario;}

    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}

    public String getCorreoUsuario() {return CorreoUsuario;}

    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}

    public String getPasswordUsuario() {return PasswordUsuario;}

    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}

    public int getCedulaUsuario() {return CedulaUsuario;}

    public void setCedulaUsuario(int CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    
    public String getImagenURL(){return this.ImagenUsuario;}
    
    public void setImagenURL(String ImagenURL){this.ImagenUsuario = ImagenURL;}
   
    
}
