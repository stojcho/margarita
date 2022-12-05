import CartItem from "../component/CartItem";
import _postOrder from "../_service/_postOrder";
import _getShoppingCard from "../_service/_getShoppingCard";
import { useState, useEffect } from "react";

export default function CartPage({ setUser, user, setCartQuantity }) {
  const [shoppingCart, setShoppingCart] = useState({
    loading: true,
    data: false,
    error: false,
  });

  const urlGetShoppingCard = "http://172.25.16.1:8080/api/user/shoppingcard";
  const getShoppingCardParams = new URLSearchParams({
    username: user.username,
  }).toString();
  const urlPostOrder = "http://172.25.16.1:8080/api/user/order/create";
  const [total, setTotal] = useState(0);
  const data = _getShoppingCard(
    urlGetShoppingCard,
    getShoppingCardParams,
    setUser,
    user
  );

  useEffect(() => {
    if (data.data) setShoppingCart(data);
  }, [data]);

  const setCart = (data) => {
    setShoppingCart({
      loading: false,
      data: data,
      error: false,
    });
  };
  const handleSubmit = async (event) => {
    event.preventDefault();
    if (shoppingCart.data) {
      if (shoppingCart.data.items.length === 0) {
        alert("Your shopping cart is empty");
        return;
      }
    }
    let orderNumber = await _postOrder(urlPostOrder, setUser, user);
    Promise.resolve(orderNumber).then(function (data) {
      if (data)
        alert("The Order was created with orderNumber: " + data.orderNumber);
      setTotal(0);
      setCartQuantity(0);
      setShoppingCart({
        loading: false,
        data: false,
        error: false,
      });
    });
  };
  useEffect(() => {
    if (shoppingCart.data) {
      setTotal(shoppingCart.data.total);
      setCartQuantity(shoppingCart.data.totalQuantity);
    }
  }, [shoppingCart.data, setCartQuantity]);
  let content = null;
  if (shoppingCart.error) {
    content = (
      <div>
        <div className="">
          There was an error please refresh or try again later.
        </div>
      </div>
    );
  }

  if (shoppingCart.loading) {
    content = <p>Loading</p>;
  }

  if (shoppingCart.data) {
    content = shoppingCart.data.items.map((item) => (
      <CartItem
        key={item.name}
        item={item}
        setUser={setUser}
        user={user}
        setShoppingCart={setCart}
      />
    ));
  }

  return (
    <div className="main">
      <section className="section-text-intro">
        <div className="shell">
          <header className="section__head" data-aos="zoom-in-up">
            <h1>Your Cart</h1>
          </header>
        </div>
      </section>

      <section className="section-form">
        <div className="shell">
          <div className="section__content">
            <div className="form">
              <form onSubmit={handleSubmit}>
                <div className="form__head">
                  <h3>Cart</h3>
                </div>

                <div className="form__inner">
                  <div className="form__body">{content}</div>

                  <div className="form__actions">
                    <p>
                      Your total: <span>{total}</span>&euro;
                    </p>

                    <button type="submit" className="btn">
                      Order
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
