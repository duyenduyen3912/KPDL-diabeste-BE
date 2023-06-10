package com.thi.bt.knn.request;

import com.thi.bt.knn.config.H;

import java.util.Date;

public class SearchModal {
    private Date fromDate;
    private Date toDate;
    private Long fromDateLong;
    private Long toDateLong;

    public Date getFromDate() {
        if(H.isTrue(this.fromDateLong)) {
            return H.convertFromLong(this.fromDateLong);
        } else {
            return this.fromDate;
        }
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        if(H.isTrue(this.toDateLong)) {
            return H.convertFromLong(this.toDateLong);
        } else {
            return this.toDate;
        }
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Long getFromDateLong() {
        return fromDateLong;
    }

    public void setFromDateLong(Long fromDateLong) {
        this.fromDateLong = fromDateLong;
    }

    public Long getToDateLong() {
        return toDateLong;
    }

    public void setToDateLong(Long toDateLong) {
        this.toDateLong = toDateLong;
    }
}
