package com.stanzaliving.api.Gateway;

import java.io.IOException;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sendgrid.Attachments;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Personalization;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service("emailUtil")
public class EmailUtil {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailUtil.class);

	public Mail buildDynamicTemplate(EmailModel emailModel) throws IOException {
		Mail mail = new Mail();

		Email fromEmail = new Email();
		fromEmail.setName(emailModel.getFromName());
		fromEmail.setEmail(emailModel.getFromEmail());
		mail.setFrom(fromEmail);

		mail.setTemplateId(emailModel.getTemplateId());

		// Here goes the Dynamic Content
		Personalization personalization = new Personalization();

		// personalization.addDynamicTemplateData("content",
		// emailModel.getContent());
		// personalization.addDynamicTemplateData("subject",
		// emailModel.getSubject());

		if (emailModel.getRecipients() != null) {
			emailModel.getRecipients().stream().filter(rec -> rec != null).forEach(recipient -> {
				personalization.addTo(new Email(recipient));
			});
		}

		if (emailModel.getCcEmail() != null) {
			emailModel.getCcEmail().stream().filter(cc -> cc != null).forEach(ccEmail -> {
				personalization.addCc(new Email(ccEmail));
			});
		}

		if (emailModel.getBccEmail() != null) {
			emailModel.getBccEmail().stream().filter(bcc -> bcc != null).forEach(bccEmail -> {
				personalization.addBcc(new Email(bccEmail));
			});
		}

		mail.addPersonalization(personalization);

		logger.info("Sending email to: {}", emailModel.getRecipients());

		if (emailModel.getAttachmentContent() != null) {
			Attachments attachments = new Attachments();
			attachments.setContent(emailModel.getAttachmentContent());
			attachments.setType(emailModel.getAttachmentType());
			attachments.setFilename(emailModel.getAttachmentFilename());
			attachments.setDisposition(emailModel.getAttachmentDisposition());
			attachments.setContentId(emailModel.getAttachmentContentId());

			mail.addAttachments(attachments);
		}

		return mail;

	}

	public void send(EmailModel emailModel) {
		SendGrid sg = new SendGrid("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");
		sg.addRequestHeader("X-Mock", "true");

		Request request = new Request();

		try {
			Mail dynamicTemplate = buildDynamicTemplate(emailModel);
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(dynamicTemplate.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			logger.info(ex.getMessage());
		}
	}

}
