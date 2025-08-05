package eterea.isolate.service.client.core;

import eterea.isolate.service.model.dto.ComprobanteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "core-service", contextId = "comprobanteClient", path = "/api/core/comprobante")
public interface ComprobanteClient {

    @GetMapping("/{comprobanteId}")
    ComprobanteDto findByComprobanteId(@PathVariable Integer comprobanteId);

}
