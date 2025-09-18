package eci.edu.co.jpa_ws_arep.models.types;

import lombok.Getter;

@Getter
public abstract class ErrorCustomType extends Exception {
    private final ServerCustomResponse serverCustomResponse;
    public ErrorCustomType(String message, ServerCustomResponse serverCustomResponse) {
        super(message);
        this.serverCustomResponse = serverCustomResponse;
    }
}
