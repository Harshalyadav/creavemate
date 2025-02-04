

import { Route, Navigate, Routes } from "react-router-dom";
import axios from "axios";
import { useDispatch } from "react-redux";
import React, { useEffect } from "react";

// HOC
import HomeLayoutHOC from "./HOC/Home.Hoc";
import RestaurantLayoutHOC from "./HOC/Restaurant.HOC";
import CheckoutLayoutHOC from "./HOC/Checkout.Hoc";

// pages
import Home from "./Page/Home";
import Overview from "./Page/Restaurant/Overview";
import OrderOnline from "./Page/Restaurant/OrderOnline";
import Reviews from "./Page/Restaurant/Reviews";
import Menu from "./Page/Restaurant/Menu";
import Photos from "./Page/Restaurant/Photos";
import Checkout from "./Page/checkout";
import RedirectRestaurant from "./Page/Restaurant/Redirect";
import GoogleAuth from "./Page/GoogleAuth";

// redux action
import { getMyself } from "./Redux/Reducer/User/user.action";

// axios global settings
if (localStorage.zomatoUser) {
  const { token } = JSON.parse(localStorage.zomatoUser);
  axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
}

function App() {
  const dispatch = useDispatch();
  useEffect(() => {
    if (localStorage.zomatoUser) dispatch(getMyself());
  }, [dispatch]);

  return (
    <>
   
    <Routes>
      {/* Redirect from "/" to "/delivery" */}
      <Route path="/" element={<Navigate to="/delivery" />} />

      {/* Restaurant routes */}
      <Route path="/restaurant/:id" element={<RedirectRestaurant />} />
      <Route
        path="/restaurant/:id/overview/*"
        element={<RestaurantLayoutHOC component={Overview} />}
      />
      <Route
        path="/restaurant/:id/order-online"
        element={<RestaurantLayoutHOC component={OrderOnline} />}
      />
      <Route
        path="/restaurant/:id/menu"
        element={<RestaurantLayoutHOC component={Menu} />}
      />
      <Route
        path="/restaurant/:id/reviews"
        element={<RestaurantLayoutHOC component={Reviews} />}
      />
      <Route
        path="/restaurant/:id/photos"
        element={<RestaurantLayoutHOC component={Photos} />}
      />

      {/* Checkout route */}
      <Route
        path="/checkout/orders"
        element={<CheckoutLayoutHOC component={Checkout} />}
      />

      {/* Home route */}
      <Route
        path="/:type"
        element={<HomeLayoutHOC component={Home} />}
      />

      {/* Google authentication route */}
      <Route
        path="/google/:token"
        element={<HomeLayoutHOC component={GoogleAuth} />}
      />
    </Routes>
    </>
   
  );
}

export default App;
