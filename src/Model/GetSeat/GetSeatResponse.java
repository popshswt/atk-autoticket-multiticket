package Model.GetSeat;

import lombok.Data;

@Data
public class GetSeatResponse {
    private String success;
    private String code;
    private String message;
    private SeatData data;
}
