import React from "react";

import { Route, Routes } from "react-router-dom";

import CheckoutLayout from "../Layout/Checkout.layout";

const CheckoutLayoutHOC=({component:Component,...rest}) => {


    return (
        <>
        <Routes>
        <Route
        {...rest}
        component={(props) =>(
            <CheckoutLayout>
                <Component {...props}/>
                </CheckoutLayout>
    )}
        />
        </Routes>
 
        </>
    );
};


export default CheckoutLayoutHOC;
