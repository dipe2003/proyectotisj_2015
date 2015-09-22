package Enumerados.EstadoCivil;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEstadoCivil {
    
    @EJB
    ManejadorEstadoCivil mECivil;
    
    /**
     * Crea un Estado Civil y lo persiste.<br/>
     * El nombre del estado civil debe ser <b>unico</b>.
     * @param NombreEstadoCivil
     * @return null si no fue creado.
     */
    public EstadoCivil CrearEstadoCivil(String NombreEstadoCivil){
        EstadoCivil est = new EstadoCivil(NombreEstadoCivil);
        if (mECivil.CrearEstadoCivil(est)!=-1){
            return est;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Estado Civil en la base de datos.
     * @param estadoCivil
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEstadoCivil(EstadoCivil estadoCivil){
        return mECivil.ModificarEstadoCivil(estadoCivil);
    }
    
    /**
     * Devuelve una lista de Estado Civil desde la base de datos.
     * @return
     */
    public List<EstadoCivil> ListarEstadoCivil(){
        return mECivil.ListarEstadoCivil();
    }
    
}
