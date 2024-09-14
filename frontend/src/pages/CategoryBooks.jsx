import React, {useEffect, useState} from 'react';
import {Container, Spinner} from "react-bootstrap";
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import BookCarousel from "../components/BookCarousel/BookCarousel.jsx";

function CategoryBooks() {
    const {subject} = useParams();
    const {books, setBooks} = useOutletContext();

    const [loading, setLoading] = useState(true);

    const getBooksBySubject = async (subject) => {
        const loadedBooks = await backend_api.get(`/books/${subject.toLowerCase()}`);
        setBooks(loadedBooks.data);
        setLoading(false);
    }

    useEffect(() => {
        getBooksBySubject(subject);
    }, []);

    if (loading) {
        return (
            <Container className="d-flex align-items-center justify-content-center min-vh-100">
                <Spinner animation="border" variant="light" />
            </Container>
        );
    }

    return <BookCarousel books={books} />;
}

export default CategoryBooks;