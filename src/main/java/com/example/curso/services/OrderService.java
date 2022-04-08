package com.example.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.entities.Order;
import com.example.curso.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	private OrderRepository OrderRepository;

	public List<Order> findAll() {
		return OrderRepository.findAll();
	}
	
	public Order findById(Integer id) {
		Optional<Order> obj = OrderRepository.findById(id);
		return obj.get();
	}
}
