package main.api.Response;

import main.api.model.StatusPedido;

public class MessageResponse {

    private String message;
    private StatusPedido status;

    public MessageResponse(String message, StatusPedido status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public StatusPedido getStatus() {
        return status;
    }
}
