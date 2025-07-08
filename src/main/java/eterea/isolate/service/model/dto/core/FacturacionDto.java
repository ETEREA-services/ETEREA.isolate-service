package eterea.isolate.service.model.dto.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturacionDto {

    @JsonProperty(value = "tipo_documento")
    private Integer tipoDocumento;

    private String documento;

    @JsonProperty(value = "tipo_afip")
    private Integer tipoAfip;

    @JsonProperty(value = "punto_venta")
    private Integer puntoVenta;

    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal exento = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal neto = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal neto105 = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal iva = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal iva105 = BigDecimal.ZERO;
    private String resultado = "";
    private String cae = "";

    @JsonProperty(value = "vencimiento_cae")
    private String vencimientoCae = "";

    @JsonProperty(value = "numero_comprobante")
    private Long numeroComprobante = 0L;

    // Comprobante Asociado
    @JsonProperty(value = "asociado_tipo_afip")
    private Integer asociadoTipoAfip = null;

    @JsonProperty(value = "asociado_punto_venta")
    private Integer asociadoPuntoVenta = null;

    @JsonProperty(value = "asociado_numero_comprobante")
    private Long asociadoNumeroComprobante = null;

    @JsonProperty(value = "asociado_fecha_comprobante")
    private String asociadoFechaComprobante = null;

    @JsonProperty(value = "id_condicion_iva")
    private Integer idCondicionIva = null;

}
