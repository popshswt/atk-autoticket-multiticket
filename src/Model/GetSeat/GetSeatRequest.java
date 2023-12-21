package Model.GetSeat;

import lombok.Data;

@Data
public class GetSeatRequest {
    private String performId;
    private String roundId;
    private String zoneId;

    public GetSeatRequest(String performId, String roundId, String zoneId) {
        this.performId = performId;
        this.roundId = roundId;
        this.zoneId = zoneId;
    }
}
