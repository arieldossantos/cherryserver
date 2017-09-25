package cherryserver.server;

import cherryserver.logger.Log;
import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author Ariel Reis
 */
public final class Server{
    
    private boolean serverStarted = false;
    private Thread process;
    public Server(){
        Log.v("Inicializando servidor");
        this.initServer();
    }
    
    public void initServer(){
        try{
          ServerSocket server = new ServerSocket(Settings.PORT);
          process = new Thread(new ConnectionProcess(server));
          process.start();
          Log.v("Servidor inicializado");
          this.serverStarted = true;
        }catch(IOException e){
          Log.e("Erro ao inicializar o server. ", e.getMessage());
        }
    }
    
    public void killServer(){
        process.stop();
        Log.v("Servidor parado");
    }
}
