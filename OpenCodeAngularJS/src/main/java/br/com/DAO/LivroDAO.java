package br.com.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.Entity.LivroEntity;
import br.com.conexao.ConexaoBD;

public class LivroDAO {

	public EntityManager getEM(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("opencode");
		return factory.createEntityManager();
	}
	
	public LivroEntity salvar(LivroEntity liv){
		EntityManager em = getEM();
		try{
			em.getTransaction().begin();
			em.persist(liv);
			em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
		return liv;
	}
	
	public LivroEntity getOne(LivroEntity liv){
		String query = "SELECT * FROM LIVRO WHERE TITULO = '"+liv.getTitulo()+"'";
		ConexaoBD conexao = ConexaoBD.getDbCon();
		try {
			ResultSet rs = conexao.query(query);
			if(!rs.isBeforeFirst()){
				return null;
			}else{
				LivroEntity retorno = new LivroEntity();
				while (rs.next()) {
					retorno.setId(rs.getInt("ID"));
		            retorno.setLinguagem(rs.getString("LINGUAGEM"));
		            retorno.setTitulo(rs.getString("TITULO"));
		        }
				return retorno;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public void deleteOne(int id){
		EntityManager em = getEM();
		LivroEntity livro = em.find(LivroEntity.class, id);
		try{
			em.getTransaction().begin();
			em.remove(livro);
			em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}
	}
	
	@SuppressWarnings("finally")
	public LivroEntity findById(int id){
		EntityManager em = getEM();
		LivroEntity livro = null;
		try{
			livro = em.find(LivroEntity.class, id);
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
			return livro;
		}
	}
	
	public List<LivroEntity> getAll(){
		String query = "SELECT * FROM LIVRO";
		ConexaoBD conexao = ConexaoBD.getDbCon();
		try {
			ResultSet rs = conexao.query(query);
			if(!rs.isBeforeFirst()){
				return null;
			}else{
				List<LivroEntity> retorno = new ArrayList<LivroEntity>();
				while (rs.next()) {
					LivroEntity livro = new LivroEntity();
					livro.setId(rs.getInt("ID"));
					livro.setLinguagem(rs.getString("LINGUAGEM"));
					livro.setTitulo(rs.getString("TITULO"));
					retorno.add(livro);
		        }
				return retorno;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public void editOne(int id, String ling, String tit){
		EntityManager em = getEM();
		LivroEntity livro = em.find(LivroEntity.class, id);
		try{
			em.getTransaction().begin();
			livro.setLinguagem(ling);
			livro.setTitulo(tit);
			em.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			em.getTransaction().rollback();
		}finally {
			em.close();
		}		
	}
}
