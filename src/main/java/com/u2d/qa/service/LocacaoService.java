package com.u2d.qa.service;

import com.u2d.qa.entity.Filme;
import com.u2d.qa.entity.Locacao;
import com.u2d.qa.entity.Usuario;
import com.u2d.qa.exception.FilmeSemEstoqueException;
import com.u2d.qa.exception.LocadoraException;
import com.u2d.qa.repository.LocacaoRepository;
import com.u2d.qa.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.u2d.qa.util.DataUtils.adicionarDias;

@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository repository;

    @Autowired
    private SPCService spcService;

    public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws LocadoraException, FilmeSemEstoqueException {
        if (usuario == null)
            throw new LocadoraException("Usuário vazio");
        validaFilmeVazio(filmes);
        Double valorTotal = 0.0;

        AtomicInteger i = new AtomicInteger(0);
        for (Filme filme: filmes) {
            i.incrementAndGet();
            validaFilmeVazio(filme);
            validaFilmeSemEstoque(filme);
            aplicaDesconto(i, filme);
            valorTotal += filme.getPrecoLocacao();
        }

        if (spcService.possuiNegativacao(usuario))
            throw new LocadoraException("Usuário Negativado");

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY))
            dataEntrega = adicionarDias(dataEntrega, 1);

        Locacao locacao = Locacao.builder()
                .filmes(filmes)
                .usuario(usuario)
                .dataLocacao(new Date())
                .dataRetorno(dataEntrega)
                .valor(valorTotal)
                .build();
        repository.save(locacao);
        return locacao;
    }

    private void aplicaDesconto(AtomicInteger i, Filme filme) {
        Double valorUnit = filme.getPrecoLocacao();
        if (i.get() == 3)
            filme.setPrecoLocacao(valorUnit *= 0.75D);
        if (i.get() == 4)
            filme.setPrecoLocacao(valorUnit *= 0.50D);
        if (i.get() == 5)
            filme.setPrecoLocacao(valorUnit *= 0.25D);
        if (i.get() == 6)
            filme.setPrecoLocacao(0.0D);
    }

    private void validaFilmeSemEstoque(Filme filme) throws FilmeSemEstoqueException {
        if (filme.getEstoque() < 1)
            throw new FilmeSemEstoqueException();
    }

    private void validaFilmeVazio(List<Filme> filmes) throws LocadoraException {
        if (filmes.isEmpty())
            throw new LocadoraException("Filme vazio");
    }
    private void validaFilmeVazio(Filme filme) throws LocadoraException {
        if (filme == null)
            throw new LocadoraException("Filme vazio");
    }
}
