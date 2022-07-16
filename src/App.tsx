import { Route, Routes } from "react-router-dom";
import "./App.css";
import PrivateRoutes from "./router/PrivateRoutes";
import "react-toastify/dist/ReactToastify.css";
import { ToastContainer } from "react-toastify";

function App() {
  return (
    <div className="App">
         <ToastContainer/>
        <PrivateRoutes />
    </div>
  );
}

export default App;
