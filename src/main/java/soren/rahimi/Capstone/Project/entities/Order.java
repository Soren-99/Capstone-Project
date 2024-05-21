package soren.rahimi.Capstone.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.enums.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderDescription;
    private Date date;
    private Long amount=0L;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount=0L;
    private Long discount=0L;
    private UUID trackingId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "coupon_id", referencedColumnName = "id")
    private Coupon coupon;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems = new ArrayList<>();

    public OrderDTO getOrderDTO(){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(id);
        orderDTO.setOrderDescription(orderDescription);
        orderDTO.setAddress(address);
        orderDTO.setTrackingId(trackingId);
        orderDTO.setAmount(amount);
        orderDTO.setDate(date);
        orderDTO.setOrderStatus(orderStatus);
        orderDTO.setUserName(user.getName());
        if (coupon != null){
            orderDTO.setCouponName(coupon.getName());
        }
        return orderDTO;
    }
}
