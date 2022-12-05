import axios from "axios";
import _verifyToken from "./_verifyToken";
export default async function _addOneItem(url, setUser, user, itemId) {
  const a = _verifyToken(setUser, user);
   return Promise.resolve(a).then(function (value) {
    var data = JSON.stringify({
      username: user.username,
      itemId: itemId,
    });
    var config = {
      method: "put",
      url: url,
      headers: {
        Authorization: "Bearer " + value,
        "Content-Type": "application/json",
      },
      data: data,
    };
    return axios(config)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
      });
  });
}
