import { useEffect } from "react";
import _getShoppingCard from "../_service/_getShoppingCard";
export default function CartItemsQuantity({
  user,
  setUser,
  setCartQuantity,
  cartQuantity,
}) {
  const urlGetShoppingCard = "http://172.25.16.1:8080/api/user/shoppingcard/info";
  let getShoppingCardParams = new URLSearchParams({
    username: user.username,
    isTotal: "false",
    isTotalQuantity: "true",
    isItems: "false",
  }).toString();
  const shoppingCartQuantity = _getShoppingCard(
    urlGetShoppingCard,
    getShoppingCardParams,
    setUser,
    user
  );
  useEffect(() => {
    if (shoppingCartQuantity.data) {
      setCartQuantity(shoppingCartQuantity.data.totalQuantity);
    }
  }, [shoppingCartQuantity, setCartQuantity]);

  return cartQuantity;
}
