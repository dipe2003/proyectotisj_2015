package Usuario.Estudiante.Estudios;

import Enumerados.TipoDeEstudio.TipoEstudio;
import Usuario.Estudiante.Estudios.ManejadorEstudio;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorEstudio {
    @EJB
    ManejadorEstudio mEst;
    
    /**
     * Crea un Estudio y lo persiste.
     * @param TipoDeEstudio 
     * @param Orientacion
     * @return Devulve un Estudio si fue creado, de lo contrario devuelve null.
     */
    public Estudio CrearEstudio(TipoEstudio TipoDeEstudio, String Orientacion){
        Estudio est = new Estudio(TipoDeEstudio, Orientacion);
        if (mEst.CrearEstudio(est)!=-1) {
            return est;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Estudio en la base de datos.
     * @param estudio
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarEstudio(Estudio estudio){
        return mEst.ModificarEstudio(estudio);
    }
    
    /**
     * Borra un Estudio de la base de datos.
     * @param estudio
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarEstudio(Estudio estudio){
        return mEst.BorrarEstudio(estudio);
    }
    
    /**
     * Busca un Estudio en la base de datos.
     * @param id
     * @return Devuelve null si el Estudio no se pudo encontrar.
     */
    public Estudio BuscarEstudio(int id){
        return mEst.BuscarEstudio(id);
    }
    
    /**
     * Devuelve una lista de Estudios desde la base de datos.
     * @return 
     */
    public List<Estudio> ListarEstudios(){
        return mEst.ListarEstudios();
    }
    
    /**
     * Devuelve uns lista de strings con los estudios cursados y su orientacion
     * @param IdsEstudios lista de ids de estudios a devolver.
     * @return 
     */
    public List<String> ListarEstudiosOrientacion(List<Integer> IdsEstudios){
        List<Estudio> estudios = mEst.ListarEstudios(IdsEstudios);
        List<String> estudiosOrientacion = new ArrayList<>();
        for (int i = 0; i < estudios.size(); i++) {
            estudiosOrientacion.add(estudios.get(i).getTipoEstudio().getTipoDeEstudio()+"-"+estudios.get(i).getOrientacionEstudio());
        }
        return estudiosOrientacion;
    }
}
