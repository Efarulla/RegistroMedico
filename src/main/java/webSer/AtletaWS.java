/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSer;

import Gestion.AtletaGestion;
import Model.Atleta;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Eduardo
 */
@Path("atleta")
@RequestScoped
public class AtletaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AtletaWS
     */
    public AtletaWS() {
    }


    /**
     * http://<server>:<port>/RegistroMedico/resources/atleta/
     * http://localhost:8080/RegistroMedico/resources/atleta/
     * @return listado de articulos en Json
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Atleta> getAtletas(){
        return AtletaGestion.getAtletas();
    }

 

     /**
     * http://<server>:<port>/RegistroMedico/resources/atleta/
     * http://localhost:8080/RegistroMedico/resources/atleta/1
     * @param codigo
     * @return listado de articulos en Json
     */
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Atleta getAtleta(@PathParam("codigo") int codigo){
        return AtletaGestion.getAtleta(codigo);
    }
    
      /**
     * http://<server>:<port>/RegistroMedico/resources/atleta/
     * http://localhost:8080/RegistroMedico/resources/atleta/
     * @param codigo
     * @return listado de articulos en Json
     */
    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Atleta deleteAtleta(@PathParam("codigo") int codigo){
        Atleta atleta = AtletaGestion.getAtleta(codigo);
        if (atleta!=null) {
            if (AtletaGestion.eliminarConCodigo(codigo)) {
                return atleta;
            }
        }
        return null;
    }
    
     /**
     * http://<server>:<port>/RegistroMedico/resources/atleta/
     * http://localhost:8080/RegistroMedico/resources/atleta/
     * @param atleta
     * @return listado de articulos en Json
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Atleta putAtleta(Atleta atleta){
        Atleta atleta2 = AtletaGestion.getAtleta(atleta.getCodigo());
        if (atleta2!=null) {
           AtletaGestion.modificar(atleta);
        } else {
           AtletaGestion.insertar(atleta);
        }
        return AtletaGestion.getAtleta(atleta.getCodigo());
    }
    
   /**
     * http://<server>:<port>/RegistroMedico/resources/atleta/
     * http://localhost:8080/RegistroMedico/resources/atleta/
     * @param atleta
     * @return listado de articulos en Json
     */




@POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Atleta postAtleta(Atleta atleta){
        Atleta atleta2 = AtletaGestion.getAtleta(atleta.getCodigo());
        if (atleta2!=null) {
           return null;
        } else {
           AtletaGestion.insertar(atleta);
        }
        return AtletaGestion.getAtleta(atleta.getCodigo());
    }


}
