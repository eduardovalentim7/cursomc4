package com.valentim.cursomvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentim.cursomvc.domain.Categoria;
import com.valentim.cursomvc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//declara a dependencia de um objeto tipo categoriarepository
	@Autowired //instancia automatimente
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
	 Categoria obj = repo.findOne(id);// vai no bd, busca uma categoria 
	 return obj;
		
		
	}

}
