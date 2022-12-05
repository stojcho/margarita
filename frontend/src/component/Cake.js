import React from "react";
import _addToCard from "../_service/_addToCard";

import { useParams } from "react-router-dom";
import _useAxiosGet from "../_service/_useAxiosGet";

export default function Cake({ setUser, user, setCartQuantity }) {
  const { id } = useParams();
  const url = `http://172.25.16.1:8080/api/product/${id}`;
  let product = _useAxiosGet(url, setUser, user);

  let content = null;

  const handleSubmit = async (e) => {
    e.preventDefault();
    var data = await _addToCard(id, 1, setUser, user);
    Promise.resolve(data).then(function (data) {
      if (data) {
        setCartQuantity(data.totalQuantity);
      }
    });
  };

  if (product.error) {
    content = <div>There was an error please refresh or try again later.</div>;
  }

  if (product.loading) {
    content = <p>Loading</p>;
  }
  if (product.data) {
    content = (
      <>
        <section className="section-text-intro">
          <div className="shell">
            <header className="section__head">
              <h1>{product.data.name}</h1>
            </header>
          </div>
        </section>
        <section className="section-product">
          <div className="shell">
            <div className="section__inner">
              <div className="section__image" data-aos="zoom-in-up">
                <img src={product.data.imageURL} alt="" />
              </div>
              <div className="section__content">
                <div className="section__entry" data-aos="fade-up">
                  <h4>{product.data.category}</h4>

                  <h2>{product.data.name}</h2>

                  <p>{product.data.description}</p>
                </div>

                <div className="section__actions" data-aos="fade-up">
                  <div className="section__price">
                    <p>{product.data.price}&euro;</p>
                  </div>

                  <div className="section__actions-inner">
                    <button onClick={handleSubmit} className="btn">
                      Add to cart
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </>
    );
  }
  return <div>{content}</div>;
}
