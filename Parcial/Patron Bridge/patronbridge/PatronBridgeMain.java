/*
 * Asignatura: Patrones de Diseño de Software 
 * Patrón Estructural - > Bridge
 * Patrón Creacional - > Factory
 * Tipo de Clase: Main()
 */

package patronbridge;
import factory.BridgeFactory;
import implementacion.InterfaceMensajeEncriptacion;


public class PatronBridgeMain {
    
    public static void main(String[] args) {
      try {
            final String message = "<Curso><Nombre>Patrones de Diseño de Software</Nombre></Curso>";
            
            // Usando el Factory para crear el Bridge desde el archivo de configuración
            System.out.println("=== Creando Bridge desde archivo de configuración ===");
            InterfaceMensajeEncriptacion bridgeFromConfig = BridgeFactory.createBridge();
            String messageFromConfig = bridgeFromConfig.EncryptarMensaje(message, "HG58YZ3CR9123456");
            System.out.println("Resultado desde configuración > " + messageFromConfig + "\n");
            
            // También se puede crear el Bridge especificando la clase directamente
            System.out.println("=== Creando Bridges especificando la clase ===");
            InterfaceMensajeEncriptacion FormatoAES = BridgeFactory.createBridge("ProcesoEncriptarAES");
            String messageAES = FormatoAES.EncryptarMensaje(message, "HG58YZ3CR9123456");
            System.out.println("Formato AES > " + messageAES + "\n");
            
            InterfaceMensajeEncriptacion FormatoDES = BridgeFactory.createBridge("ProcesoEncriptarDES");
            String messageDES = FormatoDES.EncryptarMensaje(message, "XMzDdG4D03CKm2Ix");
            System.out.println("Formato DES > " + messageDES + "\n");
            
            InterfaceMensajeEncriptacion SinFormato = BridgeFactory.createBridge("ProcesoSinEncriptar");
            String messageNO = SinFormato.EncryptarMensaje(message, null);
            System.out.println("Sin Formato > " + messageNO + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
    }
    
}
