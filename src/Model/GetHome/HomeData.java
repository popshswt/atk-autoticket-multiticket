package Model.GetHome;

import lombok.Data;

import java.util.List;
@Data
public class HomeData {
    private String type;
    private List<DataItem> items;
}
