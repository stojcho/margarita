package com.stoycho.margarita.controller;

import com.stoycho.margarita.DTO.ShoppingCardDTO;
import com.stoycho.margarita.exception.BadRequestException;
import com.stoycho.margarita.form.ShoppingCartForm;
import com.stoycho.margarita.serviceInterface.IShoppingCardService;
import com.stoycho.margarita.serviceInterface.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/user/shoppingcard")
public class ShoppingCardController {

    IShoppingCardService shoppingCardService;
    IUserService iUserService;


    @ExceptionHandler(BadRequestException.class)
    @PostMapping("/addproduct")
    public ResponseEntity<ShoppingCardDTO> addItem(@RequestParam("username") String username,
                                                   @RequestParam("productId") Long productId,
                                                   @RequestParam("quantity") int quantity) {
        ShoppingCardDTO shoppingCard= new ShoppingCardDTO(shoppingCardService.addProduct(username,productId,quantity),false,false,true);
        return  ResponseEntity.ok().body(shoppingCard);
    }
    @PutMapping("/add/one/item")
    public ResponseEntity<ShoppingCardDTO> AddOneItem(@RequestBody ShoppingCartForm form) {
        ShoppingCardDTO shoppingCard = new ShoppingCardDTO(shoppingCardService.addOneProduct(form));
        return  ResponseEntity.ok().body(shoppingCard);
    }
    @DeleteMapping("/remove/one/item")
    public ResponseEntity<ShoppingCardDTO> removeOneItem(@RequestBody ShoppingCartForm form) {
        ShoppingCardDTO shoppingCard = new ShoppingCardDTO(shoppingCardService.removeOneProduct(form));
        return  ResponseEntity.ok().body(shoppingCard);
    }

    @DeleteMapping("/remove/item")
    public ResponseEntity<ShoppingCardDTO> removeItem(@RequestBody ShoppingCartForm form) {
        ShoppingCardDTO shoppingCard=new ShoppingCardDTO(shoppingCardService.removeProduct(form));
        return  ResponseEntity.ok().body(shoppingCard);
    }

    @GetMapping()
    public ResponseEntity<ShoppingCardDTO> getShoppingCardDTO(@RequestParam("username")String username) {
        ShoppingCardDTO cardDTO = new ShoppingCardDTO(iUserService.getUser(username).getShoppingCard());
            return ResponseEntity.ok().body(cardDTO);
    }
    @GetMapping("/info")
    public ResponseEntity<ShoppingCardDTO> getShoppingCardQuantity(@RequestParam("username")String username,
                                                                   @RequestParam("isItems") boolean isItems,
                                                                   @RequestParam("isTotal") boolean isTotal,
                                                                   @RequestParam("isTotalQuantity") boolean isTotalQuantity) {
        ShoppingCardDTO cardDTO = new ShoppingCardDTO(iUserService.getUser(username).getShoppingCard(),
                isItems,
                isTotal,
                isTotalQuantity
        );
        return ResponseEntity.ok().body(cardDTO);
    }
}
