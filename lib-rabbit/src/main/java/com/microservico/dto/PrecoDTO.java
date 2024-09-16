package com.microservico.dto;

import java.io.Serializable;

public class PrecoDTO implements Serializable {

    private String codigoproduto;
    private double valor;

    public PrecoDTO() {
    }

    public PrecoDTO(String codigoproduto, double valor) {
        this.codigoproduto = codigoproduto;
        this.valor = valor;
    }

    public String getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(String codigoproduto) {
        this.codigoproduto = codigoproduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
