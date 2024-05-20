package soren.rahimi.Capstone.Project.dto;

import jakarta.persistence.*;
import lombok.Data;
import soren.rahimi.Capstone.Project.entities.CartItems;
import soren.rahimi.Capstone.Project.entities.User;
import soren.rahimi.Capstone.Project.enums.OrderStatus;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDTO {
    private Long id;
    private String orderDescription;
    private Date date;
    private Long amount;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount;
    private Long discount;
    private UUID trackingId;

    private String userName;


    private List<CartItemsDTO> cartItems;
}
