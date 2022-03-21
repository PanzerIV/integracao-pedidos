package br.com.integration.model.dto;

import java.util.Objects;

public class PedidoDTO {
    private String idUsuario;
    private String nome;
    private String idPedido;
    private String idProduto;
    private String valorProduto;
    private String dataCompra;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(String valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nome='" + nome + '\'' +
                ", idPedido='" + idPedido + '\'' +
                ", idProduto='" + idProduto + '\'' +
                ", valorProduto='" + valorProduto + '\'' +
                ", dataCompra='" + dataCompra + '\'' +
                '}';
    }

}
