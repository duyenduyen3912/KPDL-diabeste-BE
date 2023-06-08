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
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.RemovePercentage;
import weka.filters.unsupervised.instance.Resample;

import java.io.File;
import java.io.IOException;

public class KnowledgeModel {
    DataSource source; // Lưu nguồn dữ liệu
    Instances dataset; // Lưu giữ dữ liệu đầu vào
    Instances trainSet;
    Instances testSet;
    String[] model_options; // các tham số của mô hình
    String[] data_options; // các tham số xử lý dữ liệu

    public KnowledgeModel() {
    }

    public KnowledgeModel(String fileName, String myOption, String d_opts) throws Exception {

        this.source = new DataSource(fileName);
        this.dataset = source.getDataSet();
        if (myOption != null) {
            this.model_options = weka.core.Utils.splitOptions(myOption);
        }
        if (d_opts != null) {
            this.data_options = weka.core.Utils.splitOptions(d_opts);
        }
    }


    @Override
    public String toString() {
        return dataset.toSummaryString();
    }


    public void setTrainSet(String fileName) throws Exception {
        this.trainSet = this.source.getDataSet();
    }

    public void setTestSet(String fileName) throws Exception {
        this.testSet = this.source.getDataSet();
    }
}
