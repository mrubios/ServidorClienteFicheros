package srv;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
                        // Recibo el numero aleatorio
                        File f = new File(in.readLine());
                        out.writeUTF("Servidor: Numero guardado correctamente");
                        System.out.println("Carpeta: " + f);
                        System.out.println(f.listFiles());

                        break;

                    case 2:
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

    public void escribirNumeroAleatorio(File f, int numeroAleatorio) throws IOException {

        FileWriter fw = new FileWriter(f, true);
        // String datoAFichero = numeroAleatorio + ": "+ nombreCliente + "\n";
        // System.out.println(datoAFichero);
        // fw.write(datoAFichero);
        fw.write(nombreCliente + ": " + numeroAleatorio + "\r\n");
        fw.close();

    }
}