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
        static List<double[]> lectura_ejemplos = new LinkedList<>();
        static double[][] y;
        static List<double[]> lectura_pruebas = new LinkedList<>();

	public static void main(String[] args) throws IOException 
	{
            leerArchivoEjemplos();
            x = ListToArray(lectura_ejemplos); 
            leerArchivoPruebas(); 
            y = ListToArray(lectura_pruebas);
            
            for(double[] a : lectura_pruebas){
                for(int j=0; j < a.length; j++){
                    System.out.print(a[j] + " ");
                }
                System.out.println("");
            } 
	}
        
        public static void leerArchivoEjemplos() throws FileNotFoundException, IOException{
             try {
                FileInputStream fstream = new FileInputStream("X.txt");                              
                DataInputStream entrada = new DataInputStream(fstream);
                
                BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
                String strLinea;
                String ejemplo[];
                while ((strLinea = buffer.readLine()) != null && !(strLinea.equals(""))) {
                    ejemplo = strLinea.substring(1,strLinea.length()).split(" ");                                  
                    double[] copia_ejemplo = new double[ejemplo.length];                  
                     for(int i=0; i< ejemplo.length;i++){
                         double a = Double.parseDouble(ejemplo[i]);                                               
                         copia_ejemplo[i] = Double.parseDouble(ejemplo[i]);                    
                     }
                     lectura_ejemplos.add(copia_ejemplo); 
                }                
                entrada.close();
            } catch (Exception e) { 
                System.err.println("Ocurrio un error: " + e.getMessage());
            }
        }
        
    public static void leerArchivoPruebas() throws FileNotFoundException, IOException{
             try {
                FileInputStream fstream = new FileInputStream("y.txt");                              
                DataInputStream entrada = new DataInputStream(fstream);
                
                BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
                String strLinea;
                while ((strLinea = buffer.readLine()) != null && !(strLinea.equals(""))) {
                                                        
                    int ejemplo = Integer.parseInt(strLinea.substring(1,strLinea.length()));
                    double[] digito = new double[11]; 
                    
                    if(ejemplo != 0){
                       digito[ejemplo-1] = 1;
                    }else{
                       digito[10] = 1;
                    }
                    
                    lectura_pruebas.add(digito);
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
