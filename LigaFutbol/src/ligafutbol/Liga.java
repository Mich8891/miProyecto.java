/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ligafutbol;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author arbol
 */
public class Liga implements Serializable {

    private ArrayList<Equipo> equipos;
    private ArrayList<Partido> partidos;

    public Liga(ArrayList<Equipo> equipos, ArrayList<Partido> partidos) {
        this.equipos = equipos;
        this.partidos = partidos;
    }

    public Liga() {
        this.equipos = new ArrayList<>();
        this.partidos = new ArrayList<>();
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }

    public ArrayList<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(ArrayList<Partido> partidos) {
        this.partidos = partidos;
    }

    public void verEquipos() {
        System.out.println("\nEquipos:");
        System.out.println("---------");
        for (Equipo equipo : equipos) {
            System.out.println(equipo.toString());
        }
    }

    public void verPartidos() {
        System.out.println("\nPartidos:");
        System.out.println("---------");
        for (Partido partido : partidos) {
            System.out.println(partido.toString());
        }
    }

    public void listarEquipos() {
        for (Equipo equipo : equipos) {
            if (equipo != null) {
                System.out.println(equipo.toString());
            } 
        }
    }

    public void listarPartidos() {
        for (Partido partido : partidos) {
            if (partidos != null) {
                System.out.println(partido.toString());
            }
        }
    }

    public void cargarEquipos() {
        // Borra el contenido existente del ArrayList
        equipos.clear();
        try {
            FileReader fichero = new FileReader("equipos.txt");
            BufferedReader br = new BufferedReader(fichero);
            String linea = br.readLine();
            while (linea != null) {
                // Crea un nuevo equipo con el nombre leído y agrega este equipo a la lista de equipos
                equipos.add(new Equipo(linea, 0, 0, 0, 0, 0));
                linea = br.readLine();
            }
            System.out.println("Equipos cargados correctamente");
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public void cargaJornada(String archivo) {
        // Borra el contenido existente del ArrayList
        partidos.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Separar los datos del partido usando el carácter ";"
                String[] datosPartido = linea.split(";");
                //otra version de hacerlo con scanner-->
                //String linea = entrada.readLine();
                //Scanner datos = new Scanner(linea).useDelimiter(";");
                if (datosPartido.length == 4) {
                    String equipoLocal = datosPartido[0].trim();
                    int golesLocal = Integer.parseInt(datosPartido[1].trim());
                    String equipoVisitante = datosPartido[2].trim();
                    int golesVisitante = Integer.parseInt(datosPartido[3].trim());

                    // Crear un nuevo objeto Partido con los datos obtenidos y agregarlo a la lista
                    partidos.add(new Partido(equipoLocal, golesLocal, equipoVisitante, golesVisitante));
                    System.out.println("Jornada cargada");
                } else {
                    System.err.println("Formato incorrecto en la línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar la jornada desde el archivo: " + e.getMessage());
        }
    }

    public void gestionarPartido(Partido partido) {
        String equipoLocal = partido.getNombreLocal();
        String equipoVisitante = partido.getNombreVisitante();

        Equipo equipoLocalObj = null;
        Equipo equipoVisitanteObj = null;

        // Buscar los objetos Equipo correspondientes a los nombres de los equipos en el ArrayList equipos
        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(equipoLocal)) {
                equipoLocalObj = equipo;
            }
            if (equipo.getNombre().equals(equipoVisitante)) {
                equipoVisitanteObj = equipo;
            }
        }

        // Verificar si se encontraron los equipos en la lista de equipos
        if (equipoLocalObj != null && equipoVisitanteObj != null) {
            // Actualizar los datos de los equipos según el resultado del partido
            int golesLocal = partido.getGolesLocal();
            int golesVisitante = partido.getGolesVisitante();

            if (partido.ganaLocal()) {
                equipoLocalObj.setPartidosGanados(equipoLocalObj.getPartidosGanados() + 1);
                equipoLocalObj.setGolesAFavor(equipoLocalObj.getGolesAFavor() + golesLocal);
                equipoLocalObj.setGolesEnContra(equipoLocalObj.getGolesEnContra() + golesVisitante);

                equipoVisitanteObj.setPartidosPerdidos(equipoVisitanteObj.getPartidosPerdidos() + 1);
                equipoVisitanteObj.setGolesAFavor(equipoVisitanteObj.getGolesAFavor() + golesVisitante);
                equipoVisitanteObj.setGolesEnContra(equipoVisitanteObj.getGolesEnContra() + golesLocal);

            } else if (partido.ganaVisitante()) {
                equipoVisitanteObj.setPartidosGanados(equipoVisitanteObj.getPartidosGanados() + 1);
                equipoVisitanteObj.setGolesAFavor(equipoVisitanteObj.getGolesAFavor() + golesVisitante);
                equipoVisitanteObj.setGolesEnContra(equipoVisitanteObj.getGolesEnContra() + golesLocal);

                equipoLocalObj.setPartidosPerdidos(equipoLocalObj.getPartidosPerdidos() + 1);
                equipoLocalObj.setGolesAFavor(equipoLocalObj.getGolesAFavor() + golesLocal);
                equipoLocalObj.setGolesEnContra(equipoLocalObj.getGolesEnContra() + golesVisitante);
            } else {
                // Empate
                equipoLocalObj.setPartidosEmpatados(equipoLocalObj.getPartidosEmpatados() + 1);
                equipoLocalObj.setGolesAFavor(equipoLocalObj.getGolesAFavor() + golesLocal);
                equipoLocalObj.setGolesEnContra(equipoLocalObj.getGolesEnContra() + golesVisitante);

                equipoVisitanteObj.setPartidosEmpatados(equipoVisitanteObj.getPartidosEmpatados() + 1);
                equipoVisitanteObj.setGolesAFavor(equipoVisitanteObj.getGolesAFavor() + golesVisitante);
                equipoVisitanteObj.setGolesEnContra(equipoVisitanteObj.getGolesEnContra() + golesLocal);
            }
        } else {
            System.err.println("No se encontró uno o ambos equipos en la lista de equipos.");
        }
    }

