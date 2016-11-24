package com.rollingstone.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.rollingstone.domain.Customer;
import com.rollingstone.domain.LoanApplication;
import com.rollingstone.exception.HTTP400Exception;
import com.rollingstone.service.LoanApplicationService;
/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/rsmortgage-loan-application-service/v1/loan-application")
public class LoanApplicationController extends AbstractRestController {

    @Autowired
    private LoanApplicationService customerLoanApplicationService;
  
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerLoanApplication(@RequestBody LoanApplication loanApplication,
                                 HttpServletRequest request, HttpServletResponse response) {
    	LoanApplication createdLoanApplication = this.customerLoanApplicationService.createLoanApplication(loanApplication);
        response.setHeader("Location", request.getRequestURL().append("/").append(createdLoanApplication.getId()).toString());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<LoanApplication> getAllCustomersLoanApplicationByPage(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
        return this.customerLoanApplicationService.getAllLoanApplicationsByPage(page, size);
    }
    
    @RequestMapping(value = "/all",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    List<LoanApplication> getAllCustomerLoanApplications(@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                      @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                      HttpServletRequest request, HttpServletResponse response) {
    
        return this.customerLoanApplicationService.getAllLoanApplications();
    }
    
  
    
    @RequestMapping("/simple/{id}")
	public LoanApplication getSimpleCustomerLoanApplication(@PathVariable("id") Long id) {
    	LoanApplication loanApplication = this.customerLoanApplicationService.getLoanApplication(id);
         checkResourceFound(loanApplication);
         return loanApplication;
	}

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    LoanApplication getLoanApplication(@PathVariable("id") Long id,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
    	LoanApplication loanApplication = this.customerLoanApplicationService.getLoanApplication(id);
        checkResourceFound(loanApplication);
        return loanApplication;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomerLoanApplication(@PathVariable("id") Long id, @RequestBody LoanApplication loanApplication,
                                 HttpServletRequest request, HttpServletResponse response) {
        checkResourceFound(this.customerLoanApplicationService.getLoanApplication(id));
        if (id != loanApplication.getId()) throw new HTTP400Exception("ID doesn't match!");
        this.customerLoanApplicationService.updateLoanApplication(loanApplication);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerLoanApplication(@PathVariable("id") Long id, HttpServletRequest request,
                                 HttpServletResponse response) {
        checkResourceFound(this.customerLoanApplicationService.getLoanApplication(id));
        this.customerLoanApplicationService.deleteLoanApplication(id);
    }
}
