package com.example.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.curso.entities.Category;
import com.example.curso.entities.Order;
import com.example.curso.entities.OrderItem;
import com.example.curso.entities.Payment;
import com.example.curso.entities.Product;
import com.example.curso.entities.User;
import com.example.curso.entities.enums.OrderStatus;
import com.example.curso.repositories.CategoryRepository;
import com.example.curso.repositories.OrderItemRepository;
import com.example.curso.repositories.OrderRepository;
import com.example.curso.repositories.ProductRepository;
import com.example.curso.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Override
	public void run(String... args) throws Exception {
		User u1 = new User(null, "David", "david@gmail.com", "619966", "1234");
		User u2 = new User(null, "Taina", "bnktay@gmail.com", "619988", "123");

		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.WAITING_PAYMENT);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.PAID);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.SHIPPED);

		Category c1 = new Category(null, "Eletronics");
		Category c2 = new Category(null, "Books");
		Category c3 = new Category(null, "Computers");

		Product p1 = new Product(null, "The Witcher", "Tower of Swallow", 45.0, "witcherimg.com");
		Product p2 = new Product(null, "Smart TV", "58in 4K 60HZ", 1400.0, "smartvimg.com");
		Product p3 = new Product(null, "Macbook Pro", "EXPENSIVE TRASH", 8000.0, "mcbookimg.com");
		Product p4 = new Product(null, "GAMER PC", "GOOD SHIT", 3000.0, "PCimg.com");
		Product p5 = new Product(null, "Percy Jackson", "Teenager book", 15.0, "percyimg.com");

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
		categoryRepository.saveAll(Arrays.asList(c1, c2, c3));

		p1.getCategories().add(c2);
		p2.getCategories().add(c1);
		p3.getCategories().add(c3);
		p4.getCategories().add(c3);
		p5.getCategories().add(c2);

		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

		OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
		OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
		OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
		OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

		orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
		
		Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
		o1.setPayment(pay1);
		
		orderRepository.save(o1);

	}

}
