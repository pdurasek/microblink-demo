package com.pdurasek.demo.controller.v1;

import com.pdurasek.demo.dto.RentRecordDTO;
import com.pdurasek.demo.service.RentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rent-records")
public class RentRecordController {

    private RentRecordService rentRecordService;

    @Autowired
    public RentRecordController(RentRecordService rentRecordService) {
        this.rentRecordService = rentRecordService;
    }

    @GetMapping
    public ResponseEntity<List<RentRecordDTO>> getAllRentRecords() {
        return ResponseEntity.ok(rentRecordService.getAllRentRecords());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<List<RentRecordDTO>> createRentRecord(@Valid @RequestBody List<RentRecordDTO> rentRecordDTOS) {
        if (rentRecordDTOS != null) {
            List<RentRecordDTO> records = rentRecordService.captureRentRecord(rentRecordDTOS);

            if (records != null && records.size() > 0) {
                return ResponseEntity.ok().body(records);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/return", consumes = "application/json")
    public ResponseEntity<List<RentRecordDTO>> returnRent(@Valid @RequestBody List<RentRecordDTO> rentRecordDTOS) {
        if (rentRecordDTOS != null) {
            List<RentRecordDTO> records = rentRecordService.returnRentRecord(rentRecordDTOS);

            if (records != null && records.size() > 0) {
                return ResponseEntity.ok().body(records);
            }
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/syncOverdue")
    public ResponseEntity<Void> syncOverdue() {
        rentRecordService.checkOverdue();

        return ResponseEntity.ok().build();
    }
}
