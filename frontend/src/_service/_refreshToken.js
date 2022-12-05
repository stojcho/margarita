import axios from "axios";
export default async function _refreshToken(user,setUser) {
  var config = {
    method: "get",
    url: "http://172.25.16.1:8080/api/token/refresh",
    headers: {
      Authorization: "Bearer " + user.refresh_token,
    },
  };
  return axios(config)
    .then((response) => {
      if (response.data.access_token || response.data.refresh_token) {
        var userString = sessionStorage.getItem("user");
        var user = JSON.parse(userString);
        user.access_token = response.data.access_token;
        user.refresh_token = response.data.refresh_token;
        var test=JSON.stringify(user);
        sessionStorage.setItem("user", test);
        return user.access_token;
      }
    })
    .catch(function () {
      alert("Your token has expired! Please log in again ");
      setUser(null);
      return null;
    });
}