    public void procesaJornada() {
        for (Partido partido : partidos) {
            gestionarPartido(partido);
        }
        System.out.println("Jornada procesada correctamente");
        // Borrar los partidos al finalizar el procesamiento de la jornada
        partidos.clear();
    }

    // Método para guardar el objeto Liga en un archivo binario
    public void guardarEnFicheroBin(Liga liga) {
        try {
            // Crear el archivo partidos.dat para guardar los datos
            FileOutputStream fichero = new FileOutputStream("partidos.dat");
            ObjectOutputStream salida = new ObjectOutputStream(fichero);

            // Escribir el objeto Liga en el archivo
            salida.writeObject(liga);
            System.out.println("Se ha guardado correctamente el objeto Liga en el archivo partidos.dat");

            // Cerrar el stream de salida
            salida.close();
        } catch (IOException e) {
            System.out.println("No se ha podido escribir en el fichero: liga.dat");
            e.printStackTrace();
        }
    }

    /*public Liga extraerLigaBin() {
        Liga liga = null;
        try (FileInputStream fichero = new FileInputStream("partidos.dat");
             ObjectInputStream entrada = new ObjectInputStream(fichero)) {
             
            liga = (Liga) entrada.readObject();
            
            // Aquí puedes extraer los equipos y partidos y guardarlos en sus arrays correspondientes
            ArrayList<Equipo> equipos = liga.getEquipos();
            ArrayList<Partido> partidos = liga.getPartidos();
            System.out.println("Liga extraída con éxito.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se ha podido leer en el fichero partidos.dat");
            e.printStackTrace();
        }
        return liga;
    }*/
    public Liga extraerLigaBin() {
        Liga liga = null;
        try (FileInputStream fichero = new FileInputStream("partidos.dat"); ObjectInputStream entrada = new ObjectInputStream(fichero)) {

            liga = (Liga) entrada.readObject();
            System.out.println("Liga extraída con éxito.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No se ha podido leer en el fichero partidos.dat");
            e.printStackTrace();
        }
        return liga;
    }

    public void mostrarClasificacion() {
        Comparator<Equipo> comparadorEquiposDescendente = Collections.reverseOrder();
        // Clasificar los equipos
        Collections.sort(equipos, comparadorEquiposDescendente);

        // Imprimir la clasificación
        System.out.println("Clasificación de liga:");
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipo = equipos.get(i);
            System.out.println((i + 1) + ". " + equipo);
        }
    }

    public void reset() {
        for (Equipo equipo : equipos) {
            equipo.reset();
        }
        System.out.println("Liga reiniciada");
    }
}
