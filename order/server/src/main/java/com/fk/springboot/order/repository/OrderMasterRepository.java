package com.fk.springboot.order.repository;

import com.fk.springboot.order.dataobject.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
