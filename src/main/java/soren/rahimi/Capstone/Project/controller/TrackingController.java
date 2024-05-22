package soren.rahimi.Capstone.Project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.service.customer.cart.CartService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TrackingController {

    private final CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDTO> searchOrderByTrackingId(@PathVariable UUID trackingId){
        OrderDTO orderDTO = cartService.searchOrderByTrackingId(trackingId);
        if (orderDTO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDTO);
    }
}
