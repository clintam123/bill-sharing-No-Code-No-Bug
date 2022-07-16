import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Outlet } from 'react-router-dom'
import AddProduct from './AddProduct';

const Product = () => {

    const [products, setProducts] = useState<any>([]);

    useEffect(() => {
        getListProduct();
    },[])
    const getListProduct = async() => {
        try {
            const res = await axios.get('http://localhost:8080/api/v1/product-group/vendor/1?page=0&page_size=10');
            console.log(res);
            
            if(res.status === 200) {
                // setProducts([...res.data])
            }
        } catch (error) {
            
        }
    }
  return (
    <div>
        <div>
            <h1>LIST GROUP PRODUCTS</h1>
            <AddProduct />
        </div>
    </div>
  )
}

export default Product