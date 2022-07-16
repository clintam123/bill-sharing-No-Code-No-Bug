import React from "react";
import { Link, Outlet } from "react-router-dom";
import logo from "../../../assets/images/logo/logo.png";
import PrivateRoutes from "../../../router/PrivateRoutes";
import AddCategory from "../component/manageCategory/AddCategory";
import Category from "./Category";

const AdminHomepage = () => {
  return (
    <div>
      <header>
        <div className="logo">
          <img src={logo} alt="" />
          <ul>
            <li>
              <Link to="/category/list-category">Manage Categories</Link>
            </li>
            <li>
              <Link to="/category/manage-vendor">Manage Vendor</Link>
            </li>
            <li>
              <Link to="/manage-category">Manage Product</Link>
            </li>
          </ul>
        </div>
      </header>
      <div>
        
        <Outlet />
      </div>
    </div>
  );
};

export default AdminHomepage;
