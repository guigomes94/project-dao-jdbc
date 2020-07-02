package app;

import dao.DaoFactory;

public class App {

	public static void main(String[] args) {
		
		var sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("===>Testing findById<===");
		var seller = sellerDao.findById(3);
		
		System.out.println(seller);

	}

}
