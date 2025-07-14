package eterea.isolate.service.controller;

import eterea.isolate.service.model.dto.FacturaResponseDto;
import eterea.isolate.service.service.RellenadorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RellenadorController.class)
class RellenadorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RellenadorService service;

    @Test
    void consultaComprobante_shouldReturnFacturaResponse() throws Exception {
        // Given
        Integer tipoAfipId = 1;
        Integer puntoVenta = 2;
        Long numeroComprobante = 3L;
        Long nroDoc = 20123456789L;

        FacturaResponseDto responseDto = new FacturaResponseDto();
        FacturaResponseDto.FacturaDto facturaDto = new FacturaResponseDto.FacturaDto();
        facturaDto.setNroDoc(nroDoc);
        facturaDto.setImpTotal(new BigDecimal("121.00"));
        responseDto.setFactura(facturaDto);

        when(service.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante)).thenReturn(responseDto);

        // When & Then
        mockMvc.perform(get("/api/isolate/rellenador/consulta/{tipoAfipId}/{puntoVenta}/{numeroComprobante}",
                        tipoAfipId, puntoVenta, numeroComprobante)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.factura.nro_doc").value(nroDoc))
                .andExpect(jsonPath("$.factura.imp_total").value(121.00));

        verify(service).consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
    }

    @Test
    void autoCompleta_shouldReturnOk() throws Exception {
        // Given
        Integer tipoAfipId = 6;
        Integer puntoVenta = 5;
        Long numeroComprobante = 4L;
        Boolean soloFactura = false;
        Boolean dryRun = true;

        // When & Then
        mockMvc.perform(get("/api/isolate/rellenador/auto-completa/{tipoAfipId}/{puntoVenta}/{numeroComprobante}/solo-factura/{soloFactura}/dry-run/{dryRun}",
                        tipoAfipId, puntoVenta, numeroComprobante, soloFactura, dryRun))
                .andExpect(status().isOk());

        verify(service).autoCompleta(tipoAfipId, puntoVenta, numeroComprobante, soloFactura, dryRun);
    }
}
