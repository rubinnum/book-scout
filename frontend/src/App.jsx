import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {useState} from "react";
import SubjectsLayout from "./components/subjectsLayout.jsx";
import Header from './components/header.jsx';
import {Container} from "react-bootstrap";

function App() {
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
            <Header />
            <SubjectsLayout setBooks={setBooks} setCurrentSubject={setCurrentSubject} categories={categories}></SubjectsLayout>
        </Container>
    )
}

export default App
