package com.valentim.cursomvc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.valentim.cursomvc.domain.Categoria;
import com.valentim.cursomvc.domain.Cidade;
import com.valentim.cursomvc.domain.Cliente;
import com.valentim.cursomvc.domain.Endereco;
import com.valentim.cursomvc.domain.Estado;
import com.valentim.cursomvc.domain.Produto;
import com.valentim.cursomvc.enums.TipoCliente;
import com.valentim.cursomvc.repositories.CategoriaRepository;
import com.valentim.cursomvc.repositories.CidadeRepository;
import com.valentim.cursomvc.repositories.ClienteRepository;
import com.valentim.cursomvc.repositories.EnderecoRepository;
import com.valentim.cursomvc.repositories.EstadoRepository;
import com.valentim.cursomvc.repositories.ProdutoRepository;

@SpringBootApplication
public class Cursomc4Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Cursomc4Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		
		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		
		//associando na lista  - Categoria conhce os produtos 
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		//produto conhece as categorias 
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
				
		//salvar no banco
		//responsável por salvar é o CategoriaRepository
		categoriaRepository.save(Arrays.asList(cat1,cat2));
		produtoRepository.save(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlandia",est1);
		Cidade c2 = new Cidade(null,"Sao Paulo" ,est2);
		Cidade c3 = new Cidade(null,"Campinas"  ,est2);
		
		est2.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.save(Arrays.asList(est1,est2));
		cidadeRepository.save(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null,"Maria Silva","maria@gmail.com","12345678",TipoCliente.PESSOAFISICA);
		
		//telefones 
		cli1.getTelefones().addAll(Arrays.asList("33413317","33413507"));
		
		Endereco e1 = new Endereco(null,"Rua Flores","300","Apto203","Jardim","61940000",cli1,c1);
		Endereco e2 = new Endereco(null,"Avenida Matos","105","Sala 800","Centro","61841214",cli1,c2);
		
		//Cliente deve conhecer os endereços dele 
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));	
		
		
		clienteRepository.save(Arrays.asList(cli1));
		enderecoRepository.save(Arrays.asList(e1,e2));
	}
	
	
}
