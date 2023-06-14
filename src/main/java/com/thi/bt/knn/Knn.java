/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thi.bt.knn;
import com.thi.bt.knn.response.PredictResponde;
import weka.classifiers.Classifier;
import weka.classifiers.evaluation.output.prediction.PlainText;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
/**
 *
 * @author ngocl
 */
import java.io.BufferedReader;
import weka.classifiers.lazy.IBk;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.neighboursearch.LinearNNSearch;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;

public class Knn extends KnowledgeModel {
    IBk knn;
    Evaluation eval;

    public Knn(String filename, String m_opts, String d_opts) throws Exception {
        super(filename, m_opts, d_opts);
    }

   
    public void buildKNN(String fileName) throws Exception {
        
        setTrainSet(fileName);
       
        this.trainSet.setClassIndex(this.trainSet.numAttributes() - 1);
      
        this.knn = new IBk(); 
        knn.setOptions(model_options); 
        knn.buildClassifier(this.trainSet); 
    }

    public String evaluateKNN(String fileName) throws Exception {
        setTestSet(fileName); 
        this.testSet.setClassIndex(this.testSet.numAttributes()-1);

      
        Random rd = new Random(1);
        int folds = 10;
        eval = new Evaluation(this.trainSet);
        eval.crossValidateModel(knn, this.testSet, folds, rd);
        double correctlyClassifiedInstances = eval.pctCorrect();
        String resultEval = String.valueOf(correctlyClassifiedInstances);
        System.out.print("++++++" + resultEval);
        System.out.println(eval.toSummaryString("\n -----Ket qua danh gia mo hinh du doan benh tieu duong-----\n", false));
        return resultEval;
    }
//trả về kết quả
    public PredictResponde predictClassLabel(Instances data) throws Exception {
        
        DataSource source = new DataSource(data);
        Instances unLabel = source.getDataSet();
        unLabel.setClassIndex(unLabel.numAttributes() - 1);
       
        // predict
        double predict = knn.classifyInstance(unLabel.instance(0));
        unLabel.instance(0).setClassValue(predict);
        unLabel.setClassIndex(unLabel.numAttributes() - 1);
      
	    // Calculate probability distribution for predicted class
	    double[] distribution = knn.distributionForInstance(unLabel.instance(0));
	    double predictedClassProb = distribution[(int) predict];

         // Print predicted class and probability as a percentage
        System.out.println("Probability: " + predictedClassProb * 100 + "%");
        System.out.println(unLabel.instance(0).stringValue(unLabel.classIndex()));

//        return unLabel.instance(0).stringValue(unLabel.classIndex());
        return new PredictResponde(unLabel.instance(0).stringValue(unLabel.classIndex()), predictedClassProb * 100);
    }

}

