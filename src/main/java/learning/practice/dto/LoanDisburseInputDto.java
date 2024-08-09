package learning.practice.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoanDisburseInputDto implements Serializable {

    ///
    private long officeId;
    private String branchCode;
    private String projectCode;
    private String memberNo;
    private String areaCode;
    private String paymentPartner; // - SCB, HSBC, Rocket
    private long customerReference;
    private long memberId;
    private String memberBankName; //varchar(50)
    private String memberBankAccNo; //varchar(16)
    private String memberBankRouting; //varchar(16)
    private String memberBankBranchName; //varchar(50)
    private String memberName; //varchar(22)
    private String memberEmail; //optional varchar(50)
    private Double amount;
    private String paymentDate;
    private String bufferId; //varchar(50)
    ///

    public LoanDisburseInputDto() {
        officeId = 709;
        paymentPartner = "SCB";
        customerReference = 1234567890123456L;
        memberId = 111111;
        memberBankAccNo = "123456789435345";
        memberBankRouting = "123456789435345";
        memberBankBranchName = "123456789435345";
        memberName = "123456789435345";
        memberEmail = "info@sdfsdf.com";
        amount = 100.00;
        paymentDate = LocalDate.now().toString();
        bufferId = UUID.randomUUID().toString();
    }

    public static List<LoanDisburseInputDto> getList(int size) {
        List<LoanDisburseInputDto> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new LoanDisburseInputDto());
        }
        return list;
    }

    public long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(long officeId) {
        this.officeId = officeId;
    }

    public String getPaymentPartner() {
        return paymentPartner;
    }

    public void setPaymentPartner(String paymentPartner) {
        this.paymentPartner = paymentPartner;
    }

    public long getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(long customerReference) {
        this.customerReference = customerReference;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getMemberBankAccNo() {
        return memberBankAccNo;
    }

    public void setMemberBankAccNo(String memberBankAccNo) {
        this.memberBankAccNo = memberBankAccNo;
    }

    public String getMemberBankRouting() {
        return memberBankRouting;
    }

    public void setMemberBankRouting(String memberBankRouting) {
        this.memberBankRouting = memberBankRouting;
    }

    public String getMemberBankBranchName() {
        return memberBankBranchName;
    }

    public void setMemberBankBranchName(String memberBankBranchName) {
        this.memberBankBranchName = memberBankBranchName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBufferId() {
        return bufferId;
    }

    public void setBufferId(String bufferId) {
        this.bufferId = bufferId;
    }
}