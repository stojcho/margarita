package com.stoycho.margarita.serviceInterface;

import com.stoycho.margarita.form.ShoppingCartForm;
import com.stoycho.margarita.model.ShoppingCard;
import org.springframework.transaction.annotation.Transactional;

public interface IShoppingCardService {
    //Add product to user shopping card.
    //If the product already exist, the quantity is increased
    ShoppingCard addProduct(String username, Long productId, int quantity);

    @Transactional
    ShoppingCard removeOneProduct(ShoppingCartForm form);

    @Transactional
    ShoppingCard addOneProduct(ShoppingCartForm form);

    //Remove product from user shopping card.
    @Transactional
    ShoppingCard removeProduct(ShoppingCartForm form);
}
