import React, {useEffect, useState} from 'react';
import {Container, Spinner} from "react-bootstrap";
import BookCard from "../components/BookCard.jsx";
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";

function CategoryBooks() {
    const {subject} = useParams();
    const [currentBookIndex, setCurrentBookIndex] = useState(0);
    const {books, setBooks} = useOutletContext();

    const getBooksBySubject = async (subject) => {
        const loadedBooks = await backend_api.get(`/books/${subject.toLowerCase()}`);
        setBooks(loadedBooks.data);
    }

    useEffect(() => {
        getBooksBySubject(subject);
    }, [subject]);

    const handlePrevious = () => {
        if (currentBookIndex > 0) {
            setCurrentBookIndex(currentBookIndex - 1);
        }
    }

    const handleNext = () => {
        if (currentBookIndex < books.length - 1) {
            setCurrentBookIndex(currentBookIndex + 1);
        }
    }

    return (
        <Container className="d-flex justify-content-center align-items-center">
            {
                books.length > 0 ?
                    (
                        <BookCard book={books[currentBookIndex]} onNext={handleNext} onPrevious={handlePrevious}/>
                    ) :
                    <Spinner animation="border" variant="light" />
            }
        </Container>
    );
}

export default CategoryBooks;