package srv;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;
    private String nombreCliente;

    public ServidorHilo(DataInputStream in, DataOutputStream out, String nombreCliente) {
        this.in = in;
        this.out = out;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public void run() {
        boolean ejecutando = true;
        int opcion;


        while (ejecutando) {

            try {
                opcion = in.readInt();
                switch (opcion) {
                    case 1:
                        String ruta = "";
                        while (in.available() > 0) {

                            // read character
                            char c = in.readChar();

                            // print
                            System.out.print(c);
                            ruta += c;
                        }
                        // Recibo el numero aleatorio
                        File f = new File(ruta);
                        out.writeUTF("Servidor: Mostrada la lista de ficheros");
                        System.out.println("Carpeta: " + f);
                        File[] ficheros = f.listFiles();
                        for (int x = 0; x < ficheros.length; x++) {
                            out.writeUTF(ficheros[x].getName());
                        }
                        out.writeUTF("Salir");

                        break;

                    case 2:
                        String fichero = "";
                        while (in.available() > 0) {

                            // read character
                            char c = in.readChar();

                            // print
                            System.out.print(c);
                            fichero += c;
                        }
                        System.out.println();
                        // Recibo el numero aleatorio
                        File file = new File(fichero);


                        FileReader fr = new FileReader(file);
                        BufferedReader br = new BufferedReader(fr);

                        // Lectura del fichero
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            System.out.println(linea);
                            out.writeUTF(linea);
                        }
                        out.writeUTF("Salir");
                        out.writeUTF("Servidor: Ya se ha leido el fichero");

                        break;


                    case 3:
                        System.out.println(
                                "\n***************************************************************************\n  Un hilo de ejecución ha finalizado:  Un usuario ha abandonado su sesión\n***************************************************************************\n");
                        ejecutando = false;
                        break;
                    default:
                        out.writeUTF("Sólo numeros del 1 al 3");
                }

            } catch (IOException ex) {
                Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}