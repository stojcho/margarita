import { useState, useEffect } from "react";
import UseAxiosGet from "../../_service/_useAxiosGet";
import OrderTable from "./OrderTable";
export default function OrderPage({ setUser, user }) {
  const url = "http://172.25.16.1:8080/api/user/order/new";
  const [orders, setOrders] = useState({
    loading: true,
    data: false,
    error: false,
  });
  let ordersData = UseAxiosGet(url, setUser, user);
  useEffect(() => {
    if (ordersData.data) setOrders(ordersData);
  }, [ordersData]);
  let content = null;

  if (orders.error) {
    content = (
      <div>
        <div className="">
          There was an error please refresh or try again later.
        </div>
      </div>
    );
  }

  if (orders.loading) {
    content = <p>Loading</p>;
  }

  if (orders.data) {
    content = (
      <OrderTable
        orders={orders.data}
        setUser={setUser}
        user={user}
        setOrders={setOrders}
      />
    );
  }

  return (
    <div className="main">
      <section className="section-text-intro">
        <div className="shell">
          <header className="section__head" data-aos="zoom-in-up">
            <h1>Pending Orders</h1>
          </header>
        </div>
      </section>
      {content}
    </div>
  );
}
