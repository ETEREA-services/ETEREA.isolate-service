package eterea.isolate.service.service;

import eterea.isolate.service.client.core.ClienteMovimientoClient;
import eterea.isolate.service.client.core.facade.TransaccionFacturaProgramaDiaClient;
import eterea.isolate.service.client.pyafipws.FacturadorClient;
import eterea.isolate.service.client.web.OrderNoteClient;
import eterea.isolate.service.model.dto.FacturaResponseDto;
import eterea.isolate.service.model.dto.web.OrderNoteDto;
import eterea.isolate.service.model.dto.web.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RellenadorServiceTest {

    @MockitoBean
    private FacturadorClient facturadorClient;
    @MockitoBean
    private ClienteMovimientoClient clienteMovimientoClient;
    @MockitoBean
    private OrderNoteClient orderNoteClient;
    @MockitoBean
    private TransaccionFacturaProgramaDiaClient transaccionFacturaProgramaDiaClient;

    @Autowired
    private RellenadorService rellenadorService;

    @Test
    void consultaComprobante_shouldCallClient() {
        // Given
        Integer tipoAfipId = 1;
        Integer puntoVenta = 1;
        Long numeroComprobante = 123L;
        when(facturadorClient.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante)).thenReturn(new FacturaResponseDto());

        // When
        rellenadorService.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);

        // Then
        verify(facturadorClient).consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
    }

    @Test
    void autoCompleta_happyPath() {
        // Given
        Integer tipoAfipId = 6;
        Integer puntoVenta = 5;
        Long numeroComprobante = 456L;
        Long nroDoc = 12345678L;
        BigDecimal amount = new BigDecimal("100.50");

        FacturaResponseDto facturaArca = new FacturaResponseDto();
        FacturaResponseDto.FacturaDto facturaDto = new FacturaResponseDto.FacturaDto();
        facturaDto.setNroDoc(nroDoc);
        facturaDto.setImpTotal(amount);
        facturaArca.setFactura(facturaDto);

        OrderNoteDto orderNote = new OrderNoteDto();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setMonto(amount);
        orderNote.setPayment(paymentDto);
        orderNote.setOrderNumberId(1L);

        when(facturadorClient.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante)).thenReturn(facturaArca);
        when(clienteMovimientoClient.findByComprobante(anyInt(), anyInt(), anyLong())).thenThrow(new RuntimeException("Not found"));
        when(orderNoteClient.findLastByNumeroDocumento(nroDoc)).thenReturn(orderNote);
        doNothing().when(transaccionFacturaProgramaDiaClient).registroTransaccionFacturaProgramaDia(anyLong(), anyBoolean(), anyBoolean(), any());

        // When
        rellenadorService.autoCompleta(tipoAfipId, puntoVenta, numeroComprobante, false, false);

        // Then
        verify(facturadorClient).consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
        verify(clienteMovimientoClient).findByComprobante(853, puntoVenta, numeroComprobante);
        verify(orderNoteClient).findLastByNumeroDocumento(nroDoc);
        verify(transaccionFacturaProgramaDiaClient).registroTransaccionFacturaProgramaDia(eq(1L), eq(false), eq(false), any(eterea.isolate.service.model.dto.core.FacturacionDto.class));
    }

    @Test
    void autoCompleta_shouldReturnWhenAmountsDiffer() {
        // Given
        Integer tipoAfipId = 6;
        Integer puntoVenta = 5;
        Long numeroComprobante = 456L;
        Long nroDoc = 12345678L;

        FacturaResponseDto facturaArca = new FacturaResponseDto();
        FacturaResponseDto.FacturaDto facturaDto = new FacturaResponseDto.FacturaDto();
        facturaDto.setNroDoc(nroDoc);
        facturaDto.setImpTotal(new BigDecimal("100.50"));
        facturaArca.setFactura(facturaDto);

        OrderNoteDto orderNote = new OrderNoteDto();
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setMonto(new BigDecimal("99.99")); // Different amount
        orderNote.setPayment(paymentDto);

        when(facturadorClient.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante)).thenReturn(facturaArca);
        when(clienteMovimientoClient.findByComprobante(anyInt(), anyInt(), anyLong())).thenThrow(new RuntimeException("Not found"));
        when(orderNoteClient.findLastByNumeroDocumento(nroDoc)).thenReturn(orderNote);

        // When
        rellenadorService.autoCompleta(tipoAfipId, puntoVenta, numeroComprobante, false, false);

        // Then
        verify(facturadorClient).consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
        verify(clienteMovimientoClient).findByComprobante(853, puntoVenta, numeroComprobante);
        verify(orderNoteClient).findLastByNumeroDocumento(nroDoc);
        verifyNoInteractions(transaccionFacturaProgramaDiaClient);
    }
}
