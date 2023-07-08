package wms.project.InHouseNav.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class reportdto {
    private UUID baseStationId;
    private List<reportitemdto> reports;

}
