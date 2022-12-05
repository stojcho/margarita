import React from "react";
import { Link } from "react-router-dom";

function CardCake(props) {
  return (
    <div className="cake">
      <Link to={`/products/${props.product.id}`}>
        <div className="cake__image" data-aos="zoom-in-up">
          <img src={props.product.imageURL} alt="text" />
        </div>
      </Link>
      <div className="cake__entry" data-aos="fade-up">
        <p>{props.product.name}</p>

        <p>{props.product.price} &euro;</p>
      </div>
      <div className="cake__actions" data-aos="fade-up" data-aos-offset="20">
        <Link to={`/products/${props.product.id}`} className="btn">
          {" "}
          Visit
        </Link>
      </div>
    </div>
  );
}
export default CardCake;
