package cherryserver.server;

import cherryserver.logger.Log;

import java.io.*;
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
    private final String COMMAND_PHP = "php";
    private final char SEPARATOR_SPC = ' ';
    private static String path;

    @Override
    public void run() {
        this.startAcceptConnection(this.server);
    }

    public ConnectionProcess(ServerSocket socket) {
        this.server = socket;
    }

    private class HeaderHolder {

        Reader reader;
        String tempData = "";
        public String METHOD = null;
        public String URI = null;
        public String STATUS = null;

        public HeaderHolder(InputStream inpStream){
            reader = new InputStreamReader(inpStream);
        }

        private void init(){

            String[] list = null;

            try {
                int c;
                while ((c = reader.read()) != -1) {
                    //System.out.print((char) c);
                    tempData += (char) c;
                    list = tempData.split("\n");

                    if (tempData.contains("\r\n\r\n"))
                        break;
                }
            } catch (IOException ex) {
                Log.e(ex.getMessage());
            }

            /* Set value holder */
            this.METHOD = list[0].split(" ")[0];
            this.URI = list[0].split(" ")[1];
            this.STATUS = list[0].split(" ")[2];
        }

        public HeaderHolder getHeaders(){
            init();
            return this;
        }
    }

    private void startAcceptConnection(ServerSocket socket){
        while(Settings.SERVER_IS_RUNNING){
            try(Socket clientSocket = socket.accept()){
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                out.println("HTTP/1.1 200 OK");
                out.println(CONTENT_TYPE);
                out.println("\r\n");

                /* Headers */
                InputStream inp = clientSocket.getInputStream();
                HeaderHolder headers = new HeaderHolder(inp).getHeaders();

                Log.v("URI: " + headers.URI);
                Log.v("METHOD: " + headers.METHOD);
                Log.v("STATUS: " + headers.STATUS);

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
