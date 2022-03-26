package br.com.integration.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class PedidoDTO {
    private int idUsuario;
    private String nome;
    private int idPedido;
    private int idProduto;
    private double valorProduto;
    private String dataCompra;
}
