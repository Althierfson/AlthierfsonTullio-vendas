package residencia.vendas.servico;

import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import residencia.vendas.entidade.Item;
import residencia.vendas.entidade.Pedido;
import residencia.vendas.entidade.Usuario;

public class ServicoVendas {

	Pedido fecharVenda(Usuario usuario, List<Item> itens) {
		//throw new IllegalStateException("Funcionalidade não implementada.");
		LocalDate data = LocalDate.now();

		BigDecimal valorCarrinho = calcularValorDoCarrinho(itens);

		BigDecimal valorFrete = calcularFrete(itens);

		BigDecimal valorFinal = valorCarrinho.add(valorFrete);
		
		return new Pedido(
			usuario, 
			itens, 
			data, 
			valorFinal
		);
	}

	public BigDecimal calcularValorDoCarrinho(List<Item> itens) {
		
		BigDecimal valor = new BigDecimal("0.00");
		for(int i=0;i<itens.size();i++) {
			valor = valor.add(itens.get(i).preco()).setScale(2, RoundingMode.UP);
		}

		valor = verificarDesconto(valor);

		return valor;
	}

	public BigDecimal calcularFrete(List<Item> itens) {

		BigDecimal custoFrete = custoDoFrete(itens);

		if(verificarDescontoFrete(itens)) {

			BigDecimal valorDesconto = custoFrete.multiply( new BigDecimal("0.05")).setScale(2, RoundingMode.UP);
			return custoFrete.subtract(valorDesconto).setScale(2, RoundingMode.UP);

		}else {
			return custoFrete;
		}

	}

	BigDecimal verificarDesconto(BigDecimal custoTotal) {

		BigDecimal valorDesconto;

		if(custoTotal.compareTo(new BigDecimal("1000.00")) == 1) {
			valorDesconto = custoTotal.multiply(new BigDecimal("0.20")).setScale(2, RoundingMode.UP);
			return custoTotal.subtract(valorDesconto).setScale(2, RoundingMode.UP);
		}

		if(custoTotal.compareTo(new BigDecimal("500.00")) == 1) {
			valorDesconto = custoTotal.multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.UP);
			return custoTotal.subtract(valorDesconto).setScale(2, RoundingMode.UP);
		}

		return custoTotal;

	}

	boolean verificarDescontoFrete(List<Item> itens) {

		int cont=1;
		for(int i=0;i<itens.size();i++) {
			for(int j=i+1;j<itens.size();j++) {
				if(itens.get(i).tipo() == itens.get(j).tipo()) {
					cont++;
					if(cont >= 3) {
						return true;
					}
				}
			}
			cont = 1;
		}
		return false;
	}

	public BigDecimal custoDoFrete(List<Item> itens){
		BigDecimal peso = new BigDecimal("0.00").setScale(2, RoundingMode.UP);
		BigDecimal valorFrete = new BigDecimal("0.00").setScale(2, RoundingMode.UP);

		for(int i=0;i<itens.size();i++){
			peso = peso.add(itens.get(i).peso()).setScale(2, RoundingMode.UP);
		}

		// Calcular taxa de quantidade de intens

		if(itens.size() > 5){
			valorFrete = valorFrete.add( new BigDecimal("10.00")).setScale(2, RoundingMode.UP);
		}
		
		// Calcular taxa de peso

		if(peso.compareTo(new BigDecimal("50.00")) == 1) {
			// Peso acima de 50Kg
			return valorFrete.add( new BigDecimal("7.00").multiply(peso)).setScale(2, RoundingMode.UP);
		}
		
		if(peso.compareTo(new BigDecimal("10.00")) == 1){
			// Peso abaixo ou igual a 50Kg e maior que 10kg
			return valorFrete.add( new BigDecimal("4.00").multiply(peso)).setScale(2, RoundingMode.UP);
		}
		
		if(peso.compareTo(new BigDecimal("2.00")) == 1){
			// Peso abaixo ou igual a 10Kg e maior que 2kg
			return valorFrete.add( new BigDecimal("2.00").multiply(peso)).setScale(2, RoundingMode.UP);
		}

		// Peso maior que 0Kg e menor ou igual a 2kg
		return valorFrete;
	}
}
