import './style.scss';
import {Link, NavLink} from 'react-router-dom';


// Offset - ele da um recuo na esquerda

const NavBar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-2">
            <Link to="/" className="nav-log-text">
                <h4>DS Catalog</h4>
            </Link>
        </div>

        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <NavLink to="/" activeClassName="active" exact>HOME</NavLink>
                </li>
                <li>
                    <NavLink to="/catalog" activeClassName="active">CATALOGO</NavLink>
                </li>
                <li>
                    <NavLink to="/admin" activeClassName="active">ADMIN</NavLink>
                </li>
            </ul>
        </div>
    </nav>
);

export default NavBar