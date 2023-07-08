package wms.project.InHouseNav.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class locationdto {

    private UUID mobileId;
    private float x;
    private float y;
    private float errorRadius;
    private int errorCode;
    private String errorDescription;

}
