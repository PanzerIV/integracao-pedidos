package br.com.integration.model.dto;

public class PedidoDTOMapper {

    public static PedidoDTO lineToPedidoDTO(String line) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdUsuario(Integer.parseInt(line.substring(0, 10)));
        dto.setNome(line.substring(10, 55).trim());
        dto.setIdPedido(Integer.parseInt(line.substring(55, 65)));
        dto.setIdProduto(Integer.parseInt(line.substring(65, 75)));
        dto.setValorProduto(Double.parseDouble(line.substring(75, 87).trim()));
        dto.setDataCompra(line.substring(87));
        return dto;
    }
}
