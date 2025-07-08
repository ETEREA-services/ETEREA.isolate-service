package eterea.isolate.service.client.core.facade;

import eterea.isolate.service.model.dto.core.FacturacionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "core-service/api/core/transaccion-factura-programa-dia")
public interface TransaccionFacturaProgramaDiaClient {

    @PostMapping("/registro/{orderNumberId}/dry-run/{dryRun}")
    void registroTransaccionFacturaProgramaDia(@PathVariable Long orderNumberId, @PathVariable Boolean dryRun, @RequestBody FacturacionDto facturacionDto);

}
