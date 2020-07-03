package app;

import dao.DaoFactory;

public class App2 {

	public static void main(String[] args) {

		var departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("===>Testing findById<===");
		var department = departmentDao.findById(3);
		System.out.println(department);

		System.out.println("\n===>Testing findByAll<===");
		var allDepartments = departmentDao.findAll();
		allDepartments.stream().forEach(System.out::println);

		/*
		 * System.out.println("\n===>Testing insert<==="); var newDepartment = new
		 * Department(null, "Mobiles"); departmentDao.insert(newDepartment);
		 * System.out.println("Inserted! New ID = " + newDepartment.getId());
		 * 
		 * System.out.println("\n===>Testing update<==="); var upDepartment =
		 * departmentDao.findById(5); upDepartment.setName("Moda");
		 * departmentDao.update(upDepartment); System.out.println("Updated!");
		 * 
		 * System.out.println("\n===>Testing delete<==="); departmentDao.deleteById(7);
		 * System.out.println("Destroyed!");
		 */

	}

}
