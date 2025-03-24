package eterea.isolate.service.controller;

import eterea.isolate.service.service.CompletarFaltantesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/isolate/completarFaltantes")
public class CompletarFaltantesController {

    private final CompletarFaltantesService service;

    public CompletarFaltantesController(CompletarFaltantesService service) {
        this.service = service;
    }

    @GetMapping("/complete/{letraComprobante}/{puntoVenta}/{numeroComprobanteDesde}/{numeroComprobanteHasta}")
    public ResponseEntity<String> completarFaltantes(
            @PathVariable String letraComprobante,
            @PathVariable Integer puntoVenta,
            @PathVariable Long numeroComprobanteDesde,
            @PathVariable Long numeroComprobanteHasta) {
        return new ResponseEntity<>(service.completarFaltantes(letraComprobante, puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta), HttpStatus.OK);
    }

}
