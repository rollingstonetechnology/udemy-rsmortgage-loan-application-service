package com.rollingstone.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rollingstone.dao.jpa.RsMortgageLoanApplicationRepository;
import com.rollingstone.domain.Customer;
import com.rollingstone.domain.LoanApplication;


/*
 * Service class to do CRUD for Customer LoanApplication through JPS Repository
 */
@Service
public class LoanApplicationService {

    private static final Logger log = LoggerFactory.getLogger(LoanApplicationService.class);

    @Autowired
    private RsMortgageLoanApplicationRepository customerLoanApplicationRepository;
    
  
    @Autowired
    CounterService counterService;

    @Autowired
    GaugeService gaugeService;

    public LoanApplicationService() {
    }

    public LoanApplication createLoanApplication(LoanApplication loanApplication) {
        return customerLoanApplicationRepository.save(loanApplication);
    }

    public LoanApplication getLoanApplication(long id) {
        return customerLoanApplicationRepository.findOne(id);
    }

    public void updateLoanApplication(LoanApplication loanApplication) {
    	customerLoanApplicationRepository.save(loanApplication);
    }

    public void deleteLoanApplication(Long id) {
    	customerLoanApplicationRepository.delete(id);
    }

    public Page<LoanApplication> getAllLoanApplicationsByPage(Integer page, Integer size) {
        Page pageOfLoanApplications = customerLoanApplicationRepository.findAll(new PageRequest(page, size));
        
        // example of adding to the /metrics
        if (size > 50) {
            counterService.increment("com.rollingstone.getAll.largePayload");
        }
        return pageOfLoanApplications;
    }
    
    public List<LoanApplication> getAllLoanApplications() {
        Iterable<LoanApplication> pageOfLoanApplications = customerLoanApplicationRepository.findAll();
        
        List<LoanApplication> customerLoanApplications = new ArrayList<LoanApplication>();
        
        for (LoanApplication loanApplication : pageOfLoanApplications){
        	customerLoanApplications.add(loanApplication);
        }
    	log.info("In Real Service getAllLoanApplications  size :"+customerLoanApplications.size());

    	
        return customerLoanApplications;
    }
    
}
