package com.bin.ssh.dao;

import java.util.List;

import com.bin.ssh.entities.Department;

public class DepartmentDao extends BaseDao{
	
	@SuppressWarnings("unchecked")
	public List<Department> getAll(){
		String hql = "From Department" ;
		return getSession().createQuery(hql).list() ;
	}
}
