package Model.CheckBooking;

import lombok.Data;

@Data
public class CheckBookingResponse {
    private String success;
    private String code;
    private String message;
    private BookingData data;
}
