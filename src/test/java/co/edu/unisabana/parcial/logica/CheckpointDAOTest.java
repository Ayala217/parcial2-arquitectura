package co.edu.unisabana.parcial.logica;

import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.service.CheckpointDAO;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckpointDAOTest {

    @Mock
    private CheckpointRepository checkpointRepository;

    @InjectMocks
    private CheckpointDAO checkpointDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCheckin_shouldCallRepositorySave() {
        Checkin checkin = new Checkin("Facility1", "Driver1", 15);

        checkpointDAO.saveCheckin(checkin);

        verify(checkpointRepository, times(1)).save(any(Checkpoint.class));
    }

    @Test
    void saveCheckout_shouldCallRepositorySave() {
        Checkout checkout = new Checkout("Facility1", "Driver1", 20);

        checkpointDAO.saveCheckout(checkout);

        verify(checkpointRepository, times(1)).save(any(Checkpoint.class));
    }



    @Test
    void findLastCheckin_shouldReturnNullWhenNotExists() {
        when(checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse("Driver", "Facility"))
                .thenReturn(Optional.empty());

        Checkin result = checkpointDAO.findLastCheckin("Driver", "Facility");

        assertNull(result);
        verify(checkpointRepository, times(1))
                .findFirstByDriverAndFacilityAndFinalizedIsFalse("Driver", "Facility");
    }

    @Test
    void finishCheckin_shouldSetFinalizedAndSave() {
        Checkin checkin = new Checkin("Facility", "Driver", 15);
        Checkpoint checkpoint = new Checkpoint();
        when(checkpointRepository.findById(checkin.getId())).thenReturn(Optional.of(checkpoint));

        checkpointDAO.finishCheckin(checkin);

        assertTrue(checkpoint.isFinalized());
        verify(checkpointRepository, times(1)).save(checkpoint);
    }

    @Test
    void finishCheckin_shouldThrowExceptionWhenNotFound() {
        Checkin checkin = new Checkin("Facility", "Driver", 15);
        when(checkpointRepository.findById(checkin.getId())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> checkpointDAO.finishCheckin(checkin));
    }
}
