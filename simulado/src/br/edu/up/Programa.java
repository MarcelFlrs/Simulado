package br.edu.up;

import java.util.List;

import br.edu.up.daos.GerenciadorDeArquivos;
import br.edu.up.models.Pessoa;

public class Programa {
    public static void main(String[] args) {
        GerenciadorDeArquivos gArquivos = new GerenciadorDeArquivos();
        List<Pessoa> pessoasComEnderecos = gArquivos.combinarPessoasEEnderecos();
        if (gArquivos.gravarPessoaComEndereco(pessoasComEnderecos)) {
            System.out.println("Arquivo gravado com sucesso!");
        } else {
            System.out.println("Falha ao gravar o arquivo.");
        }
    }
}
