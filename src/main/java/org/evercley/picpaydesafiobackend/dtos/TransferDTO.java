package org.evercley.picpaydesafiobackend.dtos;

public class TransferDTO {
    private Double value;
    private Long payer;
    private Long payee;


    public TransferDTO(Double value, Long payer, Long payee) {
        this.value = value;
        this.payer = payer;
        this.payee = payee;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getPayer() {
        return payer;
    }

    public void setPayer(Long payer) {
        this.payer = payer;
    }

    public Long getPayee() {
        return payee;
    }

    public void setPayee(Long payee) {
        this.payee = payee;
    }
}
