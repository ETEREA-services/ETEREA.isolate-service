package eterea.isolate.service.model.dto.web;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ProductDto {

    private Long productId;
    private Long orderNumberId;
    private String sku = "";
    private Integer lineId = 0;
    private String name = "";
    private Integer qty = 0;
    private BigDecimal itemPrice = BigDecimal.ZERO;
    private OffsetDateTime bookingStart;
    private OffsetDateTime bookingEnd;
    private Integer bookingDuration = 0;
    private Integer bookingPersons = 0;
    private String personTypes = "";
    private String serviciosAdicionales = "";
    private String puntoDeEncuentro = "";
    private String encuentroHotel = "";

}
