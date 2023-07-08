package wms.project.InHouseNav.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import wms.project.InHouseNav.model.mobileStation;

public interface mobileStationRepository extends JpaRepository<mobileStation, UUID> {

}
