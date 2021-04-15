import {Route, Switch, Link} from "react-router-dom";

const Products = () => {
    return (
        <div>
            <Link to="/admin/products" className="mr-5">
                Listar Produtos
            </Link>
            <Link to="/admin/products/create" className="mr-5">
                Criar Produtos
            </Link>
            <Link to="/admin/products/10" className="mr-5">
                Editar Produtos
            </Link>
            <Switch>
                <Route path="/admin/products" exact>
                    <h1>Exibir as Listagem de produtos</h1>
                </Route>
                <Route path="/admin/products/create">
                    <h1>Criar novo produto</h1>
                </Route>
                <Route path="/admin/products/:productId">
                    <h1>Editar um produto</h1>
                </Route>
            </Switch>
        </div>
    );
}

export default Products;