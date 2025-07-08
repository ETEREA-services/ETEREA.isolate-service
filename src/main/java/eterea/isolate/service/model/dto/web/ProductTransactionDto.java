package eterea.isolate.service.model.dto.web;

import lombok.Data;

@Data
public class ProductTransactionDto {

    private Long productTransactionId;
    private Long orderNumberId;
    private String nombreProducto = "";
    private String montoProducto;

}
