/**
 * 
 */
package masch.conversor;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import masch.conversor.fronteira.processadorrequisicao.PedidoConversao;


/**
 * @author geist
 *
 */
//Ínício do caminho URL para tratamento de requisições, onde está
//mapeado o Jersey servlet.
@ApplicationPath("conversor")
public class AplicacaoConversor extends Application 
{
	//Específica quais classes fazem colaboram com o Jersey servlet no tratamento
	//das requisições.
	@Override
	public Set<Class<?>> getClasses() {
		 Set<Class<?>> s = new HashSet<Class<?>>();
	     s.add(PedidoConversao.class);
	     return s;
	}
}
