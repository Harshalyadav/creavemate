package com.cravemate.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cravemate.pojos.OrderInfo;

public interface OrderInfoRepo extends JpaRepository<OrderInfo, Integer> {
    @Query(value = "SELECT* FROM order_info s WHERE s.user_id = :solve ", nativeQuery = true)
    public List<OrderInfo> findAllByUserid(Integer solve);

    public Optional<OrderInfo> findByUseridAndOrderid(Integer userid,Integer orderid);
}
