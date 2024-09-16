package com.microservico.dto;

import java.io.Serializable;

public class EstoqueDTO implements Serializable {

    private String codigoproduto;
    private int quantidade;


    public EstoqueDTO( ) {
    }

    public EstoqueDTO( String codigoproduto, int quantidade ) {
        this.codigoproduto = codigoproduto;
        this.quantidade = quantidade;
    }

    public String getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto( String codigoproduto ) {
        this.codigoproduto = codigoproduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade( int quantidade ) {
        this.quantidade = quantidade;
    }
}
