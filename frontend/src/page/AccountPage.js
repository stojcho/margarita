import React from "react";
import _useAxiosGetAccountDTO from "../_service/_useAxiosGetAccountDTO";
import { useState, useEffect } from "react";
import _updateUser from "../_service/_updateUser";

function AccountPage({ setUser, user }) {
  const url = "http://172.25.16.1:8080/api/user";
  const [userData, setUserData] = useState({
    username: user.username,
    name: "",
    email: "",
    phone: "",
    street: "",
    streetNumber: "",
    zipCode: "",
    city: "",
    country: "",
  });
  let userDTO = _useAxiosGetAccountDTO(url, setUser, user);

  function validateInput(toValidate){
    toValidate.toUpperCase()
    if(toValidate.includes("'")){return false}
    if(toValidate.includes("--")){return false}
    if(toValidate.includes("#")){return false}
    if(toValidate.includes("%")){return false}
    if(toValidate.includes(";")){return false}
    if(toValidate.includes("=")){return false}
    if(toValidate.includes(" DROP ")){return false}
    if(toValidate.includes(" DELETE ")){return false}
    if(toValidate.includes(" SELECT ")){return false}
    if(toValidate.includes(" UPDATE ")){return false}
    if(toValidate.includes(" INSERT ")){return false}
    if(toValidate.includes(" LIKE ")){return false}
    if(toValidate.includes(" UNION ")){return false}
    //if(toValidate.includes(" OR ")){return false}
    return true
}

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(!validateInput(userData.name)
    ||!validateInput(userData.email)
    ||!validateInput(userData.phone)
    ||!validateInput(userData.street)
    ||!validateInput(userData.streetNumber)
    ||!validateInput(userData.zipCode)
    ||!validateInput(userData.city)
    ||!validateInput(userData.country))
    {
      alert("Please remove any special symbols")
      return
    }
    _updateUser({
      userData,
      setUser,
      user,
    });
    alert("Your details are saved");
  };
  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
    console.log(name, value);
  };
  useEffect(() => {
    if (userDTO.data) {
      let name = "";
      if (userDTO.data.name != null) name = userDTO.data.name;
      let email = "";
      if (userDTO.data.email != null) email = userDTO.data.email;
      let phone = "";
      if (userDTO.data.phone != null) phone = userDTO.data.phone;
      let street = "";
      if (userDTO.data.street != null) street = userDTO.data.street;
      let streetNumber = "";
      if (userDTO.data.streetNumber != null)
        streetNumber = userDTO.data.streetNumber;
      let zipCode = "";
      if (userDTO.data.zipCode != null) zipCode = userDTO.data.zipCode;
      let city = "";
      if (userDTO.data.city != null) city = userDTO.data.city;
      let country = "";
      if (userDTO.data.country != null) country = userDTO.data.country;
      setUserData({
        username: user.username,
        name: name,
        email: email,
        phone: phone,
        street: street,
        streetNumber: streetNumber,
        zipCode: zipCode,
        city: city,
        country: country,
      });
    }
  }, [userDTO.data, user]);
  
  return (
    <div className="main">
      <section className="section-text-intro">
        <div className="shell">
          <header className="section__head" data-aos="zoom-in-up">
            <h1>User Information</h1>
          </header>
        </div>
      </section>

      <section className="section-form">
        <div className="shell">
          <div className="section__content">
            <div className="form">
              <form onSubmit={handleSubmit}>
                <div className="form__head">
                  <h3>Your Information</h3>
                </div>

                <div className="form__body">
                  <div className="form__group">
                    <div className="form__cols">
                      <div className="form__col">
                        <div className="form__row">
                          <label htmlFor="name" className="form__label">
                            Your Name
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.name}
                              type="text"
                              className="field"
                              name="name"
                              id="name"
                              required
                            />
                          </div>
                        </div>
                        <div className="form__row">
                          <label htmlFor="phone" className="form__label">
                            Your Phone
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.phone}
                              type="tel"
                              className="field"
                              name="phone"
                              id="phone"
                              required
                            />
                          </div>
                        </div>
                      </div>

                      <div className="form__col">
                        <div className="form__row">
                          <label htmlFor="email" className="form__label">
                            Your Email
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.email}
                              type="email"
                              className="field"
                              name="email"
                              id="email"
                              required
                            />
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>

                  <div className="form__group">
                    <div className="form__row">
                      <label htmlFor="street" className="form__label">
                        Your Street
                      </label>

                      <div className="form__controls">
                        <input
                          onChange={handleChange}
                          value={userData.street}
                          type="text"
                          className="field"
                          name="street"
                          id="street"
                          required
                        />
                      </div>
                    </div>

                    <div className="form__cols">
                      <div className="form__col">
                        <div className="form__row">
                          <label htmlFor="city" className="form__label">
                            City
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.city}
                              type="text"
                              className="field"
                              name="city"
                              id="city"
                              required
                            />
                          </div>
                        </div>

                        <div className="form__row">
                          <label htmlFor="country" className="form__label">
                            Country / Region
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.country}
                              type="text"
                              className="field"
                              name="country"
                              id="country"
                              required
                            />
                          </div>
                        </div>
                      </div>

                      <div className="form__col">
                        <div className="form__row">
                          <label htmlFor="streetNumber" className="form__label">
                            State / Province
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.streetNumber}
                              type="text"
                              className="field"
                              name="streetNumber"
                              id="streetNumber"
                              required
                            />
                          </div>
                        </div>

                        <div className="form__row">
                          <label htmlFor="zipCode" className="form__label">
                            Zip Code
                          </label>

                          <div className="form__controls">
                            <input
                              onChange={handleChange}
                              value={userData.zipCode}
                              type="text"
                              className="field"
                              name="zipCode"
                              id="zipCode"
                              required
                            />
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div className="form__actions">
                  <button type="submit" className="btn">
                    Save
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
export default AccountPage;
