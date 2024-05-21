package soren.rahimi.Capstone.Project.service.admin.adminOrder;

import soren.rahimi.Capstone.Project.dto.OrderDTO;

import java.util.List;

public interface AdminOrderService {
    List<OrderDTO> getAllPlacedOrders();
}
