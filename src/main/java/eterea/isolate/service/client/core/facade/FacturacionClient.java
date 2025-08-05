package eterea.isolate.service.client.core.facade;

import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import eterea.isolate.service.model.dto.DatoUnaFacturaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core-service", contextId = "facturacionClient", path = "/api/core/facturacion")
public interface FacturacionClient {

    @PostMapping("/registrarTransaccionFacturaFaltante")
    ClienteMovimientoDto registrarTransaccionFacturaFaltante(@RequestBody DatoUnaFacturaDto datoUnaFactura);

    @GetMapping("/registro/from/factura/{clienteMovimientoId}/nota/credito/{comprobanteId}")
    ClienteMovimientoDto registroNotaCreditoFromFactura(
            @PathVariable Long clienteMovimientoId,
            @PathVariable Integer comprobanteId
    );

}
