package co.edu.unisabana.parcial.logica;


import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import co.edu.unisabana.parcial.service.port.CheckpointPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CheckpointServiceTest {

    @Mock
    CheckpointPort checkpointPort;

    @InjectMocks
    CheckpointService checkpointService;



    @Test
    void GivenDayOfMonthGreaterThan30_WhenCheckin_ThenThrowIllegalArgumentExeption(){

        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",31);
        Assertions.assertThrows(IllegalArgumentException.class,() -> checkpointService.checkin(checkpointDTO));

    }
    @Test
    void GivenDayOfMonthLowerThan1_WhenCheckin_ThenThrowIllegalArgumentExeption(){

        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",0);
        Assertions.assertThrows(IllegalArgumentException.class,() -> checkpointService.checkin(checkpointDTO));

    }
    @Test
    void GivenDayOfMonthBetween1And30_WhenCheckin_ThenSaveCheckin(){

        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",15);
        verify(checkpointPort,times(1)).saveCheckin(any(Checkin.class));
    }

    @Test
    void GivenNullLastCheckin_WhenCheckout_ThenThrowIllegalArgumentExeption(){

        Checkin lastCheckin = null;
        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",15);
        Assertions.assertThrows(IllegalArgumentException.class,() -> checkpointService.checkout(checkpointDTO));

    }
    @Test
    void GivenDayOfMonthGreaterThan30_WhenCheckout_ThenThrowIllegalArgumentExeption(){

        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",31);
        Assertions.assertThrows(IllegalArgumentException.class,() -> checkpointService.checkout(checkpointDTO));
    }
    @Test
    void GivenDayOfMonthLowerThan1_WhenCheckout_ThenThrowIllegalArgumentExeption(){

        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",0);
        Assertions.assertThrows(IllegalArgumentException.class,() -> checkpointService.checkout(checkpointDTO));
    }

    @Test
    void GivenDayOfMonthBetween1And30AndValidLastCheckin_WhenCheckout_ThenSaveCheckout(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",15);
        verify(checkpointPort,times(1)).saveCheckout(any(Checkout.class));
    }

}
