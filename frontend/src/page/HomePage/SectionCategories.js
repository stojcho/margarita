import { Link } from "react-router-dom";


import galery1 from "../../assets/images/temp/gallery-1.jpg";
import galery3 from "../../assets/images/temp/gallery-3.jpg";
import galery4 from "../../assets/images/temp/gallery-4.jpg";
import bdayCakes from "../../assets/images/temp/birthday-cakes.jpg";

export default function SectionCategories() {
  return (
    <section className="section-categories">
      <div className="shell">
        <header className="section__head" data-aos="fade-up">
          <h2>Our Cake Types</h2>
        </header>

        <div className="section__content">
          <div className="categories">
            <div className="categories__items">
              <div className="categories__item">
                <Link to="/cakes/basic" className="category">
                  <div className="category__image" data-aos="zoom-in-up">
                    <img src={galery3} alt="" />
                  </div>

                  <div className="category__entry" data-aos="fade-up" data-aos-offset="0">
                    <h4>Basic Cakes</h4>
                  </div>
                </Link>
              </div>

              <div className="categories__item">
                <Link to="/cakes/wedding" className="category">
                  <div className="category__image" data-aos="zoom-in-up">
                    <img src={galery4} alt="" />
                  </div>

                  <div className="category__entry" data-aos="fade-up" data-aos-offset="0">
                    <h4>Wedding Cakes</h4>
                  </div>
                </Link>
              </div>

              <div className="categories__item">
                <Link to="/cakes/birthday" className="category">
                  <div className="category__image" data-aos="zoom-in-up">
                    <img src={bdayCakes} alt="" />
                  </div>

                  <div className="category__entry" data-aos="fade-up" data-aos-offset="0">
                    <h4>Birthday Cakes</h4>
                  </div>
                </Link>
              </div>

              <div className="categories__item">
                <Link to="/cakes/special" className="category">
                  <div className="category__image" data-aos="zoom-in-up">
                    <img src={galery1} alt="" />
                  </div>

                  <div className="category__entry" data-aos="fade-up" data-aos-offset="0">
                    <h4>Special Cakes</h4>
                  </div>
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
