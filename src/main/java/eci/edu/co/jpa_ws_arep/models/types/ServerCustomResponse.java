package eci.edu.co.jpa_ws_arep.models.types;

import lombok.Getter;

@Getter
public enum ServerCustomResponse {
    NOT_FOUND(404, "Resource not found"),
    CONFLICT(409, "Conflict occurred"),
    BAD_REQUEST(400, "Bad request"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    FORBIDDEN(403, "Forbidden access"),
    UNAUTHORIZED(401, "Unauthorized access"),
    CREATED(201, "Resource created successfully"),
    OK(200, "Request successful")
    ;

    private final Integer code;
    private final String responseMessage;

    ServerCustomResponse(Integer i, String s) {
        code = i;
        responseMessage = s;
    }
}
