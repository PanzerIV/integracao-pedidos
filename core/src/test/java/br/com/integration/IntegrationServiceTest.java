package br.com.integration;

import br.com.integration.model.dto.PedidoDTO;
import br.com.integration.service.IntegrationService;
import br.com.integration.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class IntegrationServiceTest {

    @Test
    public void shouldConvertLineToPedidoDTO() {
        var line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        var dto = IntegrationService.lineToPedidoDTO(line);
        Assertions.assertEquals(dto.getIdUsuario(), 70);
        Assertions.assertEquals(dto.getNome(), "Palmer Prosacco");
        Assertions.assertEquals(dto.getIdPedido(), 753);
        Assertions.assertEquals(dto.getIdProduto(), 3);
        Assertions.assertEquals(dto.getValorProduto(), 1836.74);
        Assertions.assertEquals(dto.getDataCompra(), "20210308");

    }

    @Test
    public void shouldGetPedidosByUserAndOrder() throws IOException {
        var idUsuario = 77;
        var idPedido = 844;
        var valorProduto = 1288.77;
        var pedidos = IntegrationService.getPedidosByUserAndOrder(getPedidosDTO(), idUsuario, idPedido);

        Assertions.assertEquals(pedidos.size(), 1);
        Assertions.assertEquals(pedidos.get(0).getIdUsuario(), idUsuario);
        Assertions.assertEquals(pedidos.get(0).getIdPedido(), idPedido);
        Assertions.assertEquals(pedidos.get(0).getValorProduto(), valorProduto);
    }

    @Test
    public void shouldGetOrdersByUserAndSumProductsAmount() throws IOException {
        var idUsuario = 77;
        var idPedido1 = 832;
        var idPedido2 = 844;
        var pedidos = IntegrationService.getOrdersByUserAndSumProductsAmount(getPedidosDTO(), idUsuario);

        Assertions.assertEquals(pedidos.size(), 2);
        Assertions.assertEquals(pedidos.get(idPedido1).toString(), "{20210513=1922.74}");
        Assertions.assertEquals(pedidos.get(idPedido2).toString(), "{20211127=1288.77}");
    }

    @Test
    public void shouldCreateProductsJsonElements() throws IOException {
        var productsJson = IntegrationService.createProductsJsonElements(getPedidosDTO()).get(0);

        Assertions.assertEquals(productsJson.toString(), "{\"product_id\":3,\"value\":1836.74}");
    }

    @Test
    public void shouldCreateOrdersWithProductsJsonElements() throws IOException {
        var content = new String(Files.readAllBytes(Paths.get(getClass().getResource("/orders_with_products.json").getPath())));
        var idUsuario = 77;
        var ordersJson = IntegrationService.createOrdersWithProductsJsonElements(getPedidosDTO(), idUsuario).get(0);

        Assertions.assertEquals(ordersJson.toString(), content);
    }

    @Test
    public void shouldReadFileAndConvertToJson() throws IOException {
        var filePath = getClass().getResource("/data_test.txt").getPath();
        var content = new String(Files.readAllBytes(Paths.get(getClass().getResource("/output.json").getPath())));
        var output = IntegrationService.readFileAndConvertToJson(filePath);

        Assertions.assertEquals(output.toString(), content);
    }

    @Test
    public void shouldGetUsers() throws IOException {
        var users = IntegrationService.getUsers(getPedidosDTO());

        Assertions.assertEquals(users.size(), 11);
    }

    @Test
    public void shouldCreateUsersWithOrdersJsonElements() throws IOException {
        var content = new String(Files.readAllBytes(Paths.get(getClass().getResource("/users_with_orders.json").getPath())));

        var users = new HashMap<Integer, Set<String>>();
        users.put(80, Set.of("Tabitha Kuhn"));
        users.put(17, Set.of("Ethan Langworth"));

        var output = IntegrationService.createUsersWithOrdersJsonElements(getPedidosDTO(), users);

        Assertions.assertEquals(output.toString(), content);
    }

    private ArrayList<PedidoDTO> getPedidosDTO() throws IOException {
        String filePath = getClass().getResource("/data_test.txt").getPath();
        var lines = Utils.readLinesFromTextFile(filePath);
        var pedidosDTO = new ArrayList<PedidoDTO>();

        lines.stream().forEach(l -> {
            var pedidoDTO = IntegrationService.lineToPedidoDTO(l);
            pedidosDTO.add(pedidoDTO);
        });

        return pedidosDTO;
    }

}