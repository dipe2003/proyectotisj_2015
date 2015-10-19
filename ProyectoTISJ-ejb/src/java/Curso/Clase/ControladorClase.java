package Curso.Clase;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

@Stateless
@ManagedBean
public class ControladorClase {
    
    @EJB
    private ManejadorClase mClase;
    
    public Clase CrearClase(){
        return null;
    }
      
    public int ModificarClase(Clase clase){
        return 0;
    }
    
    public int BorrarClase(Clase clase){
        return 0;
    }
    
    public Clase BuscarClase(int idClase){
        return null;
    }
    
    public List<Clase> ListarClases(){
        return null;
    }
}
