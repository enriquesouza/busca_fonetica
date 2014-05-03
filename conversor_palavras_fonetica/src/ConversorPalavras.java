import phonetic.*;
import java.util.ArrayList;
import java.util.List;

public class ConversorPalavras {

	// "/var/www/html/conversor_palavras_fonetica/lista_palavras/palavras_dicionario.txt"
	/**
	 * @param args
	 */
	// public static void main(String[] args) {
	public List<PalavraFonetica> ConverterPalavras(String caminho) {
		// TODO Auto-generated method stub
		System.out.println("Instanciando a classe LerArquivo");
		LerArquivo arquivo = new LerArquivo();
		List<String> palavras = arquivo.ProcessarPalavras(caminho);

		List<PalavraFonetica> palavrasConvertidas = new ArrayList<PalavraFonetica>();

		System.out.println("Transformar as palavras no código fonético");
		for (String palavra : palavras) {

			PalavraFonetica palavraConvertida = new PalavraFonetica();

			String codigoFonetico = getCodigoFonetico(palavra);

			palavraConvertida.setCodigoFonetico(codigoFonetico);
			palavraConvertida.setPalavra(palavra);

			palavrasConvertidas.add(palavraConvertida);

			System.out.println(codigoFonetico);
		}
		return RemoverPalavrasDuplicadas(palavrasConvertidas);
	}

	public List<PalavraFonetica> RemoverPalavrasDuplicadas(
			List<PalavraFonetica> palavras) {

		System.out.println("Remover as palavras duplicadas");

		List<PalavraFonetica> palavrasSemDuplicacoes = new ArrayList<PalavraFonetica>();
		for (PalavraFonetica palavra : palavras) {
			Boolean existe = false;
			for (PalavraFonetica palavraSemDuplicacao : palavrasSemDuplicacoes) {
				if (palavra.getCodigoFonetico() == palavraSemDuplicacao
						.getCodigoFonetico()
						&& palavra.getPalavra() == palavraSemDuplicacao
								.getPalavra()) {
					existe = true;
					break;
				}
			}
			if (!existe) {
				palavrasSemDuplicacoes.add(palavra);
				System.out.println("palavra : " + palavra.getPalavra()
						+ "\t\t|\t\t codigo fonético: "
						+ palavra.getCodigoFonetico());

			}
		}
		return palavrasSemDuplicacoes;
	}

	public List<String> getCodigosFoneticos(String texto) {

		List<String> listaPalavrasFoneticas = new ArrayList<String>();

		if (texto != null && !texto.isEmpty()) {
			if (texto.indexOf(" ") >= 0) {

				String[] palavras = texto.split(" ");
				for (String palavra : palavras) {
					listaPalavrasFoneticas.add(getCodigoFonetico(palavra));
				}
			} else {
				listaPalavrasFoneticas.add(getCodigoFonetico(texto));
			}
		}
		return listaPalavrasFoneticas;

	}

	public String getCodigoFonetico(String palavra) {

		Phonetic p = Phonetic.b();
		Phonetic.a(p, 10); // setParameterBytes
		Phonetic.a(p, ""); // setParameterDesprezo
		Phonetic.b(p, palavra); // setParameterName

		return Phonetic.a(p); // no caso, 4CA16C1000

	}
}
