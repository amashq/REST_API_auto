package models.pet;

import java.util.Objects;

public class ResponseError {
    private int code;
    private String type;
    private String message;

    public final void setCode(final int code) {
        this.code = code;
    }

    public final int getCode() {
        return code;
    }

    public final void setType(final String type) {
        this.type = type;
    }

    public final String getType() {
        return type;
    }

    public final void setMessage(final String message) {
        this.message = message;
    }

    public final String getMessage() {
        return message;
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseError that = (ResponseError) o;
        return code == that.code && Objects.equals(type, that.type) && Objects.equals(message, that.message);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(code, type, message);
    }
}
