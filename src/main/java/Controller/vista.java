/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Eduardo
 */
@Named(value = "vista")
@RequestScoped
public class vista {
 private String principal;
   
    
    
    
    
    public vista() {
    }

    public String getPrincipal() {
        return "principal.xhtml";
    }

  
    
    
    
}
