import './style.scss';


// Offset - ele da um recuo na esquerda

const NavBar = () => (
    <nav className="row bg-primary main-nav">
        <div className="col-2">
            <a href="#" className="nav-log-text">
                <h4>DS Catalog</h4>
            </a>
        </div>

        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <a href="#" className="active">HOME</a>
                </li>
                <li>
                    <a href="#">CATALOGO</a>
                </li>
                <li>
                    <a href="#">ADMIN</a>
                </li>
            </ul>
        </div>
    </nav>
);

export default NavBar