package br.com.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="livro")
@Data
public class LivroEntity {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	@Column(name="linguagem", nullable=false)
	private String linguagem;
	@Column(name="titulo", nullable=false)
	private String titulo;

}
