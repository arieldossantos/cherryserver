package cherryserver.server;

import cherryserver.logger.Log;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Ariel Reis
 */
public class ConnectionProcess implements Runnable{
    
    private ServerSocket server;
    private final String CONTENT_TYPE = "Content-Type: text/html";
    @Override
    public void run() {
        this.startAcceptConnection(this.server);
    }

    public ConnectionProcess(ServerSocket socket) {
        this.server = socket;
    }

    private void startAcceptConnection(ServerSocket socket){
        while(true){
            Log.v("Aguardando");
            try(Socket clientSocket = socket.accept()){
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                out.println("HTTP/1.1 200 OK");
                out.println(CONTENT_TYPE);
                out.println("\r\n");
                try (BufferedReader br = new BufferedReader(new FileReader(Settings.PATH_RENDER))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush();
                out.close();
                Log.v("Nova conexão!");
            }catch(Exception e){
                System.out.println(e.toString());
                cherryserver.CherryServer.getServerInstance().killServer();
            }
        }
    }
}
