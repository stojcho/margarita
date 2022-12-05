import _refreshToken from "./_refreshToken";
export default function _verifyToken(setUser, user) {
  const get_new_access_token = async () => {
    const new_access_token = await _refreshToken(user, setUser);
    return new_access_token;
  };
  //decrypt the token
  var base64Url = user.access_token.split(".")[1];
  var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
  var jsonPayload = decodeURIComponent(
    atob(base64)
      .split("")
      .map(function (c) {
        return "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2);
      })
      .join("")
  );
  if (Date.now() >= JSON.parse(jsonPayload).exp * 1000) {
    //If the token is expired, refresh it
    var p = get_new_access_token();
    return p;
  } else return user.access_token;
}