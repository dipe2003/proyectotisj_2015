
package Estudiante;

import Estudiante.estudios.ControladorEstudio;
import Estudiante.estudios.EnumTipoEstudio;
import Estudiante.estudios.Estudio;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@Stateless
@RequestScoped
public class FacadeEstudiante implements Serializable {
    @EJB
    private ControladorEstudiante cEst;
    @EJB
    private ControladorEstudio cEstudio;
    
    public FacadeEstudiante() {}

    /**
     * Agrega el estudio especificado al estudiante indicado por Id
     * @param TipoEstudio
     * @param Orientacion
     * @param idEstudiante 
     */
    public void agregarEstudiosEstudiante(EnumTipoEstudio TipoEstudio, String Orientacion, int idEstudiante){
        Estudiante estudiante = cEst.BuscarEstudiante(idEstudiante);
        Estudio estudio = cEstudio.CrearEstudio(TipoEstudio, Orientacion);
        estudiante.addEstudioCursado(estudio);
        cEst.ModificarEstudiante(estudiante);
    }
 }
