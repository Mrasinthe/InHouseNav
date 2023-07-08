package wms.project.InHouseNav.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import wms.project.InHouseNav.dto.reportdto;
import wms.project.InHouseNav.dto.reportitemdto;
import wms.project.InHouseNav.model.baseStation;
import wms.project.InHouseNav.model.detectionReport;
import wms.project.InHouseNav.repository.baseStationRepository;
import wms.project.InHouseNav.repository.detectionReportRepository;

@Slf4j
@Service
public class BaseStationService {

    @Autowired
    private baseStationRepository bsRepository;

    @Autowired
    private detectionReportRepository dReportRepository;

    public void processReports(reportdto reportDTO) {
        List<detectionReport> dReport = new ArrayList<>();
        reportDTO.getReports()
                .forEach(reports -> ReportData(reportDTO, dReport, reports));
        dReportRepository.saveAll(dReport);
    }

    public boolean existById(UUID baseStationId) {
        return bsRepository.existsById(baseStationId);
    }

    private void ReportData(reportdto reportDTO, List<detectionReport> dreports,
            reportitemdto ritemdto) {

        Optional<baseStation> bs = bsRepository.findById(reportDTO.getBaseStationId());

        if (bs.isPresent()) {
            if (ritemdto.getDistance() < bs.get().getDetectionRadiusInMeters()) {
                detectionReport reports = getReportDetails(reportDTO, ritemdto);
                dreports.add(reports);
            } else {
                log.error("Mobile station with id: " + ritemdto.getMobileStationId()
                        + " is far from the base Station radius");
            }
        } else {
            log.error("BaseStation with ID: " + reportDTO.getBaseStationId() + " not found");
        }

    }

    private detectionReport getReportDetails(reportdto reportDTO, reportitemdto ritemdto) {
        return new detectionReport(reportDTO.getBaseStationId(), ritemdto.getMobileStationId(),
                ritemdto.getDistance(), ritemdto.getTimestamp());
    }

}
