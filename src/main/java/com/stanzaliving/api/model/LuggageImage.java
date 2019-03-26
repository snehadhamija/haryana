package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LUGGAGE_IMAGE")
public class LuggageImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "LUGGAGE_TRANSACTION_DETAIL_ID")
	private LuggageTransactionDetail luggageTransactionDetail;

	@Column(name = "IMAGE_URL", nullable = false)
	private String imageUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LuggageTransactionDetail getLuggageTransactionDetail() {
		return luggageTransactionDetail;
	}

	public void setLuggageTransactionDetail(LuggageTransactionDetail luggageTransactionDetail) {
		this.luggageTransactionDetail = luggageTransactionDetail;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
