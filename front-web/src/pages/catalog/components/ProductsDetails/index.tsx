import React, {useEffect, useState} from "react";
import './styles.scss';
import { Link, useParams } from 'react-router-dom';
import {ReactComponent as ArrowIcon} from '../../../../core/assets/img/arrow.svg'
import ProductPrice from "../../../../core/components/ProductPrice/ProductPrice";
import {makeRequest} from "../../../../core/utils/request";
import {Product} from "../../../../core/types/Product";

type ParamsType = {
    productId: string;
}

const ProductDetails = () => {
    const {productId} = useParams<ParamsType>();
    const [product, setProduct] = useState<Product>();


   useEffect(() => {
       makeRequest({url: `/products/${productId}`})
           .then(response => setProduct(response.data));
   }, [productId])

    return (
        <div className="product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to="/products" className="product-details-goback">
                    <ArrowIcon className="icon-goback"/>
                    <h1 className="text-goback">voltar</h1>
                </Link>
                <div className="row">
                  <div className="col-6 pr-5">
                      <div className="product-details-card text-center">
                          <img src={product?.img_URL} alt={product?.name} className="product-details-img"/>
                      </div>
                      <h1 className="product-details-name">{product?.name}</h1>

                      {
                          product?.price &&  <ProductPrice price={product?.price} />
                      }

                  </div>
                  <div className="col-6 product-details-card">
                      <h1 className="product-description-title">Descricao do produto</h1>
                      <p className="product-description-text">{product?.description}
                      </p>
                  </div>
                </div>
            </div>
        </div>
    );
}

// price - Utilizado condicional render

export default ProductDetails;