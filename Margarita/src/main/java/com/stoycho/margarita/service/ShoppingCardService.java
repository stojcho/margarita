package com.stoycho.margarita.service;

import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.form.ShoppingCartForm;
import com.stoycho.margarita.model.OrderItem;
import com.stoycho.margarita.model.Product;
import com.stoycho.margarita.model.ShoppingCard;
import com.stoycho.margarita.model.User;
import com.stoycho.margarita.repository.IOrderItemRepository;
import com.stoycho.margarita.repository.IProductRepository;
import com.stoycho.margarita.repository.IUserRepository;
import com.stoycho.margarita.serviceInterface.IShoppingCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ShoppingCardService implements IShoppingCardService {

    private final IUserRepository iUserRepository;
    private final IProductRepository iProductRepository;
    private final IOrderItemRepository iOrderItemRepository;

    //Add product to user shopping card.
    //If the product already exist, the quantity is increased
    @Transactional
    @Override
    public ShoppingCard addProduct(String username, Long productId, int quantity){
        Optional<User> user = iUserRepository.findByUsername(username);
        if(user.isEmpty()){
            noFoundUser();
        }

        Optional<Product> prodToAdd = iProductRepository.findById(productId);
        if(prodToAdd.isEmpty()){
            throw new BadRequestException("The product is not found in the database");
        }

        OrderItem item = new OrderItem(null, prodToAdd.get(), quantity);
        for (OrderItem orderItem : user.get().getShoppingCard().getItems()) {
            if(Objects.equals(orderItem.getProduct().getId(), item.getProduct().getId())){
                orderItem.setQuantity(orderItem.getQuantity()+item.getQuantity());
                return user.get().getShoppingCard();
            }
        }

        user.get().getShoppingCard().getItems().add(item);
        return user.get().getShoppingCard();
    }

    //Remove one product from shopping card.
    //If the product quantity is one, the product is removed from the shopping cart
    @Transactional
    @Override
    public ShoppingCard removeOneProduct(ShoppingCartForm form){
        Optional<User> user = iUserRepository.findByUsername(form.getUsername());
        if(user.isEmpty()){
            noFoundUser();
        }

        for (OrderItem orderItem:user.get().getShoppingCard().getItems()) {
            if(Objects.equals(orderItem.getItem_id(), form.getItemId())){
                if(orderItem.getQuantity()==1){
                    user.get().getShoppingCard().getItems().remove(orderItem);
                    iOrderItemRepository.deleteById(orderItem.getItem_id());

                }else{
                    orderItem.setQuantity(orderItem.getQuantity()-1);
                }
                break;
            }
        }
        return user.get().getShoppingCard();
    }
    //Add one product to user shopping card.
    @Transactional
    @Override
    public ShoppingCard addOneProduct(ShoppingCartForm form){
        Optional<User> user = iUserRepository.findByUsername(form.getUsername());
        if(user.isEmpty()){
            noFoundUser();
        }

        for (OrderItem orderItem:user.get().getShoppingCard().getItems()) {
            if(Objects.equals(orderItem.getItem_id(), form.getItemId())){
                if(orderItem.getQuantity()!=20){
                    orderItem.setQuantity(orderItem.getQuantity()+1);
                }
                break;
            }
        }
        return user.get().getShoppingCard();
    }
    //Remove product from user shopping card.
    @Transactional
    @Override
    public ShoppingCard removeProduct(ShoppingCartForm form){
        Optional<User> user = iUserRepository.findByUsername(form.getUsername());
        if(user.isEmpty()){
            noFoundUser();
        }
        for (OrderItem orderItem:user.get().getShoppingCard().getItems()) {
            if(Objects.equals(orderItem.getItem_id(), form.getItemId())){
                user.get().getShoppingCard().getItems().remove(orderItem);
                iOrderItemRepository.deleteById(orderItem.getItem_id());
                break;
            }
        }
        return user.get().getShoppingCard();
    }

    private void noFoundUser(){
        throw new BadRequestException("The user is not found in the database");
    }
}
