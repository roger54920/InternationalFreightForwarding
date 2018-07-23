package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 添加新订单
 */

public class AddOrderBean {

    /**
     * transType : 空运
     * marketUserId : 36
     * payType : 到付
     * cleanCustomsType : 正式报关
     * destinationCountry : 中国
     * departureDate : 2018-05-06
     * number : 4
     * transportationTime : 4545
     * forwardingUnit : 4554
     * weight : 44
     * deliveryAddress : 455445
     * export : 54
     * size1 : 44
     * size2 : 44
     * size3 : 44
     * brand : 4545
     * quote : 454
     * volumeSize : 4554
     * destinationCity : 455
     * sourceAddress : 4554
     * postcode : 4554
     * priceValue : 455
     * transDemand : 门到门
     * tariffPayType :
     * deliveryType :
     * size : 44×44×44
     */

    private String transType;
    private int marketUserId;
    private String payType;
    private String cleanCustomsType;
    private String destinationCountry;
    private String departureDate;
    private String transportationTime;
    private String forwardingUnit;
    private String deliveryAddress;
    private String export;
    private String size1;
    private String size2;
    private String size3;
    private String brand;
    private double quote;
    private String volumeSize;
    private String destinationCity;
    private String sourceAddress;
    private String postcode;
    private String priceValue;
    private String transDemand;
    private String tariffPayType;
    private String deliveryType;
    private String size;
    private String packageType;
    private String contractNo;
    private String billsUrl;
    private String manufacturer;
    private String licenseKey;
    private String approvalNumber;
    private String exemptionType;
    private String totalNumber;
    private String selectionReceivingAddress;
    private String islnsurance;
    private String importExportPower;//进出口权
    private List<TbOrderFileEntity> fileList;//上传附件

    public static class TbOrderFileEntity{
        //图片上传名称
        private String name;
        //图片url
        private String url;
        //附件
        private String billsUrl;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBillsUrl() {
            return billsUrl;
        }

        public void setBillsUrl(String billsUrl) {
            this.billsUrl = billsUrl;
        }
    }

    public List<TbOrderFileEntity> getFileList() {
        return fileList;
    }

    public void setFileList(List<TbOrderFileEntity> fileList) {
        this.fileList = fileList;
    }

    public String getImportExportPower() {
        return importExportPower;
    }

    public void setImportExportPower(String importExportPower) {
        this.importExportPower = importExportPower;
    }

    public String getIslnsurance() {
        return islnsurance;
    }

    public void setIslnsurance(String islnsurance) {
        this.islnsurance = islnsurance;
    }

    public String getSelectionReceivingAddress() {
        return selectionReceivingAddress;
    }

    public void setSelectionReceivingAddress(String selectionReceivingAddress) {
        this.selectionReceivingAddress = selectionReceivingAddress;
    }

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public int getMarketUserId() {
        return marketUserId;
    }

    public void setMarketUserId(int marketUserId) {
        this.marketUserId = marketUserId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getExemptionType() {
        return exemptionType;
    }

    public void setExemptionType(String exemptionType) {
        this.exemptionType = exemptionType;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getBillsUrl() {
        return billsUrl;
    }

    public void setBillsUrl(String billsUrl) {
        this.billsUrl = billsUrl;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCleanCustomsType() {
        return cleanCustomsType;
    }

    public void setCleanCustomsType(String cleanCustomsType) {
        this.cleanCustomsType = cleanCustomsType;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    public String getTransportationTime() {
        return transportationTime;
    }

    public void setTransportationTime(String transportationTime) {
        this.transportationTime = transportationTime;
    }

    public String getForwardingUnit() {
        return forwardingUnit;
    }

    public void setForwardingUnit(String forwardingUnit) {
        this.forwardingUnit = forwardingUnit;
    }


    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getExport() {
        return export;
    }

    public void setExport(String export) {
        this.export = export;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public String getSize3() {
        return size3;
    }

    public void setSize3(String size3) {
        this.size3 = size3;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public String getVolumeSize() {
        return volumeSize;
    }

    public void setVolumeSize(String volumeSize) {
        this.volumeSize = volumeSize;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(String priceValue) {
        this.priceValue = priceValue;
    }

    public String getTransDemand() {
        return transDemand;
    }

    public void setTransDemand(String transDemand) {
        this.transDemand = transDemand;
    }

    public String getTariffPayType() {
        return tariffPayType;
    }

    public void setTariffPayType(String tariffPayType) {
        this.tariffPayType = tariffPayType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    private List<DetailsOfGoodsBean> orderDetailList;

    public List<DetailsOfGoodsBean> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<DetailsOfGoodsBean> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
