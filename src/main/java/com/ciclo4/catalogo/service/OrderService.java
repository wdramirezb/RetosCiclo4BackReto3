package com.ciclo4.catalogo.service;

import com.ciclo4.catalogo.model.Order;
import com.ciclo4.catalogo.repository.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public Optional<Order> getOrder(Integer id) {
        return orderRepository.getOrder(id);
    }

    
        public Order save(Order order) {
        if (order.getId() == null) {
            return orderRepository.save(order);
        } else {
            Optional<Order> orderSave = orderRepository.getOrder(order.getId());
            if (orderSave.isEmpty()) {
                return orderRepository.save(order);
            } else {
                return order;
            }
        }
    }

    public Order update(Order order) {
        if (order.getId() != null) {
            Optional<Order> orderUpdate = orderRepository.getOrder(order.getId());
            if (!orderUpdate.isEmpty()) {
                if (order.getRegisterDay() != null) {
                    orderUpdate.get().setRegisterDay(order.getRegisterDay());
                }
                if (order.getStatus() != null) {
                    orderUpdate.get().setStatus(order.getStatus());
                }
                if (order.getSalesMan() != null) {
                    orderUpdate.get().setSalesMan(order.getSalesMan());
                }
                if (order.getProducts() != null) {
                    orderUpdate.get().setProducts(order.getProducts());
                }
                if (order.getQuantities() != null) {
                    orderUpdate.get().setQuantities(order.getQuantities());
                }
                orderRepository.update(orderUpdate.get());
                return orderUpdate.get();
            } else {
                return order;
            }
        } else {
            return order;
        }
    }

    public boolean delete(Integer id) {
        Optional<Order> orderDelete = orderRepository.getOrder(id);
        if (!orderDelete.isEmpty()) {
            orderRepository.delete(orderDelete.get());
            return true;
        }
        return false;
    }

    public List<Order> getOrdersByZone(String zone) {
        return orderRepository.getOrdersByZone(zone);
    }
}
