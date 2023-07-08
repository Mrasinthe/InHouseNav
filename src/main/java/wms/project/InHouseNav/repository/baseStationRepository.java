package wms.project.InHouseNav.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import wms.project.InHouseNav.model.baseStation;

public interface baseStationRepository extends JpaRepository<baseStation, UUID> {
    boolean existsById(UUID id);

    Optional<baseStation> findById(UUID id);

}
