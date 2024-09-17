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
        <Container fluid className="p-0">
            <Header/>
            <Outlet context={{categories, setCategories, setCurrentSubject, books, setBooks}}/>
        </Container>
    );
}

export default Root;