/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ligafutbol;

import java.util.ArrayList;

/**
 *
 * @author arbol
 */
public class LigaFutbol {

    public static void main(String[] args) {
        Liga liga = new Liga();

        int opcion;

        do {
            opcion = menu();

            switch (opcion) {
                // guardar los datos de la liga en un fichero binario
                case 1:
                    liga.guardarEnFicheroBin(liga);
                    break;
                case 2:
                    liga = liga.extraerLigaBin();
                    break;
                case 3:
                    liga.mostrarClasificacion();
                    break;
                case 4:
                    liga.cargarEquipos();
                    break;
                case 5:
                    String archivo;
                    System.out.println("Introduce nombre del archivo:");
                    archivo = Entrada.leerCadena();
                    if (archivo.equals("j1")) {
                        liga.cargaJornada("j1.txt");
                    } else if (archivo.equals("j2")) {
                        liga.cargaJornada("j2.txt");
                    } else if (archivo.equals("j3")) {
                        liga.cargaJornada("j3.txt");
                    } else {
                        System.out.println("Nombre de archivo incorrecto");
                    }
                    break;
                case 6:
                    liga.procesaJornada();
                    break;
                case 7:
                    liga.verPartidos();
                    break;
                case 8:
                    liga.reset();
                    break;
                /*case 9:
                    liga.listarEquipos();
                    liga.listarPartidos();
                    break;*/
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }

    public static int menu() {
        int opcion;
        System.out.println("\nLIGA DE FUTBOL");
        System.out.println("------------------");
        System.out.println("1. Guardar los datos de la liga en un fichero binario");
        System.out.println("2. Recuperar los datos de la liga de un fichero binario");
        System.out.println("3. Mostrar clasificación");
        System.out.println("4. Cargar equipos");
        System.out.println("5. Cargar jornada");
        System.out.println("6. Procesar jornada");
        System.out.println("7. Ver resultados de la jornada actual");
        System.out.println("8. Reiniciar liga");
        //System.out.println("9. Listar equipos/partidos");
        System.out.println("0. Salir");
        opcion = Entrada.leerEntero("\nOpcion?: ");
        return opcion;
    }

}
