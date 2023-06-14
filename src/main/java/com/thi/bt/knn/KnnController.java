package com.thi.bt.knn;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.thi.bt.knn.request.SearchModal;
import com.thi.bt.knn.response.PredictResponde;
import com.thi.bt.knn.response.ResponseData;
import com.thi.bt.knn.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
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

	@Autowired
	private KnnDAO KnnDAO ;
	
	 @GetMapping("/GetPatient")
	 public List<Patient> getPatients() throws IOException
	 {
		 List<Patient> Patient = KnnDAO.selectAllPatients();
	
		 return Patient;
	 }
	 
	 
	 @GetMapping("/GetPatient/{name}")
	 public List<Patient> getPatientSearch(Model model,@PathVariable String name) throws IOException{
		 
		List<Patient> Patient = KnnDAO.selectAllPatientByName(name);
		 
		return Patient;
	}

	@PostMapping("/GetPatient")
	public List<Patient> search(@RequestBody SearchModal searchModal) throws IOException{

		List<Patient> Patient = KnnDAO.search(searchModal);

		return Patient;
	}
	 
	
	 @RequestMapping(value = "/predict", method = RequestMethod.POST,
             produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ResponseData<List<PredictResponde>>> predict(Response res, @RequestBody Patient patient) throws IOException
	{
		List<PredictResponde> mess = KnnDAO.insertPatient(patient);

		return new ResponseEntity<>(new ResponseData<List<PredictResponde>>(mess, Result.SUCCESS), HttpStatus.OK);
	}
	
	
}
