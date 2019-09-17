package com.ecommerce.product;


import com.ecommerce.shared.exception.ErrorCode;

public enum ProductErrorCode implements ErrorCode {
    PRODUCT_NOT_FOUND(404, "没有找到产品"),
    CATEGORY_NOT_FOUND(404, "没有找到产品目录");
    private int status;
    private String message;

    ProductErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return this.name();
    }
}
