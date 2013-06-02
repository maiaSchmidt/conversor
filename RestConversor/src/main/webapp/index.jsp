<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>Masch Conversor</title>
  </head>
  <body>
    <h1>Conversor de video</h1>
    
    <form id="Converter" action="<%=request.getContextPath()%>/conversor/pedidoconversao" method="POST" enctype="application/x-www-form-urlencoded">
		<table>		
			<tr>
				<td>
					URL do video: <input type="text" name="urlVideoOriginal" value="http://dinamica-sambatech.s3.amazonaws.com/sample.dv">				
				</td>							
			</tr>
			<tr>
				<td>
					Nome do video convertido: <input type="text" name="nomeVideoConvertido">
					<input type="submit" Value="Converter video">
				</td>
			</tr>				
		</table>
	</form>	    
  </body>
</html> 
