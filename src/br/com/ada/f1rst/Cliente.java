package br.com.ada.f1rst;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cliente {
    private String nome;

    private int idade;
    private String codigo;
    private List<Conta> contas;
    private TipoDeCliente tipoDeCliente;

    public Cliente(String nome, TipoDeCliente tipoDeCliente, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.codigo = String.valueOf(new Random().nextInt(100));
        this.contas = new ArrayList<>();
        this.tipoDeCliente = tipoDeCliente;
    }


    public List<Conta> getContas() {
        return this.contas;
    }

    public TipoDeCliente getTipoDeCliente() {
        return tipoDeCliente;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {

        return idade;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", codigo='" + codigo + '\'' +
                ", contas=" + contas +
                '}';
    }
}