package eterea.isolate.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.isolate.service.adapter.FacturacionAdapter;
import eterea.isolate.service.client.core.ClienteMovimientoClient;
import eterea.isolate.service.client.core.facade.TransaccionFacturaProgramaDiaClient;
import eterea.isolate.service.client.pyafipws.FacturadorClient;
import eterea.isolate.service.client.web.OrderNoteClient;
import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import eterea.isolate.service.model.dto.FacturaResponseDto;
import eterea.isolate.service.model.dto.web.OrderNoteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RellenadorService {

    private final FacturadorClient facturadorClient;
    private final ClienteMovimientoClient clienteMovimientoClient;
    private final OrderNoteClient orderNoteClient;
    private final TransaccionFacturaProgramaDiaClient transaccionFacturaProgramaDiaClient;

    public RellenadorService(FacturadorClient facturadorClient, ClienteMovimientoClient clienteMovimientoClient, OrderNoteClient orderNoteClient, TransaccionFacturaProgramaDiaClient transaccionFacturaProgramaDiaClient) {
        this.facturadorClient = facturadorClient;
        this.clienteMovimientoClient = clienteMovimientoClient;
        this.orderNoteClient = orderNoteClient;
        this.transaccionFacturaProgramaDiaClient = transaccionFacturaProgramaDiaClient;
    }

    public FacturaResponseDto consultaComprobante(Integer tipoAfipId, Integer puntoVenta, Long numeroComprobante) {
        return facturadorClient.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
    }

    public void autoCompleta(Integer tipoAfipId, Integer puntoVenta, Long numeroComprobante, Boolean dryRun) {
        var facturaArca = facturadorClient.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
        logFacturaArca(facturaArca);
        try {
            var clienteMovimiento = clienteMovimientoClient.findByComprobante(853, puntoVenta, numeroComprobante);
            logClienteMovimiento(clienteMovimiento);
            log.debug("Error. Comprobante encontrado");
            return;
        } catch (Exception e) {
            log.debug("Ok. Comprobante no encontrado -> {}", e.getMessage());
        }
        var orderNote = orderNoteClient.findLastByNumeroDocumento(facturaArca.getFactura().getNroDoc());
        logOrderNote(orderNote);
        transaccionFacturaProgramaDiaClient.registroTransaccionFacturaProgramaDia(orderNote.getOrderNumberId(), dryRun, FacturacionAdapter.toFacturacionDto(facturaArca));
    }

    private void logOrderNote(OrderNoteDto orderNote) {
        try {
            log.debug("OrderNote -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(orderNote));
        } catch (JsonProcessingException e) {
            log.debug("OrderNote jsonify error -> {}", e.getMessage());
        }
    }

    private void logClienteMovimiento(ClienteMovimientoDto clienteMovimiento) {
        try {
            log.debug("ClienteMovimiento -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(clienteMovimiento));
        } catch (JsonProcessingException e) {
            log.debug("ClienteMovimiento jsonify error -> {}", e.getMessage());
        }
    }

    private void logFacturaArca(FacturaResponseDto facturaArca) {
        try {
            log.debug("FacturaArca -> {}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(facturaArca));
        } catch (JsonProcessingException e) {
            log.debug("FacturaArca jsonify error -> {}", e.getMessage());
        }
    }

}
