package Model.GetSeat;

import lombok.Data;

import java.util.List;

@Data
public class SeatAvailable {
    private String zoneLabel;
    private String zoneName;
    private String zoneSeatLabel;
    private String zoneId;
    private String screenLabel;
    private String priceAmt;
    private List<Seat> seat;
}
