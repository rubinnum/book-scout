import {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Container} from "react-bootstrap";
import Header from "../components/Header/Header.jsx";
import {Outlet} from "react-router-dom";


function Root() {
    const [currentSubject, setCurrentSubject] = useState("");
    const [books, setBooks] = useState([]);
    const [categories, setCategories] = useState([]);

    return (
        <div className="d-flex flex-column vh-100">
            <Header/>
            <Container fluid className="d-flex flex-grow-1 p-0">
                <Outlet context={{categories, setCategories, setCurrentSubject, books, setBooks}}/>
            </Container>
        </div>
    );
}

export default Root;