package com.valentim.cursomvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentim.cursomvc.domain.Cliente;
import com.valentim.cursomvc.repositories.ClienteRepository;
import com.valentim.cursomvc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	//declara a dependencia de um objeto tipo categoriarepository
	@Autowired //instancia automatimente
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
	 Cliente obj = repo.findOne(id);// vai no bd, busca uma categoria 
	 if(obj==null) {
		 throw new ObjectNotFoundException("Objeto Não encontrado: " +id + ",Tipo: " + Cliente.class.getName());
		 
	 }
	 return obj;
		
		
	}

}
