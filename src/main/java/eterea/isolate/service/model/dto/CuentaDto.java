package eterea.isolate.service.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CuentaDto {

    private Long numeroCuenta;
    private String nombre;
    private Integer negocioId;
    private Byte integra;
    private Byte transfiere;
    private Byte movimientoStock;
    private BigDecimal cuentaMaestro = BigDecimal.ZERO;
    private Integer grado;
    private Long grado1;
    private Long grado2;
    private Long grado3;
    private Long grado4;
    private Byte ventas;
    private Byte compras;
    private Byte gastos;

}
