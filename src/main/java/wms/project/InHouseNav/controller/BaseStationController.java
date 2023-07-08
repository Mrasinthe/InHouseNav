package wms.project.InHouseNav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import wms.project.InHouseNav.dto.reportdto;
import wms.project.InHouseNav.service.BaseStationService;

@Slf4j
@RestController
@RequestMapping("/api")
public class BaseStationController {

    @Autowired
    private BaseStationService baseStationService;

    @PostMapping("/location")
    public ResponseEntity<reportdto> receiveReports(@RequestBody reportdto reportDTO) {
        if (!baseStationService.existById(reportDTO.getBaseStationId())) {
            log.error("BaseStation with UUID: " + reportDTO.getBaseStationId() + " dont exist.");
        } else {
            baseStationService.processReports(reportDTO);
        }
        return ResponseEntity.ok(reportDTO);

    }

}
