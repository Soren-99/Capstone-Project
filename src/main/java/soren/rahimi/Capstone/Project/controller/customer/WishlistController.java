package soren.rahimi.Capstone.Project.controller.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soren.rahimi.Capstone.Project.dto.WishlistDTO;
import soren.rahimi.Capstone.Project.service.customer.wishlist.WishlistService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishList(@RequestBody WishlistDTO wishlistDTO){
        WishlistDTO postedWishlistDTO = wishlistService.addProductToWishList(wishlistDTO);
        if(postedWishlistDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDTO);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDTO>> getWishlistByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));
    }

}
