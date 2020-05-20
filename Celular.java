package com.mlu.celular;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.document.JSONDocumentManager;
import com.marklogic.client.io.JacksonHandle;

/**
 * Servlet implementation class Celular
 */
@WebServlet("/api")
public class Celular extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Celular() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//PrintWriter out = response.getWriter();
		//out.println("Configuración de Servlet Finalizada");
		
		DatabaseClient client = DatabaseClientFactory.newClient("localhost", 8053,
				"admin", "admin",
				Authentication.DIGEST);
		
		JSONDocumentManager docMgr = client.newJSONDocumentManager();
		JacksonHandle handle = new JacksonHandle();
		docMgr.read("/fun/motog6plus.json", handle);
		JsonNode doc = handle.get();
		
		PrintWriter out = response.getWriter();
		//out.println(doc);//Format JSON
		out.println("<h1>Celular</h1>");
		out.println("<p>"+"Marca: "+doc.get("marca").asText()+"<br>"
		           +"Modelo: "+doc.get("modelo").asText() + "<br>"
	               +"Compañía: "+doc.get("compañia").asText()+"<br>"
		           +"Sistema Operativo: "+doc.get("SO").asText()+"</p>"); 
	}

}
