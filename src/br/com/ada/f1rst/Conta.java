package br.com.ada.f1rst;

import java.math.BigDecimal;
import java.util.Random;

public class Conta {
    private Cliente cliente;
    private TipoDeConta tipoDeConta;
    private BigDecimal saldo;
    private String numeroDaConta;
    private String agencia;
    private TipoDeCliente tipoDeCliente;

    public Conta(Cliente cliente, TipoDeConta tipoDeConta, TipoDeCliente tipoDeCliente) {
        this.cliente = cliente;
        this.tipoDeConta = tipoDeConta;
        this.agencia = "1234";
        this.tipoDeCliente = tipoDeCliente;
        this.saldo = new BigDecimal(0);
        this.numeroDaConta = String.valueOf(new Random().nextInt(100));
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public TipoDeCliente getTipoDeCliente() {
        return tipoDeCliente;
    }

    public TipoDeConta getTipoDeConta() {
        return tipoDeConta;
    }

    public void depositar(BigDecimal valor) {
        saldo = saldo.add(valor);

    }
    public boolean sacar(BigDecimal valor) {
        if (valor.compareTo(saldo) <= 0) {
            saldo = saldo.subtract(valor);
            return true;
        } else {
            return false;
        }
    }

}
