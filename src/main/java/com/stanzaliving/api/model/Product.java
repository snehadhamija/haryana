/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME", nullable = false)
	private String productName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = true;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@Column(name = "IMG_URL", nullable = true)
	private String imgurl;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(int sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	@Override
	public String toString() {
		return "Product "
				+ "[productId=" + productId + ", "
				+ "productName=" + productName + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "imgurl=" + imgurl + "]";
	}

}
