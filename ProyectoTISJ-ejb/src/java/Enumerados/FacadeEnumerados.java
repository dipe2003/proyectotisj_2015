
package Enumerados;

import Enumerados.EstadoCivil.ControladorEstadoCivil;
import Enumerados.EstadoCivil.EstadoCivil;
import Enumerados.TipoDeEstudio.ControladorTipoEstudio;
import Enumerados.TipoDeEstudio.TipoEstudio;
import Enumerados.TipoDePregunta.ControladorTipoPregunta;
import Enumerados.TipoDePregunta.TipoPregunta;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeEnumerados implements Serializable {
    @EJB
    private ControladorTipoPregunta cTPreguntas;
    @EJB
    private ControladorTipoEstudio cTEstudio;
    @EJB
    private ControladorEstadoCivil cECivil;
    
    
    public FacadeEnumerados() {}

     /**
     * Devuelve el id del tipo de pregunta registrado.<br/>
     * El nombre del tipo de pregunta debe ser <b>unico</b>
     * @param NombreTipoDePregunta 
     * @return -1 si no se registro.
     */
    public int crearTipoDePregunta(String NombreTipoDePregunta){
        TipoPregunta tPregunta = cTPreguntas.CrearTipoDePregunta(NombreTipoDePregunta);
        if (tPregunta!=null) {
            return tPregunta.getIdTipoPregunta();
        }
        return -1;
    }
    /**
     * Devuelve los tipos de preguntas registrados en la base de datos.
     * @return 
     */
    public List<TipoPregunta> ListarTipoDePreguntas(){
        return cTPreguntas.ListarTiposDePreguntas();
    }
    
    /**
     * Devuelve el id del estado civil registrado.<br/>
     * El nombre del estado civil debe ser <b>unico</b>
     * @param NombreEstadoCivil
     * @return -1 si no se registro.
     */
    public int crearEstadoCivil(String NombreEstadoCivil){
        EstadoCivil est = cECivil.CrearEstadoCivil(NombreEstadoCivil);
        if (est!=null) {
            return est.getIdEstadoCivil();
        }
        return -1;
    }
    
    /**
     * Devuelve los estados civiles registrados en la base de datos.
     * @return 
     */
    public List<EstadoCivil> ListarEstadosCiviles(){
        return cECivil.ListarEstadoCivil();
    }
    
    /**
     * Devuelve el id del tipo de estudio registrado.<br/>
     * El nombre del tipo de estudio debe ser <b>unico</b>
     * @param NombreTipoDeEstudio 
     * @return -1 si no se registro.
     */
    public int crearTipoDeEstudio(String NombreTipoDeEstudio){
        TipoEstudio est = cTEstudio.CrearTipoDeEstudio(NombreTipoDeEstudio);
        if (est!=null) {
            return est.getIdTipoEstudio();
        }
        return -1;
    }
    /**
     * Devuelve los tipos de estudios registrados en la base de datos.
     * @return 
     */
    public List<TipoEstudio> ListarTiposDeEstudios(){
        return cTEstudio.ListarTiposDeEstudios();
    }
    
    
    
 }
