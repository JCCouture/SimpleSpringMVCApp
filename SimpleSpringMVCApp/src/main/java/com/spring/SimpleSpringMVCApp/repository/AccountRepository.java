package com.spring.SimpleSpringMVCApp.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.SimpleSpringMVCApp.dto.Account;

@Repository
public class AccountRepository {
	
	private List<Account> bd = new LinkedList<>();
	
	public Account save(Account account)
	{
		account.setPassword(account.getPassword());
		return account;
	}
	
	public Account findByEmail(String email)
	{
		Optional<Account> retval = bd.stream().filter(x -> x.getEmail() == email).findAny();
		
		return retval.get();
	}
	
	public List<Account> findAll()
	{
		return bd;
	}	
}
