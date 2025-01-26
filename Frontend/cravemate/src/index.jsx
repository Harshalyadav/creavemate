import React from "react";
import ReactDOM from "react-dom/client"; // Ensure you're using 'react-dom/client'
import { BrowserRouter } from "react-router-dom";
import "./index.css";
import App from "./App";
// Import Redux 
import { Provider } from "react-redux";
import Store from "./Redux/store";

// React-Slick
// Import css files
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

// Create a root and render the app
const rootElement = document.getElementById("root");
const root = ReactDOM.createRoot(rootElement);

root.render(
  <React.StrictMode>
    <Provider store={Store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
  </React.StrictMode>
);
