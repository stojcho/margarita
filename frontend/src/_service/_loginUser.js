import axios from "axios";
import { ValidationError } from "../exception/ValidationError";
export default async function _loginUser({ username, password }) {
  var qs = require("qs");

  var config = {
    method: "post",
    url: "http://172.25.16.1:8080/login",////url: "http://172.25.16.1:8080/login",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    data: qs.stringify({
      username,
      password,
    }),
  };
  return axios(config)
    .then((response) => response.data)
    .catch((err) => {
      if (err.response.status === 403) {
        throw new ValidationError("The username and the password do not match");
      }else{
        debugger;
        throw new Error();
      }
    });
}
