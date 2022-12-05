import { useEffect, useState } from "react";
import axios from "axios";
import _verifyToken from "./_verifyToken";

export default function _useAxiosGetAccountDTO(url, setUser, user) {
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
    const a = _verifyToken(setUser, user);
    Promise.resolve(a).then(function (value) {
      var config = {
        method: "get",
        url: url,
        headers: {
          Authorization: "Bearer " + value,
          "Content-Type": "text/plain",
        },
        params: {
          username: user.username,
        },
      };

      axios(config)
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
    });
  }, [url, setUser, user]);

  return request;
}
