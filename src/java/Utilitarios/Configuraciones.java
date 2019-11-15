package Utilitarios;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Clase para configuraciones, servidor y base de datos
 *
 * @author kamejia
 */
public class Configuraciones {

    //public static int timeoutMinutos;
    /**
     * Arreglo que contiene las propiedades para la conexión con la base de
     * datos
     */
    public static String[] propiedades = {"com.ibm.as400.access.AS400JDBCDriver", "jdbc:as400://", "servidorAS400", "USER", "Password"};

    /**
     * Método que carga las configuraciones de conexión con la base de datos
     * desde un archivo
     */
    public static void loadConfig() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("C:\\Interfaz_Mora/configWSInterfazMora.properties"));
            propiedades[2] = properties.getProperty("server");
            propiedades[3] = properties.getProperty("user");
            propiedades[4] = properties.getProperty("password");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
