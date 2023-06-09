package com.thi.bt.knn;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



import org.apache.commons.io.IOUtils;

//@Controller
@RestController
@CrossOrigin

public class KnnController {
	
	private KnnDAO KnnDAO = new KnnDAO();
	
	 @GetMapping("/GetPatient")
	 public List<Patient> getPatients(Model model) throws IOException
	 {
		 List<Patient> Patient = KnnDAO.selectAllPatients();
	
		 return Patient;
	 }
	 
	 
	 @GetMapping("/GetPatient/{name}")
	 public List<Patient> getPatientSearch(Model model,@PathVariable String name) throws IOException{
		 
		List<Patient> Patient = KnnDAO.selectAllPatientByName(name);
		 
		return Patient;
	}
	 
	
	 @RequestMapping(value = "/predict", method = RequestMethod.POST,
             produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Response predict(Response res, @RequestBody Patient patient) throws IOException
	{				
			String mess = KnnDAO.insertPatient(patient);
			if(mess.equals("error")) {
				res.setResponse("error");
				
			}
			else {
				res.setResponse(mess);
			}
			return res;
	}
	
	
}
