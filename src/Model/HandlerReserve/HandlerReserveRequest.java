package Model.HandlerReserve;

import lombok.Data;

import java.util.List;
@Data
public class HandlerReserveRequest {
    private String performId;
    private String roundId;
    private String zoneId;
    private String screenLabel;
    private SeatTo seatTo;
    private List<String> shirtTo;

    public HandlerReserveRequest(String performId, String roundId, String zoneId,
                                 String screenLabel, SeatTo seatTo, List<String> shirtTo) {
        this.performId = performId;
        this.roundId = roundId;
        this.zoneId = zoneId;
        this.screenLabel = screenLabel;
        this.seatTo = seatTo;
        this.shirtTo = shirtTo;

    }
}
