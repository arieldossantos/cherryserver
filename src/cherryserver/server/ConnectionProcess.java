package cherryserver.server;

import cherryserver.logger.Log;
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
            try(Socket serverSocket = socket.accept()){
                Log.v("Nova conexão!");
            }catch(Exception e){
                System.out.println(e.toString());
                cherryserver.CherryServer.getServerInstance().killServer();
            }
        }
    }
}
