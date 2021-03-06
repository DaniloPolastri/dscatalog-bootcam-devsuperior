import {BrowserRouter, Switch, Route, Redirect} from 'react-router-dom';
import Home from "./pages/home";
import Catalog from "./pages/catalog";
import Admin from "./pages/admin";
import NavBar from "./core/components/navbar";
import ProductDetails from "./pages/catalog/components/ProductsDetails";

//BrowserRouter - encapsular toda a nossa aplicacao
//Switch - Vai fazer a magina entre decidir qual rota ele deve rendenrizar
//Route - e aonde vc define qual a ULR da sua navegacao

const Routes = () => (
    <BrowserRouter>
        <NavBar/>
        <Switch>
            <Route path="/" exact>
                <Home />
            </Route>
            <Route path="/products" exact>
                <Catalog />
            </Route>
            <Route path="/products/:productId">
                <ProductDetails />
            </Route>
            <Redirect from="/admin" to="/admin/products" exact/>
            <Route path="/admin">
                <Admin />
            </Route>
        </Switch>
    </BrowserRouter>
);

export  default Routes;