package com.thi.bt.knn.response;


public class PredictResponde {
    private Double accuracy;
    private String prediction;
    private String phuongphap;

    public String getPhuongphap() {
        return phuongphap;
    }

    public void setPhuongphap(String phuongphap) {
        this.phuongphap = phuongphap;
    }

    public PredictResponde(String s, double v) {
        this.prediction = s;
        this.accuracy = v;
    }

    public PredictResponde() {

    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
