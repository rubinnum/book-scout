import React, {useEffect, useState} from 'react';
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import BookCarousel from "../components/BookCarousel/BookCarousel.jsx";
import CustomSpinner from "../components/elements/CustomSpinner.jsx";

function CategoryBooks() {
    const {subject} = useParams();
    const {books, setBooks} = useOutletContext();

    const [loading, setLoading] = useState(true);

    const getBooksBySubject = async (subject) => {
        const response = await backend_api.get(`/books/${subject}`);
        const loadedBooks = response.data.books
        setBooks(loadedBooks);
        setLoading(false);
    }

    useEffect(() => {
        getBooksBySubject(subject);
    }, []);

    if (loading) {
        return <CustomSpinner />
    }

    return <BookCarousel books={books} />;
}

export default CategoryBooks;