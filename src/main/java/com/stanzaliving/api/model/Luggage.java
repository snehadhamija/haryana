package com.stanzaliving.api.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE")
public class Luggage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LUGGAGE_CATEGORY_ID")
	private LuggageCategory luggageCategory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LuggageCategory getLuggageCategory() {
		return luggageCategory;
	}

	public void setLuggageCategory(LuggageCategory luggageCategory) {
		this.luggageCategory = luggageCategory;
	}

}
