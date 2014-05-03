import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.apache.commons.lang3.*;

import com.google.gson.Gson;

public class ProdutosJson {

	public List<ProdutoBE> ConverterJsonParaObjeto() {
		List<ProdutoBE> produtos = new ArrayList<ProdutoBE>();

		JSONParser parser = new JSONParser();

		try {

			Object obj = parser
					.parse(new FileReader(
							"/var/www/html/busca_elastica/json_files/nike-producao.json"));

			JSONObject jsonObject = (JSONObject) obj;

			JSONObject root = (JSONObject) jsonObject.get("root");

			// loop array
			JSONArray items = (JSONArray) root.get("produto");

			for (Object itemObject : items) {
				JSONObject item = (JSONObject) itemObject;
				ProdutoBE produto = new ProdutoBE();

				// Inteiros

				if (item.get("id_produto") != null)
					produto.setId_produto(Integer.parseInt(item.get(
							"id_produto").toString()));

				if (item.get("id_categoria") != null)
					produto.setId_categoria(Integer.parseInt(item.get(
							"id_categoria").toString()));

				if (item.get("id_departamento") != null)
					produto.setId_departamento(Integer.parseInt(item.get(
							"id_departamento").toString()));

				if (item.get("id_linha") != null)
					produto.setId_linha(Integer.parseInt(item.get("id_linha")
							.toString()));

				if (item.get("id_sku") != null)
					produto.setId_sku(Integer.parseInt(item.get("id_sku")
							.toString()));

				if (item.get("salesrank") != null)
					produto.setSalesrank(Integer.parseInt(item.get("salesrank")
							.toString()));

				// strings

				if (item.get("categoria") != null)
					produto.setCategoria(item.get("categoria").toString());

				if (item.get("departamento") != null)
					produto.setDepartamento(item.get("departamento").toString());

				if (item.get("descricao") != null)
					produto.setDescricao(item.get("descricao").toString());

				if (item.get("imagem") != null)
					produto.setImagem(item.get("imagem").toString());

				if (item.get("imagem_media") != null)
					produto.setImagem_media(item.get("imagem_media").toString());

				if (item.get("lancamento") != null)
					produto.setLancamento(item.get("lancamento").toString());

				if (item.get("linha") != null)
					produto.setLinha(item.get("linha").toString());

				if (item.get("link_produto") != null)
					produto.setLink_produto(item.get("link_produto").toString());

				if (item.get("marca") != null)
					produto.setMarca(item.get("marca").toString());

				if (item.get("nome") != null) {
					produto.setNome(item.get("nome").toString());

					ConversorPalavras conversor = new ConversorPalavras();

					List<String> foneticas = conversor.getCodigosFoneticos(item
							.get("nome").toString());

					produto.setNomeFonetico(StringUtils.join(foneticas, " "));
				}

				if (item.get("palavra_chave") != null)
					produto.setPalavra_chave(item.get("palavra_chave")
							.toString());

				if (item.get("parcelamento") != null)
					produto.setParcelamento(item.get("parcelamento").toString());

				if (item.get("preco") != null)
					produto.setPreco(item.get("preco").toString());

				if (item.get("preco_de") != null)
					produto.setPreco_de(item.get("preco_de").toString());

				if (item.get("precobol") != null)
					produto.setPrecobol(item.get("precobol").toString());

				if (item.get("qtderating") != null)
					produto.setQtderating(item.get("qtderating").toString());

				if (item.get("textobol") != null)
					produto.setTextobol(item.get("textobol").toString());

				System.out.println("Produto: " + produto.getNome()
						+ " Codigo: " + produto.getNomeFonetico());

				produtos.add(produto);

			}
			System.out.println("Tamanho do array: " + produtos.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return produtos;
	}

	public List<String> ConverterObjetoParaJson(List<ProdutoBE> produtos) {

		List<String> indexes = new ArrayList<String>();

		for (ProdutoBE produto : produtos) {
			Gson gson = new Gson();
			String json = gson.toJson(produto);
			indexes.add(json);
			
			System.out.println(json);
		}
		return indexes;
	}
}
