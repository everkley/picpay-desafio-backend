package org.evercley.picpaydesafiobackend.dtos;

public class AuthorizeResponseDTO {
    private String status;
    private AuthorizationDataDTO data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AuthorizationDataDTO getData() {
        return data;
    }

    public void setData(AuthorizationDataDTO data) {
        this.data = data;
    }
}
