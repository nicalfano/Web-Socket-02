package co.develhope.Web.Socket2.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessageDTO {
    private String clientName;
    private String clientAlert;
    private String clientMsg;
}
