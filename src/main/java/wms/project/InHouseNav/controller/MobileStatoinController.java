package wms.project.InHouseNav.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wms.project.InHouseNav.dto.locationdto;
import wms.project.InHouseNav.service.MobileStationService;

@RestController
@RequestMapping("/api")
public class MobileStatoinController {

    @Autowired
    private MobileStationService mobileStationService;

    @GetMapping("/location/{mobileStationId}")
    public locationdto getMobileStationLocation(@PathVariable UUID mobileStationId) {
        return mobileStationService.getMobileStationLocation(mobileStationId);
    }

}
