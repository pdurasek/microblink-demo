package com.pdurasek.demo.service.impl;

import com.pdurasek.demo.dto.RentRecordDTO;
import com.pdurasek.demo.mapper.RentRecordMapper;
import com.pdurasek.demo.model.Book;
import com.pdurasek.demo.model.RentRecord;
import com.pdurasek.demo.model.UserAccount;
import com.pdurasek.demo.repository.BookRepository;
import com.pdurasek.demo.repository.RentRecordRepository;
import com.pdurasek.demo.repository.UserAccountRepository;
import com.pdurasek.demo.service.RentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RentRecordServiceImpl implements RentRecordService {

    RentRecordRepository rentRecordRepository;
    BookRepository bookRepository;
    UserAccountRepository userAccountRepository;

    private final int rentLength = 7;

    @Autowired
    public RentRecordServiceImpl(RentRecordRepository rentRecordRepository, BookRepository bookRepository,
                                 UserAccountRepository userAccountRepository) {
        this.rentRecordRepository = rentRecordRepository;
        this.bookRepository = bookRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<RentRecordDTO> captureRentRecord(List<RentRecordDTO> rentRecordDTOS) {
        List<RentRecord> submittedRentRecords = RentRecordMapper.rentRecordDTOsToRentRecords(rentRecordDTOS);
        List<RentRecord> validRecords = new ArrayList<>();

        for (RentRecord rentRecord : submittedRentRecords) {
            Optional<UserAccount> userAccount = userAccountRepository.findById(rentRecord.getUserAccount().getId());

            if (userAccount.isPresent()) {
                if (!userAccount.get().isValid()) {
                    return null;
                }
                rentRecord.setUserAccount(userAccount.get());
            }
            Optional<Book> book = bookRepository.findById(rentRecord.getBook().getId());

            if (book.isPresent() && book.get().getCopiesAvailable() > 0) {
                book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
                rentRecord.setBook(book.get());

                LocalDate today = LocalDate.now();
                rentRecord.setRentedDate(today);
                rentRecord.setDueDate(today.plusDays(rentLength));
                validRecords.add(rentRecord);
            }
        }

        return RentRecordMapper.rentRecordsToRentRecordDTOs(rentRecordRepository.saveAll(validRecords));
    }

    @Override
    public List<RentRecordDTO> returnRentRecord(List<RentRecordDTO> rentRecordDTOS) {
        List<RentRecord> submittedRentRecords = RentRecordMapper.rentRecordDTOsToRentRecordsWithReferences(rentRecordDTOS);
        List<RentRecord> validRecords = new ArrayList<>();

        for (RentRecord rentRecord : submittedRentRecords) {
            Optional<UserAccount> userAccount = userAccountRepository.findById(rentRecord.getUserAccount().getId());

            if (userAccount.isPresent()) {
                if (!userAccount.get().isValid() || rentRecord.getId() == null) {
                    return null;
                }
            }
            Optional<RentRecord> currentRentRecord = rentRecordRepository.findById(rentRecord.getId());
            if (currentRentRecord.isPresent()) {
                rentRecord.setReturnedDate(LocalDate.now());
                validRecords.add(rentRecord);
            }
        }

        return RentRecordMapper.rentRecordsToRentRecordDTOs(rentRecordRepository.saveAll(validRecords));
    }

    @Override
    public List<RentRecordDTO> getAllRentRecords() {
        return RentRecordMapper.rentRecordsToRentRecordDTOs(rentRecordRepository.findAll());
    }

    @Override
    public void checkOverdue() {
        List<RentRecord> rentRecords = rentRecordRepository.findAllByReturnedDateIsNull();
        List<RentRecord> updatedRecords = new ArrayList<>();


        for (RentRecord rentRecord : rentRecords) {
            int daysBetween = (int) ChronoUnit.DAYS.between(rentRecord.getDueDate(), LocalDate.now());
            if (daysBetween > 0) {
                rentRecord.setOverdueDays(daysBetween);
                updatedRecords.add(rentRecord);
            }
        }

        rentRecordRepository.saveAll(updatedRecords);
    }
}
