import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.index.mapper.object.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class Programa {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, UnknownHostException {
		/*
		System.out.println("Iniciando a leitura das palavras");
		
		ConversorPalavras conversor = new ConversorPalavras();
		List<PalavraFonetica> palavrasConvertidas = conversor.ConverterPalavras("/var/www/html/conversor_palavras_fonetica/lista_palavras/palavras_dicionario_utf8.txt");
		
		PalavrasDalc dalc = new PalavrasDalc();
		dalc.IncluirPalavrasMongoDB(palavrasConvertidas);
		
		for(PalavraFonetica palavraConvertida : palavrasConvertidas){
			
			//PrintStream out = new PrintStream(System.out, true, "UTF-8");
		    
			
		    //out.println("palavra : " + palavraConvertida.getPalavra() + "\t\t|\t\t codigo fonético: " + palavraConvertida.getCodigoFonetico());
			
			
		}*/
		
		ProdutosJson json = new ProdutosJson();
		List<ProdutoBE> produtos = json.ConverterJsonParaObjeto();
		
		List<String> indexes = json.ConverterObjetoParaJson(produtos);
	}
}
