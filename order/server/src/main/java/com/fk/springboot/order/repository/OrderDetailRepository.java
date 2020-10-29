package com.fk.springboot.order.repository;

import com.fk.springboot.order.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String > {
}
