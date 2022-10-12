package com.kh.member.model.vo;

import java.sql.Date;

public class Member {
    private int userNo;
    private String userId;
    private String password;
    private String userName;
    private String phone;
    private String email;
    private String address;
    private String interest;
    private Date enrollDate;
    private Date modifyDate;
    private String status;
    public Member(){
        super();
    }

    public Member(int userNo, String userId, String password, String userName, String phone, String email,String address, String interest, Date enrollDate, Date modifyDate, String status) {
        this.userNo = userNo;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.address=address;
        this.interest = interest;

        this.enrollDate = enrollDate;
        this.modifyDate = modifyDate;
        this.status = status;
    }

    public Member(String userId, String password, String userName, String phone, String email,String address, String interest) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.interest = interest;
        this.address=address;
    }

    public Member(String userId, String userName, String phone, String email,String address, String interest) {
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.email = email;
        this.interest = interest;
        this.address=address;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "userNo=" + userNo +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", interest='" + interest + '\'' +
                ", enrollDate=" + enrollDate +
                ", modifyDate=" + modifyDate +
                ", status='" + status + '\'' +
                '}';
    }
}
