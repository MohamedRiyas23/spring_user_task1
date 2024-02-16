package com.ty.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.ty.config.MyConfig;
import com.ty.dto.Employee;

@Component
public class EmployeeDao {
	@Autowired
	@Qualifier(value="emfactory")
	EntityManagerFactory emfactory;
	@Autowired
	@Qualifier(value="manager")
	EntityManager manager;
	@Autowired
	@Qualifier(value="transaction")
	EntityTransaction transaction;
	@Autowired
	@Qualifier(value="scanner")
	Scanner scanner;
	@Autowired
	Employee users;
	
	public  void insert() {
		
		System.out.println("Enter how many user you want to add: ");
		int count=scanner.nextInt();
		for(int i=0;i<count;i++) {
			System.out.println("Enter the name: ");
			users.setName(scanner.nextLine());
			System.out.println("Enter the Email: ");
			users.setEmail(scanner.next());
			System.out.println("Enter the phone number: ");
			users.setPhoneNumber(scanner.next());
			
			transaction.begin();
			manager.persist(users);
			transaction.commit();
			System.out.println("==============User added!!============");
			users = new AnnotationConfigApplicationContext(MyConfig.class).getBean("employee", Employee.class);
		}
		
	}
	public void view() {
		
		Query q=manager.createQuery("select s from Employee s");
		List<Employee> emp=q.getResultList();
		System.out.println("==============deatils================");
		for(Employee e:emp) {
			System.out.println(e.getId()+"-> "+e.getName());
		}
		System.out.println("=====================================");
		System.out.println("Enter the Employee id you want to get details: ");
		int id=scanner.nextInt();
		
		Employee empId=manager.find(Employee.class, id);
		System.out.println("============user details=============");
		System.out.println("Employee name: "+empId.getName());
		System.out.println("Employee email: "+empId.getEmail());
		System.out.println("Employee Phone: "+empId.getPhoneNumber());
		System.out.println("=====================================");
	}
	
	public void updateName() {
		Query q=manager.createQuery("select s from Employee s");
		List<Employee> emp=q.getResultList();
		System.out.println("==============deatils================");
		for(Employee e:emp) {
			System.out.println(e.getId()+"->"+e.getName());
		}
		System.out.println("=====================================");
		System.out.println("Enter the Employee id you want to update: ");
		int id=scanner.nextInt();
		Employee empId=manager.find(Employee.class, id);
		System.out.println("Enter new name: ");
		scanner.nextLine();
		String name=scanner.nextLine();
		empId.setName(name);
		transaction.begin();
		manager.merge(empId);
		transaction.commit();
		System.out.println("==============user updated!!===========");
		
	}
	public void updateEmail() {
		Query q=manager.createQuery("select s from Employee s");
		List<Employee> emp=q.getResultList();
		System.out.println("==============deatils================");
		for(Employee e:emp) {
			System.out.println(e.getId()+"->"+e.getName());
		}
		System.out.println("=====================================");
		System.out.println("Enter the Employee id you want to update: ");
		int id=scanner.nextInt();
		Employee empId=manager.find(Employee.class, id);
		System.out.println("Enter new Email: ");
		String email=scanner.next();
		empId.setEmail(email);
		transaction.begin();
		manager.merge(empId);
		transaction.commit();
		System.out.println("==============user updated!!===========");
	}
	
	public void updatePhone() {
		Query q=manager.createQuery("select s from Employee s");
		List<Employee> emp=q.getResultList();
		System.out.println("==============deatils================");
		for(Employee e:emp) {
			System.out.println(e.getId()+"->"+e.getName());
		}
		System.out.println("=====================================");
		System.out.println("Enter the Employee id you want to update: ");
		int id=scanner.nextInt();
		Employee empId=manager.find(Employee.class, id);
		System.out.println("Enter new number: ");
		String phone=scanner.next();
		empId.setPhoneNumber(phone);
		transaction.begin();
		manager.merge(empId);
		transaction.commit();
		System.out.println("==============user updated!!===========");
	}
	
	public void update() {
		boolean loop=true;
		while(loop) {
			System.out.println("\n1.update name\n2.update email\n3.update phone number\n4.Exit\n\nEnter the choice: ");
			int choice=scanner.nextInt();
			switch(choice) {
			case 1:{
				updateName();
				break;
			}
			case 2:{
				updateEmail();
				break;
			}
			case 3:{
				updatePhone();
				break;
			}
			case 4:{
				loop=false;
				break;
			}
			default:{
				System.out.println("invalid choice!!!");
				break;
			}
			}
		}
	}
	public void delete() {
		Query q=manager.createQuery("select s from Employee s");
		List<Employee> emp=q.getResultList();
		System.out.println("==============deatils================");
		for(Employee e:emp) {
			System.out.println(e.getId()+"->"+e.getName());
		}
		System.out.println("=====================================");
		System.out.println("Enter the Employee id you want to delete: ");
		int id=scanner.nextInt();
		Employee empId=manager.find(Employee.class, id);
		transaction.begin();
		manager.remove(empId);
		transaction.commit();
		System.out.println("==============user deleted!!===========");
	}
	
	public void deleteAll() {
		transaction.begin();
		Query q=manager.createQuery("delete from Employee");
		int res = q.executeUpdate();
		transaction.commit();
		System.out.println("=============="+res+" user deleted!!===========");
	}
	
	
	
	}

