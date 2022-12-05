import axios from "axios";
import { ValidationError } from "../exception/ValidationError";
export default async function _registerUser(props) {

  var config = {
    method: "post",
    url: "http://172.25.16.1:8080/api/user/register",
    headers: {
      "Content-Type": "application/json",
    },
    data: JSON.stringify(props.newUser) 
  };

  return axios(config)
    .then((response) => response.data)
    .catch((err) => {
      if (err.response.status === 403||err.response.status === 400) {
        throw new ValidationError(err.response.data.message);
      }else{
        debugger;
        throw new Error();
      }
    });
}
