package eterea.isolate.service.client.web;

import eterea.isolate.service.model.dto.web.OrderNoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "import-service", contextId = "orderNoteClient", path = "/api/import/orderNote")
public interface OrderNoteClient {

    @GetMapping("/{orderNumberId}")
    OrderNoteDto findByOrderNumberId(@PathVariable Long orderNumberId);

    @GetMapping("/lastTwoDays")
    List<OrderNoteDto> findAllCompletedByLastTwoDays();

    @GetMapping("/documento/last/{numeroDocumento}")
    OrderNoteDto findLastByNumeroDocumento(@PathVariable Long numeroDocumento);

}
