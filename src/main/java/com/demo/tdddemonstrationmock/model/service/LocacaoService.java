package com.demo.tdddemonstrationmock.model.service;

import com.demo.tdddemonstrationmock.exceptions.FilmeSemEstoqueException;
import com.demo.tdddemonstrationmock.exceptions.LocadoraException;
import com.demo.tdddemonstrationmock.model.entity.Filme;
import com.demo.tdddemonstrationmock.model.entity.Locacao;
import com.demo.tdddemonstrationmock.model.entity.Usuario;

import java.util.Date;

import static com.demo.tdddemonstrationmock.utils.DataUtils.adicionarDias;

public class LocacaoService {

    public Locacao alugarFilme(Usuario usuario, Filme filme) throws FilmeSemEstoqueException, LocadoraException {

        if (filme == null){
            throw new LocadoraException("Filme vazio");
        }

        if(usuario == null){
            throw new LocadoraException("Usuário vazio");
        }

        if(filme.getEstoque() == 0){
            throw new FilmeSemEstoqueException();
        }

        Locacao locacao = new Locacao();
        locacao.setFilme(filme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(new Date());
        locacao.setValor(filme.getPrecoLocacao());

        Date dataEntrega = new Date();
        dataEntrega = adicionarDias(dataEntrega, 1);
        locacao.setDataRetorno(dataEntrega);

        return locacao;
    }
}
