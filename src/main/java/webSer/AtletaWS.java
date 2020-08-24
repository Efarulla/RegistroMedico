/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webSer;

import Gestion.AtletaGestion;
import Model.Atleta;
import java.io.StringReader;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    
    
/**
 *
 * @author Eduardo
 *
 *
 * Estructura del JSON , HAY UN ARRAY LLAMADO RESULTS { "results" : [ {
 * "lastname" : "FARULLA BARRANTES", "firstname1" : "EDUARDO", "rawcedula" :
 * "11398036234", "cedula" : "113980362", "lastname2" : "BARRANTES",
 * "guess_type" : "FISICA", "admin" : "01", "lastname1" : "FARULLA", "type" :
 * "F", "fullname" : "EDUARDO STEVEN FARULLA BARRANTES", "temp" : null,
 * "firstname" : "EDUARDO STEVEN", "class" : "F", "firstname2" : "STEVEN" } ],
 * "license" : "https://en.wikipedia.org/wiki/Beerware", "database_date" :
 * "20191028", "resultcount" : 1, "query" : "113980362" }
 *
 *
 *
 *
 *
 *
 *
 */


    private int id; //El id para buscar articulos
    private String tiraJson = "xxxx"; //área para pasar un objetoJson completo private String salida; 
    private final String URI = "https://apis.gometa.org/cedulas";
    private String salida; //para mostrar las respuestas de los servicios web

    
    
    
    

    public void hacerGet() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI + "/"+id+"&key=byhkkLzzGpK1VZF");
        JsonObject response = target.request(
                MediaType.APPLICATION_JSON).get(JsonObject.class);

     

    }
    
    
    
    
    
    
    

    //El método hacerGetAll llama al method GET (todos) y //coloca el resultado en salida
    public void hacerGetAll() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI);
        JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        salida = response.toString();

    }
//El método hacerGet llama al method GET ojo como se pasa el parámetro 
//agregando el id como parte del target, se coloca el resultado en salida

    //El método hacerDelete llama al method DELETE ojo como se pasa el parámetro //agregando el id como parte del target, se coloca el resultado en salida 
    public void hacerDelete() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI + "/" + id);
        JsonObject response = target.request(
                MediaType.APPLICATION_JSON).delete(JsonObject.class);
        salida = response.asJsonObject().toString();
    }

    //El método hacerPut llama al method PUT ojo como se pasa la información 
//se coloca el resultado en salida
    public void hacerPut() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI);
        JsonReader lectorJson = Json.createReader(new StringReader(tiraJson));
        JsonObject jsonObject = lectorJson.readObject();
        Response response = target.request(
                MediaType.APPLICATION_JSON).put(Entity.json(jsonObject));
        salida = response.readEntity(String.class);
    }

//El método hacerPost llama al method POST ojo como se pasa la información 
//se coloca el resultado en salida
    public void hacerPost() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URI);
        JsonReader lectorJson = Json.createReader(new StringReader(tiraJson));
        JsonObject jsonObject = lectorJson.readObject();
        Response response = target.request(
                MediaType.APPLICATION_JSON).post(Entity.json(jsonObject));
        salida = response.readEntity(String.class);
    }



    
    
    
    
}