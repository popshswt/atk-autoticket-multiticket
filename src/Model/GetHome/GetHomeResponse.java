package Model.GetHome;

import lombok.Data;

import java.util.List;

@Data
public class GetHomeResponse {
    private String success;
    private String code;
    private String message;
    private List<HomeData> data;
}
