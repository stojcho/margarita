import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import _loginUser from "../../_service/_loginUser";
import { ValidationError } from "../../exception/ValidationError";

export default function Login({ popupRegister, popupClose, setUser }) {
  const [username, setUserName] = useState();
  const [password, setPassword] = useState();
  const [style, setStyle] = useState({ opacity: 0 });
  useEffect(() => {
    setStyle({ opacity: 1 });
  }, []);
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const user = await _loginUser({
        username,
        password,
      });
      if (user) {
        setUser(user);
        popupClose();
      } else {
        alert("There is a problem with the server, please try again later.");
      }
    } catch (e) {
      if (e instanceof ValidationError) alert(e.message);
      else if (e instanceof Error)
        alert("There is a problem with the server.\nPlease try again later.");
    }
  };

  return (
    <>
      <div className="popup" style={style}>
        <div className="popup__head">
          <h2>Login</h2>
        </div>

        <div className="popup__content">
          <div className="form-user">
            <form id="form-validation" onSubmit={handleSubmit}>
              <div className="form__body">
                <div className="form__row">
                  <label htmlFor="user-uname" className="form__label">
                    Username
                  </label>

                  <div className="form__controls">
                    <input
                      type="text"
                      className="field"
                      name="user-uname"
                      id="user-uname"
                      placeholder="John005"
                      required
                      onChange={(e) => setUserName(e.target.value)}
                    />
                  </div>
                </div>

                <div className="form__row">
                  <label htmlFor="user-password" className="form__label">
                    Password
                  </label>

                  <div className="form__controls">
                    <input
                      type="password"
                      className="field"
                      name="user-password"
                      id="user-password"
                      minLength="8"
                      maxLength="15"
                      required
                      onChange={(e) => setPassword(e.target.value)}
                    />
                  </div>
                </div>
              </div>

              <div className="form__actions">
                <button type="submit" className="btn">
                  Login
                </button>

                <p>
                  Don't have an account?{" "}
                  <Link to={window.location} onClick={popupRegister}>
                    Register
                  </Link>
                </p>
              </div>
            </form>
          </div>
        </div>

        <Link
          to={window.location}
          className="popup__close"
          onClick={popupClose}
        >
          <span></span>
        </Link>
      </div>
      <div className="overlay"></div>
    </>
  );
}
