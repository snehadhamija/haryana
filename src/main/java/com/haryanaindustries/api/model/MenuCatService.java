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
@Table(name = "menu_cat_service")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuCatService {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "service", nullable = false)
	private String service;

	@Column(name = "detail", nullable = false, columnDefinition = "LONGTEXT")
	private String detail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private MenuCategory menuCategory;

	@Column(name = "keyword", nullable = false, columnDefinition = "LONGTEXT")
	private String keyword;

	@Column(name = "description", nullable = false, columnDefinition = "LONGTEXT")
	private String description;
	
	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	@Column(name = "sequence_id", nullable = false)
	private int sequenceId = 0;

	@Column(name = "img_url", nullable = true)
	private String imgurl;

}
