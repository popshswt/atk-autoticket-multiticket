package Model.HandlerReserve;

import lombok.Data;

import java.util.List;

@Data
public class SeatTo {
    private String seatType;
    private List<String> seats;

    public SeatTo(String seatType, List<String> seats) {
        this.seatType = seatType;
        this.seats = seats;
    }
}
