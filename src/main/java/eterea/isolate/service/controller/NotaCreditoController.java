package eterea.isolate.service.controller;

import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import eterea.isolate.service.service.NotaCreditoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/isolate/nota-credito")
public class NotaCreditoController {

    private final NotaCreditoService service;

    public NotaCreditoController(NotaCreditoService service) {
        this.service = service;
    }

    @GetMapping("/from/factura/{letraComprobante}/{puntoVenta}/{numeroComprobante}/nota/credito/{comprobanteId}")
    public ResponseEntity<ClienteMovimientoDto> generateNotaCredito(
            @PathVariable String letraComprobante,
            @PathVariable Integer puntoVenta,
            @PathVariable Long numeroComprobante,
            @PathVariable Integer comprobanteId
    ) {
        return ResponseEntity.ok(service.generateNotaCredito(letraComprobante, puntoVenta, numeroComprobante, comprobanteId));
    }

}
