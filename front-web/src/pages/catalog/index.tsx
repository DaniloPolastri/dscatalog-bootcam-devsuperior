import {useEffect, useState} from "react";
import './styles.scss';
import {Link} from 'react-router-dom'
import ProductCard from "./components/ProductCard";
import {makeRequest} from "../../core/utils/request";
import {ProductsResponse} from "../../core/types/Product";
import ProductCardLoader from "./components/ProductCardLoader";

const Catalog = () => {
    //2 passo: quando a lista de produto estiver disponivel,popular um estado no componente, e listar os produto dinamicamente
    //variavel que siboniza oque tem no valor estado, funcao que vai alterar o valor do estado
    const [productsResponse, setProductResponse] = useState<ProductsResponse>();
    const [isLoading, setIsLoading] = useState(false);


    //1 passo : quando o componente iniciar, buscar a lista de produtos
    useEffect(() => {
        //limitacoes fetch: ele e muito ferboso, nao tem suporte nativo para ler o progresso de upload de arquivos (enviar um upload de imagem)
        //nao tem suporte nativo para enviar query strings (parametros de paginacao por exemplo depois do ?)
        /*fetch('http://localhost:3000/products')
            .then(response => response.json())
            .then(response => console.log(response));*/

        const params = {
            page: 0,
            linesPerPage: 12
        }
        //iniciar o Loader
        setIsLoading(true);
        makeRequest({url: '/products', params})
            /*quando a minha requisicao termina estou amarzenando todo o payload que o back end mandou estou guardando dentro da variavel productsResponse*/
            .then(response => setProductResponse(response.data))
            .finally(() => {
                //finalizar o loader
                setIsLoading(false);
            })
    }, [])




    // porque nao pode fazer chamada de api antes do useEffect, o componente sobre varias reenderizacoes, se coloca uma api solta no componente
    // ele ira fazer uma nova chamada sempre, e que somente quando ele for iniciado.

    // ele e um react hook, uma funcao que utilizamos atraves dela acessa o sicro de vida do componente
    // o erro CORE so acontece no browse em uma requisicao assicrona



    return(
        <div className="catalog-container">
            <h1 className="catalog-title">Catálago de produtos</h1>

            <div className="catalog-products">
                { isLoading ? <ProductCardLoader /> : (
                    productsResponse?.content.map(product => (
                        <Link to={`/products/${product.id}`} key={product.id}>
                            <ProductCard product={product}/>
                        </Link>
                    ))
                )}
            </div>
         </div>
    );
}

export default Catalog;

// productsResponse?: pq do ponto de ?: jeito null safe para acessar propriedade de objeto que nao tem certeza que eles estao definido e que nao vai quebrar o codigo.