import React from "react";
import { useState,useEffect } from "react";
import SectionCategories from "./SectionCategories";
import SectionCakes from "./SectionCakes"
import SectionTestimonials from "./SectionTestimonials";

import galery1 from "../../assets/images/temp/gallery-1.jpg";
import galery2 from "../../assets/images/temp/gallery-2.jpg";
import galery3 from "../../assets/images/temp/gallery-3.jpg";
import galery4 from "../../assets/images/temp/gallery-4.jpg";
import galery5 from "../../assets/images/temp/gallery-5.jpg";

function HomePage() {
  const [animation, setAnimation] = useState(true);
  const param  = window.location.pathname;
  useEffect(() => {
    if(param==="/home"){
      setAnimation(true);
    }else{
      setAnimation(false);
    }
  }, [param])

  return (
    <>
      <div className="main">
        <div className="hero">
          <div className="shell">
            <div className="hero__inner">
              <div className="hero__content">

                <div className="hero__entry" data-aos={animation?"zoom-in-up":""}>
                  <h1>Delicious cakes for all occasions</h1>

                  <p>
                    Our cakes are proudly made with the finest ingredients and
                    bespoke in designs and decorations. We use Swiss Meringue
                    Buttercream as the primary frosting for a subtle sweetness
                    along with a silky and smooth texture.
                  </p>
                </div>
              </div>

              <div className="gallery hero__gallery" data-aos="zoom-in-up">
                <div className="gallery__group">
                  <div className="gallery__image gallery__image--size-1">
                    <img src={galery1} alt="" />
                  </div>

                  <div className="gallery__image gallery__image--size-2">
                    <img src={galery2} alt="" />
                  </div>
                </div>

                <div className="gallery__group">
                  <div className="gallery__image gallery__image--size-3">
                    <img src={galery3} alt="" />
                  </div>

                  <div className="gallery__image gallery__image--size-4">
                    <img src={galery4} alt="" />
                  </div>

                  <div className="gallery__image gallery__image--size-5">
                    <img src={galery5} alt="" />
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <SectionCategories/>

        <SectionCakes/>

        <SectionTestimonials/>
      </div>
    </>
  );
}
export default HomePage;
