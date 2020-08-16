package coffeemachine.exceptions;

import lombok.Builder;
import lombok.Getter;

public class CoffeeMachineException extends RuntimeException {
    @Getter
    private final ResponseCode responseCode;

    public CoffeeMachineException() {
        super();
        responseCode = null;
    }

    @Builder
    public CoffeeMachineException(ResponseCode responseCode, String message, Throwable throwable) {
        super(message, throwable);
        this.responseCode = responseCode;
    }

    public static CoffeeMachineException create(String message, ResponseCode responseCode) {
        return CoffeeMachineException.builder()
                .responseCode(responseCode)
                .message(message)
                .build();
    }
}
