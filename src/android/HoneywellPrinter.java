//THanks to this forums 
//https://stackoverflow.com/questions/54062673/cordova-plugin-use-aar
//


import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import java.io.IOException;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.IOException;
import java.util.Set;

import honeywell.connection.*;
import honeywell.connection.ConnectionBase;
import honeywell.connection.Connection_Bluetooth;

import honeywell.printer.DocumentDPL;
import honeywell.printer.DocumentDPL.*;
import honeywell.printer.DocumentEZ;
import honeywell.printer.DocumentLP;
import honeywell.printer.DocumentExPCL_LP;
import honeywell.printer.DocumentExPCL_PP;
import honeywell.printer.DocumentExPCL_PP.*;
import honeywell.printer.ParametersDPL;
import honeywell.printer.ParametersDPL.*;
import honeywell.printer.ParametersEZ;
import honeywell.printer.ParametersExPCL_LP;
import honeywell.printer.ParametersExPCL_LP.*;
import honeywell.printer.ParametersExPCL_PP;
import honeywell.printer.ParametersExPCL_PP.*;
import honeywell.printer.UPSMessage;
import honeywell.printer.configuration.dpl.*;
import honeywell.printer.configuration.ez.*;
import honeywell.printer.configuration.expcl.*;

public class AndroidToast extends CordovaPlugin {
    private CallbackContext callbackContext;

    ConnectionBase conn;
    DocumentDPL docDPL = new DocumentDPL();
    MediaLabel_DPL medLa_DPL = new MediaLabel_DPL(conn);// para cambiar la configuracion de la etiqueta
                                                                         // como por ejemplo el tamaño, el grosor
    ParametersDPL paramDPL = new ParametersDPL();
    PrinterInformation_DPL printerInfo;
    String mensaje = "";// necesario para mensajes

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("coneccion".equals(action)) {//action es la llamada del metodo solicitado en el xml
            coneccion(args.getString(0), callbackContext);
            return true;
        } else if ("Imprimir".equals(action)) {
            Imprimir(args.getString(0), args.getString(1), callbackContext);
            return true;
        } else if ("obtenInfoPrinter".equals(action)) {
            obtenInfoPrinter(callbackContext);
            return true;
        } else if ("closeConnection".equals(action)) {
            closeConnection(callbackContext);
            return true;
        } else if ("configuraRP2B".equals(action)) {
            configuraRP2B(callbackContext);
            return true;
        }else if("ImprimirImagen".equals(action)){
            JSONArray labels = args.getJSONArray(0);
            String MACAddress = args.getString(1);
            ImprimirImagen(labels, MACAddress, callbackContext);
            return true;
        } else {

        }

        return false;

    }

    private void configuraRP2B(CallbackContext callbackContext) {
        if (coneccion("00:10:20:3C:28:BC", callbackContext)) {
            docDPL.clear();// limpiar el cache de la impresora
            try {// Imprimir el texto especificado

                medLa_DPL.setLabelLengthLimit(false);// no limitar el largo de la impresion
                medLa_DPL.setLabelWidth(189);// Este valor cambia dependiendo de la impresora, este valor es para una impreosra Honeywell RP2B
                medLa_DPL.setContinuousLabelLength(100);//el largo continuo por defecto al imprimir cualquier cosa
                closeConnection(callbackContext);//siempre cerrar conexion despues de realizar una operacion
                callbackContext.success("Configuracion aplicada!");
            } catch (Exception e) {

                e.printStackTrace();
                callbackContext.error("Error al imprimir Error : " + e.getMessage());
            }
        } else {
            callbackContext.error("No se pudo conectar, sorry, bai  : ");
        }
    }

    private boolean coneccion(String MACAddress, CallbackContext callbackContext) {
        boolean res=false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter.isEnabled()) {//verifica si el bluetooth esta encendido
            
            try {
                conn=Connection_Bluetooth.createClient(MACAddress);
                //callbackContext.success("Conexion Establecida");
                conn.open();
                res = true;
            } catch (Exception e) {

                callbackContext.error("Error al conectar Error : " + e.getMessage());
                res=false;
            }
         } else {
            callbackContext.error("Bluetooth no está activado");
         }
        return res;
    }

    public void Imprimir(String text, String MACADDRESS, CallbackContext callbackContext) {

        if (coneccion(MACADDRESS, callbackContext)) {
            docDPL.clear();// limpiar el cache de la impresora, muy importante realizar antes de cada impresion
            try {// Imprimir el texto especificado
                
                //no esta funcionando con saltos de linea, sinceramente no se que me falto para que pudiera imprimir
                docDPL.writeTextScalable(text, "00", 10, 10);//
                // docDPL.writeText(text, 0, 0, "00",paramDPL);

                conn.write(docDPL.getDocumentData());//Imprime  la informacoin en cache de la impresora
                closeConnection(callbackContext);
                callbackContext.success("Texto Imprimido "+text.toString());

            } catch (Exception e) {

                e.printStackTrace();
                callbackContext.error("Error al imprimir Error : " + e.getMessage());
            }
        } else {
            callbackContext.error("No se pudo conectar, sorry, bai");
        }

    }

    public void ImprimirImagen(JSONArray imagen, String MACADDRESS, CallbackContext callbackContext) {//imagen viene como un dato tipo JSONArray
        if (coneccion(MACADDRESS, callbackContext)) {
        
        String mensaje="";
try {
    for (int i = imagen.length() - 1; i >= 0; i--) {//imprime en secuencia si envias varias imagenes
        docDPL.clear();// limpiar el cache de la impresora
        String base64Image = imagen.get(i).toString();

        byte[] data = base64Image.getBytes("UTF-8");
        String base64 = Base64.encodeToString(data, Base64.DEFAULT);//Decodificacion de base64 con utf--8


        mensaje = "paso1 ";
        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        mensaje += "paso 2 ";
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        mensaje += "paso 3 ";

        docDPL.writeImage(decodedByte,0,0,paramDPL);//se envia la imagen, los parametros se pueden configurar
        mensaje += "paso 4 ";
        conn.write(docDPL.getDocumentData());//se impriume la imagen almacenada
        mensaje += "paso 5 ";
        //conn.close();
        //mensaje += "paso 6 ";
        closeConnection(callbackContext);
         callbackContext.success(mensaje);
    }
    
} catch (Exception e) {
    callbackContext.error("mensaje "+e.getMessage());
}
        
} else {
    callbackContext.error("No se pudo conectar, sorry, bai");
}

    }

    public void obtenInfoPrinter(CallbackContext callbackContext) {// obtener informacion de la impresora
        if (coneccion("00:10:20:3C:28:BC", callbackContext)) {
            docDPL.clear();// limpiar el cache de la impresora
            try {
                printerInfo.queryPrinter(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String message = "";
            if (printerInfo.getValid() == false) {
                message = "No response from printer\r\n";
                mensaje = message;
            } else {
                message = String.format("Firmware Version: %s\n", printerInfo.getVersionInformation());
                mensaje = message;
            }
            callbackContext.success(mensaje);
            closeConnection(callbackContext);
        } else {
            callbackContext.error("No se pudo conectar, sorry, bai");
        }
    }

    public void closeConnection(CallbackContext callbackContext) {// cerrar conexion
        // ====Method 1========//
        try {
            conn.close();
            callbackContext.success("Cerro conexion");
        } catch (Exception e) {
            callbackContext.error("Error al cerrar conexion: " + e.getMessage());
           
        }
        // ====Method 2========//
        // conn.setIsClosing(true);

    }
}