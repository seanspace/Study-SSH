package com.bin.ssh.dao;

import java.util.List;

import org.hibernate.Query;

import com.bin.ssh.entities.Employee;

public class EmployeeDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<Employee> getAll(){
		String hql = "From Employee e left outer join fetch e.department" ;
		List<Employee> employees= getSession().createQuery(hql).list() ;
		//employees.clear();
		return employees ;
	}
	
	public void delete(Integer id) {
		String hql = "Delete From Employee e where e.id = ?" ;
		getSession().createQuery(hql).setInteger(0, id).executeUpdate();
	}
	
	public void saveOrUpdate(Employee employee){
		getSession().saveOrUpdate(employee);
	}
	
	public Employee getEmployeeByLastName(String lastName){
		String hql = "From Employee e where e.lastName = ?" ;
		Query query = getSession().createQuery(hql).setString(0, lastName) ;
		return (Employee) query.uniqueResult() ;
	}

	public Employee get(Integer id) {
		return (Employee) getSession().get(Employee.class, id) ;
	}
	

}
