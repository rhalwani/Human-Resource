/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
/**
 *
 * @author Riad
 */
@ManagedBean
@SessionScoped
public class LoginMBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * Creates a new instance of LoginMBean
     */
    public LoginMBean() {
    }

    public String closeSession() {
        FacesContext context = FacesContext.getCurrentInstance();

        ExternalContext externalContext = context.getExternalContext();
        externalContext.invalidateSession();

        return "/home/logout";
    }
}
