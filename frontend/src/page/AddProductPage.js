import React, { useState } from "react";
import _addProduct from "../_service/_addProduct";

export default function AddProductPage({ setUser, user }) {
  const [newProduct, setnewProduct] = useState({
    name: "",
    category: "",
    description: "",
    price: "",
  });
  const [newImage, setnewImage] = useState({ image: "", imageURL: "" });
  const handleSubmit = async (e) => {
    e.preventDefault();
    _addProduct({
      newProduct,
      newImage,
      setUser,
      user,
    });
    setnewProduct({
      name: "",
      category: "",
      description: "",
      price: "",
    });
    setnewImage({ image: "", imageURL: "" });
    alert("The product was added, \n wish you sales!");
  };

  const handleProductChange = (e) => {
    const { name, value } = e.target;
    setnewProduct((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };
  const handleimageChange = (e) => {
    if (!e.target.files[0]) return;

    //Restrict the file extention
    if (!e.target.files[0].name.endsWith(".jpg")) {
      alert("You can upload jpg files only.");
      window.location.reload(false);
    }

    let image = URL.createObjectURL(e.target.files[0]);
    setnewImage({ image: e.target.files[0], imageURL: image });
  };

  return (
    <div className="main">
      <section className="section-text-intro">
        <div className="shell">
          <header className="section__head" data-aos="zoom-in-up">
            <h1>Add Product</h1>
          </header>
        </div>
      </section>

      <section className="section-form">
        <div className="shell">
          <div className="section__content">
            <div className="form">
              <form id="form-validation" onSubmit={handleSubmit}>
                <div className="form__head">
                  <h3>Add Product</h3>
                </div>

                <div className="form__body">
                  <div className="form__cols">
                    <div className="form__col">
                      <div className="form__row">
                        <label htmlFor="name" className="form__label">
                          Product Name
                        </label>

                        <div className="form__controls">
                          <input
                            onChange={handleProductChange}
                            name="name"
                            value={newProduct.name}
                            type="text"
                            req
                            className="field"
                            id="name"
                            required
                          />
                        </div>
                      </div>

                      <div className="form__row">
                        <label htmlFor="category" className="form__label">
                          Product Category
                        </label>

                        <div className="form__controls">
                          <input
                            onChange={handleProductChange}
                            value={newProduct.category}
                            type="text"
                            className="field"
                            name="category"
                            id="category"
                            required
                          />
                        </div>
                      </div>

                      <div className="form__row">
                        <label htmlFor="description" className="form__label">
                          Product Description
                        </label>

                        <div className="form__controls">
                          <textarea
                            className="field field__textarea"
                            name="description"
                            id="description"
                            onChange={handleProductChange}
                            value={newProduct.description}
                            required
                          ></textarea>
                        </div>
                      </div>
                    </div>

                    <div className="form__col">
                      <div className="form__row">
                        <label htmlFor="price" className="form__label">
                          Price
                        </label>

                        <div className="form__controls">
                          <input
                            onChange={handleProductChange}
                            value={newProduct.price}
                            type="number"
                            className="field"
                            name="price"
                            id="price"
                            required
                          />
                        </div>
                      </div>

                      <div className="form__row">
                        <label htmlFor="image" className="form__label">
                          Add Image
                        </label>

                        <div className="form__controls">
                          <input
                            onChange={handleimageChange}
                            type="file"
                            className="field__file"
                            accept=".jpg"
                            name="image"
                            id="image"
                            required
                          />
                        </div>

                        <div className="form__image">
                          <img src={newImage.imageURL} alt="" />
                        </div>
                      </div>
                    </div>
                  </div>
                </div>

                <div className="form__actions">
                  <button type="submit" className="btn">
                    Add product
                  </button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}
