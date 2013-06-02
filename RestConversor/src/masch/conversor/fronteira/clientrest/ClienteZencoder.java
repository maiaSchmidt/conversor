/**
 * 
 */
package masch.conversor.fronteira.clientrest;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author geist
 *
 */
public class ClienteZencoder {

	//Caminho para o acesso ao recurso de conversão do Zencoder
	private static final String URL_ZENCODER_CONVERSOR = "https://app.zencoder.com/api/v2/jobs";

	//Nessa URL "masch" é o nome do meu bucklet(como uma pasta) definido na minha conta da amazon(
	//serviço de armazenamento), o número final é a chave que permite o zencoder ter acesso ao
	//bucklet. Fiz uma configuração no bucklet para que com essa chave fosse permitido fazer várias
	//alterações no meu repositório para que os videos convertidos fosse salvos.
	private static final String REPOSITORIO_VIDEOS_CONVERTIDOS = "s3://masch/395540211253";
	
	private static final String EXTENSAO_VIDEO_OGG = "ogg";
	
	public ClientResponse requisitarConversao(String urlVideoOriginal, String nomeVideoConvertido)
	{		
		Client cliente = Client.create();
				
		WebResource conversorZencoder = cliente.resource(URL_ZENCODER_CONVERSOR);
				
		String entradaConversor = "{\"input\":\""+ urlVideoOriginal + "\",\"output\": [{\"base_url\": \""+ 
		REPOSITORIO_VIDEOS_CONVERTIDOS + "\",\"format\": \""+EXTENSAO_VIDEO_OGG+"\",\"public\": 1," +
		"\"filename\": \"" + nomeVideoConvertido + "." + EXTENSAO_VIDEO_OGG + "\"}]}";
				
		ClientResponse respostaConversor = conversorZencoder.
			type(MediaType.APPLICATION_JSON_TYPE).
			header("Zencoder-Api-Key", "e9677a1761687ff29ce0754550ec8338").
			post(ClientResponse.class, entradaConversor);
		
		return respostaConversor;
	}
}
