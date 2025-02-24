package com.api.airport.models.payloads;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MensajeResponse implements Serializable {
    private String mensaje;
    private Object object;
}
