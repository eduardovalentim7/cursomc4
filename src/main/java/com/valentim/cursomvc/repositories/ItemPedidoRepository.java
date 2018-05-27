package com.valentim.cursomvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valentim.cursomvc.domain.ItemPedido;;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
