package br.com.integration.service;

import br.com.integration.model.dto.PedidoDTO;
import br.com.integration.util.IntegrationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class IntegrationService {
    public void readFileAndConvertToModel(String file) {

        var pedidosDTO = new ArrayList<PedidoDTO>();

        try {
            var lines = IntegrationUtil.readLinesFromTextFile(file);

//            System.out.println("Total de linhas : " + lines.size());
            lines.stream().forEach(l -> {
                pedidosDTO.add(lineToPedidoDTO(l));
            });

            var sortedList = pedidosDTO.stream().sorted(Comparator.comparing(PedidoDTO::getIdUsuario)).sorted(Comparator.comparing(PedidoDTO::getIdPedido)).collect(Collectors.toList());
            sortedList.forEach(System.out::println);
//            System.out.println("list = " + pedidosDTO.size());
//            System.out.println("sortedList = " + sortedList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PedidoDTO lineToPedidoDTO(String line) {
        PedidoDTO dto = new PedidoDTO();
        dto.setIdUsuario(line.substring(0, 10));
        dto.setNome(line.substring(10, 55));
        dto.setIdPedido(line.substring(55, 65));
        dto.setIdProduto(line.substring(65, 75));
        dto.setValorProduto(line.substring(75, 87));
        dto.setDataCompra(line.substring(87));
        return dto;
    }
}
