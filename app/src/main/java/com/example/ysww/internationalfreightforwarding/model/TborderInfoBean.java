package com.example.ysww.internationalfreightforwarding.model;

import java.util.List;

/**
 * 获取订单详情接口
 */

public class TborderInfoBean extends BaseBean{

    /**
     * tbOrder : {"totalNumber":8,"cleanCustomsType":"正式报关","orderId":"18060516293966031972","contractNo":"45454","orderStatus":"1","packageType":"包装","volumeSize":"5","manufacturer":"图","payType":"预付","quote":8,"deliveryAddress":"收货地址","tariffPayType":"收件人付","billsUrl":"magazine-unlock-03-2.3.1002-_9D89D458A0BBE371CE554463B5C5C819.jpg","departureDate":"2018-06-05 00:00:00.0","brand":"货品名称","exemptionType":"痛","export":"出口口薄","createDate":1528187380000,"companyCode":"3","sourceAddress":"提货","deliveryType":"自送渠道仓库","postcode":"455444","priceValue":"8","approvalNumber":"痛","destinationCountry":"中国","transDemand":"门到门","destinationCity":"北京","licenseKey":"6","marketUserId":36,"orderDetailList":[{"number":8,"createBy":"刘先生01","orderId":"18060516293966031972","length":9,"width":82,"weight":8,"id":37,"createDate":1528187380000,"height":2}],"createBy":"刘先生01","forwardingUnit":"发货地哦哦","transportationTime":"8","transType":"空运","status":"1"}
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
         * totalNumber : 8
         * cleanCustomsType : 正式报关
         * orderId : 18060516293966031972
         * contractNo : 45454
         * orderStatus : 1
         * packageType : 包装
         * volumeSize : 5
         * manufacturer : 图
         * payType : 预付
         * quote : 8
         * deliveryAddress : 收货地址
         * tariffPayType : 收件人付
         * billsUrl : magazine-unlock-03-2.3.1002-_9D89D458A0BBE371CE554463B5C5C819.jpg
         * departureDate : 2018-06-05 00:00:00.0
         * brand : 货品名称
         * exemptionType : 痛
         * export : 出口口薄
         * createDate : 1528187380000
         * companyCode : 3
         * sourceAddress : 提货
         * deliveryType : 自送渠道仓库
         * postcode : 455444
         * priceValue : 8
         * approvalNumber : 痛
         * destinationCountry : 中国
         * transDemand : 门到门
         * destinationCity : 北京
         * licenseKey : 6
         * marketUserId : 36
         * orderDetailList : [{"number":8,"createBy":"刘先生01","orderId":"18060516293966031972","length":9,"width":82,"weight":8,"id":37,"createDate":1528187380000,"height":2}]
         * createBy : 刘先生01
         * forwardingUnit : 发货地哦哦
         * transportationTime : 8
         * transType : 空运
         * status : 1
         */

        private int totalNumber;
        private String cleanCustomsType;
        private String orderId;
        private String contractNo;
        private String orderStatus;
        private String packageType;
        private String volumeSize;
        private String manufacturer;
        private String payType;
        private int quote;
        private String deliveryAddress;
        private String tariffPayType;
        private String billsUrl;
        private String departureDate;
        private String brand;
        private String exemptionType;
        private String export;
        private long createDate;
        private String companyCode;
        private String sourceAddress;
        private String deliveryType;
        private String postcode;
        private String priceValue;
        private String approvalNumber;
        private String destinationCountry;
        private String transDemand;
        private String destinationCity;
        private String licenseKey;
        private int marketUserId;
        private String createBy;
        private String forwardingUnit;
        private String transportationTime;
        private String transType;
        private String status;
        private List<OrderDetailListBean> orderDetailList;
        private String companyName;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getTotalNumber() {
            return totalNumber;
        }

        public void setTotalNumber(int totalNumber) {
            this.totalNumber = totalNumber;
        }

        public String getCleanCustomsType() {
            return cleanCustomsType;
        }

