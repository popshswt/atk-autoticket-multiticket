package Model.GetSeat;

import lombok.Data;

import java.util.List;

@Data
public class SeatData {
    private String zone_type;
    private List<SeatAvailable> seats_available;
}
