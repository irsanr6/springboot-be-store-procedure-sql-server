package com.irsan.springbootsprestapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: Irsan Ramadhan
 * @email: irsan.ramadhan@iconpln.co.id
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldErrorModel implements Serializable {

    private static final long serialVersionUID = -1601933602938830444L;
    private String field;
    private String message;

}
