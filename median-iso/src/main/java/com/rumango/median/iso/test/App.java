package com.rumango.median.iso.test;
//package com.mkyong.stock;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//import com.mkyong.util.HibernateUtil;
//
//public class App {
//	public static void main(String[] args) {
//
//		//Session session = EntityManagerFactory.unwrap(Session.class);
//		
//		Session session = entityManager.unwrap(org.hibernate.Session.class);
//		
//		System.out.println("Hibernate many to many (Annotation)");
//		Session session2 = sessionFactory.openSession();
//
//		session.beginTransaction();
//
//		Stock stock = new Stock();
//		stock.setStockCode("7052");
//		stock.setStockName("PADINI");
//
//		Category category1 = new Category("CONSUMER", "CONSUMER COMPANY");
//		Category category2 = new Category("INVESTMENT", "INVESTMENT COMPANY");
//
//		Set<Category> categories = new HashSet<Category>();
//		categories.add(category1);
//		categories.add(category2);
//
//		stock.setCategories(categories);
//
//		session.save(stock);
//
//		session.getTransaction().commit();
//		System.out.println("Done");
//	}
//}