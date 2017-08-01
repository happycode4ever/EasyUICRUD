package com.atguigu.easyui.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.easyui.entities.Employee;
import com.atguigu.easyui.services.EmployeeService;
import com.github.pagehelper.Page;

@Controller
public class EmployeeHandler {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/remote")
	public void remoteData(HttpServletResponse response) throws IOException, InterruptedException{
		Thread.sleep(1000);
		response.getWriter().write("Remote Data");
	}
	@ResponseBody
	@RequestMapping("/emps")
	public List<Employee> getAllEmps(){
		List<Employee> list = employeeService.getAllEmp();
		return list;
	}
	@ResponseBody
	@RequestMapping("/empsByPage")
	public Map<String,Object> getAllEmps(@RequestParam("page")Integer page,@RequestParam("rows")Integer rows){
		List<Employee> list = employeeService.getEmpPageList(page, rows);
		Page<Employee> info = (Page<Employee>) list;
		Map<String, Object> map = new HashMap<>();
		//分页需要total和rows字段
		map.put("total", info.getTotal());
		map.put("rows", info.getResult());
//		info.getTotal();
		return map;
	}
	@RequestMapping("/saveEmp")
	public void saveEmp(Employee emp,HttpServletResponse response) throws IOException{
		employeeService.saveEmployee(emp);
		response.getWriter().write("success");
	}
	@RequestMapping("/updateEmp")
	public void updateEmp(Employee emp,HttpServletResponse response) throws IOException{
//		System.out.println(emp);
		employeeService.updateEmployee(emp);
		response.getWriter().write("success");
	}
	@RequestMapping("/deleteEmp")
	public void deleteEmp(Integer empId,HttpServletResponse response) throws IOException{
		System.out.println(empId);
		employeeService.removeEmployee(empId);
		response.getWriter().write("success");
	}

}
