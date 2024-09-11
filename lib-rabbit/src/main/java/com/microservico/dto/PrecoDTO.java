package com.microservico.dto;

import java.io.Serializable;

public class PrecoDTO implements Serializable {

    private String codigoproduto;
    private double preco;

    public String getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto( String codigoproduto ) {
        this.codigoproduto = codigoproduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco( double preco ) {
        this.preco = preco;
    }
}
