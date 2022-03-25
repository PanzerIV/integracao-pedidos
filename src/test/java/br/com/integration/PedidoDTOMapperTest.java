package br.com.integration;

import br.com.integration.model.dto.PedidoDTOMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PedidoDTOMapperTest {

    @Test
    public void shouldConvertLineToPedidoDTO() {
        var line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        var dto = PedidoDTOMapper.lineToPedidoDTO(line);
        Assertions.assertEquals(dto.getIdUsuario(), 70);
        Assertions.assertEquals(dto.getNome(), "Palmer Prosacco");
        Assertions.assertEquals(dto.getIdPedido(), 753);
        Assertions.assertEquals(dto.getIdProduto(), 3);
        Assertions.assertEquals(dto.getValorProduto(), 1836.74);
        Assertions.assertEquals(dto.getDataCompra(), "20210308");

    }
}
