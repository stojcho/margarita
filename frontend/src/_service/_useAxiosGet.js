import { useEffect, useState } from "react";
import axios from "axios";
import _verifyToken from "./_verifyToken";

export default function _useAxiosGet(url,setUser, user) {
  const [request, setRequest] = useState({
    loading: false,
    data: null,
    error: false,
  });
  useEffect(() => {
    setRequest({
      loading: true,
      data: null,
      error: false,
    });
    const a = _verifyToken(setUser,user);
    Promise.resolve(a).then(function(value) {
      axios
      .get(url, { headers: { Authorization: "Bearer " + value }})
      .then((response) => {
        setRequest({
          loading: false,
          data: response.data,
          error: false,
        });
      })
      .catch(() => {
        setRequest({
          loading: false,
          data: null,
          error: true,
        });
      });
    })
  }, [url,setUser,user]);

  return request;
}
