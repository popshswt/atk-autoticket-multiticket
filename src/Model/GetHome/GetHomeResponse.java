package Model.GetHome;
import lombok;

@Data
public class GetHomeResponse {
    private String success;
    private String code;
    private String message
    private HomeData data;
}
