package eterea.isolate.service.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DatoUnaFacturaDto {

    private ClienteMovimientoDto clienteMovimiento;
    private ArticuloMovimientoDto articuloMovimiento;

}
