import axios from "axios";
import _verifyToken from "./_verifyToken";
export default function _updateUser({ userData,setUser, user }) {
  var data = JSON.stringify(userData);
  var config = {
    method: "put",
    url: "http://172.25.16.1:8080/api/user/update",
    headers: {
      Authorization: "Bearer " + _verifyToken(setUser, user),
      "Content-Type": "application/json",
       crossDomain: true 
    },
    data: data,
  };

   axios(config)
    .then((response) => response.data)
    .catch((error) => {
      console.log(error);
    });
}
