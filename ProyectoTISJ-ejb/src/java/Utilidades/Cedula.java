
package Utilidades;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

@Named
@Stateless
@SessionScoped
public class Cedula implements Serializable{

   public Cedula() {}    

   /**
    * Comprueba que la cadena ingresada como Cedula de Identidad sea valida.
    * @param CedulaAComprobar
    * @return False: si no es valida.
    */
   public boolean EsCedulaValida(String CedulaAComprobar){
      int cedula = 0;
      int digitoVerificador = 0;
       
      try{
        if (CedulaAComprobar.length()<8) {
           return false;
        }else{
            digitoVerificador = Integer.parseInt(CedulaAComprobar.substring(7));
            int sum = 0;
            int[] multiplicador= {2, 9, 8, 7, 6, 3, 4};
            for (int i = 0; i < 7; i++) {
                int actual = Integer.parseInt(CedulaAComprobar.substring(i,i+1));
                sum += Integer.parseInt(CedulaAComprobar.substring(i,i+1))*multiplicador[i];
            }
            int comprobacion = (10 - (sum % 10) ) % 10;
            if (comprobacion != digitoVerificador) {
                return false;
            }           
        }
      }catch(NumberFormatException ex){
          return false;
      }      
      return true;
   }
   
}
