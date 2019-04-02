package com.stanzaliving.api.kafka;

import com.stanzaliving.api.Gateway.IEmailModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KafkaEmailModel implements IEmailModel {

    private String fromName;

    private String fromEmail;

    private String subject;

    private List<String> recipients;

    private String content;

    private String contentType;

    private HashMap<String, String> properties;

    private String templateId;

    private List<String> ccName;

    private List<String> ccEmail;

    private String attachmentContent;

    private String attachmentType;

    private String attachmentFilename;

    private String attachmentDisposition;

    private String attachmentContentId;

    private List<String> bccEmail;

    public String getSubject() {
        return subject;
    }

    public void addRecipients(String recipient) {
        if (this.recipients == null) {
            this.recipients = new ArrayList<>();
        }
        this.recipients.add(recipient);
    }

    public void addCcRecipientEmail(String ccEmail) {
        if (this.ccEmail == null) {
            this.ccEmail = new ArrayList<>();
        }
        this.ccEmail.add(ccEmail);
    }

    public void addBccRecipientEmail(String bccEmail) {
        if (this.bccEmail == null) {
            this.bccEmail = new ArrayList<>();
        }
        this.bccEmail.add(bccEmail);
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<String> getCcName() {
        return ccName;
    }

    public void setCcName(List<String> ccName) {
        this.ccName = ccName;
    }

    public List<String> getCcEmail() {
        return ccEmail;
    }

    public void setCcEmail(List<String> ccEmail) {
        this.ccEmail = ccEmail;
    }

    public String getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(String attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentFilename() {
        return attachmentFilename;
    }

    public void setAttachmentFilename(String attachmentFilename) {
        this.attachmentFilename = attachmentFilename;
    }

    public String getAttachmentDisposition() {
        return attachmentDisposition;
    }

    public void setAttachmentDisposition(String attachmentDisposition) {
        this.attachmentDisposition = attachmentDisposition;
    }

    public String getAttachmentContentId() {
        return attachmentContentId;
    }

    public void setAttachmentContentId(String attachmentContentId) {
        this.attachmentContentId = attachmentContentId;
    }

    public List<String> getBccEmail() {
        return bccEmail;
    }

    public void setBccEmail(List<String> bccEmail) {
        this.bccEmail = bccEmail;
    }

    public KafkaEmailModel() {
    }
}
