package eci.edu.co.jpa_ws_arep.errors;

import eci.edu.co.jpa_ws_arep.models.types.ErrorCustomType;
import eci.edu.co.jpa_ws_arep.models.types.ServerCustomResponse;

public class ErrorCustomRepository extends ErrorCustomType {
    public ErrorCustomRepository(String message, ServerCustomResponse serverCustomResponse) {
        super(message, serverCustomResponse);
    }
}
