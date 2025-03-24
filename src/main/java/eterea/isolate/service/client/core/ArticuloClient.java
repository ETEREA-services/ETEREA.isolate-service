package eterea.isolate.service.client.core;

import eterea.isolate.service.model.dto.ArticuloDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "core-service/api/core/articulo")
public interface ArticuloClient {

    @GetMapping("/{articuloId}")
    ArticuloDto findByArticuloId(@PathVariable String articuloId);

}
