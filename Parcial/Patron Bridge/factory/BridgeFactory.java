/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Creacional - > Factory
 * Patrón Estructural - > Bridge
 * Tipo de Clase: Factory
 * Clase que implementa el patrón Factory para crear el Bridge mediante configuración.
 */
package factory;

import encriptacion.InterfaceEncriptar;
import encriptacion.ProcesoEncriptarAES;
import encriptacion.ProcesoEncriptarDES;
import encriptacion.ProcesoSinEncriptar;
import implementacion.InterfaceMensajeEncriptacion;
import implementacion.PuenteMensajeEncriptacion;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Gustavo
 */
public class BridgeFactory {
    
    private static final String CONFIG_FILE = "config.properties";
    private static final String ENCRYPTION_CLASS_KEY = "encryption.class";
    
    /**
     * Crea un Bridge (PuenteMensajeEncriptacion) basado en la configuración del archivo.
     * @return InterfaceMensajeEncriptacion - El bridge configurado
     * @throws Exception Si hay error leyendo la configuración o creando el objeto
     */
    public static InterfaceMensajeEncriptacion createBridge() throws Exception {
        Properties properties = loadConfiguration();
        String className = properties.getProperty(ENCRYPTION_CLASS_KEY);
        
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("La propiedad '" + ENCRYPTION_CLASS_KEY + "' no está definida en el archivo de configuración.");
        }
        
        InterfaceEncriptar encriptador = createEncryptionInstance(className.trim());
        return new PuenteMensajeEncriptacion(encriptador);
    }
    
    /**
     * Crea un Bridge con una clase de encriptación específica.
     * @param encryptionClassName Nombre de la clase de encriptación
     * @return InterfaceMensajeEncriptacion - El bridge configurado
     * @throws Exception Si hay error creando el objeto
     */
    public static InterfaceMensajeEncriptacion createBridge(String encryptionClassName) throws Exception {
        InterfaceEncriptar encriptador = createEncryptionInstance(encryptionClassName);
        return new PuenteMensajeEncriptacion(encriptador);
    }
    
    /**
     * Carga el archivo de configuración.
     * @return Properties con la configuración
     * @throws IOException Si hay error leyendo el archivo
     */
    private static Properties loadConfiguration() throws IOException {
        Properties properties = new Properties();
        
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo de configuración: " + CONFIG_FILE);
            throw e;
        }
        
        return properties;
    }
    
    /**
     * Crea una instancia de la clase de encriptación especificada.
     * @param className Nombre de la clase de encriptación
     * @return InterfaceEncriptar - Instancia del encriptador
     * @throws Exception Si la clase no existe o no se puede instanciar
     */
    private static InterfaceEncriptar createEncryptionInstance(String className) throws Exception {
        switch (className) {
            case "ProcesoEncriptarAES":
                System.out.println("Factory: Creando instancia de ProcesoEncriptarAES");
                return new ProcesoEncriptarAES();
                
            case "ProcesoEncriptarDES":
                System.out.println("Factory: Creando instancia de ProcesoEncriptarDES");
                return new ProcesoEncriptarDES();
                
            case "ProcesoSinEncriptar":
                System.out.println("Factory: Creando instancia de ProcesoSinEncriptar");
                return new ProcesoSinEncriptar();
                
            default:
                throw new IllegalArgumentException("Clase de encriptación no reconocida: " + className + 
                    ". Las clases válidas son: ProcesoEncriptarAES, ProcesoEncriptarDES, ProcesoSinEncriptar");
        }
    }
}
