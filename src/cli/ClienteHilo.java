package cli;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteHilo extends Thread {

    private DataInputStream in;
    private DataOutputStream out;

    public static void limpiarConsola() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        Scanner sn = new Scanner(System.in);

        String mensaje;
        int opcion = 0;
        boolean salir = false;

        while (!salir) {

            try {
                System.out.print("\n--- Pulse ENTER para continuar --- ");
                sn.nextLine();
                limpiarConsola();
                System.out.println("\n\n*************** Menu aplicación ***************");
                System.out.println("1. Listar archivos");
                System.out.println("2. Mostrar datos de un archivo");
                System.out.println("3. Salir");
                System.out.print("\nElige una opción: ");
                opcion = sn.nextInt();
                out.writeInt(opcion);

                switch (opcion) {
                    case 1:
                        // Le mando al servidor el numero aleatorio
                        out.writeChars("C:\\Users\\USUARIO\\Desktop");
                        // Recibo y muestro el mensaje
                        mensaje = in.readUTF();
                        System.out.println(mensaje);
                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

                        break;
                    case 5:

                        break;
                    case 6:
                        salir = !salir;
                        break;
                    default:
                        mensaje = in.readUTF();
                        System.out.println(mensaje);

                }

            } catch (IOException ex) {
                Logger.getLogger(ClienteHilo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        sn.close();
    }

}
