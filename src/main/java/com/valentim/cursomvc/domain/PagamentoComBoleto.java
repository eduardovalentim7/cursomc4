package com.valentim.cursomvc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.valentim.cursomvc.enums.EstadoPagamento;

@Entity
public class PagamentoComBoleto extends Pagamento{
	private Date dataVencimento;
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
		
	}

	//ATENCAO - Acrescenta os atributos acima
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,Date dataVencimento,Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataVencimento;
		this.dataVencimento = dataPagamento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}
