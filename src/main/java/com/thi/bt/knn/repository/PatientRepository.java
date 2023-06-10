package com.thi.bt.knn.repository;

import com.thi.bt.knn.Patient;
import com.thi.bt.knn.request.SearchModal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Tìm kiếm bệnh nhân theo tên
    List<Patient> findByName(String name);


    @Query(value = "select pt from Patient pt where 1 = 1 " +
            " and pt.requestBy = :requestUserId " +
            " and (:fromDate is null or pt.date >= :fromDate) " +
            " and (:toDate is null or pt.date <= :toDate) order by pt.date desc"
    )
    List<Patient> search(Date fromDate, Date toDate, int requestUserId);
}