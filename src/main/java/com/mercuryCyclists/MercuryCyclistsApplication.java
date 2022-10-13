package com.mercuryCyclists;

import com.mercuryCyclists.Inventory.entity.Part;
import com.mercuryCyclists.Inventory.entity.Product;
import com.mercuryCyclists.Inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

/**
 * Main Spring Boot application
 */
@SpringBootApplication
public class MercuryCyclistsApplication implements CommandLineRunner {
	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(MercuryCyclistsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p1 = new Product();
		p1.setName("Test Bike 1");
		p1.setPrice(1000.0);
		p1.setQuantity(1L);
		Part part1 = new Part();
		part1.setName("wheel");
		part1.setQuantity(10L);
		part1.setSupplierId(1L);
		Part part2 = new Part();
		part2.setName("chain");
		part2.setQuantity(10L);
		part2.setSupplierId(1L);
		Set set = new HashSet<Part>();
		set.add(part1);
		set.add(part2);
		p1.setSet(set);
		productService.addProduct(p1);

		Product p2 = new Product();
		p2.setName("Test Bike 2");
		p2.setPrice(1000.0);
		p2.setQuantity(1L);
		Part part3 = new Part();
		part3.setName("wheel");
		part3.setQuantity(10L);
		part3.setSupplierId(1L);
		Part part4 = new Part();
		part4.setName("chain");
		part4.setQuantity(10L);
		part4.setSupplierId(1L);
		Set set2 = new HashSet<Part>();
		set2.add(part3);
		set2.add(part4);
		p2.setSet(set2);
		productService.addProduct(p2);
	}
}
