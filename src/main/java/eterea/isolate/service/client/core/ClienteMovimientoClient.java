package eterea.isolate.service.client.core;

import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "core-service/api/core/clienteMovimiento")
public interface ClienteMovimientoClient {

    @GetMapping("/rango/facturas/{letraComprobante}/{debita}/{puntoVenta}/{numeroComprobanteDesde}/{numeroComprobanteHasta}")
    List<ClienteMovimientoDto> findAllFacturasByRango(@PathVariable String letraComprobante,
                                                      @PathVariable Byte debita,
                                                      @PathVariable Integer puntoVenta,
                                                      @PathVariable Long numeroComprobanteDesde,
                                                      @PathVariable Long numeroComprobanteHasta);

    @GetMapping("/consulta/comprobante/{comprobanteId}/{puntoVenta}/{numeroComprobante}")
    ClienteMovimientoDto findByComprobante(@PathVariable Integer comprobanteId, @PathVariable Integer puntoVenta, @PathVariable Long numeroComprobante);

}
