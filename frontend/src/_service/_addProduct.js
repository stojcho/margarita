import axios from "axios";
import _verifyToken from "./_verifyToken";

export default function _addProduct(props) {

  var FormData = require("form-data");
  
  const data = new FormData();
  data.append("image", props.newImage.image);
  const params = new URLSearchParams({
    name: props.newProduct.name,
    description: props.newProduct.description,
    category: props.newProduct.category,
    price: props.newProduct.price
  }).toString();
  const a = _verifyToken(props.setUser,props.user);
  Promise.resolve(a).then(function(value) {
  var config = {
    method: "post",
    url: "http://172.25.16.1:8080/api/product/save?" + params,////
    headers: {
      Authorization: "Bearer " + value, 
      contentType: 'application/json'
    },
    data: data,
  };

  try {
    const response = axios(config);
    return response.data;
  } catch (error) {
    console.log(error);
  }})
}
