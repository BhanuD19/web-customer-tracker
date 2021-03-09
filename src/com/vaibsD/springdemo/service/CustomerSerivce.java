package com.vaibsD.springdemo.service;

import java.util.List;

import com.vaibsD.springdemo.entity.Customer;

public interface CustomerSerivce {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

}
