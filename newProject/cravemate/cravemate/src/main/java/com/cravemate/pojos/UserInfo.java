package com.cravemate.pojos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, updatable = false, nullable = false)
    private Integer userid;

    @Column(name = "name", unique = false, updatable = true, nullable = false)
    private String name;

    @Column(name = "phone_number", unique = true, updatable = false, nullable = false)
    private String phonenumber;

    @Column(name = "secret_question", unique = false, updatable = false, nullable = false)
    private String secretquestion;

    @Column(name = "address", unique = false, updatable = false, nullable = false)
    private String address;

    @Column(name = "answer", unique = false, updatable = false, nullable = false)
    private String answer;

    @Column(name = "password", unique = false, updatable = true, nullable = false)
    private String password;

    @Column(name = "login_status", unique = false, updatable = true, nullable = false)
    private Boolean loginstatus;

    @Column(name = "role", unique = false, updatable = true, nullable = false)
    private Integer role = 1;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSecretquestion() {
        return secretquestion;
    }

    public void setSecretquestion(String secretquestion) {
        this.secretquestion = secretquestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoginstatus() {
        return loginstatus;
    }

    public void setLoginstatus(Boolean loginstatus) {
        this.loginstatus = loginstatus;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // @JsonManagedReference
    // @OneToMany(cascade = CascadeType.ALL, mappedBy = "userInfo")
    // private List<StudentSolvedQuiz> solvedQuizList = new
    // ArrayList<StudentSolvedQuiz>();

}
