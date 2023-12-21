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
}
