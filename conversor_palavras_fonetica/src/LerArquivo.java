import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class LerArquivo {


    public List<String> ProcessarPalavras (String caminho) {

    	List<String> palavras = new ArrayList<String>();
    	
        String name         = caminho;
        File f              = new File (name);
        BufferedReader br   = null;

        try {
            
        	br = new BufferedReader(
        			   new InputStreamReader(
        	                      new FileInputStream(f), "UTF8"));
        
            String output;
            while ( (output = br.readLine()) != null) {

            	palavras.add(output);
               System.out.println(output);

                //count++;
            }

            //System.out.println("Number of lines: " + count);


        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	//throw new Exception("Can't find file");
        } catch (IOException e) {
        	e.printStackTrace();
        	//throw new Exception("Unable to read file");
            
        }
        finally {
        try {
            br.close();
        } catch (IOException e) {
        	e.printStackTrace();
        	//throw new Exception("Unable to close file");
        } catch (NullPointerException ex){
            // File was not opened
        }
        }

        return palavras;
    }

}