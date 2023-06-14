package com.thi.bt.knn;

import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.StringWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.thi.bt.knn.request.SearchModal;
import com.thi.bt.knn.response.PredictResponde;
import com.thi.bt.knn.service.APIService;
import com.thi.bt.knn.service.PatientService;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;


@Service
public class KnnDAO {
    @Autowired
    private PatientService patientService;
    @Autowired
    private APIService apiService;
    private Gson gson = new Gson();


    public List<Patient> selectAllPatients() {
        List<Patient> Patients = patientService.getAllPatients();
        return Patients;
    }


    public List<PredictResponde>  insertPatient(Patient Patient) {
        List<PredictResponde> predictRespondes = new ArrayList<>();
        PredictResponde predictResponde = new PredictResponde();
        String kNearest = null;
        try {
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
           // System.out.println(data);


            //load file from resource
            ClassLoader classLoader = getClass().getClassLoader();
            Knn model = new Knn(classLoader.getResource("data/diabetes_train.arff").getFile(),
                    "-K 3 -W 0 -A \"weka.core.neighboursearch.LinearNNSearch -A \\\"weka.core.EuclideanDistance -R first-last\\\"\"",
                    null);
            model.buildKNN(classLoader.getResource("data/diabetes_train.arff").getFile());
            model.evaluateKNN(classLoader.getResource("data/diabetes_test.arff").getFile());

            predictResponde = model.predictClassLabel(data);
            System.out.println(predictResponde);
            User userLogin = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            Patient.setDate(new Date());
            Patient.setRequestBy(userLogin.getId());


        } catch (Exception e) {
            e.printStackTrace();
        }
        predictResponde.setPhuongphap("KNN");
        predictRespondes.add(predictResponde);

        //Random Forest algorithm
        //call api to https://kpdl.vjet.software/predict ( POST method)

        //convert input to number
        List<Long> dataRF = new ArrayList<>();
        dataRF.add(Long.valueOf(Patient.getAge()));
        dataRF.add(Patient.getGender().equals("Male") ? 0L : 1L);
        dataRF.add(Patient.getPolyuria().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getPolydipsia().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getwLoss().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getWeakness().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getPolyphagia().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getGenital().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getVisualBlurring().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getItching().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getIrritability().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getDelayedHealing().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getPartial().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getMuscleStiffness().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getAlopecia().equals("Yes") ? 1L : 0L);
        dataRF.add(Patient.getObesity().equals("Yes") ? 1L : 0L);

        PredictResponde predictRespondeRFA = new PredictResponde();
        String resultRFA = apiService.callApi(dataRF);
        if (resultRFA != null && !resultRFA.isEmpty()) {
            predictRespondeRFA = gson.fromJson(resultRFA, PredictResponde.class);
           predictRespondes.add(predictRespondeRFA);
        }

        String buildResult = "KNN: " + predictResponde.getPrediction() + " - RF: " + predictRespondeRFA.getPrediction();
        Patient.setClassDT(buildResult);
        patientService.savePatient(Patient);

        return predictRespondes;
    }

    public List<Patient> selectAllPatientByName(String search) {

        return null;
    }


    public List<Patient> search(SearchModal searchModal) {
        return patientService.search(searchModal);
    }
}