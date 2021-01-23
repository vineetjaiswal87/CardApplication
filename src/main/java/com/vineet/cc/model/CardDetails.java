package com.vineet.cc.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Entity class with required columns to hold the card details in the database.
 *
 */
@Entity
@Table(name = "CardDetails")
@Data
public class CardDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	@Column(name = "Name")
	private String name;
	@Column(name = "CardNumber", unique = true)
	private String cardNum;
	@Column(name = "LimitAmount")
	private BigDecimal limit;
	@Column(name = "BalanceAmount")
	private BigDecimal balance;
	@Column(name = "CreatedUser")
	private String createdUserId;
	@Column(name = "ModifiedUser")
	private String modifiedUserId;
	@Column(name = "CreatedTime")
	private Date createdDateTime;
	@Column(name = "ModifiedTime")
	private Date modifiedDateTime;
	
	public CardDetails() {}
	
	/**
	 * @param name
	 * @param cardNum
	 * @param limit
	 * @param userId
	 */
	public CardDetails(String name, String cardNum, BigDecimal limit, String userId) {
		this.name = name;
		this.cardNum = cardNum;
		this.limit = limit;
		this.balance = BigDecimal.ZERO;
		this.createdUserId = userId;
		this.modifiedUserId = userId;
		this.createdDateTime = new Date();
		this.modifiedDateTime = new Date();
	}
}
