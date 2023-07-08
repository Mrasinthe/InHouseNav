package wms.project.InHouseNav.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class reportitemdto {
    private UUID mobileStationId;
    private float distance;
    private LocalDateTime timestamp;

}
