import './styles.scss'
import {useHistory} from "react-router-dom";
import React from "react";

type Props = {
    title: string
    //ReactNode e aceita qualquer tipo de componente, funcoes que retona componente, umas das forma mais generica
    children: React.ReactNode;
}

const BaseForm = ({title, children}:Props) => {
    const history = useHistory();
    const handleCancel = () => {
        //pode pensar as rota como diretorio, e deixando desse jeito fica generico para utilizar em outras paginas
        history.push('../')
    }
    return(
        <div className="admin-base-form card-base">
            <h1 className="base-form-title">
                {title}
            </h1>
            {children}
            <div className="base-form-actions">
                <button className="border-radius-10 btn btn-outline-danger mr-3" onClick={handleCancel}>CANCELAR</button>
                <button className="border-radius-10 btn btn-primary">CADASTRAR</button>
            </div>
        </div>
    );
}

export default BaseForm;