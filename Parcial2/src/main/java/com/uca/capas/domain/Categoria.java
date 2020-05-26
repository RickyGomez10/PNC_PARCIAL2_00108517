package com.uca.capas.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(schema="public",name="cat_categoria")
public class Categoria {
	
	@Id
	@Column(name="c_categoria")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer codigoCategoria;
	
	
	@Size(message ="El nombre de la categoria no debe tener más de 50 caracteres", max=50)
	@NotEmpty(message = "Este campo no puede estar vacío")
	@Column(name="s_categoria")
	public String categoria;
	
	@OneToMany(mappedBy="categoria", fetch = FetchType.EAGER)
	private List<Libro> libros;
	
	public Categoria() {
		
	}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}
	
	
	
	
}
