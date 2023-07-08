package wms.project.InHouseNav.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detection_reporttable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class detectionReport {

    @Id
    @Column(name = "report_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;
    @Column(name = "base_station_uuid")
    private UUID baseStationUuid;
    @Column(name = "mobile_station_uuid")
    private UUID mobileStationUuid;
    @Column(name = "distance")
    private float distance;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public detectionReport(UUID baseStationUuid, UUID mobileStationUuid, float distance, LocalDateTime timestamp) {
        this.baseStationUuid = baseStationUuid;
        this.mobileStationUuid = mobileStationUuid;
        this.distance = distance;
        this.timestamp = timestamp;
    }

}
