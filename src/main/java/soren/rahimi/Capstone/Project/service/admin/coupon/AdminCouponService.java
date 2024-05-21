package soren.rahimi.Capstone.Project.service.admin.coupon;

import soren.rahimi.Capstone.Project.entities.Coupon;

import java.util.List;

public interface AdminCouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllCoupons();
}
