package soren.rahimi.Capstone.Project.service.admin.adminOrder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.entities.Order;
import soren.rahimi.Capstone.Project.enums.OrderStatus;
import soren.rahimi.Capstone.Project.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepository orderRepository;

    public List<OrderDTO> getAllPlacedOrders(){

        List<Order> orderList = orderRepository.
                findAllByOrderStatusIn(List.of(OrderStatus.Placed, OrderStatus.Shipped, OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDTO).collect(Collectors.toList());
    }
}
