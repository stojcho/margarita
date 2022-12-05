import { Link } from "react-router-dom";
export default function Footer(props) {
  return (
    <footer className="footer">
      <div className="footer__content">
        <div className="shell">
          <div className="footer__inner">
            <div className="footer__aside">
              <Link to="/home" className="logo footer__logo">
                {" "}
              </Link>

              <p>
                Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque
                rem cum dolorem accusamus odit.
              </p>
            </div>

            <div className="footer__body">
              <div className="footer__cols">
                <div className="footer__col">
                  <h4>Contact</h4>

                  <div className="footer__contacts">
                    <ul>
                      <li>
                        <i className="fas fa-phone-alt"></i>

                        <Link to={window.location}>(012) 345-5678</Link>
                      </li>

                      <li>
                        <i className="fas fa-envelope"></i>

                        <Link to={window.location}>businessmail@gmail.com</Link>
                      </li>
                    </ul>
                  </div>

                  <h4>Stay connected</h4>

                  <div className="footer__socials">
                    <ul>
                      <li>
                        <Link to={window.location}>
                          <i className="fab fa-facebook-f"></i>
                        </Link>
                      </li>

                      <li>
                        <Link to={window.location}>
                          <i className="fab fa-twitter"></i>
                        </Link>
                      </li>

                      <li>
                        <Link to={window.location}>
                          <i className="fab fa-instagram"></i>
                        </Link>
                      </li>

                      <li>
                        <Link to={window.location}>
                          <i className="fab fa-pinterest-p"></i>
                        </Link>
                      </li>

                      <li>
                        <Link to={window.location}>
                          <i className="fab fa-tiktok"></i>
                        </Link>
                      </li>
                    </ul>
                  </div>
                </div>

                <div className="footer__col">
                  <h4>Explore cakes</h4>

                  <nav className="footer__nav">
                    <ul>
                      <li>
                        <Link to="/cakes/basic"> Basic Cakes</Link>
                      </li>

                      <li>
                        <Link to="/cakes/wedding">Wedding Cakes</Link>
                      </li>

                      <li>
                        <Link to="/cakes/birthday">Birthday Cakes</Link>
                      </li>

                      <li>
                        <Link to="/cakes/special">Special Cakes</Link>
                      </li>
                    </ul>
                  </nav>
                </div>

                <div className="footer__col">
                  <h4>Site map</h4>

                  <nav className="footer__nav">
                    <ul>
                      <li>
                        <Link to={window.location}> About us</Link>
                      </li>

                      <li>
                        <Link to={window.location}>Privacy Policy</Link>
                      </li>
                    </ul>
                  </nav>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="footer__bar">
        <div className="shell">
          <p>&copy;2021 All Rights Reserved by Margarita Pastry Shop</p>
        </div>
      </div>
    </footer>
  );
}
