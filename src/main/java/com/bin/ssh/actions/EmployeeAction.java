package com.bin.ssh.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.bin.ssh.entities.Employee;
import com.bin.ssh.service.DepartmentService;
import com.bin.ssh.service.EmployeeService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class EmployeeAction extends ActionSupport implements RequestAware
 ,ModelDriven<Employee>,Preparable {

	private static final long serialVersionUID = 6016377845546232503L;
	private EmployeeService employeeService ;
	private Integer id ;
	private DepartmentService departmentService ;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	public String input(){
		req.put("departments", departmentService.getAll()) ;
		
		return INPUT ;
	}
	public void prepareInput(){
		if (id != null){
			model = employeeService.get(id) ;
		}
	}
	
	public String delete(){
		try {
			employeeService.delete(id);
			
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			
		}
		return "ajax-success" ;
	}
	
	public String list(){
		List<Employee> list = employeeService.getAll() ;
		req.put("employees", list) ;
		
		return "list" ;
	}
	
	public String save(){
		if (id == null) {
			model.setCreateTime(new Date());
		}
		employeeService.saveOrUpdate(model);
		return SUCCESS ;
	}
	
	private String lastName ;
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String validateLastName() throws UnsupportedEncodingException{
		if (employeeService.lastNameIsValid(lastName)) {
			inputStream = new ByteArrayInputStream("1".getBytes("utf-8")) ;
		} else {
			inputStream = new ByteArrayInputStream("0".getBytes("utf-8")) ;
		}
		return "ajax-success" ;
	}
	
	/**
	 * 可以根据id来判断,为save方法准备的model.
	 */
	public void prepareSave(){
		if (id == null) {
			model = new Employee() ;
		} else {
			model = employeeService.get(id) ;
		}
	}

	private Map<String, Object> req ;
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.req = arg0 ;
	}

	@Override
	public void prepare() throws Exception {
		System.out.println("已经执行.prepare");
	}
	
	private Employee model ;

	@Override
	public Employee getModel() {
		return model;
	}
}
