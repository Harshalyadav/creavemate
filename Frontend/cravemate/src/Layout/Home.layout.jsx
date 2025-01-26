// import React, { useEffect } from "react";
// import { useDispatch } from "react-redux";

// // Components
// import Navbar from "../Components/Navbar";
// import FoodTab from "../Components/FoodTab";

// // redux action
// import { getCart } from "../Redux/Reducer/Cart/Cart.action";

// const HomeLayout = (props) => {
//   const dispatch = useDispatch();

//   useEffect(() => {
//     dispatch(getCart());
//   }, []);

//   return (
//     <>
//       <Navbar />
//       <FoodTab />
//       <div className="container mx-auto px-4 lg:px-20 ">{props.children}</div>
//     </>
//   );
// };

//


import React, { useEffect } from "react";
import { Routes, Route } from "react-router-dom";
import { useDispatch } from "react-redux";

// Components
import Navbar from "../Components/Navbar";
import FoodTab from "../Components/FoodTab";
// import Home from "../Page/Home";
import { getCart } from "../Redux/Reducer/Cart/Cart.action";

const HomeLayout = ({ children }) => {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getCart());
  }, [dispatch]);

  return (
    <>
      <Navbar />
      <FoodTab />
      <div className="container mx-auto px-4 lg:px-20">{children}</div>
    </>
  );
};

export default HomeLayout;

