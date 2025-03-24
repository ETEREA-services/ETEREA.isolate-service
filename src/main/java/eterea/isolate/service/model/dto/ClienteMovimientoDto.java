package eterea.isolate.service.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class ClienteMovimientoDto {

    private Long clienteMovimientoId;
    private Integer comprobanteId;
    private Integer puntoVenta = 0;
    private long numeroComprobante = 0L;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaComprobante;
    
    private Long clienteId;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaVencimiento;
    
    private Integer negocioId;
    private Integer empresaId;
    private BigDecimal importe = BigDecimal.ZERO;
    private BigDecimal cancelado = BigDecimal.ZERO;
    private BigDecimal neto = BigDecimal.ZERO;
    private BigDecimal netoCancelado = BigDecimal.ZERO;
    private BigDecimal montoIva = BigDecimal.ZERO;
    private BigDecimal montoIvaRni = BigDecimal.ZERO;
    private BigDecimal reintegroTurista = BigDecimal.ZERO;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaContable;
    
    private Integer ordenContable = 0;
    private Byte recibo = 0;
    private Byte asignado = 0;
    private Byte anulada = 0;
    private Byte decreto104316 = 0;
    private String letraComprobante;
    private BigDecimal montoExento = BigDecimal.ZERO;
    private Long reservaId;
    private BigDecimal montoCuentaCorriente = BigDecimal.ZERO;
    private Long cierreCajaId = 0L;
    private Long cierreRestaurantId = 0L;
    private Integer nivel = 0;
    private Byte eliminar = 0;
    private Byte cuentaCorriente = 0;
    private String letras = "";
    private String cae = "";
    private String caeVencimiento = "";
    private String codigoBarras = "";
    private BigDecimal participacion = BigDecimal.ZERO;
    private Integer monedaId;
    private BigDecimal cotizacion = BigDecimal.ZERO;
    private String observaciones;
    private Long clienteMovimientoIdSlave = 0L;

    private ComprobanteDto comprobante;
    private ClienteDto cliente;
    private MonedaDto moneda;

}
