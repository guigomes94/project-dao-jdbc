package app;

import dao.DaoFactory;
import model.Department;

public class App {

	public static void main(String[] args) {
		
		var sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===>Testing findById<===");
		var seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n===>Testing findByDepartment<===");
		var dep = new Department(2, null);
		var sellers = sellerDao.findByDepartment(dep);
		sellers.stream().forEach(System.out::println);
		
		System.out.println("\n===>Testing findByAll<===");
		var allSellers = sellerDao.findAll();
		allSellers.stream().forEach(System.out::println);

	}

}
