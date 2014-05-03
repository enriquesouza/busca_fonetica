import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

public class PalavrasDalc {

	public String GetNomeBaseDados(){
		return "busca_elastica";
	}
	public String GetNomeColecao(){
		return "palavrasFoneticas";
	}
	
	public MongoClient GetMongoClient() throws UnknownHostException{
		String textUri = "mongodb://befbr:BuscA3Lastica_C0pA2014@ubuntuserver:27017/busca_elastica";
		MongoClientURI uri = new MongoClientURI(textUri);
		MongoClient m = new MongoClient(uri);
		return m;
	}
	
	public void IncluirPalavrasMongoDB(List<PalavraFonetica> palavras)
			throws UnknownHostException {
		
		MongoClient client = GetMongoClient();		
		DB base = client.getDB(GetNomeBaseDados());
		if(base.collectionExists(GetNomeColecao()) == true){
			DBCollection colecao = base.getCollection(GetNomeColecao());	
			int i = 1;
			for(PalavraFonetica palavra : palavras){
				BasicDBObject doc = new BasicDBObject();				
				doc.append("codigoFonetico", palavra.getCodigoFonetico());				
				doc.append("palavra", palavra.getPalavra());				
				colecao.insert(doc);
				System.out.println(i);
				i++;
			}
			
		}

	}
}
