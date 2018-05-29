package com.valentim.cursomvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentim.cursomvc.domain.Pedido;
import com.valentim.cursomvc.repositories.PedidoRepository;
import com.valentim.cursomvc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	//declara a dependencia de um objeto tipo categoriarepository
	@Autowired //instancia automatimente
	private PedidoRepository repo;
	
	public Pedido find(Integer id) {
	 Pedido obj = repo.findOne(id);// vai no bd, busca uma categoria 
	 if(obj==null) {
		 throw new ObjectNotFoundException("Objeto Não encontrado: " +id + ",Tipo: " + Pedido.class.getName());
		 
	 }
	 return obj;
		
		
	}

}
