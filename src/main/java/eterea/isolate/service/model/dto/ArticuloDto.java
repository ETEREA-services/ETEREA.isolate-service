package eterea.isolate.service.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ArticuloDto {

    private String articuloId;
    private Integer negocioId;
    private String descripcion = "";
    private String leyendaVoucher = "";
    private BigDecimal precioVentaSinIva = BigDecimal.ZERO;
    private BigDecimal precioVentaConIva = BigDecimal.ZERO;
    private Long cuentaVentas;
    private Long cuentaCompras;
    private Long cuentaGastos;
    private Integer centroStockId;
    private Long rubroId;
    private Long subRubroId;
    private BigDecimal precioCompra = BigDecimal.ZERO;
    private Byte iva105 = 0;
    private BigDecimal precioCompraNeto = BigDecimal.ZERO;
    private Byte exento = 0;
    private Long stockMinimo = 0L;
    private Long stockOptimo = 0L;
    private Byte bloqueoCompras = 0;
    private Byte bloqueoStock = 0;
    private Byte bloqueoVentas = 0;
    private Long unidadMedidaId;
    private Byte conDecimales = 0;
    private Byte ventas = 0;
    private Byte compras = 0;
    private String unidadMedida = "";
    private Integer conversionId;
    private Byte ventaSinStock = 0;
    private Byte controlaStock = 0;
    private Byte asientoCostos = 0;
    private String mascaraBalanza = "";
    private Byte habilitaIngreso = 0;
    private BigDecimal comision = BigDecimal.ZERO;
    private Integer prestadorId;
    private Long autoNumericoId;

}