package com.stanzaliving.api.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.stanzaliving.api.constants.Constants;
import com.stanzaliving.api.dto.LuggageTransactionStatusDto;
import com.stanzaliving.api.dto.UserDto;
import com.stanzaliving.api.model.LuggageTransactionDetail;
import com.stanzaliving.api.util.BaseUtil;

@Service("springRestClientService")
@Transactional
public class SpringRestClientServiceImpl implements SpringRestClientService {

	@Autowired
	LuggageComplaintService luggageComplaintService;

	// Fetching user details when call is made from any of the end-points
	// and finally mapping the response to userdto.class
	@Override
	public UserDto getUserDto(HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authCredentials = request.getHeader("Authorization");
		String currentUser = BaseUtil.getPrincipal();
		HttpEntity<String> req = new HttpEntity<String>(getHeaders(principal, authCredentials));
		ResponseEntity<UserDto> response = restTemplate.exchange(Constants.COREUSERFETCHURL + currentUser,
				HttpMethod.GET, req, UserDto.class);
		UserDto userDto = response.getBody();
		return userDto;
	}

	// Fetching user details for a particular user by mobileNumber(other than
	// the current user)
	// when call is made from any of the end-points
	// and finally mapping the response to userdto.class
	@Override
	public UserDto getUserDtoForOtherUser(HttpServletRequest request, String mobileNumber) {
		RestTemplate restTemplate = new RestTemplate();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authCredentials = request.getHeader("Authorization");
		HttpEntity<String> req = new HttpEntity<String>(getHeaders(principal, authCredentials));
		ResponseEntity<UserDto> response;
		try {
			response = restTemplate.exchange(Constants.COREUSERFETCHURL + mobileNumber, HttpMethod.GET, req,
					UserDto.class);
			UserDto userDto = response.getBody();
			return userDto;
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	// Fetching user details for a particular user by id(other than the current
	// user)
	// when call is made from any of the end-points
	// and finally mapping the response to userdto.class
	@Override
	public UserDto getUserDtoForOtherUserById(HttpServletRequest request, Integer userId) {
		RestTemplate restTemplate = new RestTemplate();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authCredentials = request.getHeader("Authorization");
		HttpEntity<String> req = new HttpEntity<String>(getHeaders(principal, authCredentials));
		ResponseEntity<UserDto> response = restTemplate.exchange(Constants.CORESERVERURL + "profile/" + userId,
				HttpMethod.GET, req, UserDto.class);
		UserDto userDto = response.getBody();
		return userDto;
	}

	// Fetching user details when call is made from customUserDetailsService
	// and finally mapping the response to userdto.class
	@Override
	public UserDto getUserDtoUsingDefaultHeaders(String mobileNumber) {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> req = new HttpEntity<String>(getDefaultHeaders());
		ResponseEntity<UserDto> response = restTemplate.exchange(Constants.COREUSERFETCHURL + mobileNumber,
				HttpMethod.GET, req, UserDto.class);
		UserDto userDto = response.getBody();
		return userDto;
	}

	// Fetching user details when call is made from any of the end-points
	// and finally mapping the response to ParameterizedTypeReference
	@Override
	public Map<Object, Object> getUserMap(HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authCredentials = request.getHeader("Authorization");
		String currentUser = BaseUtil.getPrincipal();
		HttpEntity<String> req = new HttpEntity<String>(getHeaders(principal, authCredentials));
		ParameterizedTypeReference<Map<Object, Object>> responseType = new ParameterizedTypeReference<Map<Object, Object>>() {
		};
		ResponseEntity<Map<Object, Object>> response = restTemplate.exchange(Constants.COREUSERFETCHURL + currentUser,
				HttpMethod.GET, req, responseType);
		return response.getBody();
	}

	// Using authCredentials to fetch user details from core server
	public HttpHeaders getHeaders(Object principal, String authCredentials) {
		String plainCredentials = decodeAuthorizationHeader(authCredentials);
		String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	// Using Default credentials just to fetch user details from core server
	public HttpHeaders getDefaultHeaders() {
		String plainCredentials = Constants.DEFAULTCREDENTIALS;
		String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		return headers;
	}

	public HttpHeaders getDefaultHeadersForPost() {
		String plainCredentials = Constants.DEFAULTCREDENTIALS;
		String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Credentials);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

	// Using Base64 to decode authCredentials
	public String decodeAuthorizationHeader(String authCredentials) {
		if (null == authCredentials) {
			return "Auth Credentials found null !";
		}
		final String encodedUserPassword = authCredentials.replaceFirst("Basic" + " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decodeBase64(encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		final String finalString = username + ":" + password;
		return finalString;
	}

	@Override
	public String postCampaignDataUsingDefaultHeaders(Integer complaintId, String rating) {
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, Object> map = new HashMap<>();
		map.put("complaintId", complaintId);
		map.put("rating", rating);
		HttpEntity<HashMap<String, Object>> req = new HttpEntity<HashMap<String, Object>>(map,
				getDefaultHeadersForPost());
		ResponseEntity<String> response = restTemplate.exchange(Constants.CORESERVERURL + "resolvedComplaintFeedback/",
				HttpMethod.POST, req, String.class);
		return response.getBody();
	}

	// Fetching all user details
	// and finally mapping the response to userdto.class
	@Override
	public List<HashMap<String, Object>> getAllUserDto(HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authCredentials = request.getHeader("Authorization");
		HttpEntity<String> req = new HttpEntity<String>(getHeaders(principal, authCredentials));
		ParameterizedTypeReference<List<HashMap<String, Object>>> responseType = new ParameterizedTypeReference<List<HashMap<String, Object>>>() {
		};
		ResponseEntity<List<HashMap<String, Object>>> response = restTemplate
				.exchange(Constants.CORESERVERURL + "user/list", HttpMethod.GET, req, responseType);
		return response.getBody();
	}

	@Override
	public Object createComplaintForMissingItems(LuggageTransactionStatusDto luggageTransactionStatusDto,
			List<LuggageTransactionDetail> luggageTransactionDetails) {
		ResponseEntity<String> response = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HashMap<String, Object> map = new HashMap<>();
			map = luggageComplaintService.createComplaintObject(luggageTransactionStatusDto, luggageTransactionDetails);
			HttpEntity<HashMap<String, Object>> req = new HttpEntity<HashMap<String, Object>>(map,
					getDefaultHeadersForPost());
			response = restTemplate.exchange(Constants.COMPLAINTPRODURL + "complaint/", HttpMethod.POST, req,
					String.class);
			System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (response != null && response.getBody() != null) {
			return response.getBody();
		}
		return null;
	}

}
