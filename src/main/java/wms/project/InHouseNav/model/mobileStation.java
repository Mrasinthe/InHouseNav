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
@Table(name = "mobileStationtable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class mobileStation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mobile_station_uuid")
    private UUID id;
    @Column(name = " last_known_x")
    private float lastKnownX;
    @Column(name = "last_known_y")
    private float lastKnownY;

}
