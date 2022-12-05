import axios from "axios";
import _verifyToken from "./_verifyToken";
export default async function _addToCard(productId, quantity,setUser, user ) {

  const params = new URLSearchParams({
    username: user.username,
    productId: productId,
    quantity: quantity,
  }).toString();
  const a = _verifyToken(setUser, user);
  return Promise.resolve(a).then(function (value) {
    var config = {
      method: "post",
      url: "http://172.25.16.1:8080/api/user/shoppingcard/addproduct?" + params,
      headers: {
        Authorization: "Bearer " + value,
      },
    };
    return axios(config)
      .then(
        (response) => response.data,
        alert("The product was added to your cart")
      )
      .catch((error) => {
        console.log(error);
      });
  });
}
