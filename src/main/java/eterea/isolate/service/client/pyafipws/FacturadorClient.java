package eterea.isolate.service.client.pyafipws;

import eterea.isolate.service.model.dto.FacturaResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "pyafipws-service/api/afipws")
public interface FacturadorClient {

    @GetMapping("/consulta_comprobante")
    FacturaResponseDto consultaComprobante(@RequestParam("tipo_cbte") Integer tipoCbte,
                                            @RequestParam("punto_vta") Integer puntoVta,
                                            @RequestParam("cbte_nro") Long cbteNro);
}
