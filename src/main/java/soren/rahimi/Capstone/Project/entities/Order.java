package soren.rahimi.Capstone.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import soren.rahimi.Capstone.Project.enums.OrderStatus;

import java.time.LocalDate;
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
    private LocalDate date;
    private Long amount=0L;
    private String address;
    private String payment;
    private OrderStatus orderStatus;
    private Long totalAmount=0L;
    private Long discount=0L;
    private UUID trackingId;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<CartItems> cartItems;
}
