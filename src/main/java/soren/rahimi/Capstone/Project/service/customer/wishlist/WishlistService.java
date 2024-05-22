package soren.rahimi.Capstone.Project.service.customer.wishlist;

import soren.rahimi.Capstone.Project.dto.WishlistDTO;

import java.util.List;

public interface WishlistService {
    WishlistDTO addProductToWishList(WishlistDTO wishlistDTO);

    List<WishlistDTO> getWishlistByUserId(Long userId);
}
