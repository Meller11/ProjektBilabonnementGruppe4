package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.AdminModel;
import com.example.projektbilabonnementgruppe4.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  private AdminRepository adminRepository;

  public void saveNewEmployee(AdminModel adminModel) {
    adminRepository.saveNewEmployee(adminModel);
  }

}
