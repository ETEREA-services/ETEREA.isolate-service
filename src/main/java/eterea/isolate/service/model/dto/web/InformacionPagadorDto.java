package eterea.isolate.service.model.dto.web;

import lombok.Data;

@Data
public class InformacionPagadorDto {

    private Long orderNumberId;
    private String eMail = "";
    private String nombre = "";
    private String numeroDocumento = "";
    private String telefono;
    private String tipoDocumento = "";

}
