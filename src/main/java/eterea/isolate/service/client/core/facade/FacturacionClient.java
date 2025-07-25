package eterea.isolate.service.client.core.facade;

import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import eterea.isolate.service.model.dto.DatoUnaFacturaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core-service", contextId = "facturacionClient", path = "/api/core/facturacion")
public interface FacturacionClient {

    @PostMapping("/registrarTransaccionFacturaFaltante")
    ClienteMovimientoDto registrarTransaccionFacturaFaltante(@RequestBody DatoUnaFacturaDto datoUnaFactura);

}
