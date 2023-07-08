package wms.project.InHouseNav.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import wms.project.InHouseNav.dto.locationdto;
import wms.project.InHouseNav.model.mobileStation;
import wms.project.InHouseNav.repository.mobileStationRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MobileStationService {

    @Autowired
    private mobileStationRepository msRepository;

    public locationdto getMobileStationLocation(UUID mobileStationId) {
        Optional<mobileStation> optionalMobileStation = msRepository.findById(mobileStationId);
        log.info("optionalMobileStation : {}", optionalMobileStation);
        locationdto locationDto = new locationdto();

        if (optionalMobileStation.isPresent()) {

            mobileStation mobileStation = optionalMobileStation.get();
            locationDto.setMobileId(mobileStation.getId());
            locationDto.setX(mobileStation.getLastKnownX());
            locationDto.setY(mobileStation.getLastKnownY());
            // locationDto.setErrorRadius(0.0f);
            // locationDto.setErrorCode(0);
            // locationDto.setErrorDescription("");
            return locationDto;

        } else {
            locationDto.setMobileId(mobileStationId);
            locationDto.setX(0.0f);
            locationDto.setY(0.0f);
            locationDto.setErrorRadius(0.0f);
            locationDto.setErrorCode(404);
            locationDto.setErrorDescription("Mobile station not found");
            return locationDto;
        }
    }

}
