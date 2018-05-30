package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 获取订单详情接口
 */

public class TborderInfoBean extends BaseBean{

    /**
     * tbOrder : {"orderId":"18050620383973661793","marketUserId":36,"marketUserName":null,"transType":"空运","transDemand":"门到门","payType":"到付","cleanCustomsType":"正式报关","sourceAddress":"4554","totalNumber":4,"export":"54","destinationCountry":"中国","departureDate":"2018-05-06 00:00:00.0","departureDateStr":null,"destinationCity":"455","brand":"4545","quote":454,"postcode":"4554","priceValue":"455","transportationTime":"4545","deliveryAddress":"455445","forwardingUnit":"4554","orderDetailList":[{"id":1,"orderId":"18050620383973661793","number":1,"weight":455,"length":4554,"width":4545,"height":4545,"createDate":"2018-05-14 23:35:32","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:03"},{"id":7,"orderId":"18050620383973661793","number":1,"weight":4566,"length":33,"width":322,"height":33,"createDate":"2018-05-14 23:56:23","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:07"},{"id":9,"orderId":"18050620383973661793","number":1,"weight":69,"length":65,"width":69,"height":665,"createDate":"2018-05-14 23:57:54","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:09"},{"id":10,"orderId":"18050620383973661793","number":1,"weight":69,"length":65,"width":69,"height":668,"createDate":"2018-05-14 23:58:20","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:11"}],"volumeSize":"4554","packageType":null,"manufacturer":null,"contractNo":null,"licenseKey":null,"approvalNumber":null,"exemptionType":null,"tariffPayType":"","deliveryType":"","emsNo":null,"postingCharges":null,"postingTime":null,"billsUrl":null,"createDate":"2018-05-06 20:38:40","createBy":"展示","updateDate":null,"lastUpdateBy":null,"status":"1","orderStatus":"10","companyCode":"3","companyName":null,"channelName":null,"channelUserName":null,"quoteId":null,"quoteNum":null,"isFuelRate":null,"fuelRate":null,"isTax":null,"remark":null,"workday":null,"fuelOilQuote":null,"channelQuote":null,"quoteStatus":null,"channelQuoteId":null}
     */

    private TbOrderBean tbOrder;

    public TbOrderBean getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(TbOrderBean tbOrder) {
        this.tbOrder = tbOrder;
    }

    public static class TbOrderBean {
        /**
         * orderId : 18050620383973661793
         * marketUserId : 36
         * marketUserName : null
         * transType : 空运
         * transDemand : 门到门
         * payType : 到付
         * cleanCustomsType : 正式报关
         * sourceAddress : 4554
         * totalNumber : 4
         * export : 54
         * destinationCountry : 中国
         * departureDate : 2018-05-06 00:00:00.0
         * departureDateStr : null
         * destinationCity : 455
         * brand : 4545
         * quote : 454
         * postcode : 4554
         * priceValue : 455
         * transportationTime : 4545
         * deliveryAddress : 455445
         * forwardingUnit : 4554
         * orderDetailList : [{"id":1,"orderId":"18050620383973661793","number":1,"weight":455,"length":4554,"width":4545,"height":4545,"createDate":"2018-05-14 23:35:32","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:03"},{"id":7,"orderId":"18050620383973661793","number":1,"weight":4566,"length":33,"width":322,"height":33,"createDate":"2018-05-14 23:56:23","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:07"},{"id":9,"orderId":"18050620383973661793","number":1,"weight":69,"length":65,"width":69,"height":665,"createDate":"2018-05-14 23:57:54","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:09"},{"id":10,"orderId":"18050620383973661793","number":1,"weight":69,"length":65,"width":69,"height":668,"createDate":"2018-05-14 23:58:20","createBy":"展示","updateBy":"展示","lastUpdateDate":"2018-05-15 00:00:11"}]
         * volumeSize : 4554
         * packageType : null
         * manufacturer : null
         * contractNo : null
         * licenseKey : null
         * approvalNumber : null
         * exemptionType : null
         * tariffPayType :
         * deliveryType :
         * emsNo : null
         * postingCharges : null
         * postingTime : null
         * billsUrl : null
         * createDate : 2018-05-06 20:38:40
         * createBy : 展示
         * updateDate : null
         * lastUpdateBy : null
         * status : 1
         * orderStatus : 10
         * companyCode : 3
         * companyName : null
         * channelName : null
         * channelUserName : null
         * quoteId : null
         * quoteNum : null
         * isFuelRate : null
         * fuelRate : null
         * isTax : null
         * remark : null
         * workday : null
         * fuelOilQuote : null
         * channelQuote : null
         * quoteStatus : null
         * channelQuoteId : null
         */

        private String orderId;
        private int marketUserId;
        private String marketUserName;
        private String transType;
        private String transDemand;
        private String payType;
        private String cleanCustomsType;
        private String sourceAddress;
        private int totalNumber;
        private String export;
        private String destinationCountry;
        private String departureDate;
        private String departureDateStr;
        private String destinationCity;
        private String brand;
        private int quote;
        private String postcode;
        private String priceValue;
        private String transportationTime;
        private String deliveryAddress;
        private String forwardingUnit;
        private String volumeSize;
        private String packageType;
        private String manufacturer;
        private String contractNo;
        private String licenseKey;
        private String approvalNumber;
        private String exemptionType;
        private String tariffPayType;
        private String deliveryType;
        private String emsNo;
        private String postingCharges;
        private String postingTime;
        private String billsUrl;
        private String createDate;
        private String createBy;
        private String updateDate;
        private String lastUpdateBy;
        private String status;
        private String orderStatus;
        private String companyCode;
        private String companyName;
        private String channelName;
        private String channelUserName;
        private String quoteId;
        private String quoteNum;
        private String isFuelRate;
        private String fuelRate;
        private String isTax;
        private String remark;
        private String workday;
        private String fuelOilQuote;
        private String channelQuote;
        private String quoteStatus;
        private String channelQuoteId;
        private List<OrderDetailListBean> orderDetailList;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getMarketUserId() {
            return marketUserId;
        }

