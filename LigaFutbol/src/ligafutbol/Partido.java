/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ligafutbol;

import java.io.Serializable;

/**
 *
 * @author arbol
 */
public class Partido implements Serializable {

    private String nombreLocal;
    private int golesLocal;
    private String nombreVisitante;
    private int golesVisitante;

    public Partido(String nombreLocal, int golesLocal, String nombreVisitante, int golesVisitante) {
        this.nombreLocal = nombreLocal;
        this.golesLocal = golesLocal;
        this.nombreVisitante = nombreVisitante;
        this.golesVisitante = golesVisitante;
    }

    public String getNombreLocal() {
        return nombreLocal;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public String getNombreVisitante() {
        return nombreVisitante;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    @Override
    public String toString() {
        return "" + nombreLocal + " " + golesLocal + " " + nombreVisitante + " " + golesVisitante + "";
    }

    

    public boolean ganaLocal() {
        return golesLocal > golesVisitante;
    }

    public boolean ganaVisitante() {
        return golesVisitante > golesLocal;
    }
}
