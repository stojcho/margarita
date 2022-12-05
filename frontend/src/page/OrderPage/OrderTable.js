import { useState } from "react";
import useTable from "../../_service/useTable";
import TableFooter from "./OrderTableFooter";
import OrderBtn from "./OrderBtn";

export default function OrderTable({ orders, setUser, user, setOrders }) {
  const [page, setPage] = useState(1);
  const { slice, range } = useTable(orders, page, 7);

  return (
    <section className="section-table">
      <div className="shell">
        <div className="section__content">
          <div className="table">
            <table>
              <thead>
                <tr>
                  <th>Order Number</th>

                  <th>Date</th>

                  <th>Customer Name</th>

                  <th>Customer Phone</th>

                  <th>Address</th>

                  <th>Products</th>

                  <th>Total</th>

                  <th>Status</th>

                  <th></th>
                </tr>
              </thead>

              <tbody>
                {slice.map((order) => (
                  <tr key={order.orderNumber}>
                    <td>{order.orderNumber}</td>
                    <td>{order.date}</td>
                    <td>{order.customerName}</td>
                    <td>{order.customerPhone}</td>
                    <td>{order.customerAddress}</td>
                    <td>
                      {order.orderItemDTOS.map((orderItem) => (
                        <p key={order.orderNumber}>
                          &#8470;{orderItem.productId}
                          <span>, {orderItem.quantity}</span>
                        </p>
                      ))}
                    </td>
                    <td>{order.total}</td>
                    <td>{order.status}</td>
                    <td>
                      <OrderBtn
                        order_id={order.orderId}
                        setUser={setUser}
                        user={user}
                        setOrders={setOrders}
                      />
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
            <TableFooter
              range={range}
              slice={slice}
              setPage={setPage}
              page={page}
            />
          </div>
        </div>
      </div>
    </section>
  );
}
