package eterea.isolate.service.model.dto.web;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class PaymentDto {

    private Long orderNumberId;
    private String transaccionComercioId = "";
    private String transaccionPlataformaId = "";
    private String tipo = "";
    private BigDecimal monto = BigDecimal.ZERO;
    private String estado = "";
    private String detalle = "";
    private String metodoPago;
    private String medioPago;
    private Integer estadoId;
    private Integer cuotas;
    private String informacionAdicional;
    private String marcaTarjeta;
    private String informacionAdicionalLink;
    private OffsetDateTime fechaTransaccion;
    private OffsetDateTime fechaPago;
    private InformacionPagadorDto informacionPagador;
    private List<ProductTransactionDto> productTransactions;

}
