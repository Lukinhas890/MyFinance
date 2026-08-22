package Service;

import Model.Categoria;
import Model.TipoTransacao;
import Model.Transacao;
import Model.TransacaoNaoEncontradaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import DAO.TransacaoDAO;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SistemaFinanceiroTest {

    @Mock
    private TransacaoDAO transacaoDAO;

    private SistemaFinanceiro sistemaFinanceiro;

    @BeforeEach
    void setUp() throws Exception {
        sistemaFinanceiro = new SistemaFinanceiro();
        Field field = SistemaFinanceiro.class.getDeclaredField("transacaoDAO");
        field.setAccessible(true);
        field.set(sistemaFinanceiro, transacaoDAO);
    }

    @Test
    void calcularSaldo_deveSomarReceitasESubtrairDespesas() {
        List<Transacao> transacoes = Arrays.asList(
                new Transacao(1, Categoria.ALIMENTACAO, 100.0, TipoTransacao.RECEITA, "salario"),
                new Transacao(2, Categoria.TRANSPORTE, 40.0, TipoTransacao.DESPESA, "onibus"),
                new Transacao(3, Categoria.LAZER, 20.0, TipoTransacao.DESPESA, "cinema"),
                new Transacao(4, Categoria.ESTUDOS, 80.0, TipoTransacao.RECEITA, "curso")
        );

        when(transacaoDAO.listar()).thenReturn(transacoes);

        double saldo = sistemaFinanceiro.calcularSaldo();

        assertEquals(120.0, saldo);
    }

    @Test
    void adicionarTransacao_deveChamarDaoComTransacaoValida() {
        sistemaFinanceiro.adicionarTransacao(250.0, Categoria.ESTUDOS, TipoTransacao.RECEITA, "curso");

        verify(transacaoDAO).salvar(any(Transacao.class));
    }

    @Test
    void buscarPorId_quandoOptionalPreenchido_deveRetornarTransacao() throws TransacaoNaoEncontradaException {
        Transacao esperada = new Transacao(10, Categoria.ALIMENTACAO, 50.0, TipoTransacao.DESPESA, "mercado");
        when(transacaoDAO.buscarPorId(10)).thenReturn(Optional.of(esperada));

        Transacao retorno = sistemaFinanceiro.buscarPorId(10);

        assertEquals(esperada, retorno);
        assertEquals(10, retorno.getId());
    }

    @Test
    void buscarPorId_quandoOptionalVazio_deveLancarExcecao() {
        when(transacaoDAO.buscarPorId(99)).thenReturn(Optional.empty());

        assertThrows(TransacaoNaoEncontradaException.class, () -> sistemaFinanceiro.buscarPorId(99));
    }

    @Test
    void buscarPorCategoria_quandoListaVazia_deveLancarExcecao() {
        when(transacaoDAO.buscarPorCategoria(Categoria.LAZER)).thenReturn(List.of());

        assertThrows(TransacaoNaoEncontradaException.class,
                () -> sistemaFinanceiro.buscarPorCategoria(Categoria.LAZER));
    }

    @Test
    void atualizarTransacao_quandoIdNaoExiste_deveLancarExcecao() {
        when(transacaoDAO.buscarPorId(500)).thenReturn(Optional.empty());

        assertThrows(TransacaoNaoEncontradaException.class,
                () -> sistemaFinanceiro.atualizarTransacao(500, 75.0, Categoria.TRANSPORTE,
                        TipoTransacao.DESPESA, "taxi"));
    }

    @Test
    void removerTransacao_quandoTransacaoExiste_deveRemoverEDevolverObjeto() {
        Transacao transacao = new Transacao(7, Categoria.LAZER, 30.0, TipoTransacao.DESPESA, "cinema");
        when(transacaoDAO.buscarPorId(7)).thenReturn(Optional.of(transacao));

        Transacao removida = sistemaFinanceiro.removerTransacao(7);

        assertNotNull(removida);
        assertEquals(7, removida.getId());
        verify(transacaoDAO).deletar(7);
    }
}
