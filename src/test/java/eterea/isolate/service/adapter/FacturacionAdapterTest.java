package eterea.isolate.service.adapter;

import eterea.isolate.service.model.dto.FacturaResponseDto;
import eterea.isolate.service.model.dto.core.FacturacionDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FacturacionAdapterTest {

    @Test
    void toFacturacionDto_nullInput_returnsNull() {
        assertNull(FacturacionAdapter.toFacturacionDto(null));
    }

    @Test
    void toFacturacionDto_facturaNull_returnsNull() {
        FacturaResponseDto dto = new FacturaResponseDto();
        assertNull(FacturacionAdapter.toFacturacionDto(dto));
    }

    @Test
    void toFacturacionDto_mapsFieldsCorrectly() {
        FacturaResponseDto.FacturaDto factura = new FacturaResponseDto.FacturaDto();
        factura.setTipoDoc(80);
        factura.setNroDoc(20123456789L);
        factura.setTipoCbte(1);
        factura.setPuntoVta(2);
        factura.setImpTotal(new BigDecimal("100.50"));
        factura.setImpOpEx(new BigDecimal("0.00"));
        factura.setImpNeto(new BigDecimal("82.00"));
        factura.setImpIva(new BigDecimal("18.50"));
        factura.setResultado("A");
        factura.setCae("1234567890");
        factura.setCbtDesde(123L);
        factura.setFchVencCae(LocalDate.of(2025, 7, 15).atStartOfDay().toLocalDate());
        factura.setFechaCbte(LocalDate.of(2025, 7, 14).atStartOfDay().toLocalDate());

        FacturaResponseDto response = new FacturaResponseDto();
        response.setFactura(factura);

        FacturacionDto result = FacturacionAdapter.toFacturacionDto(response);
        assertNotNull(result);
        assertEquals("20123456789", result.getDocumento());
        assertEquals(1, result.getTipoAfip());
        assertEquals(2, result.getPuntoVenta());
        assertEquals(new BigDecimal("100.50"), result.getTotal());
        assertEquals("A", result.getResultado());
        assertEquals("1234567890", result.getCae());
        assertEquals(123L, result.getNumeroComprobante());
        assertEquals("2025-07-15", result.getVencimientoCae());
        assertEquals("2025-07-14", result.getFechaComprobante());
    }
}
