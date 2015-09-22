package Enumerados.TipoDePregunta;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorTipoPregunta {
    
    @EJB
    ManejadorTipoPregunta mTPregunta;
    
    /**
     * Crea un Tipo de Pregunta y la persiste.<br/>
     * El nombre del tipo de pregunta debe ser <b>unico</b>.
     * @param NombreTipoDePregunta
     * @return null si no fue creado.
     */
    public TipoPregunta CrearTipoDePregunta(String NombreTipoDePregunta){
        TipoPregunta tPregunta = new TipoPregunta(NombreTipoDePregunta);
        if (mTPregunta.CrearTipoDePregunta(tPregunta)!=-1){
            return tPregunta;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Tipo de Pregunta en la base de datos.
     * @param TipoDePregunta
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarTipoDePregunta(TipoPregunta TipoDePregunta){
        return mTPregunta.ModificarTipoPregunta(TipoDePregunta);
    }
    
    /**
     * Devuelve una lista de un Tipo de Pregunta desde la base de datos.
     * @return
     */
    public List<TipoPregunta> ListarTiposDePreguntas(){
        return mTPregunta.ListarTiposDePreguntas();
    }
    
}
