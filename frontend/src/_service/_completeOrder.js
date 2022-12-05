import axios from "axios";
import _verifyToken from "./_verifyToken";
export default async function _completeOrder( orderId, status,setUser, user) {
  var data = JSON.stringify({
    orderId: orderId,
    status: status,
  });
  const a = _verifyToken(setUser, user);
  return Promise.resolve(a).then(function (value) {
    var config = {
      method: "put",
      url: "http://172.25.16.1:8080/api/user/order/update/status",
      headers: {
        Authorization: "Bearer " + value,
        'Content-Type': 'application/json'
      },
      data: data,
    };
    return axios(config)
      .then((response) => response.data, alert("The order has been shipped."))
      .catch((err) => {
        alert("There is a problem with the server{}", err);
      });
  });
}
