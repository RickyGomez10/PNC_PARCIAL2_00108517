package com.uca.capas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema="public",name="cat_libro")
public class Libro {
	@Id
	@Column(name="c_libro")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoLibro;
	
	@Size(message ="El titulo no debe tener más de 500 caracteres", max=500)
	@NotEmpty(message = "Este campo no puede estar vacío")
	@Column(name="s_titulo")
	private String titulo;
	
	@Size(message ="El autor no debe tener más de 15 caracteres", max=150)
	@NotEmpty(message = "Este campo no puede estar vacío")
	@Column(name="s_autor")
	private String autor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="c_categoria")
	private Categoria categoria;
	
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name="f_ingreso")
	private Date fecha;
	
	@Column(name="b_estado")
	private Boolean estado;
	
	@Size(message ="El isbn no debe tener más de 15 caracteres", max=10)
	@NotEmpty(message = "Este campo no puede estar vacío")
	@Column(name="s_isbn")
	private String isbn;
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Transient
	private Integer codigoCategoria;
	
	//delegate
	public String getEstadoDelegate() {
		if(this.estado == null) return "";
		else {
			return estado == true?"Activo" :"Inactivo";
		}
	}
	
	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
	public Libro() {
		
	}

	public Integer getCodigoLibro() {
		return codigoLibro;
	}

	public void setCodigoLibro(Integer codigoLibro) {
		this.codigoLibro = codigoLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCodigoCategoria() {
		return codigoCategoria;
	}

	public void setCodigoCategoria(Integer codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	
}
