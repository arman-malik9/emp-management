package com.myproject.adminMS;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class adminController {

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Value("${pivotal.loginservice.name}")
	protected String loginService;
	
	@Value("${pivotal.employeeservice.name}")
	protected String empService;
	

	@RequestMapping("/adminScreen")
	public String adminScreen()
	{
		return "adminScreen.html";
	}
	
	@RequestMapping("/view")
	public ModelAndView viewAllRegisteration()
	{
		//view code goes here
		//EmployeeMS Rest Api
		String str = getDataFromEmpService();
		System.out.println(str);
		
		
		 // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> list = null;
 
        try {
            // Convert JSON string to List of Maps
            list = objectMapper.readValue(str, new TypeReference<List<Map<String, Object>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        ModelAndView mv = new ModelAndView();
		// Add the list to the model
        mv.addObject("dataList", list);
        mv.setViewName("viewAllRegisteration");
 
        
		
		return mv;
	}
	

	public String getDataFromEmpService() {
		ServiceInstance instance = loadBalancerClient.choose(empService);
		URI uri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
		String url = uri +"/userData"; //end point which return the data in jason.
		System.out.println(url);
		RestTemplate restTemplate = new RestTemplate();
	 	String response = restTemplate.getForObject(url, String.class);
	 	return response;
			
	}
	@RequestMapping("/redirectToLogin")
	public String redirectToLogIn(HttpServletResponse response) throws IOException
	{
		ServiceInstance instance = loadBalancerClient.choose(loginService);
		URI uri = URI.create(String.format("http://%s:%s", instance.getHost(), instance.getPort()));
		String url = uri +"/emp/login";
		response.sendRedirect(url);
	
		return null;
	}
}
