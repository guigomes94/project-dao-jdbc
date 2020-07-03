package app;

import java.util.Date;

import dao.DaoFactory;
import model.Department;
import model.Seller;

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

		System.out.println("\n===>Testing insert<===");
		var newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.00, dep);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New ID = " + newSeller.getId());

		System.out.println("\n===>Testing update<===");
		var upSeller = sellerDao.findById(6);
		upSeller.setName("James Bond");
		upSeller.setEmail("007bond@gmail.com");
		sellerDao.update(upSeller);
		System.out.println("Updated!");

		System.out.println("\n===>Testing delete<===");
		sellerDao.deleteById(10);
		System.out.println("Destroyed!");

	}

}
