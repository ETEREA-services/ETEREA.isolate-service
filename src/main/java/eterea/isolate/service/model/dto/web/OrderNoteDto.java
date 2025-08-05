package eterea.isolate.service.model.dto.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderNoteDto {

    private Long orderNumberId;
    private String orderStatus = "";
    private OffsetDateTime orderDate;
    private OffsetDateTime paidDate;
    private OffsetDateTime completedDate;
    private OffsetDateTime modifiedDate;
    private String orderCurrency = "";
    private String customerNote = "";
    private String billingFirstName = "";
    private String billingLastName = "";
    private String billingFullName = "";
    private String billingDniPasaporte = "";
    private String billingAddress = "";
    private String billingCity = "";
    private String billingState = "";
    private String billingPostCode = "";
    private String billingCountry = "";
    private String billingEmail = "";
    private String billingPhone = "";
    private String shippingFirstName = "";
    private String shippingLastName = "";
    private String shippingFullName = "";
    private String shippingAddress = "";
    private String shippingCity = "";
    private String shippingState = "";
    private String shippingPostCode = "";
    private String shippingCountryFull = "";
    private String paymentMethodTitle = "";
    private String cartDiscount = "";
    private BigDecimal orderSubtotal = BigDecimal.ZERO;
    private BigDecimal orderSubtotalRefunded = BigDecimal.ZERO;
    private String shippingMethodTitle = "";
    private String orderShipping = "";
    private BigDecimal orderShippingRefunded = BigDecimal.ZERO;
    private BigDecimal orderTotal = BigDecimal.ZERO;
    private BigDecimal orderTotalTax = BigDecimal.ZERO;
    private String orderNotes = "";
    private List<ProductDto> products;
    private PaymentDto payment;

    public String jsonify() {
        try {
            return JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "jsonify error {}" + e.getMessage();
        }
    }

}
