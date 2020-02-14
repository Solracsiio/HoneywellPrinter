var exec = cordova.require('cordova/exec');

// var AndroidToast = function() {
//     console.log('AndroidToast instanced');
// };  
module.exports = {
    Imprimir: function(texto,MACAddress,onSuccess,onError){//Metodos llamados, (texto,Macaddress,onsucess,onerror) son los valores recibidos
        exec(onSuccess,onError,'HoneywellPrinter','Imprimir',[texto,MACAddress])// los valres recibidos se asignan un en JSONArray para su uso a en el codigo nativo java
    },                                                                      // se tiene que especificar el nombre de la clase "AndroidToast" y el nombre del metodo "Imprimir". y los parametros que espera en formato de Arreglo []
    coneccion: function(MACAddress,onSuccess,onError){
        exec(onSuccess,onError,'HoneywellPrinter','coneccion',[MACAddress])
    },
    closeConnection: function(onSuccess,onError){
        exec(onSuccess,onError,'HoneywellPrinter','closeConnection',[])
    },
    obtenInfoPrinter: function(onSuccess,onError){
        exec(onSuccess,onError,'HoneywellPrinter','obtenInfoPrinter',[])
    },
    configuraRP2B: function(onSuccess,onError){
        exec(onSuccess,onError,'HoneywellPrinter','configuraRP2B',[])
    },
    ImprimirImagen: function(base64,MACAddress,onSuccess,onError){//base 64 es un Arreglo de codigo base 64 de una imagen SIN este texto  "data:image/png;base64," y debe estar entre corchetes asi: var base64 = [base64Code];
        exec(onSuccess,onError,'HoneywellPrinter','ImprimirImagen',[base64,MACAddress])
    }
};