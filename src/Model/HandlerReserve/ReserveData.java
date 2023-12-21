package Model.HandlerReserve;

import lombok.Data;

@Data
public class ReserveData {
    private String uuid;
    private String waitTime;
    private String timeOut;
    private String retry;
}
