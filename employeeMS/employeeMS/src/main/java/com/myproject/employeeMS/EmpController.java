package com.myproject.employeeMS;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class EmpController {
	
	
	@Autowired
	private AssessmentRepo assessmentRepo;
	
	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Value("${pivotal.loginservice.name}")
	protected String loginService;
	

	
	@RequestMapping("/selectRegisterationPage")
	public String selectRegisterationPage()
	{
		return "selectRegisterationPage.html";
	}
	@RequestMapping("/technicalRegisteration")
	public String technialRegisterationPage()
	{
		return "technicalRegisteration.html";
	}
	@RequestMapping("/beahviouralRegisteration")
	public String behaviouralRegisterationPage()
	{
		return "behaviouralRegisteration.html";
	}
	

	

	
	@PostMapping("/persistTechAss")
	public String persistTechAss(@RequestParam("userId") Long userId, @RequestParam("assessment") String selectedAssessment, @RequestParam("date") String date)
	{	
//		List<Assessments> enteries = assessmentRepo.findAll();
//		System.out.println(enteries);
//		if(assessmentRepo.findByUserId(userId).isPresent() && assessmentRepo.findByAssessment(selectedAssessment).isPresent()) {
//		
//		}
		
			String type = "Technical";
			Assessments assessments = new Assessments(userId, selectedAssessment, date, type);
			System.out.println(assessments);
			assessmentRepo.save(assessments);
			return "success.html";
		
	}
	
	@PostMapping("/persistBehavAss")
	public String persistBehavAss(@RequestParam("userId") Long userId, @RequestParam("assessment") String selectedAssessment, @RequestParam("date") String date)
	{	
		String type = "Behavioural";
		Assessments assessments = new Assessments(userId, selectedAssessment, date, type);
		assessmentRepo.save(assessments);
		return "success.html";
	}
	
	@GetMapping("/userData")
	@ResponseBody
	public List<Assessments> getData(){
		List<Assessments> list =assessmentRepo.findAll();
		return list;
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
