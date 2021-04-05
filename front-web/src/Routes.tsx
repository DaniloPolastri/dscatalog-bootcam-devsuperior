import {BrowserRouter, Switch, Route} from 'react-router-dom';
import Home from "./pages/home";
import Catalog from "./pages/catalog";
import Admin from "./pages/admin";
import NavBar from "./core/components/navbar";

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
            <Route path="/catalog">
                <Catalog />
            </Route>
            <Route path="/admin">
                <Admin />
            </Route>
        </Switch>
    </BrowserRouter>
);

export  default Routes;