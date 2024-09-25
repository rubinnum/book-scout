import React, {useEffect, useState} from 'react';
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import BookCarousel from "../components/BookCarousel/BookCarousel.jsx";
import CustomSpinner from "../components/elements/CustomSpinner.jsx";

function CategoryBooks() {
    const {subject} = useParams();
    const {books, setBooks} = useOutletContext();
    const [currentIndex, setCurrentIndex] = useState(0);

    const [loading, setLoading] = useState(true);

    const getBooksBySubject = async (subject) => {
        const response = await backend_api.get(`/books/${subject}`);
        const loadedBooks = response.data.books
        setBooks(prevBooks => [...prevBooks, ...loadedBooks]);
        setLoading(false);
    }

    useEffect(() => {
        if (books.length === 0 || (currentIndex === books.length - 1)) {
            getBooksBySubject(subject);
        }
    }, [currentIndex, subject, setBooks]);

    if (loading) {
        return <CustomSpinner />
    }

    return <BookCarousel
        books={books}
        currentIndex={currentIndex}
        setCurrentIndex={setCurrentIndex}
        subject={subject}
    />;
}

export default CategoryBooks;