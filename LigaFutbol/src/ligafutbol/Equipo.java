/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ligafutbol;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author arbol
 */
public class Equipo implements Comparable, Serializable {
//atributos de la clase
    private String nombre;
    private int partidosGanados;
    private int partidosEmpatados;
    private int partidosPerdidos;
    private int golesAFavor;
    private int golesEnContra;
//Constructor de Equipo
    public Equipo(String nombre, int partidosGanados, int partidosEmpatados, int partidosPerdidos, int golesAFavor, int golesEnContra) {
        this.nombre = nombre;
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }
    
    public Equipo(){
        
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    public int getPartidosEmpatados() {
        return partidosEmpatados;
    }

    public void setPartidosEmpatados(int partidosEmpatados) {
        this.partidosEmpatados = partidosEmpatados;
    }

    public int getPartidosPerdidos() {
        return partidosPerdidos;
    }

    public void setPartidosPerdidos(int partidosPerdidos) {
        this.partidosPerdidos = partidosPerdidos;
    }

    public int getGolesAFavor() {
        return golesAFavor;
    }

    public void setGolesAFavor(int golesAFavor) {
        this.golesAFavor = golesAFavor;
    }

    public int getGolesEnContra() {
        return golesEnContra;
    }

    public void setGolesEnContra(int golesEnContra) {
        this.golesEnContra = golesEnContra;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipo other = (Equipo) obj;
        if (this.partidosGanados != other.partidosGanados) {
            return false;
        }
        if (this.partidosEmpatados != other.partidosEmpatados) {
            return false;
        }
        if (this.partidosPerdidos != other.partidosPerdidos) {
            return false;
        }
        if (this.golesAFavor != other.golesAFavor) {
            return false;
        }
        if (this.golesEnContra != other.golesEnContra) {
            return false;
        }
        return Objects.equals(this.nombre, other.nombre);
    }

    public int puntos() {
        int puntosGanados = partidosGanados * 3;
        int puntosEmpatados = partidosEmpatados * 1;
        int puntosTotales = puntosGanados + puntosEmpatados;
        return puntosTotales;
    }

    public int partidosJugados() {
        int partidosJugados = partidosGanados + partidosEmpatados + partidosPerdidos;
        return partidosJugados;
    }

    public int diferenciaDeGoles() {
        int diferenciaGoles = golesAFavor - golesEnContra;
        return diferenciaGoles;
    }

    public void reset() {
        this.partidosGanados = 0;
        this.partidosEmpatados = 0;
        this.partidosPerdidos = 0;
        this.golesAFavor = 0;
        this.golesEnContra = 0;
    }
    
    

    @Override
    public String toString() {
        return  nombre + "\t" + puntos()+ "\t" + partidosGanados + "\t" + partidosEmpatados + "\t" + partidosPerdidos + "\t" + golesAFavor + "\t" + golesEnContra + "\t"  + "\t" + diferenciaDeGoles();
    }
//implementación de compareTo
    @Override
    public int compareTo(Object objetoEquipo) {

        if (objetoEquipo instanceof Equipo) {
            Equipo otroEquipo = (Equipo) objetoEquipo;
            // Compara los equipos por partidos ganados
            if (this.partidosGanados != otroEquipo.partidosGanados) {
                return Integer.compare(this.partidosGanados, otroEquipo.partidosGanados);
            } else {
                // Si los partidos ganados son iguales, compara por goles a favor
                return Integer.compare(this.golesAFavor, otroEquipo.golesAFavor);
            }
        } else {
            throw new IllegalArgumentException("El objeto a comparar no es un Equipo");
        }
        
        
    }
    
    

}
