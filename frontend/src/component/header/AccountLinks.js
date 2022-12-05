import { Link } from "react-router-dom";
export default function AccountLinks({
  popupLogin,
  popupRegister,
  user,
  setUser,
}) {
  let content = "";
  if (
    typeof user === "undefined" ||
    user == null ||
    typeof user.roles === "undefined"
  ) {
    content = (
      <div className="account header__account">
        <Link to={window.location}>
          <div className="account__icon">
            <i className="fas fa-user"></i>
          </div>

          <span>Not logged in</span>
        </Link>

        <div className="account__menu">
          <ul>
            <li>
              <div className="account__btn">
                <Link
                  to={window.location}
                  className="btn "
                  onClick={popupLogin}
                >
                  Log in
                </Link>
              </div>
            </li>

            <li>
              <div className="account__btn">
                <Link
                  to={window.location}
                  className="btn"
                  onClick={popupRegister}
                >
                  Register
                </Link>
              </div>
            </li>
          </ul>
        </div>
      </div>
    );
  } else if (user.roles.includes("ROLE_ADMIN")) {
    content = (
      <div className="account header__account is-logged">
        <Link to={window.location}>
          <div className="account__icon">
            <i className="fas fa-user"></i>
          </div>

          <span>{user.username}</span>
        </Link>

        <div className="account__menu">
          <ul>
            <li>
              <div className="account__btn">
                <Link to="/account" className="btn">
                  Account
                </Link>
              </div>
            </li>

            <li>
              <div className="account__btn">
                <Link to="/product/add" className="btn">
                  Add Product
                </Link>
              </div>
            </li>

            <li>
              <div className="account__btn">
                <Link to="/orders/new" className="btn ">
                  New orders
                </Link>
              </div>
            </li>
            <li>
              <div className="account__btn">
                <Link to="/dashboard/products" className="btn">
                  Dashboard products
                </Link>
              </div>
            </li>
            <li>
              <div className="account__btn">
                <Link to="/dashboard/orders" className="btn">
                  Dashboard orders
                </Link>
              </div>
            </li>
            <li>
              <div className="account__btn">
                <Link to="/home" className="btn" onClick={() => setUser(null)}>
                  Log out
                </Link>
              </div>
            </li>
          </ul>
        </div>
      </div>
    );
  } else if (user.roles.includes("ROLE_EMPLOYEE")) {
    content = (
      <div className="account header__account is-logged">
        <Link to={window.location}>
          <div className="account__icon">
            <i className="fas fa-user"></i>
          </div>

          <span>{user.username}</span>
        </Link>

        <div className="account__menu">
          <ul>
            <li>
              <div className="account__btn">
                <Link to="/account" className="btn ">
                  Account
                </Link>
              </div>
            </li>

            <li>
              <div className="account__btn">
                <Link to="/orders/new" className="btn ">
                  New orders
                </Link>
              </div>
            </li>
            
            <li>
              <div className="account__btn">
                <Link to="/home" className="btn" onClick={() => setUser(null)}>
                  Log out
                </Link>
              </div>
            </li>
          </ul>
        </div>
      </div>
    );
  } else if (user.roles.includes("ROLE_USER")) {
    content = (
      <div className="account header__account">
        <Link to={window.location}>
          <div className="account__icon">
            <i className="fas fa-user"></i>
          </div>

          <span>{user.username}</span>
        </Link>

        <div className="account__menu">
          <ul>
            <li>
              <div className="account__btn">
                <Link to="/account" className="btn ">
                  Account
                </Link>
              </div>
            </li>
            <li>
              <div className="account__btn">
                <Link to="/home" className="btn" onClick={() => setUser(null)}>
                  Log out
                </Link>
              </div>
            </li>
          </ul>
        </div>
      </div>
    );
  }

  return content;
}
