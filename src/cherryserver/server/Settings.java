package cherryserver.server;

/**
 *
 * @author Ariel Reis
 */
public class Settings {
    public static final int PORT = 8080;
    public static final String USER_EXECUTION_PATH = System.getProperty("user.dir"); // User class execute path
    public static final String PATH_RENDER = USER_EXECUTION_PATH + "\\dist\\public\\index.html";
}
