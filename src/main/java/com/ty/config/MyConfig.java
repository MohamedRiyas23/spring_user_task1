package com.ty.config;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.ty.dto","com.ty.dao"})
public class MyConfig {
	@Bean(name="emfactory")
	public EntityManagerFactory getEmf() {
		EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("vikas");
		return entityManagerFactory;
	}
	@Bean(name="manager")
	public EntityManager getEm() {
		EntityManager entityManager=getEmf().createEntityManager();
		return entityManager;
	}
	@Bean(name="transaction")
	public EntityTransaction getEt() {
		EntityTransaction entityTransaction=getEm().getTransaction();
		return entityTransaction;
	}
	@Bean(name="scanner")
	public Scanner getScanner() {
		Scanner sc=new Scanner(System.in);
		return sc;
	}

}
