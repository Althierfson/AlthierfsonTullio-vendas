package residencia.vendas.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import residencia.vendas.builder.ItemBuilder;
import residencia.vendas.builder.PedidoBuilder;
import residencia.vendas.builder.UsuarioBuilder;
import residencia.vendas.entidade.Item;
import residencia.vendas.entidade.Pedido;
import residencia.vendas.entidade.TipoItem;
import residencia.vendas.entidade.Usuario;

public class TesteServicoVendas {

    @Test
    public void testeValordoFrete() {
        // Testando as condições de retorno do valor do frete

        // Criação dos elementos para testes
        final ServicoVendas servicoVendas = new ServicoVendas();
        List<Item> carrinho = new ArrayList<>();
        BigDecimal resultadoEsperado;

        // Configuração do Test #1 Peso < 2kg
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("0.00");

        // Execução do teste #1
        assertEquals(resultadoEsperado, servicoVendas.calcularFrete(carrinho));

        // Configuração do Test #2 Peso > 2kg && <= 10kg
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("5.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("10.00");

        // Execução do teste #2
        assertEquals(resultadoEsperado, servicoVendas.calcularFrete(carrinho));

        // Configuração do Test #3 Peso > 10kg && <= 50kg
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("35.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("140.00");

        // Execução do teste #3
        assertEquals(resultadoEsperado, servicoVendas.calcularFrete(carrinho));

        // Configuração do Test #4 Peso > 50kg
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("60.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("420.00");

        // Execução do teste #4
        assertEquals(resultadoEsperado, servicoVendas.calcularFrete(carrinho));

        // Configuração do Test #5 Carrinho com mais de 5 Itens
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.COMPUTADORES).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ESPORTES).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.LIVROS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ROUPAS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.BRINQUEDOS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.BRINQUEDOS).build());
        resultadoEsperado = new BigDecimal("24.00");

        // Execução do teste #5
        assertEquals(resultadoEsperado, servicoVendas.calcularFrete(carrinho));

        // Configuração do Test #6 Carrinho com mais de 2 itens do mesmo tipo
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.COMPUTADORES).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ESPORTES).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.LIVROS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.BRINQUEDOS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.BRINQUEDOS).build());
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("2.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.BRINQUEDOS).build());
        resultadoEsperado = new BigDecimal("22.80");

        // Execução do teste #5
        assertEquals(resultadoEsperado, servicoVendas.calcularFrete(carrinho));


    }

    @Test
    public void testevalorDoCarrinho() {
        // Testando as condições de retorno do valor total dos produtos

        // Criação dos elementos para testes
        final ServicoVendas servicoVendas = new ServicoVendas();
        List<Item> carrinho = new ArrayList<>();
        BigDecimal resultadoEsperado;

        // Configuração do Test #1 Carrinho de valor > 500 R$
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("700.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("630.00");

        // Execução do teste #1
        assertEquals(resultadoEsperado, servicoVendas.calcularValorDoCarrinho(carrinho));

        // Configuração do Test #2 Carrinho de valor > 1.000 R$
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("1200.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("960.00");

        // Execução do teste #2
        assertEquals(resultadoEsperado, servicoVendas.calcularValorDoCarrinho(carrinho));

        // Configuração do Test #3 Carrinho sem desconto
        carrinho = new ArrayList<>();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("300.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = new BigDecimal("300.00");

        // Execução do teste #2
        assertEquals(resultadoEsperado, servicoVendas.calcularValorDoCarrinho(carrinho));
    }

    @Test
    public void TesteFecharVenda(){
        // Testando as condições de retorno da função fechar venda

        // Criação dos elementos para testes
        final ServicoVendas servicoVendas = new ServicoVendas();
        List<Item> carrinho = new ArrayList<>();
        Usuario usuario;
        Pedido resultadoEsperado;

        // Configuração do Test #1 Carrinho de valor > 500 R$
        usuario = UsuarioBuilder.usuarioBuilder();
        carrinho.add(ItemBuilder.umItem().custando(new BigDecimal("700.00")).pesando(new BigDecimal("1.00")).doTipo(TipoItem.ELETRONICOS).build());
        resultadoEsperado = PedidoBuilder.umPedido().custando(new BigDecimal("630.00")).comALista(carrinho).doUsuario(usuario).builder();

        // Execução do teste #1
        assertEquals(resultadoEsperado, servicoVendas.fecharVenda(usuario, carrinho));
    }
}
