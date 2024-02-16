package com.ty.controller;

import java.util.Scanner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ty.config.MyConfig;
import com.ty.dao.EmployeeDao;

public class EmployeeController {
	public static void main(String[] args) {
		ConfigurableApplicationContext appcont = new AnnotationConfigApplicationContext(MyConfig.class);
		EmployeeDao users = (EmployeeDao) appcont.getBean("employeeDao");
		Scanner sc=new Scanner(System.in);
		boolean loop=true;
		while(loop) {
			System.out.println("1.Insert-Employee\n2.View-Employee\n3.update-Employee\n4.delete-Employee\n5.delete all\n6.Exit\nEnter the choice: ");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:{
				users.insert();
				break;
			}
			case 2:{
				users.view();
				break;
			}
			case 3:{
				users.update();
				break;
			}
			case 4:{
				users.delete();
				break;
			}
			case 5:{
				users.deleteAll();
				break;
			}
			case 6:{
				loop=false;
				break;
			}
			default:{
				System.out.println("invalid choice");
				break;
			}
			}
		}
		System.out.println("Exited!!");
	}
}
