package com.rollingstone.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

/*
 * A User POJO serving as an Entity as well as a Data Transfer Object i.e DTO
 */
@Entity
@Table(name = "rsmortgage_loan_applicant")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoanApplicant {



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loan_application_id", nullable = false)
	@JsonBackReference
	LoanApplication loanApplication;
	
	public LoanApplicant(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public LoanApplication getLoanApplication() {
		return loanApplication;
	}

	public void setLoanApplication(LoanApplication loanApplication) {
		this.loanApplication = loanApplication;
	}

	@Override
	public String toString() {
		return "LoanApplicant [id=" + id + ", loanApplication=" + loanApplication + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LoanApplicant other = (LoanApplicant) obj;
		
		if (id != other.id)
			return false;
		return true;
	}

	public LoanApplicant(long id, Customer customer) {
		super();
		this.id = id;
		
	}
	
	
}
