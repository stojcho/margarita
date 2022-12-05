package com.stoycho.margarita.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetShoppingCartForm {
    private String username;
    private boolean isTotal;
    private boolean isTotalQuantity;
    private boolean isItems;
}
