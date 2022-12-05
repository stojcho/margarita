import { Route, Switch, Redirect } from "react-router-dom";
import { useState, useEffect } from "react";
import useUser from "./_service/useUser";
import Footer from "./component/footer/Footer";
import Header from "./component/header/Header";
import HomePage from "./page/HomePage/HomePage";
import CakesPage from "./page/CakesPage";
import AccountPage from "./page/AccountPage";
import Cake from "./component/Cake";
import DashboardProductsPage from "./page/DashboardProductsPage";
import DashboardOrdersPage from "./page/DashboardOrdersPage";
import AddProductPage from "./page/AddProductPage";
import NotFound from "./page/NotFound";
import CartPage from "./page/CartPage";
import OrderPage from "./page/OrderPage/OrderPage";

import AOS from "aos";
import "aos/dist/aos.css";

function App() {
  const { user, setUser } = useUser();
  const [cartQuantity, setCartQuantity] = useState(0);

  const logout = () => {
    setUser(null);
    return null;
  };

  useEffect(() => {
    AOS.init({
      offset: 50,
    });
  }, []);

  return (
    <div className="wrapper">
      <Switch>
        <Route path="/" exact>
          <Header
            setUser={setUser}
            user={user}
            setCartQuantity={setCartQuantity}
            cartQuantity={cartQuantity}
          />
          <HomePage />
        </Route>

        <Route path="/login" exact>
          {user ? (
            <Redirect to="/home" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />
              <HomePage />
            </>
          )}
        </Route>

        <Route path="/register" exact>
          {user ? (
            <Redirect to="/home" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />
              <HomePage />
            </>
          )}
        </Route>
        <Route path={["/cakes", "/cakes/:category"]} exact>
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <CakesPage setUser={setUser} user={user} />
            </>
          )}
        </Route>

        <Route path="/home" exact>
          <>
            <Header
              setUser={setUser}
              user={user}
              setCartQuantity={setCartQuantity}
              cartQuantity={cartQuantity}
            />
            <HomePage />
          </>
        </Route>

        <Route path="/account" exact>
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <AccountPage setUser={setUser} user={user} />
            </>
          )}
        </Route>

        <Route path="/products/:id">
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <Cake
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
              />
            </>
          )}
        </Route>

        <Route path="/dashboard/products">
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <DashboardProductsPage setUser={setUser} user={user} />
            </>
          )}
        </Route>

        <Route path="/dashboard/orders">
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <DashboardOrdersPage setUser={setUser} user={user} />
            </>
          )}
        </Route>

        <Route path="/product/add">
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <AddProductPage setUser={setUser} user={user} />
            </>
          )}
        </Route>

        <Route path="/cart" exact>
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />
              <CartPage
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
              />
            </>
          )}
        </Route>

        <Route path="/orders/new" exact>
          {!user ? (
            <Redirect to="/login" />
          ) : (
            <>
              <Header
                setUser={setUser}
                user={user}
                setCartQuantity={setCartQuantity}
                cartQuantity={cartQuantity}
              />{" "}
              <OrderPage setUser={setUser} user={user} />
            </>
          )}
        </Route>

        <Route path="/logout" exact>
          {user ? logout : <Redirect to="/login" />}
        </Route>

        <Route path="/notfound" exact>
          <>
            <Header
              setUser={setUser}
              user={user}
              setCartQuantity={setCartQuantity}
              cartQuantity={cartQuantity}
            />{" "}
            <NotFound />
          </>
        </Route>
        <Redirect to="/notfound" />
      </Switch>
      <Footer />
    </div>
  );
}

export default App;
