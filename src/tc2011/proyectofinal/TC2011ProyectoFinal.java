/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tc2011.proyectofinal;

/**
 *
 * @author r2d2a
 */
public class TC2011ProyectoFinal
{

	 static double[][] x;
     static List<double[]> lectura_ejemplos = new LinkedList<>()

	public static void main(String[] args) throws IOException 
	{
          
        leerArchivoEjemplos();
        x = ListToArray(lectura_ejemplos);    		
	}
        
        public static void leerArchivoEjemplos() throws FileNotFoundException, IOException{
             try {
                FileInputStream fstream = new FileInputStream("X.txt");
                
                DataInputStream entrada = new DataInputStream(fstream);
                
                BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
                String strLinea;
                while ((strLinea = buffer.readLine()) != null && !(strLinea.equals(""))) {
                    ejemplo = strLinea.substring(1,strLinea.length()).split(" ");                                  
                    double[] copia_ejemplo = new double[ejemplo.length];                  
                     for(int i=0; i< ejemplo.length;i++){
                         double a = Double.parseDouble(ejemplo[i]);                                               
                         copia_ejemplo[i] = Double.parseDouble(ejemplo[i]);                    
                     }
                     System.out.println("Con " + copia_ejemplo.length + " valores");
                     lectura_ejemplos.add(copia_ejemplo); 
                }                
                entrada.close();
            } catch (Exception e) { 
                System.err.println("Ocurrio un error: " + e.getMessage());
            }
        }

    public static double[][] ListToArray(List<double[]> lectura_ejemplos) {
        double[][] x = new double[lectura_ejemplos.size()][lectura_ejemplos.get(0).length];
        for(int i = 0; i< lectura_ejemplos.size(); i++){
            for(double[] a : lectura_ejemplos){
                x[i] = a;
            }
        }        
        return x;
    }
	
}
