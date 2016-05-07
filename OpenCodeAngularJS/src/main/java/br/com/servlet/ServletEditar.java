package br.com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import br.com.DAO.LivroDAO;
import br.com.Entity.LivroEntity;

public class ServletEditar extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public ServletEditar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());
        int id = jObj.getInt("id");
        String linguagem = jObj.getString("linguagem");
        String titulo = jObj.getString("titulo");
 
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
   
		LivroDAO livDAO = new LivroDAO();
		try{
			livDAO.editOne(id,linguagem,titulo);
		}catch (Exception e){
			e.printStackTrace();
			response.getWriter().append("Deu erro na hora de salvar!");
		}
	}

}
