# HoneywellPrinter
a bluetooth connection for honeywell printers using using the sdk of honeywell

Add to your project with this command:

<code> cordova plugin add honeywellprinter </code>

Or if you clone/download zip file and extract, use this command:

<code>cordova plugin add _Unziped Directory_ </code>

for Uninstall is the next command:

<code> cordova plugin rm HoneywellPrinter </code>

You can call the methods using the methods belows

For print text


<code>
           
HoneywellPrinter.Imprimir(texto, mac,
        
        function (success) {
        
            //alert("Print ok"); 
            
            console.log(texto)
            
        }, function (error) {
        
            alert(error);
            
            console.log("Error " + error)
            
        }
        
    );
    
  </code>
  
  For printing an image
  
  <code>     
HoneywellPrinter.ImprimirImagen(base64, mac,
        
        function (success) {
        
            //alert("Print ok");
            
            alert(success);
            
            console.log(texto)
            
        }, function (error) {
        
            alert(error);
            
            console.log("Error " + error)
            
        }
        
    );
    
  </code>
  
  base 64 var value must be like this example 
  <code> var base64=["iVBORw0KGgoAAAANSUhEUgAAAQAAAAEACAYAAABccqhmAAAABHNCSVQICAgIfAhkiAAABGZJREFUeJzt3bFR3FAUQFGth9BUwBDSgGujAAqgNjdAyFABjr0OPC7A+zTzJe45ueDvanXnJ//psjFxXb0AtsvqBZzZt9ULANYRAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAj7CkcpT30k9+f3WYOfXh52Wsn/e3v5GP+NH79+77CSZU7//NgBQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQNjd6gVsCwd6TIdxMLfyHuwwjGSP3+7SoSKeAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAibnkVedpb/n8/Xx5uvfXv52HElt3l6eVi9hKVW3oPpd3///L7TSkZGz7AdAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQJAIQtPw48fT10/Tgt6+xxlHmHV5Q7DgzcRgAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAgTAAg7LItfsX3dB7AxBFmCRzhFeUrrbwHR/jud5gHMGIHAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGF3k4tXDvM4giMMlDi76Xd4hKEuE9NnaDpQpP0EQ5wAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQJgAQNjo9eBHcPbXQ7PO9LfzFV4PbwcAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYQIAYZdt2663Xvz5+rjjUpq+wpnyCfMcZu6f30fX2wFAmABAmABAmABAmABAmABAmABAmABAmABAmABAmABAmABAmABAmABAmABAmABA2N3qBUxNByKw2Inv31cYiGMHAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGGXbduuqxexyvW6/qNfLpfR9Ss/w3Tt23b+9Z+dHQCECQCECQCECQCECQCECQCECQCECQCECQCECQCECQCECQCECQCECQCE3W1/jwTfavl52iMc6eWcpr+dgxwnHi3CDgDCBADCBADCBADCBADCBADCBADCBADCBADCBADCBADCBADCBADCBADCBADCjnCgeXQo2zwAVtlpHsDSZ9AOAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMIEAMJOPxBk9I8NE0nbaaDHlIEgwBoCAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGECAGGHOBA95FA/q5z++bEDgDABgDABgDABgDABgDABgDABgDABgDABgDABgDABgDABgDABgDABgDABgLA/p8tdCq3oHksAAAAASUVORK5CYII="]
  </code>
  
  
  
  
