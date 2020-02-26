package com.pdurasek.demo.repository;

import com.pdurasek.demo.model.RentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRecordRepository extends JpaRepository<RentRecord, Integer> {

    List<RentRecord> findAllByReturnedDateIsNull();
}
