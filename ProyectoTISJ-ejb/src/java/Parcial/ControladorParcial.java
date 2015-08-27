
package Parcial;

import Curso.Curso;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;


public class ControladorParcial {
    @EJB
    ManejadorParcial mParcial;
    
    /**
     * Crea un Parcial y lo persiste.
     * @param FechaEvaluacion 
     * @param CursoParcial
     * @return Devuelve un Parcial si fue creado, de lo contrario devuelve null.
     */
    public Parcial CrearParcial(Date FechaEvaluacion, Curso CursoParcial){
        Parcial parcial = new Parcial(FechaEvaluacion, CursoParcial);
        if (mParcial.CrearParcial(parcial)!=-1) {
            return parcial;
        }
        return null;
    }
    
    /**
     * Modifica los datos de un Parcial en la base de datos.
     * @param parcial
     * @return Devuelve -1 si no se pudo actualizar.
     */
    public int ModificarParcial(Parcial parcial){
        return mParcial.ModificarParcial(parcial);
    }
    
    /**
     * Borra un Parcial de la base de datos.
     * @param parcial
     * @return Devuelve -1 si no se pudo borrar.
     */
    public int BorrarParcial(Parcial parcial){
        return mParcial.BorrarParcial(parcial);
    }
    
    /**
     * Busca un Parcial en la base de datos.
     * @param id
     * @return Devuelve null si el Parcial no se pudo encontrar.
     */
    public Parcial BuscarParcial(int id){
        return mParcial.BuscarParcial(id);
    }
    
    /**
     * Devuelve una lista de Parciales desde la base de datos.
     * @return 
     */
    public List<Parcial> ListarParciales(){
        return mParcial.ListarParciales();
    }
}
