import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import java.util.*;

import org.apache.lucene.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.FilterBuilders.*;
import org.elasticsearch.index.query.QueryBuilders.*;

public class BuscaElastica {

	private String endereco = "ubuntuserver";
	private Integer porta = 9300;

	public TransportClient transportClient = null;

	public TransportClient getTransportClient() {

		if (transportClient == null) {
			TransportClient client = new TransportClient();
			InetSocketTransportAddress socket = new InetSocketTransportAddress(
					this.endereco, this.porta);
			this.transportClient = client.addTransportAddress(socket);

		}
		return this.transportClient;
	}

	public IndexResponse CriarIndice(String indice, String tipo, String id,
			Map<String, Object> dado) {
		TransportClient client = getTransportClient();
		return client.prepareIndex(indice, tipo, id).setSource(dado).execute()
				.actionGet();
	}

	public IndexResponse CriarIndice(String indice, String tipo, String id,
			String dado) {
		TransportClient client = getTransportClient();
		return client.prepareIndex(indice, tipo, id).setSource(dado).execute()
				.actionGet();
	}

	public SearchResponse PrepararBusca(String indice, String tipo, String dado) {
		return getTransportClient().prepareSearch(indice).setTypes(tipo)
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH).setQuery(dado).execute().actionGet();
	}

}
