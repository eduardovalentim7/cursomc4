package com.valentim.cursomvc.enums;

public enum TipoCliente {
	PESSOAFISICA(1,"Pessoa Fisica"),
	PESSOAJURIDICA(2,"Pessoa Juridica");
	
	private int cod;
	private String Descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.Descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return Descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoCliente x :  TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Id Inválido " + cod);
	}

}
