package br.com.integration.service;

import br.com.integration.model.dto.PedidoDTO;
import br.com.integration.model.dto.PedidoDTOMapper;
import br.com.integration.util.Utils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class IntegrationService {

    public static JsonArray readFileAndConvertToJson(String file) {
        JsonArray output = new JsonArray();
        var pedidosDTO = new ArrayList<PedidoDTO>();

        try {
            var lines = Utils.readLinesFromTextFile(file);

            lines.stream().forEach(l -> {
                var pedidoDTO = PedidoDTOMapper.lineToPedidoDTO(l);
                pedidosDTO.add(pedidoDTO);
            });

            var users = getUsers(pedidosDTO);

            output = createUsersWithOrdersJsonElements(pedidosDTO, users);

                    var test = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static JsonArray createUsersWithOrdersJsonElements(ArrayList<PedidoDTO> pedidosDTO, Map<Integer, Set<String>> users) {
        var output = new JsonArray();
        users.entrySet().forEach(u -> {
            var user = new JsonObject();
            user.addProperty("user_id", u.getKey());
            user.addProperty("name", u.getValue().stream().findFirst().get());

            JsonArray orders = createOrdersWithProductsJsonElements(pedidosDTO, u.getKey());

            user.add("orders", orders);

            output.add(user);
        });
        return output;
    }

    public static Map<Integer, Set<String>> getUsers(ArrayList<PedidoDTO> pedidos) {
        return pedidos.stream().collect(Collectors.groupingBy(PedidoDTO::getIdUsuario, Collectors.mapping(PedidoDTO::getNome, Collectors.toSet())));
    }

    public static JsonArray createOrdersWithProductsJsonElements(ArrayList<PedidoDTO> pedidos, int idUsuario) {
        var orders = new JsonArray();
        Map<Integer, Map<String, Double>> ordersWithAmount = getOrdersByUserAndSumProductsAmount(pedidos, idUsuario);

        ordersWithAmount.entrySet().forEach(o -> {
            var idPedido = o.getKey();
            var order = new JsonObject();
            order.addProperty("order_id", idPedido);
            o.getValue().entrySet().forEach(op ->
            {
                order.addProperty("total", Utils.formartAmount(op.getValue()));
                order.addProperty("date", Utils.stringToLocalDate(op.getKey()).toString());
            });

            var pedidosDTO = getPedidosByUserAndOrder(pedidos, idUsuario, idPedido);
            order.add("products", createProductsJsonElements(pedidosDTO));
            orders.add(order);
        });
        return orders;
    }

    public static JsonArray createProductsJsonElements(List<PedidoDTO> pedidosDTO) {
        var products = new JsonArray();
        pedidosDTO.forEach(p -> {
            var produto = new JsonObject();
            produto.addProperty("product_id", p.getIdProduto());
            produto.addProperty("value", p.getValorProduto());
            products.add(produto);
        });
        return products;
    }

    public static Map<Integer, Map<String, Double>> getOrdersByUserAndSumProductsAmount(ArrayList<PedidoDTO> pedidos, int idUsuario) {
        return pedidos.stream().
                filter(l -> l.getIdUsuario() == idUsuario)
                .sorted(Comparator.comparing(PedidoDTO::getIdPedido))
                .collect(
                        Collectors.groupingBy(PedidoDTO::getIdPedido,
                                Collectors.groupingBy(PedidoDTO::getDataCompra,
                                        Collectors.summingDouble(PedidoDTO::getValorProduto))));
    }

    public static List<PedidoDTO> getPedidosByUserAndOrder(ArrayList<PedidoDTO> pedidos, int idUsuario, int idPedido) {
        return pedidos.stream()
                .filter(l -> l.getIdUsuario() == idUsuario)
                .filter(l -> l.getIdPedido() == idPedido)
                .sorted(Comparator.comparing(PedidoDTO::getIdProduto)).collect(Collectors.toList());
    }

}
