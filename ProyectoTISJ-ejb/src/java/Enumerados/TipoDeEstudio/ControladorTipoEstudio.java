package Enumerados.TipoDeEstudio;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorTipoEstudio {
    
    @EJB
    ManejadorTipoEstudio mTEstudios;
    
    /**
     * Crea un Tipo de Estudio y lo persiste.<br/>
     * El nombre del tipo de estudio debe ser <b>unico</b>.
     * @param NombreTipoDeEstudio
     * @return null si no fue creado.
     */
    public TipoEstudio CrearTipoDeEstudio(String NombreTipoDeEstudio){
        TipoEstudio estudio = new TipoEstudio(NombreTipoDeEstudio);
        if (mTEstudios.CrearTipoDeEstudio(estudio)!=-1){
            return estudio;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Tipo de Estudio en la base de datos.
     * @param TipoDeEstudio
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarTipoDeEstudio(TipoEstudio TipoDeEstudio){
        return mTEstudios.ModificarTipoEstudio(TipoDeEstudio);
    }
    
    /**
     * Devuelve una lista de Tipos de Estudios desde la base de datos.
     * @return
     */
    public List<TipoEstudio> ListarTiposDeEstudios(){
        return mTEstudios.ListarTipoDeEstudios();
    }
    
}
