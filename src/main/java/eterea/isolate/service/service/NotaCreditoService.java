package eterea.isolate.service.service;

import eterea.isolate.service.client.core.ClienteMovimientoClient;
import eterea.isolate.service.client.core.ComprobanteClient;
import eterea.isolate.service.client.core.facade.FacturacionClient;
import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotaCreditoService {

    private final ClienteMovimientoClient clienteMovimientoClient;
    private final ComprobanteClient comprobanteClient;
    private final FacturacionClient facturacionClient;

    public NotaCreditoService(ClienteMovimientoClient clienteMovimientoClient, ComprobanteClient comprobanteClient, FacturacionClient facturacionClient) {
        this.clienteMovimientoClient = clienteMovimientoClient;
        this.comprobanteClient = comprobanteClient;
        this.facturacionClient = facturacionClient;
    }

    public ClienteMovimientoDto generateNotaCredito(
            String letraComprobante,
            Integer puntoVenta,
            Long numeroComprobante,
            Integer comprobanteId
    ) {
        log.debug("Processing NotaCreditoService.generateNotaCredito");
        var clienteMovimiento = clienteMovimientoClient.findByFactura(
                letraComprobante,
                (byte) 1,
                puntoVenta,
                numeroComprobante
        );
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());
        var comprobante = comprobanteClient.findByComprobanteId(comprobanteId);
        log.debug("Comprobante -> {}", comprobante.jsonify());
        return facturacionClient.registroNotaCreditoFromFactura(clienteMovimiento.getClienteMovimientoId(), comprobanteId);
    }

}
