import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.mapper.object.ObjectMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;
import org.json.JSONObject;

public class Programa {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException,
			UnknownHostException {
		/*
		 * System.out.println("Iniciando a leitura das palavras");
		 * 
		 * ConversorPalavras conversor = new ConversorPalavras();
		 * List<PalavraFonetica> palavrasConvertidas =
		 * conversor.ConverterPalavras(
		 * "/var/www/html/conversor_palavras_fonetica/lista_palavras/palavras_dicionario_utf8.txt"
		 * );
		 * 
		 * PalavrasDalc dalc = new PalavrasDalc();
		 * dalc.IncluirPalavrasMongoDB(palavrasConvertidas);
		 * 
		 * for(PalavraFonetica palavraConvertida : palavrasConvertidas){
		 * 
		 * //PrintStream out = new PrintStream(System.out, true, "UTF-8");
		 * 
		 * 
		 * //out.println("palavra : " + palavraConvertida.getPalavra() +
		 * "\t\t|\t\t codigo fonético: " +
		 * palavraConvertida.getCodigoFonetico());
		 * 
		 * 
		 * }
		 */

		BuscaElastica client = new BuscaElastica();

		/*
		 * ProdutosJson json = new ProdutosJson(); List<ProdutoBE> produtos =
		 * json.ConverterJsonParaObjeto();
		 * 
		 * for (ProdutoBE produto : produtos) {
		 * 
		 * IndexResponse response = client.CriarIndice("nike", "busca_produto2",
		 * produto.getId_produto().toString(),
		 * json.ConverterObjetoParaJson(produto));
		 * 
		 * System.out.println(response.isCreated());
		 * 
		 * }
		 * 
		 * System.out.println("Todos os índices foram criados!");
		 */
		ConversorPalavras conversor = new ConversorPalavras();

		String query = "{" + "\"query_string\":{"
				+ "\"default_field\":\"NomeFonetico\"," + "\"query\":\""
				+ conversor.getCodigoFonetico("air") + "\"" +

				",\"fields\":[\"NomeFonetico\"]" +

				"}}";

		SearchResponse searchResponse = client.PrepararBusca("nike",
				"busca_produto2", query);

		System.out.println("Está consulta demorou: "
				+ searchResponse.getTookInMillis() + "milis");

		System.out.println("Retornou "
				+ searchResponse.getHits().getTotalHits() + " resultados");

		// for (SearchHit hit : searchResponse.getHits()) {
		// System.out.println(hit.getSourceAsString());
		// }

	}
}