        public void setMarketUserId(int marketUserId) {
            this.marketUserId = marketUserId;
        }

        public String getMarketUserName() {
            return marketUserName;
        }

        public void setMarketUserName(String marketUserName) {
            this.marketUserName = marketUserName;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public String getTransDemand() {
            return transDemand;
        }

        public void setTransDemand(String transDemand) {
            this.transDemand = transDemand;
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

        public String getSourceAddress() {
            return sourceAddress;
        }

        public void setSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
        }

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public String getExport() {
            return export;
        }

        public void setExport(String export) {
            this.export = export;
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

        public String getDepartureDateStr() {
            return departureDateStr;
        }

        public void setDepartureDateStr(String departureDateStr) {
            this.departureDateStr = departureDateStr;
        }

        public String getDestinationCity() {
            return destinationCity;
        }

        public void setDestinationCity(String destinationCity) {
            this.destinationCity = destinationCity;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getQuote() {
            return quote;
        }

        public void setQuote(int quote) {
            this.quote = quote;
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

        public String getTransportationTime() {
            return transportationTime;
        }

        public void setTransportationTime(String transportationTime) {
            this.transportationTime = transportationTime;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getForwardingUnit() {
            return forwardingUnit;
        }

        public void setForwardingUnit(String forwardingUnit) {
            this.forwardingUnit = forwardingUnit;
        }

        public String getVolumeSize() {
            return volumeSize;
        }

        public void setVolumeSize(String volumeSize) {
            this.volumeSize = volumeSize;
        }

        public String getPackageType() {
            return packageType;
        }

        public void setPackageType(String packageType) {
            this.packageType = packageType;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
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

        public String getEmsNo() {
            return emsNo;
        }

        public void setEmsNo(String emsNo) {
            this.emsNo = emsNo;
        }

        public String getPostingCharges() {
            return postingCharges;
        }

        public void setPostingCharges(String postingCharges) {
            this.postingCharges = postingCharges;
        }

        public String getPostingTime() {
            return postingTime;
        }

        public void setPostingTime(String postingTime) {
            this.postingTime = postingTime;
        }

        public String getBillsUrl() {
            return billsUrl;
        }

        public void setBillsUrl(String billsUrl) {
            this.billsUrl = billsUrl;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getLastUpdateBy() {
            return lastUpdateBy;
        }

        public void setLastUpdateBy(String lastUpdateBy) {
            this.lastUpdateBy = lastUpdateBy;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelUserName() {
            return channelUserName;
        }

        public void setChannelUserName(String channelUserName) {
            this.channelUserName = channelUserName;
        }

        public String getQuoteId() {
            return quoteId;
        }

        public void setQuoteId(String quoteId) {
            this.quoteId = quoteId;
        }

        public String getQuoteNum() {
            return quoteNum;
        }

        public void setQuoteNum(String quoteNum) {
            this.quoteNum = quoteNum;
        }

        public String getIsFuelRate() {
            return isFuelRate;
        }

        public void setIsFuelRate(String isFuelRate) {
            this.isFuelRate = isFuelRate;
        }

        public String getFuelRate() {
            return fuelRate;
        }

        public void setFuelRate(String fuelRate) {
            this.fuelRate = fuelRate;
        }

        public String getIsTax() {
            return isTax;
        }

        public void setIsTax(String isTax) {
            this.isTax = isTax;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getWorkday() {
            return workday;
        }

        public void setWorkday(String workday) {
            this.workday = workday;
        }

        public String getFuelOilQuote() {
            return fuelOilQuote;
        }

        public void setFuelOilQuote(String fuelOilQuote) {
            this.fuelOilQuote = fuelOilQuote;
        }

        public String getChannelQuote() {
            return channelQuote;
        }

        public void setChannelQuote(String channelQuote) {
            this.channelQuote = channelQuote;
        }

        public String getQuoteStatus() {
            return quoteStatus;
        }

        public void setQuoteStatus(String quoteStatus) {
            this.quoteStatus = quoteStatus;
        }

        public String getChannelQuoteId() {
            return channelQuoteId;
        }

        public void setChannelQuoteId(String channelQuoteId) {
            this.channelQuoteId = channelQuoteId;
        }

        public List<OrderDetailListBean> getOrderDetailList() {
            return orderDetailList;
        }

        public void setOrderDetailList(List<OrderDetailListBean> orderDetailList) {
            this.orderDetailList = orderDetailList;
        }

        public static class OrderDetailListBean {
            /**
             * id : 1
             * orderId : 18050620383973661793
             * number : 1
             * weight : 455
             * length : 4554
             * width : 4545
             * height : 4545
             * createDate : 2018-05-14 23:35:32
             * createBy : 展示
             * updateBy : 展示
             * lastUpdateDate : 2018-05-15 00:00:03
             */

            private int id;
            private String orderId;
            private int number;
            private int weight;
            private int length;
            private int width;
            private int height;
            private String createDate;
            private String createBy;
            private String updateBy;
            private String lastUpdateDate;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getLength() {
                return length;
            }

            public void setLength(int length) {
                this.length = length;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public String getLastUpdateDate() {
                return lastUpdateDate;
            }

            public void setLastUpdateDate(String lastUpdateDate) {
                this.lastUpdateDate = lastUpdateDate;
            }
        }
    }
}
