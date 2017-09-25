package cherryserver.server;

import cherryserver.logger.Log;
import java.io.IOException;
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
    
    @Override
    public void run() {
        this.startAcceptConnection(this.server);
    }

    public ConnectionProcess(ServerSocket socket) {
        this.server = socket;
    }
    
    
    private void startAcceptConnection(ServerSocket socket){
        while(true){
            try{
                Socket serverSocket = socket.accept();
            }catch(IOException e){
                System.out.println(e.toString());
                cherryserver.CherryServer.getServerInstance().killServer();
            }
        }
    }
}
