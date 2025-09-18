package eci.edu.co.jpa_ws_arep.errors;

import eci.edu.co.jpa_ws_arep.models.types.ErrorCustomType;
import eci.edu.co.jpa_ws_arep.models.types.ServerCustomResponse;

public class ErrorCustomController extends ErrorCustomType {
    public static final ServerCustomResponse NOT_FOUND = ServerCustomResponse.NOT_FOUND;
    public static final ServerCustomResponse BAD_REQUEST = ServerCustomResponse.BAD_REQUEST;
    public static final ServerCustomResponse CONFLICT = ServerCustomResponse.CONFLICT;
    public static final ServerCustomResponse INTERNAL_SERVER_ERROR = ServerCustomResponse.INTERNAL_SERVER_ERROR;
    public static final ServerCustomResponse FORBIDDEN = ServerCustomResponse.FORBIDDEN;
    public static final ServerCustomResponse UNAUTHORIZED = ServerCustomResponse.UNAUTHORIZED;

    public ErrorCustomController(String message, ServerCustomResponse serverCustomResponse) {
        super(message, serverCustomResponse);
    }
}
