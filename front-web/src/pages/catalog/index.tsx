import {useEffect} from "react";
import './styles.scss';
import {Link} from 'react-router-dom'
import ProductCard from "./components/ProductCard";

const Catalog = () => {
    //1 passo : quando o componente iniciar, buscar a lista de produtos
    //2 passo: quando a lista de produto estiver disponivel,popular um estado no componente, e listar os produto dinamicamente

    // porque nao pode fazer chamada de api antes do useEffect, o componente sobre varias reenderizacoes, se coloca uma api solta no componente
    // ele ira fazer uma nova chamada sempre, e que somente quando ele for iniciado.

    // ele e um react hook, uma funcao que utilizamos atraves dela acessa o sicro de vida do componente
    // o erro CORE so acontece no browse em uma requisicao assicrona



    useEffect(() => {
        fetch('http://localhost:3000/products')
            .then(response => response.json())
            .then(response => console.log(response));
    }, [])

    return(
        <div className="catalog-container">
            <h1 className="catalog-title">Catálago de produtos</h1>

            <div className="catalog-products">
                <Link to="/products/1"><ProductCard/></Link>
                <Link to="/products/2"><ProductCard/></Link>
                <Link to="/products/3"><ProductCard/></Link>
                <Link to="/products/4"><ProductCard/></Link>
                <Link to="/products/5"><ProductCard/></Link>
                <Link to="/products/6"><ProductCard/></Link>
                <Link to="/products/7"><ProductCard/></Link>
                <Link to="/products/8"><ProductCard/></Link>
                <Link to="/products/9"><ProductCard/></Link>
            </div>
         </div>
    );
}

export default Catalog;
