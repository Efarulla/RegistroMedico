/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class Atleta {
    private int codigo;
    private int cedula;
    private String nombre;
    private String apellido;
    private Date fechaNaci;
    private double peso;
    private String correo;
    private String direccion;
    private int telefono;

    public Atleta() {
    }

    public Atleta(int codigo, int cedula, String nombre, String apellido, Date fechaNaci, double peso, String correo, String direccion, int telefono) {
        this.codigo = codigo;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNaci = fechaNaci;
        this.peso = peso;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNaci() {
        return fechaNaci;
    }

    public void setFechaNaci(Date fechaNaci) {
        this.fechaNaci = fechaNaci;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "{"+"codigo"+":"+ codigo+","+ ", cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNaci=" + fechaNaci + ", peso=" + peso + ", correo=" + correo + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }

    
    
    
}
