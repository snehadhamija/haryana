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
@Table(name = "CART_DETAIL")
public class CartDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CART_DETAIL_ID")
	private int cartDetailId;

	@Column(name = "SESSION_ID", nullable = false)
	private String sessionId;

	@Column(name = "CART_DETAIL", nullable = false, columnDefinition = "LONGTEXT")
	private String cartDetail;

	public int getCartDetailId() {
		return cartDetailId;
	}

	public void setCartDetailId(int cartDetailId) {
		this.cartDetailId = cartDetailId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCartDetail() {
		return cartDetail;
	}

	public void setCartDetail(String cartDetail) {
		this.cartDetail = cartDetail;
	}

}
