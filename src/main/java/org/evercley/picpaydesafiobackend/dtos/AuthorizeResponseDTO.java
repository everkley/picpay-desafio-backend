package org.evercley.picpaydesafiobackend.dtos;

public class AuthorizeResponseDTO {
    private Boolean authorized;
    private String message;

    public Boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
