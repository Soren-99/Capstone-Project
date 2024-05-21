package soren.rahimi.Capstone.Project.dto;

import lombok.Data;

@Data
public class PlaceOrderDTO {

    private Long userId;
    private String address;
    private String orderDescription;
}
