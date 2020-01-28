package com.vcare.api.kafka;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vcare.api.Gateway.ILeadsModel;

@Entity
@Table(name = "LEADS")
public class KafkaLeadsModel implements ILeadsModel{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private String gender;

    @Column(name = "message")
    private String message;

    @Column(name = "location")
    private String location;

    @Column(name = "education")
    private String education;

    @Column(name = "gclid")
    private String gclid;
            
    @Column(name = "source")
    private String source;
            
    @Column(name = "medium")
    private String medium;
            
    @Column(name = "campaign")
    private String campaign;
    
    @Column(name = "term")
    private String term;
    
    @Column(name = "content")
    private String content;
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGclid() {
        return gclid;
    }

    public void setGclid(String gclid) {
        this.gclid = gclid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getCampaign() {
        return campaign;
    }

    public void setCampaign(String campaign) {
        this.campaign = campaign;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Leads(String name, String email, String mobile, String gender, String message, String location, String education, String gclid, String source, String medium, String campaign, String term, String content) {
//        this.name = name;
//        this.email = email;
//        this.mobile = mobile;
//        this.gender = gender;
//        this.message = message;
//        this.location = location;
//        this.education = education;
//        this.gclid = gclid;
//        this.source = source;
//        this.medium = medium;
//        this.campaign = campaign;
//        this.term = term;
//        this.content = content;
//    }

   

    @Override public String toString() {
    return "Lead(" + name + ", " + email + ")";
  }
}
