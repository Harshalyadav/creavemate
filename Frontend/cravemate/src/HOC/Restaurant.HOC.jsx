import React from "react";

import { Route , Routes} from "react-router-dom";

import RestaurantLayout from "../Layout/Restaurant.layout";


const RestaurantLayoutHOC=({component:Component,...rest}) => {


    return (
        <>
        <Routes>
        <Route
        {...rest}
        component={(props) =>(
            <RestaurantLayout>
                <Component {...props}/>
                </RestaurantLayout>
    )}
        />
        </Routes>
        </>
    );
};


export default RestaurantLayoutHOC;