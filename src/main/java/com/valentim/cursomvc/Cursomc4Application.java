package com.valentim.cursomvc;

import java.text.SimpleDateFormat;
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
import com.valentim.cursomvc.domain.ItemPedido;
import com.valentim.cursomvc.domain.Pagamento;
import com.valentim.cursomvc.domain.PagamentoComBoleto;
import com.valentim.cursomvc.domain.PagamentoComCartao;
import com.valentim.cursomvc.domain.Pedido;
import com.valentim.cursomvc.domain.Produto;
import com.valentim.cursomvc.enums.EstadoPagamento;
import com.valentim.cursomvc.enums.TipoCliente;
import com.valentim.cursomvc.repositories.CategoriaRepository;
import com.valentim.cursomvc.repositories.CidadeRepository;
import com.valentim.cursomvc.repositories.ClienteRepository;
import com.valentim.cursomvc.repositories.EnderecoRepository;
import com.valentim.cursomvc.repositories.EstadoRepository;
import com.valentim.cursomvc.repositories.ItemPedidoRepository;
import com.valentim.cursomvc.repositories.PagamentoRepository;
import com.valentim.cursomvc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(Cursomc4Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informatica");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama, mesa e Banho");
		Categoria cat4 = new Categoria(null,"Eletronicos");
		Categoria cat5 = new Categoria(null,"Jardinagem");
		Categoria cat6 = new Categoria(null,"Decoração");
		Categoria cat7 = new Categoria(null,"Perfumaria");
		
		
		
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
		categoriaRepository.save(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null,sdf.parse("30/09/2017 10:32") , cli1,e1);
		Pedido ped2 = new Pedido(null,sdf.parse("10/10/2017 19:35") , cli1,e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped1,6);
		ped1.setPagamento(pgto1);
		Pagamento pgto2 = new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.save(Arrays.asList(ped1,ped2));
		pagamentoRepository.save(Arrays.asList(pgto1,pgto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,0.00,2,80.00);	
		ItemPedido ip3 = new ItemPedido(ped2,p2,100.00,1,800.00);
		
		//Associações cada pedido conhecer seus itens 
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		//cada  produto conhecer seus itens 
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.save(Arrays.asList(ip1,ip2,ip3));
		
		
		
	}
	
	
	
}
