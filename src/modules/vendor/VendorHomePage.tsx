import React from 'react'
import { Link } from 'react-router-dom'
import { Outlet } from 'react-router-dom'
import Product from './manageProduct/Product'

const VendorHomePage = () => {
  return (
    <div>
        <header>
            <div>
                <ul>
                    <li>
                        <Link to="/vendor/product">Manage Product</Link>
                    </li>
                </ul>
            </div>
        </header>
        <Outlet />
    </div>
  )
}

export default VendorHomePage