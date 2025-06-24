package models.lombok;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class DemoQaLoginResponseModel {
    private boolean success;

    public DemoQaLoginResponseModel() {}

    @JsonCreator
    public DemoQaLoginResponseModel(boolean success) {
        this.success = success;
    }

    @JsonValue
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
