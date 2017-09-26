package cherryserver;

import cherryserver.server.Server;

/**
 *
 * @author Ariel Reis
 */
public class CherryServer {

    private static Server servidor;
    
    public static void main(String[] args) {
        System.setProperty("https.protocols", "SSLv3");
        CherryServer.createServer();
        //servidor.killServer();
    }
    
    private static void createServer(){
        servidor = new Server();
    }
    
    public static Server getServerInstance(){
        return servidor;
    }
}
