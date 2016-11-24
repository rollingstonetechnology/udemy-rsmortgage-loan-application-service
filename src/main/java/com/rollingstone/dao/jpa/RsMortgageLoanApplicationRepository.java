package com.rollingstone.dao.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.rollingstone.domain.LoanApplication;
import com.rollingstone.domain.RealEstateProperty;



public interface RsMortgageLoanApplicationRepository extends PagingAndSortingRepository<LoanApplication, Long> {

    Page findAll(Pageable pageable);
}
