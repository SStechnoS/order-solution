package com.ordersolution.repository;

import com.ordersolution.model.CustomerOrder;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends ListCrudRepository<CustomerOrder, Long> {

    List<CustomerOrder> findAllByCustomerId(Long customerId);
}
