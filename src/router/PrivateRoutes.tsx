import React from "react";
import { Route, Routes } from "react-router-dom";
import AddCategory from "../modules/admin/component/manageCategory/AddCategory";
import GetCategories from "../modules/admin/component/manageCategory/GetCategories";
import ManageVendor from "../modules/admin/component/manageVendor/ManageVendor";
import AdminHomepage from "../modules/admin/view/AdminHomepage";
import Dashboard from "../modules/admin/view/Dashboard";
import Product from "../modules/vendor/manageProduct/Product";
import VendorHomePage from "../modules/vendor/VendorHomePage";

const PrivateRoutes = () => {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Dashboard />}>
          <Route path="/vendor" element={<VendorHomePage />}>
            <Route path="/vendor/product" element={<Product />}/>
          </Route>
          <Route path="/category" element={<AdminHomepage />}>
            <Route path="/category/manage-vendor" element={<ManageVendor />}/>
            <Route path="/category/list-category" element={<GetCategories />} />
            <Route path="/category/add-category" element={<AddCategory />} />
          </Route>
        </Route>
      </Routes>
    </div>
  );
};

export default PrivateRoutes;
