
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.TipoPasoFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoPaso;


@Named
@ViewScoped
public class TipoPasoBean2 implements Serializable{
    
 public TipoPasoBean2() {
    }
    
    boolean activo,visible=true;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @EJB
    TipoPasoFacadeLocal tipoPaso;
     List<TipoPaso> lista= new ArrayList<>();
    TipoPaso tp =new TipoPaso();
    List<TipoPaso> filtroTP= new ArrayList<>();

/**metodo para seleccionar componentes de mi tabla
 * selecion  d objetos
 * @param event 
 */
     public void onRowSelect(SelectEvent event) {
       tp = (TipoPaso) event.getObject();
        visible=false;
     }
  
     public void cancelar(){
         tp= new TipoPaso();
         visible=true;
     }
     
     public void nuevo(){
         visible=false;
     }
    
    public void BotonNuevo(){
        tp=new TipoPaso();
         visible=false;
     }
     
    public List<TipoPaso> getFiltroTP() {
        return filtroTP;
    }
    public void setFiltroTP(List<TipoPaso> filtroTP) {
        this.filtroTP = filtroTP;
    }
    
    TipoPaso selectedTP;

    public TipoPaso getSelectedTP() {
        return selectedTP;
    }

    public void setSelectedTP(TipoPaso selectedTP) {
        this.selectedTP = selectedTP;
    }

 
 public void crear(){
     try {
         tipoPaso.create(tp);
         llenar();
         tp=new TipoPaso();
         addMessage("Registro Ingresado");
       
     } catch (Exception e) {
         System.out.println("Error: "+ e);
         addMessage("Error registro invalido !");
     }
      
    }
 
 public void modificar(){
     try {
        tipoPaso.edit(tp);
        llenar();
        tp=new TipoPaso();
        visible=true;
     } catch (Exception e) {
         System.out.println("Error: "+ e);
         addMessage("Error al modificar !");
     }
 
 }
 
 public void eliminar(){
     try {
         tipoPaso.remove(tp);
         llenar();
         tp=new TipoPaso();
         visible=true;
     } catch (Exception e) {
         System.out.println("Error: "+ e);
         addMessage("Error al Eliminar registro !");
     }
     
 }
 
/**
 * Mensaje de advertencia
 * 
 * @param summary
 */
     
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    public void chkCambio(){
        if(activo == true){
            this.lista = obtenerUtilizados();
        }else{
            llenar();  
        }
    }
   
        private List<TipoPaso> obtenerUtilizados() {
        List salida;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("uesocc.edu.sv.ingenieria.prn335_webproject3_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Query c = em.createNamedQuery("TipoPaso.asignados");
        salida = c.getResultList();
        
        if(salida != null){
        return salida;
        }else{
            return Collections.EMPTY_LIST;
        }
        }
        
    
   public void llenar(){
        if(lista!= null){
            this.lista=tipoPaso.findAll();
        }else {
            this.lista=Collections.EMPTY_LIST;
        }
   }
    @PostConstruct
    public void init(){
       llenar();
    }
    
   
public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public TipoPasoFacadeLocal getTipoPaso() {
        return tipoPaso;
    }

    public void setTipoPaso(TipoPasoFacadeLocal tipoPaso) {
        this.tipoPaso = tipoPaso;
    }

    public List<TipoPaso> getLista() {
        return lista;
    }

    public void setLista(List<TipoPaso> lista) {
        this.lista = lista;
    }

    public TipoPaso getTp() {
        return tp;
    }

    public void setTp(TipoPaso tp) {
        this.tp = tp;
    }  
    
}