        public void setCleanCustomsType(String cleanCustomsType) {
            this.cleanCustomsType = cleanCustomsType;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getContractNo() {
            return contractNo;
        }

        public void setContractNo(String contractNo) {
            this.contractNo = contractNo;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getPackageType() {
            return packageType;
        }

        public void setPackageType(String packageType) {
            this.packageType = packageType;
        }

        public String getVolumeSize() {
            return volumeSize;
        }

        public void setVolumeSize(String volumeSize) {
            this.volumeSize = volumeSize;
        }

        public String getManufacturer() {
            return manufacturer;
        }

        public void setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public int getQuote() {
            return quote;
        }

        public void setQuote(int quote) {
            this.quote = quote;
        }

        public String getDeliveryAddress() {
            return deliveryAddress;
        }

        public void setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
        }

        public String getTariffPayType() {
            return tariffPayType;
        }

        public void setTariffPayType(String tariffPayType) {
            this.tariffPayType = tariffPayType;
        }

        public String getBillsUrl() {
            return billsUrl;
        }

        public void setBillsUrl(String billsUrl) {
            this.billsUrl = billsUrl;
        }

        public String getDepartureDate() {
            return departureDate;
        }

        public void setDepartureDate(String departureDate) {
            this.departureDate = departureDate;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getExemptionType() {
            return exemptionType;
        }

        public void setExemptionType(String exemptionType) {
            this.exemptionType = exemptionType;
        }

        public String getExport() {
            return export;
        }

        public void setExport(String export) {
            this.export = export;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getSourceAddress() {
            return sourceAddress;
        }

        public void setSourceAddress(String sourceAddress) {
            this.sourceAddress = sourceAddress;
        }

        public String getDeliveryType() {
            return deliveryType;
        }

        public void setDeliveryType(String deliveryType) {
            this.deliveryType = deliveryType;
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

        public String getApprovalNumber() {
            return approvalNumber;
        }

        public void setApprovalNumber(String approvalNumber) {
            this.approvalNumber = approvalNumber;
        }

        public String getDestinationCountry() {
            return destinationCountry;
        }

        public void setDestinationCountry(String destinationCountry) {
            this.destinationCountry = destinationCountry;
        }

        public String getTransDemand() {
            return transDemand;
        }

        public void setTransDemand(String transDemand) {
            this.transDemand = transDemand;
        }

        public String getDestinationCity() {
            return destinationCity;
        }

        public void setDestinationCity(String destinationCity) {
            this.destinationCity = destinationCity;
        }

        public String getLicenseKey() {
            return licenseKey;
        }

        public void setLicenseKey(String licenseKey) {
            this.licenseKey = licenseKey;
        }

        public int getMarketUserId() {
            return marketUserId;
        }

        public void setMarketUserId(int marketUserId) {
            this.marketUserId = marketUserId;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getForwardingUnit() {
            return forwardingUnit;
        }

        public void setForwardingUnit(String forwardingUnit) {
            this.forwardingUnit = forwardingUnit;
        }

        public String getTransportationTime() {
            return transportationTime;
        }

        public void setTransportationTime(String transportationTime) {
            this.transportationTime = transportationTime;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<OrderDetailListBean> getOrderDetailList() {
            return orderDetailList;
        }

        public void setOrderDetailList(List<OrderDetailListBean> orderDetailList) {
            this.orderDetailList = orderDetailList;
        }

        public static class OrderDetailListBean {
            /**
             * number : 8
             * createBy : 刘先生01
             * orderId : 18060516293966031972
             * length : 9
             * width : 82
             * weight : 8
             * id : 37
             * createDate : 1528187380000
             * height : 2
             */

            private int number;
            private String createBy;
            private String orderId;
            private int length;
            private int width;
            private int weight;
            private int id;
            private long createDate;
            private int height;

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public String getOrderId() {
                return orderId;
            }

            public void setOrderId(String orderId) {
                this.orderId = orderId;
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

            public int getWeight() {
                return weight;
            }

            public void setWeight(int weight) {
                this.weight = weight;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }
        }
    }
}
