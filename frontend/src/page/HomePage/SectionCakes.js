import { Link } from "react-router-dom";

import galery1 from "../../assets/images/temp/gallery-1.jpg";
import galery2 from "../../assets/images/temp/gallery-2.jpg";
import galery3 from "../../assets/images/temp/gallery-3.jpg";
import galery4 from "../../assets/images/temp/gallery-4.jpg";
import galery5 from "../../assets/images/temp/gallery-5.jpg";
import bdayCakes from "../../assets/images/temp/birthday-cakes.jpg";
import donut from "../../assets/images/temp/donut.png";
import cakeAside1 from "../../assets/images/temp/cake-aside-1.jpg";

export default function SectionCakes() {
  return (
    <section className="section-cakes">
      <div className="section__image-aside at-center-left">
        <img src={donut} data-aos="zoom-in" alt="" />
      </div>

      <div className="section__image-aside at-bottom-right">
        <img src={cakeAside1} data-aos="zoom-in" alt="" />
      </div>

      <div className="shell">
        <header className="section__head" data-aos="fade-up">
          <h3>Our Top Sellers</h3>
        </header>

        <div className="section__content">
          <div className="cakes">
            <div className="cakes__items">
              <div className="cakes__item">
                <div className="cake">
                  <Link to="/products/50" className="cake__image" data-aos="zoom-in-up">
                    <img src={galery1} alt="" />
                  </Link>

                  <div className="cake__entry" data-aos="fade-up">
                    <h4>Lorem ipsum dolor.</h4>

                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                      Perferendis, minus.
                    </p>
                  </div>

                  <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
                    <Link to="/products/50" className="btn">Buy Now</Link>
                  </div>
                </div>
              </div>

              <div className="cakes__item">
                <div className="cake">
                  <Link to="/products/50" className="cake__image" data-aos="zoom-in-up">
                    <img src={galery2} alt="" />
                  </Link>

                  <div className="cake__entry" data-aos="fade-up">
                    <h4>Lorem ipsum dolor.</h4>

                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                      Perferendis, minus.
                    </p>
                  </div>

                  <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
                    <Link to="/products/50" className="btn">Buy Now</Link>
                  </div>
                </div>
              </div>

              <div className="cakes__item">
                <div className="cake">
                  <Link to="/products/50" className="cake__image" data-aos="zoom-in-up">
                    <img src={galery3} alt="" />
                  </Link>

                  <div className="cake__entry" data-aos="fade-up">
                    <h4>Lorem ipsum dolor.</h4>

                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                      Perferendis, minus.
                    </p>
                  </div>

                  <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
                    <Link to="/products/50" className="btn">Buy Now</Link>
                  </div>
                </div>
              </div>

              <div className="cakes__item">
                <div className="cake">
                  <Link to="/products/50" className="cake__image" data-aos="zoom-in-up">
                    <img src={galery4} alt="" />
                  </Link>

                  <div className="cake__entry" data-aos="fade-up">
                    <h4>Lorem ipsum dolor.</h4>

                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                      Perferendis, minus.
                    </p>
                  </div>

                  <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
                    <Link to="/products/50" className="btn">Buy Now</Link>
                  </div>
                </div>
              </div>

              <div className="cakes__item">
                <div className="cake">
                  <Link to="/products/50" className="cake__image" data-aos="zoom-in-up">
                    <img src={galery5} alt="" />
                  </Link>

                  <div className="cake__entry" data-aos="fade-up">
                    <h4>Lorem ipsum dolor.</h4>

                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                      Perferendis, minus.
                    </p>
                  </div>

                  <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
                    <Link to="/products/50" className="btn">Buy Now</Link>
                  </div>
                </div>
              </div>

              <div className="cakes__item">
                <div className="cake">
                  <Link to="/products/50" className="cake__image" data-aos="zoom-in-up">
                    <img src={bdayCakes} alt="" />
                  </Link>

                  <div className="cake__entry" data-aos="fade-up">
                    <h4>Lorem ipsum dolor.</h4>

                    <p>
                      Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                      Perferendis, minus.
                    </p>
                  </div>

                  <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
                    <Link to="/products/50" className="btn">Buy Now</Link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
