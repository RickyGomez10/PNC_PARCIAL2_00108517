package com.uca.capas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Categoria;


@Repository
public class CategoriaDAOImpl implements CategoriaDAO {
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	@Override
	public List<Categoria> findAll() throws DataAccessException {
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.cat_categoria");
		Query query = entityManager.createNativeQuery(sb.toString(), Categoria.class);
		List<Categoria> resultset = query.getResultList();
		return resultset;
	}

	@Override
	public Categoria findOne(Integer code) throws DataAccessException {
		Categoria categoria = entityManager.find(Categoria.class, code);
		return categoria;
	}

	@Override
	@Transactional
	public void insertar(Categoria categoria) throws DataAccessException {
		// TODO Auto-generated method stub
		entityManager.persist(categoria);
	}

}
