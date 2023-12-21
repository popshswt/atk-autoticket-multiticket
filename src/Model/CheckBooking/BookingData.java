package Model.CheckBooking;

import lombok.Data;

@Data
public class BookingData {
    private String code;
    private String reserveId;
    private String success;
    private String description;
}
