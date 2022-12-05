package com.stoycho.margarita.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartForm {
    private String username;
    private Long itemId;
}
