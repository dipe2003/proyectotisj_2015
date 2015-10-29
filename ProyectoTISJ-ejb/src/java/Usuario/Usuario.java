
package Usuario;

import Enumerados.EstadoCivil.EstadoCivil;
import Usuario.Estudiante.EnumSexo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
abstract public class Usuario implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdUsuario;
    private String NombreUsuario;
    private String ApellidoUsuario;
    private String CorreoUsuario;
    private String PasswordUsuario;
    private String SaltPasswordUsuario;
    private String ImagenUsuario;
    private int CedulaUsuario;
    private String CredencialCivicaUsuario;
    private String DomicilioUsuario;
    private String DepartamentoUsuario;
    private String LocalidadUsuario;
    private String TelefonoUsuario;
    private String CelularUsuario;
    @OneToOne
    @JoinColumn(name="EstadoCivil_Id", insertable=true, updatable=true)
    private EstadoCivil EstadoCivilUsuario;
    @Temporal(value = TemporalType.DATE)
    private Date FechaNacimientoUsuario;
    private String LugarNacimientoUsuario;
    private EnumSexo SexoUsuario;
    
    //  Constructores
    public Usuario() { }
    public Usuario(String NombreUsuario, String ApellidoUsuario, String CorreoUsuario, String PasswordUsuario, String SaltPassword,
            String ImagenUsuario, int CedulaUsuario, String CredencialCivicaUsuario, String DomicilioUsuario, 
            String DepartamentoUsuario, String LocalidadUsuario, String TelefonoUsuario, String CelularUsuario, 
            EstadoCivil EstadoCivilUsuario, Date FechaNacimientoUsuario, String LugarNacimientoUsuario, 
            EnumSexo SexoUsuario) {
        this.NombreUsuario = NombreUsuario;
        this.ApellidoUsuario = ApellidoUsuario;
        this.CorreoUsuario = CorreoUsuario;
        this.PasswordUsuario = PasswordUsuario;
        this.SaltPasswordUsuario = SaltPassword;
        this.ImagenUsuario = ImagenUsuario;
        this.CedulaUsuario = CedulaUsuario;
        this.CredencialCivicaUsuario = CredencialCivicaUsuario;
        this.DomicilioUsuario = DomicilioUsuario;
        this.DepartamentoUsuario = DepartamentoUsuario;
        this.LocalidadUsuario = LocalidadUsuario;
        this.TelefonoUsuario = TelefonoUsuario;
        this.CelularUsuario = CelularUsuario;
        this.EstadoCivilUsuario = EstadoCivilUsuario;
        this.FechaNacimientoUsuario = FechaNacimientoUsuario;
        this.LugarNacimientoUsuario = LugarNacimientoUsuario;
        this.SexoUsuario = SexoUsuario;
    }
    
        //  Getters
    public int getIdUsuario() {return IdUsuario;}
    public String getNombreUsuario() {return NombreUsuario;}
    public String getApellidoUsuario() {return ApellidoUsuario;}
    public String getCorreoUsuario() {return CorreoUsuario;}
    public String getPasswordUsuario() {return PasswordUsuario;}
    public int getCedulaUsuario() {return CedulaUsuario;}
    public String getCredencialCivicaUsuario() {return CredencialCivicaUsuario;}
    public String getDomicilioUsuario() {return DomicilioUsuario;}
    public String getDepartamentoUsuario() {return DepartamentoUsuario;}
    public String getLocalidadUsuario() {return LocalidadUsuario;}
    public String getTelefonoUsuario() {return TelefonoUsuario;}
    public String getCelularUsuario() {return CelularUsuario;}
    public String getImagenURL(){return this.ImagenUsuario;}
    public EstadoCivil getEstadoCivilUsuario() {return EstadoCivilUsuario;}
    public Date getFechaNacimientoUsuario() {return FechaNacimientoUsuario;}
    public EnumSexo getSexoUsuario() {return SexoUsuario;}
    public String getLugarNacimientoUsuario() {return LugarNacimientoUsuario;}
    public String getSaltPasswordUsuario() {return SaltPasswordUsuario;}
    public String getImagenUsuario() {return ImagenUsuario;}
    
    //  Setters
    public void setIdUsuario(int IdUsuario) {this.IdUsuario = IdUsuario;}
    public void setNombreUsuario(String NombreUsuario) {this.NombreUsuario = NombreUsuario;}
    public void setApellidoUsuario(String ApellidoUsuario) {this.ApellidoUsuario = ApellidoUsuario;}
    public void setCorreoUsuario(String CorreoUsuario) {this.CorreoUsuario = CorreoUsuario;}
    public void setPasswordUsuario(String PasswordUsuario) {this.PasswordUsuario = PasswordUsuario;}
    public void setCedulaUsuario(int CedulaUsuario) {this.CedulaUsuario = CedulaUsuario;}
    public void setCredencialCivicaUsuario(String CredencialCivicaUsuario) {this.CredencialCivicaUsuario = CredencialCivicaUsuario;}
    public void setDomicilioUsuario(String DomicilioUsuario) {this.DomicilioUsuario = DomicilioUsuario;}
    public void setDepartamentoUsuario(String DepartamentoUsuario) {this.DepartamentoUsuario = DepartamentoUsuario;}
    public void setLocalidadUsuario(String LocalidadUsuario) {this.LocalidadUsuario = LocalidadUsuario;}
    public void setTelefonoUsuario(String TelefonoUsuario) {this.TelefonoUsuario = TelefonoUsuario;}
    public void setCelularUsuario(String CelularUsuario) {this.CelularUsuario = CelularUsuario;}
    public void setImagenURL(String ImagenURL){this.ImagenUsuario = ImagenURL;}
    public void setEstadoCivilUsuario(EstadoCivil EstadoCivilUsuario) {this.EstadoCivilUsuario = EstadoCivilUsuario;}
    public void setFechaNacimientoUsuario(Date FechaNacimientoUsuario) {this.FechaNacimientoUsuario = FechaNacimientoUsuario;}
    public void setLugarNacimientoUsuario(String LugarNacimientoUsuario) {this.LugarNacimientoUsuario = LugarNacimientoUsuario;}
    public void setSexoUsuario(EnumSexo SexoUsuario) {this.SexoUsuario = SexoUsuario;}
    public void setSaltPasswordUsuario(String SaltPasswordUsuario) {this.SaltPasswordUsuario = SaltPasswordUsuario;}
    public void setImagenUsuario(String ImagenUsuario) {this.ImagenUsuario = ImagenUsuario;}
        
    /**
     * Compara si el password coincide con el de usuario.
     * @param Password
     * @return 
     */
    public boolean isValidPass(String Password){return Password.equals(this.PasswordUsuario);}
    
    /**
     * Devuelve el nombre completo del usuario.
     * @return 
     */
    public String getNombreCompleto(){return this.NombreUsuario + " " + this.ApellidoUsuario;}

}