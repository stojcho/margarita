import { useState, useEffect } from "react";
import _addOneItem from "../_service/_addOneItem";
import _deleteOneItem from "../_service/_deleteOneItem";
export default function CartItem({ item, setUser, user, setShoppingCart }) {
  const removeOneProductURL =
    "http://172.25.16.1:8080/api/user/shoppingcard/remove/one/item";
  const addOneProductURL =
    "http://172.25.16.1:8080/api/user/shoppingcard/add/one/item";
  const [quantity, setQuantity] = useState("");
  useEffect(() => {
    setQuantity(item.quantity);
  }, [item.quantity]);

  const increaseQuantity = async (event) => {
    event.preventDefault();
    var data = await _addOneItem(
      addOneProductURL,
      setUser,
      user,
      item.orderItemId
    );
    Promise.resolve(data).then(function (data) {
      setShoppingCart(data);
    });
  };

  const decreaseQuantity = async (event) => {
    event.preventDefault();
    var data = await _deleteOneItem(
      removeOneProductURL,
      setUser,
      user,
      item.orderItemId
    );
    Promise.resolve(data).then(function (data) {
      setShoppingCart(data);
    });
  };

  return (
    <div className="form__row">
      <div className="form__row-inner">
        <div className="product">
          <div className="product__inner">
            <div className="product__image">
              <img src={item.imageURL} alt="" />
            </div>

            <div className="product__content">
              <div className="product__head">
                <h5>{item.name}</h5>
              </div>

              <div className="product__price">
                <p>{item.price}&euro;</p>
              </div>
            </div>
          </div>
        </div>

        <div className="quantity form__quantity">
          <fieldset>
            <legend>Quantity</legend>

            <button
              className="quantity__btn quantity__sub"
              onClick={decreaseQuantity}
            >
              <i className="fas fa-minus"></i>
            </button>

            <input
              type="number"
              className="quantity__field"
              min="1"
              max="99"
              step="1"
              value={quantity}
              disabled={true}
            />

            <button
              className="quantity__btn quantity__add"
              onClick={increaseQuantity}
            >
              <i className="fas fa-plus"></i>
            </button>
          </fieldset>
        </div>
      </div>
    </div>
  );
}
