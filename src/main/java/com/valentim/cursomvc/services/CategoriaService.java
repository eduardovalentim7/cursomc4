package com.valentim.cursomvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.valentim.cursomvc.domain.Categoria;
import com.valentim.cursomvc.repositories.CategoriaRepository;
import com.valentim.cursomvc.services.exceptions.DataIntegrityException;
import com.valentim.cursomvc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	// declara a dependencia de um objeto tipo categoriarepository
	@Autowired // instancia automatimente
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);// vai no bd, busca uma categoria
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto Não encontrado: " + id + ",Tipo: " + Categoria.class.getName());

		}
		return obj;

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);

	}

	public Categoria update(Categoria obj) {
		find(obj.getId());//chama o metodo acima 
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}catch(DataIntegrityViolationException e){
			throw new  DataIntegrityException("Não é possivel Excluir uma categoria que possui produtos");
		}
		
	}
	}
