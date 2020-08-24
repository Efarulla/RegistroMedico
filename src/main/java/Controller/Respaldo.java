/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Gestion.AtletaGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo
 */
@Named(value = "respaldo")
@SessionScoped
public class Respaldo implements Serializable {

    /**
     * Creates a new instance of Respaldo
     */
    public Respaldo() {
    }

    public void respaldoAtleta() {
        String json = AtletaGestion.getJson();
        File f = new File(FacesContext.getCurrentInstance().getExternalContext()
                .getRealPath("/Respaldo") + "Atletas.zip");
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
            ZipEntry e = new ZipEntry("Atletas.json");

            try {
                out.putNextEntry(e);
                byte[] data = json.getBytes();
                out.write(data, 0, data.length);
                out.closeEntry();
                out.close();
                File zipPath = new File(FacesContext.getCurrentInstance()
                        .getExternalContext().getRealPath("/Respaldo") + "Atletas.zip");
                byte[] zip = Files.readAllBytes(zipPath.toPath());
                HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                ServletOutputStream sos = respuesta.getOutputStream();
                respuesta.setContentType("application/zip");
                respuesta.setHeader("Content-Disposition", "attachment; filename=Atleta.zip");
                sos.write(zip);
                sos.flush();
                FacesContext.getCurrentInstance().responseComplete();
            } catch (IOException ex) {
                Logger.getLogger(Respaldo.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Respaldo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
