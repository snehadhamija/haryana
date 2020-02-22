/**
 * @author nipunaggarwal
 *
 */
package com.haryanaindustries.api.model;

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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Entity
@Table(name = "menu_category")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "category", nullable = false)
	private String category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cid")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Menu menu;
	
	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@Column(name = "sequence_id", nullable = false)
	private int sequenceId = 0;

	@Column(name = "img_url", nullable = true)
	private String imgurl;

}
