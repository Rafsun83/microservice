package com.example.self_management.model.domain;

import com.example.self_management.enums.wallets.WalletType;

import java.math.BigDecimal;
import java.util.Date;


public class Wallet {
    private Long id;
    private Long userId;
    private WalletType type;
    private BigDecimal totalAmount;
    private String category;
    private Date createdAt;
    private Date updatedAt;

    private String note;
    private String walletName;


    public Wallet() {}

    public Wallet(Long id, WalletType type, BigDecimal totalAmount, String category, Long userId, String note, String walletName) {
        this.id = id;
        this.type = type;
        this.totalAmount = totalAmount;
        this.category = category;
        this.userId = userId;
        this.note = note;
        this.walletName = walletName;
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public WalletType getType(){
        return type;
    }
    public void setType(WalletType type){
        this.type = type;
    }
    public BigDecimal getTotalAmount(){
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount){
        this.totalAmount = totalAmount;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }
    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
    public Date getUpdatedAt(){
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt){
        this.updatedAt = updatedAt;
    }

    public Long getUserId(){
        return userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public String getWalletName(){
        return walletName;
    }
    public void setWalletName(String walletName){
        this.walletName = walletName;
    }
}
