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

    public void autoCompleta(Integer tipoAfipId, Integer puntoVenta, Long numeroComprobante, Boolean soloFactura, Boolean dryRun) {
        log.debug("Processing RellenadorService.autoCompleta");
        var facturaArca = facturadorClient.consultaComprobante(tipoAfipId, puntoVenta, numeroComprobante);
        log.debug("FacturaArca -> {}", facturaArca.jsonify());
        try {
            var clienteMovimiento = clienteMovimientoClient.findByComprobante(853, puntoVenta, numeroComprobante);
            log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());
            log.debug("\n\n\nError. Comprobante encontrado\n\n\n");
            return;
        } catch (Exception e) {
            log.debug("Ok. Comprobante no encontrado -> {}", e.getMessage());
        }
        log.debug("Buscando OrderNote . . .");
        OrderNoteDto orderNote;
        try {
            orderNote = orderNoteClient.findLastByNumeroDocumentoAndImporte(facturaArca.getFactura().getNroDoc(), facturaArca.getFactura().getImpTotal());
            log.debug("OrderNote -> {}", orderNote.jsonify());
        } catch (Exception e) {
            log.debug("\n\n\nNo fue posible encontrar un OrderNote\n\n\n");
            return;
        }
        if (facturaArca.getFactura().getImpTotal().compareTo(orderNote.getPayment().getMonto()) != 0) {
            log.debug("Error. Importe ARCA diferente OrderNote");
            return;
        }
        transaccionFacturaProgramaDiaClient.registroTransaccionFacturaProgramaDia(
                orderNote.getOrderNumberId(),
                soloFactura,
                dryRun,
                FacturacionAdapter.toFacturacionDto(facturaArca)
        );
    }

}
