import axios from "axios";
import _verifyToken from "./_verifyToken";
export default async function _postOrder(url, setUser, user) {
  const params = new URLSearchParams({
    username: user.username,
  }).toString();
  const a = _verifyToken(setUser, user);
  return Promise.resolve(a).then(function (value) {
    var config = {
      method: "post",
      url: url + "?" + params,
      headers: {
        Authorization: "Bearer " + value,
        "Content-Type": "application/json",
      },
      data: url,
    };
    return axios(config)
      .then((response) => response.data)
      .catch((error) => {
        console.log(error);
      });
  });
}
