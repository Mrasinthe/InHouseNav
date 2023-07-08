package wms.project.InHouseNav;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import wms.project.InHouseNav.dto.locationdto;
import wms.project.InHouseNav.model.mobileStation;
import wms.project.InHouseNav.repository.mobileStationRepository;
import wms.project.InHouseNav.service.MobileStationService;

public class MobileControllerUnitTest {

    @Mock
    private mobileStationRepository msRepository;

    @InjectMocks
    private MobileStationService mobileStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMobileStationLocation_ExistingMobileStation() {

        UUID mobileStationId = UUID.randomUUID();
        mobileStation mobileStation = new mobileStation();
        mobileStation.setId(mobileStationId);
        mobileStation.setLastKnownX(1.0f);
        mobileStation.setLastKnownY(2.0f);
        Optional<mobileStation> optionalMobileStation = Optional.of(mobileStation);
        when(msRepository.findById(mobileStationId)).thenReturn(optionalMobileStation);

        locationdto result = mobileStationService.getMobileStationLocation(mobileStationId);

        assertEquals(mobileStationId, result.getMobileId());
        assertEquals(1.0f, result.getX());
        assertEquals(2.0f, result.getY());
        assertEquals(0.0f, result.getErrorRadius());
        assertEquals(0, result.getErrorCode());
        assertEquals(null, result.getErrorDescription());
        verify(msRepository, times(1)).findById(mobileStationId);
    }

    @Test
    void testGetMobileStationLocation_NonExistingMobileStation() {
        // Arrange
        UUID mobileStationId = UUID.randomUUID();
        Optional<mobileStation> optionalMobileStation = Optional.empty();
        when(msRepository.findById(mobileStationId)).thenReturn(optionalMobileStation);

        // Act
        locationdto result = mobileStationService.getMobileStationLocation(mobileStationId);

        // Assert
        assertEquals(mobileStationId, result.getMobileId());
        assertEquals(0.0f, result.getX());
        assertEquals(0.0f, result.getY());
        assertEquals(0.0f, result.getErrorRadius());
        assertEquals(404, result.getErrorCode());
        assertEquals("Mobile station not found", result.getErrorDescription());
        verify(msRepository, times(1)).findById(mobileStationId);
    }

}
