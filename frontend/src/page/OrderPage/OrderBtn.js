import _completeOrder from "../../_service/_completeOrder";

export default function OrderBtn({ order_id, setUser, user, setOrders }) {
  const handleSubmit = async () => {
    let data = await _completeOrder(order_id, "SHIPPED", setUser, user);
    Promise.resolve(data).then(function (data) {
      setOrders({
        loading: false,
        data: data,
        error: false,
      });
    });
  };
  return <button onClick={handleSubmit}>Complete order</button>;
}
