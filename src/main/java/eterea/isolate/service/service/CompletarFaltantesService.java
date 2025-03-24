package eterea.isolate.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.isolate.service.client.core.ArticuloClient;
import eterea.isolate.service.client.core.ClienteMovimientoClient;
import eterea.isolate.service.client.core.facade.FacturacionClient;
import eterea.isolate.service.model.dto.ArticuloDto;
import eterea.isolate.service.model.dto.ArticuloMovimientoDto;
import eterea.isolate.service.model.dto.ClienteMovimientoDto;
import eterea.isolate.service.model.dto.DatoUnaFacturaDto;
import eterea.isolate.service.service.miscelaneos.ToolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompletarFaltantesService {

    private final ClienteMovimientoClient clienteMovimientoClient;
    private final ArticuloClient articuloClient;
    private final FacturacionClient facturacionClient;

    public CompletarFaltantesService(ClienteMovimientoClient clienteMovimientoClient,
                                     ArticuloClient articuloClient,
                                     FacturacionClient facturacionClient) {
        this.clienteMovimientoClient = clienteMovimientoClient;
        this.articuloClient = articuloClient;
        this.facturacionClient = facturacionClient;
    }

    public String completarFaltantes(String letraComprobante, Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta) {
        log.debug("Processing CompletarFaltantesService.completarFaltantes");
        List<ClienteMovimientoDto> clienteMovimientos = clienteMovimientoClient.findAllFacturasByRango(letraComprobante, (byte) 1, puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta);
        
        // Create a map of existing invoice numbers to their dates
        Map<Long, OffsetDateTime> numerosExistentes = clienteMovimientos.stream()
                .collect(Collectors.toMap(
                    ClienteMovimientoDto::getNumeroComprobante,
                    ClienteMovimientoDto::getFechaComprobante
                ));
        
        // Find missing numbers and their corresponding dates
        Map<Long, OffsetDateTime> numerosFaltantes = new HashMap<>();
        OffsetDateTime fechaAnterior = null;
        
        for (Long numero = numeroComprobanteDesde; numero <= numeroComprobanteHasta; numero++) {
            if (numerosExistentes.containsKey(numero)) {
                fechaAnterior = numerosExistentes.get(numero);
            } else if (fechaAnterior != null) {
                numerosFaltantes.put(numero, fechaAnterior);
            }
        }

        // Log missing numbers and their dates
        numerosFaltantes.forEach((numero, fecha) -> {
                    log.debug("Numero Faltante: {}, Fecha: {}", numero, fecha);
                    generarFaltante(numero, fecha);
                }
        );
        
        return "Completado";
    }

    private void generarFaltante(Long numeroComprobante, OffsetDateTime fecha) {
        log.debug("Processing CompletarFaltantesService.generarFaltante");
        var total = new BigDecimal(12000);
        ClienteMovimientoDto clienteMovimiento = ClienteMovimientoDto.builder()
                .negocioId(54)
                .empresaId(1)
                .clienteId(1L)  // Consumidor Final
                .comprobanteId(901)  // Factura B 34 Cta Cte Manual
                .fechaComprobante(fecha)
                .fechaVencimiento(fecha)
                .importe(total)
                .cancelado(BigDecimal.ZERO)
                .puntoVenta(34)
                .numeroComprobante(numeroComprobante)
                .montoIva(new BigDecimal("2082.64"))
                .montoIvaRni(BigDecimal.ZERO)
                .neto(new BigDecimal("9917.36"))
                .netoCancelado(BigDecimal.ZERO)
                .letraComprobante("B")
                .montoExento(BigDecimal.ZERO)
                .monedaId(1)
                .cotizacion(BigDecimal.ONE)
                .letras(ToolService.number_2_text(total))
                .observaciones("Generación manual por error en el WordPress")
                .reintegroTurista(BigDecimal.ZERO)
                .montoCuentaCorriente(BigDecimal.ZERO)
                .cae("")
                .caeVencimiento("")
                .codigoBarras("")
                .participacion(BigDecimal.ZERO)
                .reservaId(0L)
                .build();

        ArticuloDto articulo = articuloClient.findByArticuloId("603301012");

        ArticuloMovimientoDto articuloMovimiento = ArticuloMovimientoDto.builder()
                .centroStockId(articulo.getCentroStockId())
                .comprobanteId(clienteMovimiento.getComprobanteId())
                .item(1)
                .articuloId(articulo.getArticuloId())
                .negocioId(clienteMovimiento.getNegocioId())
                .cantidad(new BigDecimal(-1))
                .precioUnitario(clienteMovimiento.getImporte())
                .precioUnitarioSinIva(clienteMovimiento.getNeto())
                .precioUnitarioConIva(clienteMovimiento.getImporte())
                .numeroCuenta(articulo.getCuentaVentas())
                .iva105((byte) 0)
                .exento((byte)0)
                .fechaMovimiento(clienteMovimiento.getFechaComprobante())
                .fechaFactura(clienteMovimiento.getFechaComprobante())
                .precioCompra(articulo.getPrecioCompra())
                .articulo(articulo)
                .comision(BigDecimal.ZERO)
                .nivel(0)
                .precioValuacion(BigDecimal.ZERO)
                .build();

        clienteMovimiento = facturacionClient.registrarTransaccionFacturaFaltante(
                DatoUnaFacturaDto.builder()
                        .clienteMovimiento(clienteMovimiento)
                        .articuloMovimiento(articuloMovimiento)
                        .build()
        );
        logClienteMovimiento(clienteMovimiento);

    }

    private void logClienteMovimiento(ClienteMovimientoDto clienteMovimiento) {
        try {
            log.debug("clienteMovimiento={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(clienteMovimiento));
        } catch (JsonProcessingException e) {
            log.debug("clienteMovimiento jsonify error {}", e.getMessage());
        }
    }

}
