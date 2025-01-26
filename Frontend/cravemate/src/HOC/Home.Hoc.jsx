// import React from "react";

// import { Route , Routes} from "react-router-dom";

// import HomeLayout from "../Layout/Home.layout";

// const HomeLayoutHOC=({component:Component,...rest}) => {


//     return (
//         <>
//         <Routes>
         
//         <Route
//         {...rest}
//         component={(props) =>(
//             <HomeLayout>
//                 <Component {...props}/>
//                 </HomeLayout>
//     )}
//         />
//         </Routes>
    
//         </>
//     );
// };



// export default HomeLayoutHOC;


import React from "react";
import { Route } from "react-router-dom";
import HomeLayout from "../Layout/Home.layout";

const HomeLayoutHOC = ({ component: Component, ...rest }) => {
  return (
    // <Route
    //   {...rest}
    //   element={
        <HomeLayout>
          <Component />
        </HomeLayout>
    //   }
    // />
  );
};

export default HomeLayoutHOC;
