package com.pdurasek.demo.mapper;

import com.pdurasek.demo.dto.RentRecordDTO;
import com.pdurasek.demo.model.RentRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RentRecordMapper {

    public static RentRecordDTO rentRecordToRentRecordDTO(RentRecord rentRecord) {
        RentRecordDTO rentRecordDTO = rentRecordToRentRecordDTOWithoutReferences(rentRecord);
        rentRecordDTO.setBook(BookMapper.bookToBookDTOWithoutRentedUsers(rentRecord.getBook()));
        rentRecordDTO.setUserAccount(UserAccountMapper.userToUserDTOWithoutReferences(rentRecord.getUserAccount()));

        return rentRecordDTO;
    }

    public static List<RentRecordDTO> rentRecordsToRentRecordDTOs(List<RentRecord> rentRecords) {
        if (rentRecords == null) {
            return null;
        }

        List<RentRecordDTO> list = new ArrayList<>(rentRecords.size());
        for (RentRecord rentRecord : rentRecords) {
            list.add(rentRecordToRentRecordDTO(rentRecord));
        }

        return list;
    }

    public static RentRecordDTO rentRecordToRentRecordDTOWithBook(RentRecord rentRecord) {
        RentRecordDTO rentRecordDTO = rentRecordToRentRecordDTOWithoutReferences(rentRecord);
        rentRecordDTO.setBook(BookMapper.bookToBookDTOWithoutRentedUsers(rentRecord.getBook()));

        return rentRecordDTO;
    }

    public static List<RentRecordDTO> rentRecordsToRentRecordDTOsWithBook(List<RentRecord> rentRecords) {
        if (rentRecords == null) {
            return null;
        }

        List<RentRecordDTO> list = new ArrayList<>(rentRecords.size());
        for (RentRecord rentRecord : rentRecords) {
            list.add(rentRecordToRentRecordDTOWithBook(rentRecord));
        }

        return list;
    }

    public static RentRecordDTO rentRecordToRentRecordDTOWithoutReferences(RentRecord rentRecord) {
        RentRecordDTO rentRecordDTO = new RentRecordDTO();
        rentRecordDTO.setId(rentRecord.getId());
        rentRecordDTO.setDueDate(rentRecord.getDueDate());
        rentRecordDTO.setRentedDate(rentRecord.getRentedDate());
        rentRecordDTO.setReturnedDate(rentRecord.getReturnedDate());
        rentRecordDTO.setOverdueDays(rentRecord.getOverdueDays());

        return rentRecordDTO;
    }

    public static List<RentRecordDTO> rentRecordsToRentRecordDTOsWithoutReferences(List<RentRecord> rentRecords) {
        if (rentRecords == null) {
            return null;
        }

        List<RentRecordDTO> list = new ArrayList<>(rentRecords.size());
        for (RentRecord rentRecord : rentRecords) {
            list.add(rentRecordToRentRecordDTOWithoutReferences(rentRecord));
        }

        return list;
    }

    public static RentRecord rentRecordDTOToRentRecord(RentRecordDTO rentRecordDTO) {
        RentRecord rentRecord = new RentRecord();
        rentRecord.setBook(BookMapper.bookDTOToBook(rentRecordDTO.getBook()));
        rentRecord.setUserAccount(UserAccountMapper.userAccountDTOToUserAccount(rentRecordDTO.getUserAccount()));

        return rentRecord;
    }

    public static List<RentRecord> rentRecordDTOsToRentRecords(List<RentRecordDTO> rentRecordDTOs) {
        if (rentRecordDTOs == null) {
            return null;
        }

        List<RentRecord> list = new ArrayList<>(rentRecordDTOs.size());
        for (RentRecordDTO rentRecordDTO : rentRecordDTOs) {
            list.add(rentRecordDTOToRentRecord(rentRecordDTO));
        }

        return list;
    }

    public static RentRecord rentRecordDTOToRentRecordWithReferences(RentRecordDTO rentRecordDTO) {
        RentRecord rentRecord = new RentRecord();
        rentRecord.setId(rentRecordDTO.getId());
        rentRecord.setDueDate(rentRecordDTO.getDueDate());
        rentRecord.setRentedDate(rentRecordDTO.getRentedDate());
        rentRecord.setReturnedDate(rentRecordDTO.getReturnedDate());
        rentRecord.setOverdueDays(rentRecordDTO.getOverdueDays());
        rentRecord.setBook(BookMapper.bookDTOToBook(rentRecordDTO.getBook()));
        rentRecord.setUserAccount(UserAccountMapper.userAccountDTOToUserAccount(rentRecordDTO.getUserAccount()));

        return rentRecord;
    }

    public static List<RentRecord> rentRecordDTOsToRentRecordsWithReferences(List<RentRecordDTO> rentRecordDTOs) {
        if (rentRecordDTOs == null) {
            return null;
        }

        List<RentRecord> list = new ArrayList<>(rentRecordDTOs.size());
        for (RentRecordDTO rentRecordDTO : rentRecordDTOs) {
            list.add(rentRecordDTOToRentRecordWithReferences(rentRecordDTO));
        }

        return list;
    }
}
