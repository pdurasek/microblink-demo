package com.pdurasek.demo.service;

import com.pdurasek.demo.dto.RentRecordDTO;

import java.util.List;

public interface RentRecordService {

    List<RentRecordDTO> captureRentRecord(List<RentRecordDTO> rentRecordDTOS);

    List<RentRecordDTO> returnRentRecord(List<RentRecordDTO> rentRecordDTOS);

    List<RentRecordDTO> getAllRentRecords();

    void checkOverdue();
}
