package wms.project.InHouseNav.model;

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
@Table(name = "base_stationtable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class baseStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "base_station_uuid")
    private UUID id;
    @Column(name = "base_station_name")
    private String name;
    @Column(name = "coordinate_x")
    private float x;
    @Column(name = "coordinate_y")
    private float y;
    @Column(name = "detectionradiusinmeters")
    private float detectionRadiusInMeters;

}
