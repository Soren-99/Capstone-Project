package soren.rahimi.Capstone.Project.service.customer.wishlist;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.WishlistDTO;
import soren.rahimi.Capstone.Project.entities.Product;
import soren.rahimi.Capstone.Project.entities.User;
import soren.rahimi.Capstone.Project.entities.Wishlist;
import soren.rahimi.Capstone.Project.repository.ProductRepository;
import soren.rahimi.Capstone.Project.repository.UserRepository;
import soren.rahimi.Capstone.Project.repository.WishlistRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WishlistRepository wishlistRepository;

    @Transactional
    public WishlistDTO addProductToWishList(WishlistDTO wishlistDTO){
        Optional<Product> optionalProduct = productRepository.findById(wishlistDTO.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishlistDTO.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());

            return wishlistRepository.save(wishlist).getWishlistDTO();
        }
        return null;
    }

    @Transactional
    public List<WishlistDTO> getWishlistByUserId(Long userId){
        return wishlistRepository.findAllByUserId(userId).stream().map(Wishlist::getWishlistDTO).collect(Collectors.toList());
    }
}
