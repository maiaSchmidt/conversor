/**
 * 
 */
package masch.conversor.fronteira.processadorrequisicao;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import masch.conversor.fronteira.clientrest.ClienteZencoder;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.ClientResponse.Status;


/**
 * @author geist
 *
 */
//Anotação indica o fim do caminho URL para acessar essa funcionalidade (trata os pedidos de conversão). 
//O começo do caminho é definido pela anotação @ApplicationPath na classe AplicacaoConversor.
@Path("pedidoconversao")
public class PedidoConversao {

	private static final String URL = "\"url\":\"";
	private static final String ID  = "\",\"id\":";
	private ClienteZencoder clienteZencoder = new ClienteZencoder();

	//Anotação indica que o método trata os posts provenintes do caminho definido (@Path da classe)
	@POST
	//Anotação indica que o método só vai tratar posts cujo form tem esse tipo de formatação de dado (enctype do form)  
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void tratarPedidoConversao (
		@FormParam("urlVideoOriginal") //Anotação indica que só vamos retira do form do post, o valor do parâmetro de nome "urlVideoOriginal" 
		String urlVideoOriginal,
		@FormParam("nomeVideoConvertido")
		String nomeVideoConvertido, 
		@Context HttpServletResponse servletResponse) throws IOException
	{
		//Resposta recebida do conversor de video do Zencoder.
		ClientResponse respostaRecebidaZencoder = null;		
		try
		{
			//Chamada ao cliente do serviço WebService fornecido pelo zencoder
			respostaRecebidaZencoder = clienteZencoder.requisitarConversao(urlVideoOriginal, 
					nomeVideoConvertido);
			
			//Verifica se houve algum erro na conversão.
			if (respostaRecebidaZencoder.getStatus() !=  Status.CREATED.getStatusCode() ) {
				throw new RuntimeException("Falha : HTTP código de erro : "
				     + respostaRecebidaZencoder.getStatus());
			} else
			{
				String dadosResposta = respostaRecebidaZencoder.getEntity(String.class);
				String enderecoVideoConvertido = dadosResposta.split(URL)[1].split(ID)[0] ;						
				
				//Sabendo o endereço onde o video encontra-se decodificado, o sistema
				//redireciona para essa página. TODO: Implementar algum esquema que
				//verifique que o video já está no repositório indicado, já que o 
				//processo de conversão é assíncrono.
				servletResponse.sendRedirect(enderecoVideoConvertido);
			}		
		}  catch (Exception e) 
		{			
			servletResponse.sendError(respostaRecebidaZencoder.getStatus());
		}		
	}
}
