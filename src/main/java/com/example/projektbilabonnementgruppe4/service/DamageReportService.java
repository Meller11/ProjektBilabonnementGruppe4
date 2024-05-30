package com.example.projektbilabonnementgruppe4.service;
import com.example.projektbilabonnementgruppe4.model.DamageReport;
import com.example.projektbilabonnementgruppe4.repository.viewModel.DamageReportWithCarAndRA;
import com.example.projektbilabonnementgruppe4.repository.DamageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DamageReportService {

    private final DamageReportRepository damageReportRepository;

    @Autowired
    public DamageReportService(DamageReportRepository damageReportRepository) {
        this.damageReportRepository = damageReportRepository;
    }

    public List<DamageReportWithCarAndRA> damageReportOverview(){
        return damageReportRepository.damageReportOverview();
    }
    public DamageReportWithCarAndRA damageReportByID(int contract_id){
        return damageReportRepository.damageReportByID(contract_id);
    }

    public DamageReport updateDamageReport(DamageReport damageReport, int contract_id){
        return damageReportRepository.updateDamageReport(damageReport, contract_id);
    }

    public void deleteDamageReport(int contract_id){
        damageReportRepository.deleteDamageReport(contract_id);
    }

    public void createDamageReport(DamageReport damageReport){
        damageReportRepository.createNewDamageReport(damageReport);
    }

    public List<DamageReport> listOfAllDamageReports(){
        return damageReportRepository.listOfAllDamageReports();
    }

}


