package br.edu.up.daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.up.models.Pessoa;

public class GerenciadorDeArquivos {

    private String header = "";
    private String pessoas = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\simulado\\src\\br\\edu\\up\\Pessoas.csv";
    private String enderecos = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\simulado\\src\\br\\edu\\up\\Enderecos.csv";
    private String pessoaComEndereco = "C:\\Users\\autologon\\Documents\\GitHub\\Simulado\\simulado\\src\\br\\edu\\up\\PessoaComEndereco.csv";

    public List<Pessoa> getPessoas() {
        List<Pessoa> listaDePessoas = new ArrayList<>();

        try {
            File arquivoLeitura = new File(pessoas);
            Scanner leitor = new Scanner(arquivoLeitura);

            header = leitor.nextLine();

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String[] dados = linha.split(";");

                String codigo = dados[0];
                String nome = dados[1];

                listaDePessoas.add(new Pessoa(codigo, nome, "", ""));
            }

            leitor.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo Pessoas.csv não encontrado! " + e.getMessage());
        }

        return listaDePessoas;
    }

    public List<Pessoa> lerEnderecos() {
        List<Pessoa> listaDeEnderecos = new ArrayList<>();

        try {
            File arquivoLeitura = new File(enderecos);
            Scanner leitor = new Scanner(arquivoLeitura);

            leitor.nextLine();

            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine();
                String dados[] = linha.split(";");

                String codigo = dados[0];
                String rua = dados[1];
                String cidade = dados[2];

                listaDeEnderecos.add(new Pessoa(codigo, "", rua, cidade));

            }

            leitor.close();

        } catch (Exception e) {
            System.out.println("Arquivo Enderecos.csv não encontrado! " + e.getMessage());
        }
        return listaDeEnderecos;
    }

    public List<Pessoa> combinarPessoasEEnderecos() {

        List<Pessoa> listaDePessoas = getPessoas();
        List<Pessoa> listaDeEnderecos = lerEnderecos();

        for (Pessoa pessoa : listaDePessoas) {
            for (Pessoa endereco : listaDeEnderecos) {
                if (pessoa.getCodigo().equals(endereco.getCodigo())) {
                    pessoa.setRua(endereco.getRua());
                    pessoa.setCidade(endereco.getCidade());
                    break;
                }
            }
        }

        return listaDePessoas;

    }

    public boolean gravarPessoaComEndereco(List<Pessoa> pessoas) {

        try {
            FileWriter arquivoGravar = new FileWriter(pessoaComEndereco);
            PrintWriter gravador = new PrintWriter(arquivoGravar);

            gravador.println("codigo;nome;rua;cidade");

            for (Pessoa pessoa : pessoas) {
                gravador.println(pessoa.toCsv());
            }
            gravador.close();

            return true;

        } catch (IOException e) {
            System.out.println("Não foi possível gravar o arquivo!");
        }

        return false;
    }

}
