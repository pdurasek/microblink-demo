package com.pdurasek.demo.service;

import com.pdurasek.demo.dto.OCR.ScanDTO;
import com.pdurasek.demo.dto.UserAccountDTO;

import java.util.List;

public interface UserAccountService {

    UserAccountDTO getUserAccountFromOCR(ScanDTO scanDTO);

    List<UserAccountDTO> getOverdueUsers();

    List<UserAccountDTO> findAllUserAccounts();

    UserAccountDTO findUserAccountById(int id);

    UserAccountDTO createUserAccount(UserAccountDTO userAccountDTO);

    UserAccountDTO updateUserAccount(UserAccountDTO userAccountDTO);
}
