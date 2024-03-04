package com.microservice.oderservice.service;

import com.microservice.oderservice.dto.InventoryDTO;
import com.microservice.oderservice.dto.OrderDTO;
import com.microservice.oderservice.dto.OrderItemsDTO;
import com.microservice.oderservice.entity.Order;
import com.microservice.oderservice.entity.OrderItems;
import com.microservice.oderservice.repository.OrderRepository;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    WebClient.Builder webClientBuilder;

    private final ObservationRegistry observationRegistry;

    public OrderService(ObservationRegistry observationRegistry) {
        this.observationRegistry = observationRegistry;
    }

    public String placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());


        List<OrderItems> orderItems = orderDTO.getOrderItemsDTOList()
                .stream()
                .map(orderItemsDTO -> mapToDTO(orderItemsDTO)).collect(Collectors.toList());
        order.setOrderItems(orderItems);

        // Giờ mình cần lấy ra danh sách skucode ở chi tiết các đơn hàng
        List<String> skucodes = order.getOrderItems().stream().map(OrderItems::getSkuCode).toList();

        //Call inventory service place order if product is in stock
        // mình phải truyền vào đây url của inventory vào đây
        InventoryDTO[] inventoryDTOSArray = webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skucodes).build())
                .retrieve()
                .bodyToMono(InventoryDTO[].class)
                .block();
        // verify các sản phẩm còn hàng trong inventoryArray hay khoong
        boolean allProductsInstock = Arrays.stream(inventoryDTOSArray).allMatch(inventoryDTO -> inventoryDTO.isInstock());
        if (allProductsInstock) {
            orderRepository.save(order);
            return "Order Successfully!";
        }else {
            throw new IllegalArgumentException("Product not in stock.Please try another!");
        }
    }

    public OrderItems mapToDTO(OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = new OrderItems();
        orderItems.setSkuCode(orderItemsDTO.getSkuCode());
        orderItems.setPrice(orderItemsDTO.getPrice());
        orderItems.setQuantity(orderItemsDTO.getQuantity());

        return orderItems;
    }
//    public OrderItemsDTO convertoDTO(OrderItems orderItems){
//        OrderItemsDTO orderItemsDTO = new OrderItemsDTO();
//        orderItemsDTO.setSkuCode(orderItems.getSkuCode());
//        orderItemsDTO.setQuantity(orderItems.getQuantity());
//        orderItemsDTO.setPrice(orderItemsDTO.getPrice());
//        return orderItemsDTO;
//    }
}
