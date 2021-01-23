package com.vineet.cc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vineet.cc.model.CardDetails;


public interface CardDetailsRepository extends CrudRepository<CardDetails, Integer> {

	boolean existsBycardNum(String cardNum);

	List<CardDetails> findAll();
}
