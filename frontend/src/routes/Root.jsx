import {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Container} from "react-bootstrap";
import Header from "../components/Header/Header.jsx";
import {Outlet} from "react-router-dom";


function Root() {
    const [currentSubject, setCurrentSubject] = useState("");
    const [books, setBooks] = useState([]);

    const categories = [
        "Antiques", "Architecture", "Art", "Bibles", "Biography", "Body",
        "Business", "Comics", "Computers", "Cooking", "Crafts", "Crime", "Design",
        "Drama", "Education", "Family", "Fiction", "Games", "Gardening", "Health",
        "History", "House", "Humor", "Juvenile", "Law", "Literary", "Mathematics",
        "Medical", "Music", "Nature", "Pets", "Philosophy", "Photography",
        "Poetry", "Political", "Psychology", "Reference", "Religion", "Science",
        "Social", "Sports", "Technology", "Transportation", "Travel", "Young"
    ];

    return (
        <Container fluid className="p-0">
            <Header/>
            <Outlet context={{categories, setCurrentSubject, books, setBooks}}/>
        </Container>
    );
}

export default Root;