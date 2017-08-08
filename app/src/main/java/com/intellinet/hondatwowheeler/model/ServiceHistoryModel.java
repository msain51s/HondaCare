package com.intellinet.hondatwowheeler.model;

import com.intellinet.hondatwowheeler.R;

import tellh.com.stickyheaderview_rv.adapter.DataBean;
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter;

/**
 * Created by Administrator on 8/2/2017.
 */

public class ServiceHistoryModel extends DataBean {

    public int getBikeImageUrl() {
        return bikeImageUrl;
    }

    public void setBikeImageUrl(int bikeImageUrl) {
        this.bikeImageUrl = bikeImageUrl;
    }

    public String getBikeModelName() {
        return bikeModelName;
    }

    public void setBikeModelName(String bikeModelName) {
        this.bikeModelName = bikeModelName;
    }

    public String getBikeRegisterationNo() {
        return bikeRegisterationNo;
    }

    public void setBikeRegisterationNo(String bikeRegisterationNo) {
        this.bikeRegisterationNo = bikeRegisterationNo;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerContactNo() {
        return dealerContactNo;
    }

    public void setDealerContactNo(String dealerContactNo) {
        this.dealerContactNo = dealerContactNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public int bikeImageUrl;
    public String bikeModelName;
    public String bikeRegisterationNo;

    public String dealerName;

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String dealerAddress;
    public String dealerContactNo;
    public String emailAddress;
    public String bookingDate;
    public String serviceType;
    public String problemDescription;
    private boolean shouldSticky;
    @Override
    public int getItemLayoutId(StickyHeaderViewAdapter adapter) {
        return R.layout.adapter_service_history_list_item;
    }
    public void setShouldSticky(boolean shouldSticky) {
        this.shouldSticky = shouldSticky;
    }
    // Decide whether the item view should be suspended on the top.
    @Override
    public boolean shouldSticky() {
        return shouldSticky;
    }
}

