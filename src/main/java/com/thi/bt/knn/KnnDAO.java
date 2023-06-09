package com.thi.bt.knn;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.StringWriter;
import java.io.PrintWriter;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;


public class KnnDAO {
		private DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
		private String jdbcURL = "jdbc:mysql://localhost:3306/kpdl";
		private String jdbcUsername = "root";
		private String jdbcPassword = "duyenduyen3912";
		
		
		private static final String INSERT_Patient_SQL = "insert into patient (name,age,gender,polyuria,polydipsia,wLoss,weakness,polyphagia,genital,visualBlurring,itching,irritability,delayedHealing,partial,muscleStiffness,alopecia,obesity,class, date) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		private static final String SELECT_Patient_BY_NAME ="select * from patient where name=?";
		private static final String SEARCH_Patient_BY_NAME ="select * from patient where name like ?";
		private static final String SELECT_ALL_Patient ="select * from Patient ";
		public KnnDAO() {
			
		}
		
		protected Connection getConnection() {
			Connection connection = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
			}catch (SQLException e) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			return connection;
		}
		
		public List<Patient> selectAllPatients(){
			List<Patient> Patients = new ArrayList<>();
			try(Connection connection = getConnection()) {
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Patient);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					int idPatient = resultSet.getInt("idPatient");
				
					String name = resultSet.getString("name");
					int age = resultSet.getInt("age");
					String gender = resultSet.getString("gender");
					String polyuria = resultSet.getString("polyuria");
					String polydipsia = resultSet.getString("polydipsia");
					String wLoss = resultSet.getString("wLoss");
					String weakness = resultSet.getString("weakness");
					String polyphagia = resultSet.getString("polyphagia");
					String genital = resultSet.getString("genital");
					String visualBlurring = resultSet.getString("visualBlurring");
					String itching = resultSet.getString("itching");
					String irritability = resultSet.getString("irritability");
					String delayedHealing = resultSet.getString("delayedHealing");
					String partial = resultSet.getString("partial");
					String muscleStiffness = resultSet.getString("muscleStiffness");
					String alopecia = resultSet.getString("alopecia");
					String obesity = resultSet.getString("obesity");
					String classDT = resultSet.getString("class");
					Date date = resultSet.getDate("date");
					String date2 = date.toString();
					
					Patient b = new Patient(gender,polyuria,polydipsia,wLoss,weakness,polyphagia,genital,visualBlurring,itching,irritability,delayedHealing,partial,muscleStiffness,alopecia,obesity,idPatient,age, date2, name, classDT);
					Patients.add(b);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return Patients;
		}
		
		
		
		
		
		
		
		public String insertPatient(Patient Patient) {
			String resultPredict = null;
			try(Connection connection = getConnection()) {
				// Tạo các đặc trưng cho file ARFF
				
				Attribute age = new Attribute("Age");
				Attribute Gender = new Attribute("Gender", Arrays.asList("Male", "Female"));
				Attribute Polyuria = new Attribute("Polyuria", Arrays.asList("Yes", "No"));
				Attribute Polydipsia = new Attribute("Polydipsia", Arrays.asList("Yes", "No"));
				Attribute wLoss = new Attribute("sudden weight loss", Arrays.asList("Yes", "No"));
				Attribute weakness = new Attribute("weakness", Arrays.asList("Yes", "No"));
				Attribute Polyphagia = new Attribute("Polyphagia", Arrays.asList("Yes", "No"));
				Attribute Genital = new Attribute("Genital thrush", Arrays.asList("Yes", "No"));
				Attribute visualBlurring = new Attribute("visual blurring", Arrays.asList("Yes", "No"));
				Attribute Itching = new Attribute("Itching", Arrays.asList("Yes", "No"));
				Attribute Irritability = new Attribute("Irritability", Arrays.asList("Yes", "No"));
				Attribute delayedHealing = new Attribute("delayed healing", Arrays.asList("Yes", "No"));
				Attribute partial = new Attribute("partial paresis", Arrays.asList("Yes", "No"));
				Attribute muscleStiffness = new Attribute("muscle stiffness", Arrays.asList("Yes", "No"));
				Attribute Alopecia = new Attribute("Alopecia", Arrays.asList("Yes", "No"));
				Attribute Obesity = new Attribute("Obesity", Arrays.asList("Yes", "No"));
				List<String> classValues = new ArrayList<String>();
				classValues.add("Positive");
				classValues.add("Negative");
				Attribute classDT = new Attribute("class", classValues);
				
				
				// Tạo một đối tượng ArrayList để chứa các thuộc tính
				ArrayList<Attribute> attributes = new ArrayList<>();
				attributes.add(age);
				attributes.add(Gender);
				attributes.add(Polyuria);
				attributes.add(Polydipsia);
				attributes.add(wLoss);
				attributes.add(weakness);
				attributes.add(Polyphagia);
				attributes.add(Genital);
				attributes.add(visualBlurring);
				attributes.add(Itching);
				attributes.add(Irritability);
				attributes.add(delayedHealing);
				attributes.add(partial);
				attributes.add(muscleStiffness);
				attributes.add(Alopecia);
				attributes.add(Obesity);
				attributes.add(classDT);
				
				Instances data = new Instances("UserData", attributes, 1);
				// Tạo một bản ghi mới với thông tin người dùng
				
				
				DenseInstance instance = new DenseInstance(17);
				instance.setValue(age, Patient.getAge());
				instance.setValue(Gender, Patient.getGender());
				instance.setValue(Polyuria, Patient.getPolyuria());
				instance.setValue(Polydipsia, Patient.getPolydipsia());
				instance.setValue(wLoss, Patient.getwLoss());
				instance.setValue(weakness, Patient.getWeakness());
				instance.setValue(Polyphagia, Patient.getPolyphagia());
				instance.setValue(Genital, Patient.getGenital());
				instance.setValue(visualBlurring, Patient.getVisualBlurring());
				instance.setValue(Itching, Patient.getItching());
				instance.setValue(Irritability, Patient.getIrritability());
				instance.setValue(delayedHealing, Patient.getDelayedHealing());
				instance.setValue(partial, Patient.getPartial());
				instance.setValue(muscleStiffness, Patient.getMuscleStiffness());
				instance.setValue(Alopecia, Patient.getAlopecia());
				instance.setValue(Obesity, Patient.getObesity());
				instance.setMissing(classDT);
				
				
				data.add(instance);
				System.out.println(data);
				
				Knn model = new Knn("C:\\Users\\ngocl\\eclipse-workspace\\bai-thi2\\src\\data\\diabetes_train.arff",
		                "-K 3 -W 0 -A \"weka.core.neighboursearch.LinearNNSearch -A \\\"weka.core.EuclideanDistance -R first-last\\\"\"",
		                null);
		        model.buildKNN("C:\\Users\\ngocl\\eclipse-workspace\\bai-thi2\\src\\data\\diabetes_train.arff");
		        model.evaluateKNN("C:\\Users\\ngocl\\eclipse-workspace\\bai-thi2\\src\\data\\diabetes_test.arff");
		        resultPredict = model.predictClassLabel(data);
		        System.out.println(resultPredict);
				
				
				
				PreparedStatement ps = connection.prepareStatement(INSERT_Patient_SQL);
				int result = 0;
				
				ps.setString(1, Patient.getName());
				ps.setInt(2, Patient.getAge());
				ps.setString(3, Patient.getGender());
				ps.setString(4, Patient.getPolyuria());
				ps.setString(5, Patient.getPolydipsia());
				ps.setString(6, Patient.getwLoss());
				ps.setString(7, Patient.getWeakness());
				ps.setString(8, Patient.getPolyphagia());
				ps.setString(9, Patient.getGenital());
				ps.setString(10, Patient.getVisualBlurring());
				ps.setString(11, Patient.getItching());
				ps.setString(12, Patient.getIrritability());
				ps.setString(13, Patient.getDelayedHealing());
				ps.setString(14, Patient.getPartial());
				ps.setString(15, Patient.getMuscleStiffness());
				ps.setString(16, Patient.getAlopecia());
				ps.setString(17, Patient.getObesity());
				ps.setString(18, resultPredict);
				Date date = new Date(System.currentTimeMillis());
				ps.setDate(19, new java.sql.Date(date.getTime()));
				result = ps.executeUpdate();

				
			} catch(Exception e) {
				e.printStackTrace();
			}
			return resultPredict;
		}
		
		public List<Patient> selectAllPatientByName(String search){
			List<Patient> Patients = new ArrayList<>();
			try(Connection connection = getConnection()) {
				PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_Patient_BY_NAME);
				preparedStatement.setString(1,'%' +search+ '%');
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					int idPatient = resultSet.getInt("idPatient");
					
					String name = resultSet.getString("name");
					int age = resultSet.getInt("age");
					String gender = resultSet.getString("gender");
					String polyuria = resultSet.getString("polyuria");
					String polydipsia = resultSet.getString("polydipsia");
					String wLoss = resultSet.getString("wLoss");
					String weakness = resultSet.getString("weakness");
					String polyphagia = resultSet.getString("polyphagia");
					String genital = resultSet.getString("genital");
					String visualBlurring = resultSet.getString("visualBlurring");
					String itching = resultSet.getString("itching");
					String irritability = resultSet.getString("irritability");
					String delayedHealing = resultSet.getString("delayedHealing");
					String partial = resultSet.getString("partial");
					String muscleStiffness = resultSet.getString("muscleStiffness");
					String alopecia = resultSet.getString("alopecia");
					String obesity = resultSet.getString("obesity");
					String classDT = resultSet.getString("class");
					Date date = resultSet.getDate("date");
					String date2 = date.toString();
					
					Patient b = new Patient(gender,polyuria,polydipsia,wLoss,weakness,polyphagia,genital,visualBlurring,itching,irritability,delayedHealing,partial,muscleStiffness,alopecia,obesity,idPatient,age, date2, name, classDT);
					Patients.add(b);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			return Patients;
		}
		
		
}