package soren.rahimi.Capstone.Project.service.admin.coupon;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.entities.Coupon;
import soren.rahimi.Capstone.Project.exceptions.ValidationException;
import soren.rahimi.Capstone.Project.repository.CouponRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{

    private final CouponRepository couponRepository;

    public Coupon createCoupon(Coupon coupon){
        if (couponRepository.existsByCode(coupon.getCode())){
            throw new ValidationException("Coupon code already exists.");
        }
        return couponRepository.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepository.findAll();
    }
}
