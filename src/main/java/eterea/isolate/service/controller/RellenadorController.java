package eterea.isolate.service.controller;

import eterea.isolate.service.model.dto.FacturaResponseDto;
import eterea.isolate.service.service.RellenadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/isolate/rellenador")
public class RellenadorController {

    private final RellenadorService service;

    public RellenadorController(RellenadorService service) {
        this.service = service;
    }

    @GetMapping("/consulta/{tipoAfipId}/{puntoVenta}/{numeroComprobante}")
    public ResponseEntity<FacturaResponseDto> consultaComprobante(@PathVariable Integer tipoAfipId, @PathVariable Integer puntoVenta, @PathVariable Long numeroComprobante) {
        return ResponseEntity.ok(service.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante));
    }

    @GetMapping("/autoCompleta/{tipoAfipId}/{puntoVenta}/{numeroComprobante}/dry-run/{dryRun}")
    public ResponseEntity<Void> autoCompleta(@PathVariable Integer tipoAfipId, @PathVariable Integer puntoVenta, @PathVariable Long numeroComprobante, @PathVariable Boolean dryRun) {
        service.autoCompleta(tipoAfipId, puntoVenta, numeroComprobante, dryRun);
        return ResponseEntity.ok().build();
    }

}
