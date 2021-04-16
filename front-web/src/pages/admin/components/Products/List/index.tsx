import {useHistory} from 'react-router-dom'

const List = () => {
    const history = useHistory();
    const handleCreate = () => {
        //adicionar um item na pilha de rotas
        history.push('/admin/products/create')
    }

    return (
        <div className="admin-products-list">
            <button className="btn btn-primary btn-lg" onClick={handleCreate}>
                ADICIONAR
            </button>
        </div>
    );
}

export default List