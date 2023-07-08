package wms.project.InHouseNav;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import wms.project.InHouseNav.dto.locationdto;
import wms.project.InHouseNav.dto.reportdto;
import wms.project.InHouseNav.dto.reportitemdto;
import wms.project.InHouseNav.model.baseStation;
import wms.project.InHouseNav.repository.baseStationRepository;
import wms.project.InHouseNav.repository.detectionReportRepository;
import wms.project.InHouseNav.service.BaseStationService;

public class BaseControllerUnitTest {

    @Mock
    private baseStationRepository bsRepository;

    @Mock
    private detectionReportRepository dReportRepository;

    @InjectMocks
    private BaseStationService baseStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_ProcessReports() {
        UUID baseStationId = UUID.randomUUID();

        baseStation baseStation = new baseStation();
        baseStation.setId(baseStationId);

        when(bsRepository.existsById(baseStationId)).thenReturn(true);
        when(bsRepository.findById(baseStationId)).thenReturn(Optional.of(baseStation));

        reportdto reportDTO = new reportdto();
        reportitemdto reportItem = new reportitemdto();
        reportItem.setMobileStationId(UUID.randomUUID());
        reportItem.setDistance(50.0f);
        reportItem.setTimestamp(LocalDateTime.now());
        reportDTO.setBaseStationId(baseStationId);
        reportDTO.setReports(new ArrayList<>());
        reportDTO.getReports().add(reportItem);

        baseStationService.processReports(reportDTO);

        verify(dReportRepository, times(1)).saveAll(reportDTO.getReports());
    }

    @Test
    void testProcessReports_BaseStationNotFound() {
        UUID baseStationId = UUID.randomUUID();

        when(bsRepository.existsById(baseStationId)).thenReturn(false);

        reportdto reportDTO = new reportdto();
        reportitemdto reportItem = new reportitemdto();
        reportItem.setMobileStationId(UUID.randomUUID());
        reportItem.setDistance(50.0f);
        reportItem.setTimestamp(LocalDateTime.now());
        reportDTO.setBaseStationId(baseStationId);
        reportDTO.setReports(new ArrayList<>());
        reportDTO.getReports().add(reportItem);

        baseStationService.processReports(reportDTO);

        verify(dReportRepository, never()).saveAll(Mockito.anyList());

    }

    @Test
    void test_ExistById_BaseStationExists() {

        UUID baseStationId = UUID.randomUUID();
        when(bsRepository.existsById(baseStationId)).thenReturn(true);

        boolean result = baseStationService.existById(baseStationId);

        assertTrue(result);
    }

    @Test
    void test_ExistById_BaseStationDoesNotExist() {
        UUID baseStationId = UUID.randomUUID();
        when(bsRepository.existsById(baseStationId)).thenReturn(false);

        boolean result = baseStationService.existById(baseStationId);

        assertFalse(result);
    }
}
