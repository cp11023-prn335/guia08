/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.TipoPasoFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.TipoPaso;

@Named
@ViewScoped
public class TipoPasoBean implements Serializable{

@EJB
private TipoPasoFacadeLocal TipoPasoEJB;
private TipoPaso tipoPaso;
private List<TipoPaso> lista=new ArrayList();  
boolean activo;

@PostConstruct
public void init(){
tipoPaso=new TipoPaso();
}

 
public boolean isActivo(){
return activo;
}

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
   

public void Crear(){
    try {    
    TipoPasoEJB.create(tipoPaso);
     init();
     tipoPaso = new TipoPaso();
    } catch (Exception e) {
        System.err.println("ERROR2017:"+e);    
        
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:", ""+e));
    }
}


    public TipoPasoFacadeLocal getTipoPasoEJB() {
        return TipoPasoEJB;
    }

    public void setTipoPasoEJB(TipoPasoFacadeLocal TipoPasoEJB) {
        this.TipoPasoEJB = TipoPasoEJB;
    }

    public TipoPaso getTipoPaso() {
        return tipoPaso;
    }

    public void setTipoPaso(TipoPaso tipoPaso) {
        this.tipoPaso = tipoPaso;
    }

    public List<TipoPaso> getLista() {
        return lista;
    }

    public void setLista(List<TipoPaso> lista) {
        this.lista = lista;
    }
    
}
