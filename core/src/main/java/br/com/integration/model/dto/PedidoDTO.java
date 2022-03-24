package br.com.integration.model.dto;

public class PedidoDTO {
    private int idUsuario;
    private String nome;
    private int idPedido;
    private int idProduto;
    private double valorProduto;
    private String dataCompra;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
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
