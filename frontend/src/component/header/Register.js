import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

import { ValidationError } from "../../exception/ValidationError"; 
import _registerUser from "../../_service/_registerUser";

export default function Register({ popupLogin, popupClose }) {
  const [newUser, setNewUser] = useState({
    email: "",
    username: "",
    password: "",
  });
  const [style, setStyle] = useState({ opacity: 0 });
  useEffect(() => {
    setStyle({ opacity: 1 });
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      let status = await _registerUser({
        newUser,
      });
      if (status) {
        alert(
          "You are regitered! \nAfter you login please fill in your full information in your account page."
        );
        setNewUser({
          email: "",
          username: "",
          password: "",
        });
      }
    } catch (e) {
      if (e instanceof ValidationError) alert(e.message);
      else if (e instanceof Error)
        alert("There is a problem with the server.\nPlease try again later.");
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewUser((prevState) => ({
      ...prevState,
      [name]: value,
    }));
    console.log(name, value);
  };
  return (
    <>
      <div className="popup" style={style}>
        <div className="popup__head">
          <h2>Register</h2>
        </div>
        <div className="popup__content">
          <div className="popup__entry">
            <p>Create your account, it's free and only takes a minute.</p>
          </div>

          <div className="form-user">
            <form id="form-validation" onSubmit={handleSubmit}>
              <div className="form__body">
                <div className="form__row">
                  <label htmlFor="username" className="form__label">
                    Username
                  </label>

                  <div className="form__controls">
                    <input
                      type="text"
                      className="field"
                      name="username"
                      id="username"
                      placeholder="John005"
                      onChange={handleChange}
                      value={newUser.username}
                      required
                    />
                  </div>
                </div>

                <div className="form__row">
                  <label htmlFor="email" className="form__label">
                    Email
                  </label>

                  <div className="form__controls">
                    <input
                      type="email"
                      className="field"
                      name="email"
                      id="email"
                      placeholder="example@gmail.com"
                      onChange={handleChange}
                      value={newUser.email}
                      required
                    />
                  </div>
                </div>

                <div className="form__row">
                  <label htmlFor="password" className="form__label">
                    Password
                  </label>

                  <div className="form__controls">
                    <input
                      type="password"
                      className="field"
                      name="password"
                      id="password"
                      onChange={handleChange}
                      value={newUser.password}
                      required
                    />
                  </div>
                </div>

                <div className="checkbox form__checkbox">
                  <input type="checkbox" name="test" id="test" required />

                  <label htmlFor="test" className="form__label">
                    I accept the <Link to="">Terms of Use</Link> &{" "}
                    <Link to="">Privacy Policy</Link>
                  </label>
                </div>
              </div>

              <div className="form__actions">
                <button type="submit" className="btn">
                  Register
                </button>

                <p>
                  Already have an account?{" "}
                  <Link to={window.location} onClick={popupLogin}>
                    Login
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
