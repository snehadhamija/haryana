package com.stanzaliving.api.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.stanzaliving.api.dao.LuggageOtpDetailDao;
import com.stanzaliving.api.model.LuggageOtpDetail;
import com.stanzaliving.api.model.LuggageTransactionDetail;

@Service("luggageOtpDetailService")
@Transactional
public class LuggageOtpDetailServiceImpl implements LuggageOtpDetailService {

	@Autowired
	private LuggageOtpDetailDao dao;

	@Override
	public void save(LuggageOtpDetail luggageOtpDetail) {
		dao.save(luggageOtpDetail);
	}

	@Override
	public LuggageOtpDetail saveLuggageOtpDetail(String base64Otp, Date validTill, String sentTo, String sentBy) {
		LuggageOtpDetail luggageOtpDetail = new LuggageOtpDetail();
		luggageOtpDetail.setOtp(base64Otp);
		luggageOtpDetail.setValidTill(validTill);
		luggageOtpDetail.setSentTo(sentTo);
		luggageOtpDetail.setSentBy(sentBy);
		dao.save(luggageOtpDetail);
		return luggageOtpDetail;
	}

	@Override
	public LuggageOtpDetail saveOrUpdateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail,
			List<LuggageTransactionDetail> luggageTransactionDetails) {
		Set<LuggageTransactionDetail> luggageTransactionDetailsSet = new HashSet<>(luggageTransactionDetails);
		luggageOtpDetail.setLuggageTransactionDetails(luggageTransactionDetailsSet);
		dao.save(luggageOtpDetail);
		return luggageOtpDetail;
	}

	@Override
	public LuggageOtpDetail validateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail) {
		luggageOtpDetail.setIsValidated(true);
		dao.saveOrUpdateLuggageOtpDetail(luggageOtpDetail);
		return luggageOtpDetail;
	}

	@Override
	public LuggageOtpDetail updateLuggageOtpDetail(LuggageOtpDetail luggageOtpDetail, String base64Otp,
			Date validTill) {
		luggageOtpDetail.setOtp(base64Otp);
		luggageOtpDetail.setValidTill(validTill);
		dao.saveOrUpdateLuggageOtpDetail(luggageOtpDetail);
		return luggageOtpDetail;
	}

	@Override
	public LuggageOtpDetail findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<LuggageOtpDetail> findAllLuggageOtpDetails() {
		return dao.findAllLuggageOtpDetails();
	}

	@Override
	public LuggageOtpDetail checkIfOtpAlreadySent(String sentToUser, String sentByUser, Boolean isValidated) {
		return dao.checkIfOtpAlreadySent(sentToUser, sentByUser, isValidated);
	}

	@Override
	public boolean checkIfOtpExpired(Integer luggageOtpDetailId) {
		LuggageOtpDetail luggageOtpDetail = dao.findById(luggageOtpDetailId);
		Date now = new Date();
		if (now.after(luggageOtpDetail.getValidTill())) {
			return true;
		}
		return false;
	}

	@Override
	public LuggageOtpDetail validateOtp(String sentTo, String otp, Integer luggageOtpDetailId) {
		String base64Otp = new String(Base64.encodeBase64(otp.getBytes()));
		return dao.validateOtp(sentTo, base64Otp, luggageOtpDetailId);
	}
}
