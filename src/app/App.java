package app;

import dao.DaoFactory;

public class App {

	public static void main(String[] args) {
		
		var sellerDao = DaoFactory.createSellerDao();
		
		var seller = sellerDao.findById(3);
		
		System.out.println(seller);

	}

}
