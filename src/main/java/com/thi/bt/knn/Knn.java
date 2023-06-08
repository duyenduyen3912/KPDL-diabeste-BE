/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thi.bt.knn;

/**
 *
 * @author ngocl
 */
import java.io.BufferedReader;
import weka.classifiers.lazy.IBk;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void evaluateKNN(String fileName) throws Exception {
        setTestSet(fileName); 
        this.testSet.setClassIndex(this.testSet.numAttributes()-1);

      
        Random rd = new Random(1);
        int folds = 10;
        eval = new Evaluation(this.trainSet);
        eval.crossValidateModel(knn, this.testSet, folds, rd);
        System.out.println(eval.toSummaryString("\n -----Ket qua danh gia mo hinh nhan dien 4 loai hoa-----\n", false));
    }

    public String predictClassLabel(Instances data) throws Exception {
      
        DataSource source = new DataSource(data);
        Instances unLabel = source.getDataSet();
        unLabel.setClassIndex(unLabel.numAttributes() - 1);

      
//        for (int i = 0; i < unLabel.numInstances() ; i++) {
            double predict = knn.classifyInstance(unLabel.instance(0));
            unLabel.instance(0).setClassValue(predict);
            
            return unLabel.instance(0).stringValue(unLabel.classIndex());
//        }

     
        
        
         
        
    
    }
}
