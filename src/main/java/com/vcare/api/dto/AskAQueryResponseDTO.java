/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vcare.api.Enum.ContactMode;
import com.vcare.api.Enum.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author nipunaggarwal
 *
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AskAQueryResponseDTO {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private Gender gender;
	private ContactMode contactMode;
	@JsonProperty(value = "faq", access = JsonProperty.Access.READ_WRITE)
	private FAQResponseDTO faqResponseDTO;

}
