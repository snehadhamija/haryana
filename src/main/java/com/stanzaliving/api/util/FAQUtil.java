/**
 * @author nipunaggarwal
 *
 */
package com.stanzaliving.api.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stanzaliving.api.dto.FAQResponseDTO;
import com.stanzaliving.api.model.FAQ;
import com.stanzaliving.api.service.FAQService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class FAQUtil {

	@Autowired
	private FAQService faqService;

	public Comparator<FAQResponseDTO> compareBySequenceId() {
		return (FAQResponseDTO dto1, FAQResponseDTO dto2) -> dto1.getSequenceId().compareTo(dto2.getSequenceId());
	}

	public FAQResponseDTO convertFAQEntityToFAQResponseDto(FAQ faq) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		return modelMapper.map(faq, FAQResponseDTO.class);
	}

	public List<FAQResponseDTO> getSortedFAQResponseDtoList(List<FAQ> faqs) {
		List<FAQResponseDTO> faqResponseDTOs =
				faqs
						.stream()
						.map(faq -> convertFAQEntityToFAQResponseDto(faq))
						.collect(Collectors.toList());
		Collections.sort(faqResponseDTOs, compareBySequenceId());
		return faqResponseDTOs;
	}

	public FAQ getSubDisease(Integer faqId) {
		FAQ faq = faqService.findById(faqId);
		return Objects.isNull(faq) ? null : faq;
	}

}
