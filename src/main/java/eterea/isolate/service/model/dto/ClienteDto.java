package eterea.isolate.service.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
public class ClienteDto {

    private Long clienteId;
    private String nombre;
    private Integer negocioId;
    private String cuit = "";
    private String razonSocial = "";
    private String nombreFantasia = "";
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    private OffsetDateTime fechaRestaurant;
    
    private Integer cantidadPaxs = 0;
    private Integer tipoCliente = 0;
    private String domicilio = "";
    private String telefono = "";
    private String fax = "";
    private String email = "";
    private String numeroMovil = "";
    private Integer posicionIva = 0;
    private Integer constante = 0;
    private Integer documentoId = 0;
    private String tipoDocumento = "";
    private String numeroDocumento = "";
    private BigDecimal limiteCredito = BigDecimal.ZERO;
    private String nacionalidad = "";
    private Integer clienteCategoriaId;
    private String impositivoId = "";
    private Byte facturarExtranjero = 0;
    private Byte bloqueado = 0;
    private Byte discapacitado = 0;

}
