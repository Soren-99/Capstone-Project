package soren.rahimi.Capstone.Project.service.admin.adminOrder;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.AnalyticsResponse;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.entities.Order;
import soren.rahimi.Capstone.Project.enums.OrderStatus;
import soren.rahimi.Capstone.Project.repository.OrderRepository;

import java.time.LocalDate;
import java.util.*;
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

    public OrderDTO changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();

            if(Objects.equals(status, "Shipped")){
                order.setOrderStatus(OrderStatus.Shipped);
            }else if(Objects.equals(status, "Delivered")){
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return orderRepository.save(order).getOrderDTO();
        }
        return null;
    }

    public AnalyticsResponse calculateAnalytics(){
        LocalDate currentDate = LocalDate.now();
        LocalDate previousMonthDate = currentDate.minusMonths(1);

        Long currentMonthOrders = getTotalOrdersForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthOrders = getTotalOrdersForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long currentMonthEarnings = getTotalEarningsForMonth(currentDate.getMonthValue(), currentDate.getYear());
        Long previousMonthEarnings = getTotalEarningsForMonth(previousMonthDate.getMonthValue(), previousMonthDate.getYear());

        Long placed = orderRepository.countByOrderStatus(OrderStatus.Placed);
        Long shipped = orderRepository.countByOrderStatus(OrderStatus.Shipped);
        Long delivered = orderRepository.countByOrderStatus(OrderStatus.Delivered);

        return new AnalyticsResponse(placed, shipped, delivered, currentMonthOrders, previousMonthOrders, currentMonthEarnings, previousMonthEarnings);
    }

    public Long getTotalOrdersForMonth(int month, int year){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date endOfMonth = calendar.getTime();

        List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

        return (long) orders.size();
    }

    public Long getTotalEarningsForMonth(int month, int year){
    Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

    Date startOfMonth = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

    Date endOfMonth = calendar.getTime();

    List<Order> orders = orderRepository.findByDateBetweenAndOrderStatus(startOfMonth, endOfMonth, OrderStatus.Delivered);

    Long sum = 0L;
    for (Order order: orders){
        sum += order.getAmount();
    }
    return sum;
}
}
