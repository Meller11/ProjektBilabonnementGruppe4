package com.example.projektbilabonnementgruppe4.service;

import com.example.projektbilabonnementgruppe4.model.AdminModel;
import com.example.projektbilabonnementgruppe4.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  private final AdminRepository adminRepository;

  @Autowired
  public AdminService(AdminRepository adminRepository) {
    this.adminRepository = adminRepository;
  }
  public void saveNewEmployee(AdminModel adminModel) {
    adminRepository.saveNewEmployee(adminModel);
  }

}
