package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.SellerDao;
import db.DB;
import db.DbException;
import model.Department;
import model.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE seller.Id = ?;"
					);
			query.setInt(1, id);
			result = query.executeQuery();
			
			if (result.next()) {
				var dep = instantiateDepartment(result);
				
				var seller = instatianteSeller(result, dep);
				
				return seller;
				
			}
			return null;
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(query);
			
		}
	}

	private Seller instatianteSeller(ResultSet result, Department dep) throws SQLException {
		var seller = new Seller();
		seller.setId(result.getInt("id"));
		seller.setName(result.getString("name"));
		seller.setEmail(result.getString("email"));
		seller.setBaseSalary(result.getDouble("baseSalary"));
		seller.setBirthDate(result.getDate("birthDate"));
		seller.setDepartment(dep);
		
		return seller;
	}

	private Department instantiateDepartment(ResultSet result) throws SQLException {
		var dep = new Department();
		dep.setId(result.getInt("departmentId"));
		dep.setName(result.getString("depName"));
		
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"ORDER BY Name"
					);
			
			result = query.executeQuery();
			
			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (result.next()) {
				
				var dep = map.get(result.getInt("departmentId"));
				
				if (dep == null) {
					dep = instantiateDepartment(result);
					map.put(result.getInt("departmentid"), dep);
				}
				
				
				var seller = instatianteSeller(result, dep);
				
				sellers.add(seller);
				
			}
			return sellers;
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(query);
			
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement query = null;
		ResultSet result = null;
		
		try {
			query = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + 
					"FROM seller INNER JOIN department " + 
					"ON seller.DepartmentId = department.Id " + 
					"WHERE DepartmentId = ? " + 
					"ORDER BY Name"
					);
			query.setInt(1, department.getId());
			result = query.executeQuery();
			
			List<Seller> sellers = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();
			
			while (result.next()) {
				
				var dep = map.get(result.getInt("departmentId"));
				
				if (dep == null) {
					dep = instantiateDepartment(result);
					map.put(result.getInt("departmentid"), dep);
				}
				
				
				var seller = instatianteSeller(result, dep);
				
				sellers.add(seller);
				
			}
			return sellers;
			
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeResultSet(result);
			DB.closeStatement(query);
			
		}
	}
}