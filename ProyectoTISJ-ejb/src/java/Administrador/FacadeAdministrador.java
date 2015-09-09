
package Administrador;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@Stateless
@SessionScoped
public class FacadeAdministrador implements Serializable {

    @EJB
    private ControladorAdministrador cAdmin;

    
    public FacadeAdministrador() {}
    
    /**
     * Modifica los datos de un Administrador en la base de datos.
     * @param Admin
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarAdministrador(Administrador Admin){
        return cAdmin.ModificarAdministrador(Admin);
    }
    
    
}
