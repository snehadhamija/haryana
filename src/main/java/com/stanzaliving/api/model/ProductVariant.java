/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "PRODUCT_VARIANT")
public class ProductVariant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_VARIANT_ID")
	private int productVariantId;

	@Column(name = "PRODUCT_VARIANT_NAME", nullable = false)
	private String productVariantName;

	@Column(name = "IS_ACTIVE", nullable = false)
	private Boolean isActive = true;

	@Column(name = "SEQUENCE_ID", nullable = false)
	private int sequenceId = 0;

	@Column(name = "IMG_URL", nullable = true)
	private String imgurl;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PRODUCT_ID")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Product product;

	public int getProductVariantId() {
		return productVariantId;
	}

	public void setProductVariantId(int productVariantId) {
		this.productVariantId = productVariantId;
	}

	public String getProductVariantName() {
		return productVariantName;
	}

	public void setProductVariantName(String productVariantName) {
		this.productVariantName = productVariantName;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductVariant "
				+ "[productVariantId=" + productVariantId + ", "
				+ "productVariantName=" + productVariantName + ", "
				+ "isActive=" + isActive + ", "
				+ "sequenceId=" + sequenceId + ", "
				+ "imgurl=" + imgurl + ", "
				+ "product=" + product + "]";
	}

}
