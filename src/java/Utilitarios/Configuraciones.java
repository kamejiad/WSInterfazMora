
package Utilitarios;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 *
 * @author kamejia
 */
public class Configuraciones {
    
    //public static int timeoutMinutos;
    public static String [] propiedades ={"com.ibm.as400.access.AS400JDBCDriver" , "jdbc:as400://","servidorAS400", "USER", "Password"};
    public static void loadConfig(){
        try{
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Interfaz_Mora/configWSInterfazMora.properties"));
            propiedades[2] = properties.getProperty("server");
            propiedades[3] = properties.getProperty("user");
            propiedades[4] = properties.getProperty("password");
        } catch(IOException e){
            System.out.println(e);
        }
    }
}
