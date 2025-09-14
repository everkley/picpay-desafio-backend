package org.evercley.picpaydesafiobackend.dtos;

public class AuthorizeRequestDTO {
    private Long payerId;
    private Double value;

    public Long getPayerId() {
        return payerId;
    }

    public void setPayerId(Long payerId) {
        this.payerId = payerId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
