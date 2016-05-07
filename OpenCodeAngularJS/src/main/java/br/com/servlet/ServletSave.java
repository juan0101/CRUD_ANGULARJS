package br.com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.DAO.LivroDAO;
import br.com.Entity.LivroEntity;

public class ServletSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	LivroDAO livDAO = new LivroDAO();
		try{
			List<LivroEntity> listaLivro = livDAO.getAll();
			JSONArray jsonArray = new JSONArray();  
            for (LivroEntity obj : listaLivro) {  
                 JSONObject jsonObject = new JSONObject();  
                 jsonObject.put("id", obj.getId());
                 jsonObject.put("linguagem", obj.getLinguagem());  
                 jsonObject.put("titulo", obj.getTitulo());  
                 jsonArray.put(jsonObject);  
            }
            response.getWriter().print(jsonArray);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
        BufferedReader br = request.getReader();
        String str = null;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        JSONObject jObj = new JSONObject(sb.toString());
        String linguagem = jObj.getString("ling");
        String titulo = jObj.getString("titulo");
 
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
   
		LivroDAO livDAO = new LivroDAO();
		LivroEntity livro = new LivroEntity();
		livro.setLinguagem(linguagem);
		livro.setTitulo(titulo);
		try{
			livDAO.salvar(livro);
			LivroEntity newLivro = livDAO.getOne(livro);
			 
            JSONObject jsonObject = new JSONObject(); 
			jsonObject.put("id", newLivro.getId());
            jsonObject.put("linguagem", newLivro.getLinguagem());  
            jsonObject.put("titulo", newLivro.getTitulo());
            
            response.getWriter().print(jsonObject);
		}catch (Exception e){
			e.printStackTrace();
			response.getWriter().append("Deu erro na hora de salvar!");
		}
	}

}
