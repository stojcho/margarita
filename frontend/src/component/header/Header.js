import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import Login from "./Login";
import Register from "./Register";
import AccountLinks from "./AccountLinks";
import CartItemsQuantity from "../CartItemsQuantity";

export default function Header({
  user,
  setUser,
  setCartQuantity,
  cartQuantity,
}) {
  const [isMenu, setIsMenu] = useState(false);
  const [popup, setPopup] = useState({
    login: false,
    register: false,
  });

  const popupLogin = () => {
    setPopup({
      login: true,
      register: false,
    });
  };
  const popupRegister = () => {
    setPopup({
      login: false,
      register: true,
    });
  };
  const popupClose = () => {
    setPopup({
      login: false,
      register: false,
    });
  };

  const handleClick = () => {
    if (isMenu === true) {
      openModal();
    } else {
      hideModal();
    }
    setIsMenu(!isMenu);
  };
  useEffect(() => {
    if (window.location.pathname === "/register") {
      popupRegister();
    } else if (window.location.pathname === "/login") {
      popupLogin();
    }
  }, []);

  const openModal = () => {
    document.body.classList.add("has-menu-opened");
  };
  const hideModal = () => {
    document.body.classList.remove("has-menu-opened");
  };
  const isPopups = () => {
    if (popup.login === true)
      return (
        <Login
          popupRegister={popupRegister}
          popupClose={popupClose}
          setUser={setUser}
        />
      );
    else if (popup.register === true)
      return <Register popupLogin={popupLogin} popupClose={popupClose} />;
  };
  return (
    <>
      {isPopups()}

      <header className="header">
        <div className="header__bar">
          <div className="shell">
            <div className="header__inner">
              <div className="header__contacts">
                <ul>
                  <li>
                    <Link to={window.location}>
                      <i className="fas fa-phone-alt"></i>
                    </Link>

                    <span>
                      <p>Contact us</p>

                      <Link to={window.location}>(012) 345-5678</Link>
                    </span>
                  </li>

                  <li>
                    <Link to={window.location}>
                      <i className="fas fa-envelope"></i>
                    </Link>

                    <span>
                      <p>Email us</p>

                      <Link to={window.location}>businessmail@gmail.com</Link>
                    </span>
                  </li>
                </ul>
              </div>

              <div className="header__actions">
                <ul>
                  <li>
                    <AccountLinks
                      popupRegister={popupRegister}
                      popupLogin={popupLogin}
                      user={user}
                      setUser={setUser}
                    />
                  </li>

                  <li>
                    <Link to="/cart">
                      <i className="fas fa-shopping-cart"></i>
                    </Link>

                    <span>
                      <p>Cart</p>

                      <p>
                        {user ? (
                          <CartItemsQuantity
                            setUser={setUser}
                            user={user}
                            setCartQuantity={setCartQuantity}
                            cartQuantity={cartQuantity}
                          />
                        ) : (
                          <>0</>
                        )}{" "}
                        Items
                      </p>
                    </span>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>

        <div className="header__content">
          <div className="shell">
            <Link to="/home" className="logo header__logo"></Link>

            <div className="header__overlay"></div>

            <div className="header__menu">
              <div className="header__menu-content">
                <nav className="nav">
                  <ul>
                    <li>
                      <Link onClick={handleClick} to="/cakes/basic">
                        {" "}
                        Basic Cakes
                      </Link>
                    </li>

                    <li>
                      <Link onClick={handleClick} to="/cakes/wedding">
                        {" "}
                        Wedding Cakes
                      </Link>
                    </li>

                    <li>
                      <Link onClick={handleClick} to="/cakes/birthday">
                        {" "}
                        Birthday Cakes
                      </Link>
                    </li>

                    <li>
                      <Link onClick={handleClick} to="/cakes/special">
                        {" "}
                        Special Cakes
                      </Link>
                    </li>
                  </ul>
                </nav>
              </div>
            </div>

            <button className="nav-trigger" onClick={handleClick}>
              <span></span>
              <span></span>
              <span></span>
            </button>
          </div>
        </div>
      </header>
    </>
  );
}
