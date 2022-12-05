import React from "react";
import CardCake from "./CardCake";
import _useAxiosGet from "../_service/_useAxiosGet";
import { useParams } from "react-router-dom";
import { Suspense } from "react";

import basicImage from "../assets/images/temp/basic-cakes-intro.jpg";
import birthdayImage from "../assets/images/temp/birthday-cakes-intro.jpg";
import specialImage from "../assets/images/temp/special-cakes-intro.jpg";
import weddingImage from "../assets/images/temp/wedding-cakes-intro.jpg";

function ListCakes({ setUser, user }) {
  const url = "http://172.25.16.1:8080/api/product/all";
  let products = _useAxiosGet(url, setUser, user);
  const { category } = useParams();
  const image = () => {
    if ((category === "basic") | (category === undefined)) return basicImage;
    else if (category === "wedding") return weddingImage;
    else if (category === "special") return specialImage;
    else if (category === "birthday") return birthdayImage;
  };
  const mainText = () => {
    if (category === "basic") return "Basic";
    else if (category === "wedding") return "Wedding";
    else if (category === "special") return "Special";
    else if (category === "birthday") return "Birthday";
    else return "All";
  };
  let content = null;

  const takeProducts = () =>
    products.data.map((product) => {
      if (category) {
        if (product.category === category.toUpperCase()) {
          return (
            <div key={product.id} className="cakes__item">
              <Suspense key={product.id} fallback={<div>Loading</div>}>
                <CardCake key={product.id} product={product} />
              </Suspense>
            </div>
          );
        }
        return <p key={product.id} />;
      } else {
        return (
          <div key={product.id} className="cakes__item">
            <Suspense key={product.id} fallback={<div>Loading</div>}>
              <CardCake key={product.id} product={product} />
            </Suspense>
          </div>
        );
      }
    });

  if (products.error) {
    content = (
      <div>
        <div className="">
          There was an error please refresh or try again later.
        </div>
      </div>
    );
  }

  if (products.loading) {
    content = (
      <section
        className="section-image-intro"
        style={{ backgroundImage: `url(${image()})` }}
      >
        <div className="shell">
          <header className="section__head" data-aos="zoom-in-up">
            <h1>{mainText()} Cakes</h1>
          </header>
        </div>
      </section>
    );
  }

  if (products.data) {
    content = (
      <div className="main">
        <section
          className="section-image-intro"
          style={{ backgroundImage: `url(${image()})` }}
        >
          <div className="shell">
            <header className="section__head" data-aos="zoom-in-up">
              <h1>{mainText()} Cakes</h1>
            </header>
          </div>
        </section>

        <section className="section-cakes section-cakes--category">
          <div className="section__image-aside at-top-left">
            <img src="assets/images/temp/donut.png" alt="" />
          </div>

          <div className="section__image-aside at-center-right">
            <img src="assets/images/temp/cake-aside-1.jpg" alt="" />
          </div>

          <div className="section__image-aside at-bottom-left">
            <img src="assets/images/temp/cake-aside-2.png" alt="" />
          </div>

          <div className="shell">
            <div className="section__content">
              <div className="cakes">
                <div className="cakes__items">{takeProducts()}</div>
              </div>
            </div>
          </div>
        </section>
      </div>
    );
  }

  return <>{content}</>;
}
export default ListCakes;
