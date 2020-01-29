/**
 * @author nipunaggarwal
 *
 */
package com.vcare.api.util;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vcare.api.dto.AskAQueryRequestDTO;
import com.vcare.api.dto.AskAQueryResponseDTO;
import com.vcare.api.model.AskAQuery;
import com.vcare.api.model.FAQ;
import com.vcare.api.service.AskAQueryService;

/**
 * @author nipunaggarwal
 *
 */
@Component
public class AskAQueryUtil {

	@Autowired
	private AskAQueryService askAQueryService;

	@Autowired
	private FAQUtil faqUtil;

	public List<AskAQueryResponseDTO> getAskAQueryResponseDTOs(String mobileNumber, String email) {
		List<AskAQuery> askedQueries = askAQueryService.findQueriesForMobileAndEmail(mobileNumber, email);
		List<AskAQueryResponseDTO> askAQueryResponseDTOs =
				askedQueries
						.stream()
						.map(askedQuery -> convertAskAQueryEntityToAskAQueryResponseDto(askedQuery))
						.collect(Collectors.toList());
		return askAQueryResponseDTOs;
	}

	public AskAQuery saveAskAQueryObject(AskAQueryRequestDTO askAQueryRequestDTO) {
		AskAQuery askAQuery = null;
		FAQ faq = faqUtil.getFaqForIdOrQuestion(askAQueryRequestDTO.getFaqId(), askAQueryRequestDTO.getQuestion());
		if (Objects.nonNull(faq)) {
			askAQuery = convertAndSaveAskAQueryEntity(askAQueryRequestDTO, faq);
		}
		return askAQuery;
	}

	public AskAQuery convertAndSaveAskAQueryEntity(AskAQueryRequestDTO askAQueryRequestDTO, FAQ faq) {
		AskAQuery askAQuery = convertAskAQueryRequestDTOToEntity(askAQueryRequestDTO);
		askAQuery.setFaq(faq);
		askAQueryService.save(askAQuery);
		return askAQuery;
	}

	public AskAQuery convertAskAQueryRequestDTOToEntity(AskAQueryRequestDTO askAQueryRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		AskAQuery askAQuery = modelMapper.map(askAQueryRequestDTO, AskAQuery.class);
		askAQuery.setAskAQueryId(0);
		return askAQuery;
	}

	public AskAQueryResponseDTO convertAskAQueryEntityToAskAQueryResponseDto(AskAQuery askAQuery) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		AskAQueryResponseDTO askAQueryResponseDTO = modelMapper.map(askAQuery, AskAQueryResponseDTO.class);
		askAQueryResponseDTO.setFaqResponseDTO(faqUtil.convertFAQEntityToFAQResponseDto(askAQuery.getFaq()));
		return askAQueryResponseDTO;
	}
}
