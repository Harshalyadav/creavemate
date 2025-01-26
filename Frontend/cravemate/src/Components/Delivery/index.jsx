import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";

// components
import DeliveryCarousal from "./DeliveryCarousal";
import Brand from "./Brand";
import RestaurantCard from "../RestaurantCard";

export  const restaurantData =[
  {
    _id:"1",
    name:"Food",
    city:"Raipur",
    address:"",
    mapLocation:"",
    cuisine:"uhugugu666",
    restaurantTimings:"",
    contactNumber:"",
    website:"",
    popularDishes:"",
    averageCost:"459",
    menuImages:"",
    menu:"",
    reviews:"",
    photos:"https://b.zmtcdn.com/data/dish_images/c598d69f4864f3cba4b0de2d8efc0e521612436494.png"
  },
  {
    _id:"2",
    name:"Food",
    city:"Bilaspur",
    images:['https://yt3.googleusercontent.com/BSIHmMYNaszACgf9mruSCJJd7A6Qi_CT0G8x_CjDe9TJhuOze18vCi5cftYNetMs13GMBYS8=s176-c-k-c0x00ffffff-no-rj','https://i.ytimg.com/an_webp/rZCHcp3lFFg/mqdefault_6s.webp?du=3000&sqp=CLix7KMG&rs=AOn4CLCTkhHNwazlXOzLPXjCJPomrSLGBg'],
    address:"",
    mapLocation:"",
    cuisine:"567",
    restaurantTimings:"",
    contactNumber:"",
    website:"",
    popularDishes:"",
    averageCost:"124",
    menuImages:"",
    menu:"",
    reviews:"",
    photos:"https://b.zmtcdn.com/data/homepage_dish_data/4/742929dcb631403d7c1c1efad2ca2700.png"
  },
  {
    _id:"3",
    name:"",
    city:"",
    address:"",
    mapLocation:"",
    cuisine:"iii",
    restaurantTimings:"",
    contactNumber:"",
    website:"",
    popularDishes:"",
    averageCost:"123",
    menuImages:"",
    menu:"",
    reviews:"",
    photos:"https://b.zmtcdn.com/data/homepage_dish_data/4/742929dcb631403d7c1c1efad2ca2700.png"
  },
  {
    _id:"4",
    name:"",
    city:"",
    address:"",
    mapLocation:"",
    cuisine:"",
    restaurantTimings:"",
    contactNumber:"",
    website:"",
    popularDishes:"",
    averageCost:"",
    menuImages:"",
    menu:"",
    reviews:"",
    photos:"https://b.zmtcdn.com/data/homepage_dish_data/4/742929dcb631403d7c1c1efad2ca2700.png"
  }
]

const Delivery = () => {
  const [restaurantList, setRestaurantList] = useState([]);

  const reduxState = useSelector(
    (globalStore) => globalStore.restaurant.restaurants
  );

     
  
  
  useEffect(() => {
    setRestaurantList(restaurantData.map((restaurant)=>restaurant))
    // reduxState.restaurants && setRestaurantList(reduxState.restaurants);
  }, []);

  return (
    <>
      <DeliveryCarousal />
      {/* <Brand /> */}
      <h1 className="text-xl mt-4 mb-2 md:mt-8 md:text-3xl md:font-semibold">
        Delivery Restaurants in Vijay Nagar
      </h1>
  klgkngsgfknlgs
      {/* <RestaurantCard/> */}
        
      <div className="flex justify-between flex-wrap">
        {restaurantList.map((restaurant) =>  (
            <RestaurantCard
            {...restaurant}
            // setRestaurantList1={restaurant}
            key={restaurant._id}
            whereIsthisres="asf"
            />
           )
        )}  
      </div>
    </>
  );
};
export default Delivery;