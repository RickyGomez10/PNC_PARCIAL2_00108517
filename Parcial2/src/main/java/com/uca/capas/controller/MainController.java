package com.uca.capas.controller;

import java.awt.Checkbox;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.CategoriaDAO;

import com.uca.capas.dao.LibroDAO;
import com.uca.capas.domain.Categoria;

import com.uca.capas.domain.Libro;

@Controller
public class MainController {

	@Autowired
	private LibroDAO libroDAO;
	
	@Autowired
	private CategoriaDAO categoriaDAO;
	
	//Paginas web
	@RequestMapping("/index")
	public ModelAndView initMain() {
		ModelAndView mav = new ModelAndView();
		List<Categoria>  categoria = null;
		Libro libro = new Libro();
		try {
			categoria = categoriaDAO.findAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("mensaje"," ");
		mav.addObject("categoria", categoria);
		mav.addObject("libro", libro);
		mav.setViewName("Inicio");
		return mav;
		
	}
	
		
	
	@RequestMapping("/Categoria")
	public ModelAndView initMain2() {
		ModelAndView mav = new ModelAndView();
		Libro libro = new Libro();
		Categoria categoria = new Categoria();
		
		mav.addObject("categoria", categoria);
		mav.addObject("libro", libro);
		mav.setViewName("Categoria");
		return mav;
		
	}
	
	@RequestMapping("/Libro")
	public ModelAndView initMain3() {
		ModelAndView mav = new ModelAndView();
		Libro libro = new Libro();
		List<Categoria>  categoria = null;
		try {
			categoria = categoriaDAO.findAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("categoria", categoria);
		mav.addObject("libro", libro);
		mav.setViewName("Libro");
		return mav;
	}
	
	//Backend
	@RequestMapping(value="/IngresarCategoria", method=RequestMethod.POST)
	public ModelAndView insertar(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		//Contiene error?
		if(!result.hasErrors()) {
		try {
			
		
			categoriaDAO.insertar(categoria);
			mav.addObject("mensaje","Categoria guardada con exito");
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("mensaje","No se pudo guardar la categoria");
		}
		mav.addObject("categoria", categoria);
		mav.setViewName("Inicio");
		}else {
			mav.setViewName("Categoria");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/IngresarLibro", method=RequestMethod.POST)
	public ModelAndView insertar2(@Valid @ModelAttribute Libro libro, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(!result.hasErrors()) {
		try {
			Date date = new Date();
			libro.setFecha(date);
			Categoria categoria = categoriaDAO.findOne(libro.getCodigoCategoria());
			libro.setCategoria(categoria);
			libroDAO.insertar(libro);
			mav.addObject("mensaje","Libro guardado con exito");
		}catch(Exception e) {
			e.printStackTrace();
			mav.addObject("mensaje","No se pudo guardar el libro");
		}
		mav.addObject("libro", libro);
		mav.setViewName("Inicio");
		}else {
			List<Categoria>  categoria = null;
			categoria = categoriaDAO.findAll();
			mav.addObject("categoria", categoria);
			mav.setViewName("Libro");
		}
		
		return mav;
	}
	
	@RequestMapping("/VerLibros")
	public ModelAndView initMain4() {
		ModelAndView mav = new ModelAndView();
		List<Libro>  libros = null;
		try {
			libros = libroDAO.findAll();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("libros", libros);
		mav.setViewName("VerLibro");
		return mav;
		
	}
	
	
}
