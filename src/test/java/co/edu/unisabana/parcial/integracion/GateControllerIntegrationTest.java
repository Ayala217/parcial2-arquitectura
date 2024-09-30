package co.edu.unisabana.parcial.integracion;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.controller.dto.ResponseGate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
public class GateControllerIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void IntegrationTest() {

        CheckpointDTO checkpointDTO = new CheckpointDTO("facility","driver",15);
        ResponseEntity<ResponseGate> respuestaInsercion = testRestTemplate.postForEntity("/checkpoint/checkin",checkpointDTO,ResponseGate.class);
        System.out.println(respuestaInsercion);
        Assertions.assertEquals(true,respuestaInsercion.getStatusCode().is2xxSuccessful());

        ResponseEntity<ResponseGate> resutado= testRestTemplate.postForEntity("/checkpoint/checkout",checkpointDTO,ResponseGate.class);
        System.out.println(respuestaInsercion);
        Assertions.assertEquals(true,resutado.getStatusCode().is2xxSuccessful());


    }


}
