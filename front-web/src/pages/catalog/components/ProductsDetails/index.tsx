import './styles.scss';
import { useParams } from 'react-router-dom';

type ParamsType = {
    productId: string;
}

const ProductDetails = () => {
    const {productId} = useParams<ParamsType>();
    console.log(productId);
    return (
        <div className="product-details-container">
            <h1>Product details</h1>
        </div>
    );
}

export default ProductDetails;