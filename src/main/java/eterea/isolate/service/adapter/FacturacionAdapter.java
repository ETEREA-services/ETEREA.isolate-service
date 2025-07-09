package eterea.isolate.service.adapter;

import eterea.isolate.service.model.dto.FacturaResponseDto;
import eterea.isolate.service.model.dto.core.FacturacionDto;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class FacturacionAdapter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static FacturacionDto toFacturacionDto(FacturaResponseDto facturaResponseDto) {
        if (facturaResponseDto == null || facturaResponseDto.getFactura() == null) {
            return null;
        }

        FacturaResponseDto.FacturaDto source = facturaResponseDto.getFactura();
        FacturacionDto.FacturacionDtoBuilder builder = FacturacionDto.builder();

        builder.tipoDocumento(source.getTipoDoc())
                .documento(Objects.toString(source.getNroDoc(), null))
                .tipoAfip(source.getTipoCbte())
                .puntoVenta(source.getPuntoVta())
                .total(source.getImpTotal())
                .exento(source.getImpOpEx())
                .neto(source.getImpNeto())
                .iva(source.getImpIva())
                .resultado(source.getResultado())
                .cae(source.getCae())
                .numeroComprobante(source.getCbtDesde());

        if (source.getFchVencCae() != null) {
            builder.vencimientoCae(source.getFchVencCae().format(DATE_FORMATTER));
        }

        if (source.getFechaCbte() != null) {
            builder.fechaComprobante(source.getFechaCbte().format(DATE_FORMATTER));
        }

        // Los campos sin correspondencia directa se inicializan con valores por defecto en el DTO
        // gracias a @Builder.Default o la inicialización directa en el campo.

        return builder.build();
    }
}
