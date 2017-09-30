package cherryserver.logger;

/**
 *
 * @author Ariel Reis
 */
public abstract class Log {
 
    /**
     * 
     * Log do erro
     * 
     * @param e Mensagem de Erro
     */
    public static void e(String e){
        System.out.println("##\t\tERROR\t\t##");
        System.out.println("Error: " + e);
        System.out.println();
    }
    /**
     * 
     * Log do erro com causa
     * 
     * @param e
     * @param cause 
     */
    public static void e(String e, String cause){
        System.out.println("##\t\tERROR\t\t##");
        System.out.println("Error: " + e);
        System.out.println("Cause: " + cause);
        System.out.println();

    }
    
    /**
     * Exibe mensagem de verbose
     * 
     * @param v Mensagem de verbose 
     */
    
    public static void v(String v){
        System.out.println("##\t\tMESSAGE \t\t##");
        System.out.println(v);
        System.out.println();
    }
    
}
