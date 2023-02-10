package com.springcrud.dao;

import java.util.List;
import jakarta.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springcrud.entity.Employee;

import jakarta.persistence.EntityManager;

@Repository
public class EmployeeDaoHibernateImpl implements EmployeeDao {
	
//	define EntityManager variable 
	private EntityManager entityManager ;
	
//	constructor injection 
	@Autowired
	public EmployeeDaoHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Employee> findAll() {
		
//		create a Query 
        Query query = entityManager.createQuery("from Employee");
		
//		execute the query and getResult
		List<Employee> employeeList = query.getResultList();
		
		return employeeList;
	}
	

	@Override
	public Employee findById(int empId) {
	    
//	    get the employee using its id
	    Employee employee = entityManager.find(Employee.class, empId);
		
		return employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		

		Employee dbEmployee = entityManager.merge(employee);
		
		employee.setId(dbEmployee.getId());
		
	}

	
	@Override
	public void deleteEmployee(int empId) {
		
//	    delete data from table 
//		create a query
		Query query = entityManager.createQuery("delete from Employee where id=:empId");

		query.setParameter("empId", empId);
		
		query.executeUpdate();
		
	}

}
