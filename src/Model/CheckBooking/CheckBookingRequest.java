package Model.CheckBooking;

import lombok.Data;

@Data
public class CheckBookingRequest {
    private String uuid;

    public CheckBookingRequest(String uuid) {
        this.uuid = uuid;
    }
}
