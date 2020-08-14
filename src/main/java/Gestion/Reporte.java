/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Model.Conexion;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Eduardo
 */
public class Reporte {
    
    //("/prospecto/prospecto.jasper"
    
    
    public static void verPdf(String realPath, Map<String,Object> parametros) {        
        try {
            //Cargo el reporte generado en Jasper Studio prospecto.jasper
            File jasper = new File(
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRealPath(realPath));
            
            //Lleno un reporte a partir del archivo compilado .jasper y la conexion a la BD..
            JasperPrint reporteJasper = JasperFillManager.fillReport(
                    jasper.getPath(), parametros, Conexion.getConexion());
            
            //Generando una página de respuesta del servidor Web
            HttpServletResponse respuesta =
                    (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                    .getResponse();
            
            //Llevar la página de respuesta
            respuesta.setContentType("application/pdf");
            respuesta.addHeader("Content-Type","application/pdf");
            
            //Vamos a llevar la página de respuesta con el reporte...
            ServletOutputStream flujo =respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            
            //Indicar que efectivamente se hace la respuesta...
            FacesContext.getCurrentInstance().responseComplete();
            
        } catch (IOException | JRException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public static void descargarPdf(String realPath, Map<String,Object> parametros,String nombreArchivo) {        
        try {
            //Cargo el reporte generado en Jasper Studio prospecto.jasper
            File jasper = new File(
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getRealPath(realPath));
            
            //Lleno un reporte a partir del archivo compilado .jasper y la conexion a la BD..
            JasperPrint reporteJasper = JasperFillManager.fillReport(
                    jasper.getPath(),parametros, Conexion.getConexion());
            
            //Generando una página de respuesta del servidor Web
            HttpServletResponse respuesta =
                    (HttpServletResponse) FacesContext
                            .getCurrentInstance()
                            .getExternalContext()
                    .getResponse();
            
            //Llevar la página de respuesta
            respuesta.addHeader("Content-disposition",nombreArchivo);//"attachment; filename=prospecto.pdf");
            
            
            //Vamos a llevar la página de respuesta con el reporte...
            ServletOutputStream flujo =respuesta.getOutputStream();
            JasperExportManager.exportReportToPdfStream(reporteJasper, flujo);
            
            //Indicar que efectivamente se hace la respuesta...
            FacesContext.getCurrentInstance().responseComplete();            
        } catch (JRException | IOException ex) {
            Logger.getLogger(Reporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
