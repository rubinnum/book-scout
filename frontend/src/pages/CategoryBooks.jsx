import React, {useEffect, useState, useRef} from 'react';
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import BookCarousel from "../components/BookCarousel/BookCarousel.jsx";
import CustomSpinner from "../components/elements/CustomSpinner.jsx";

function CategoryBooks() {
    const {subject} = useParams();
    const {books, setBooks} = useOutletContext();
    const [batchesNumber, setBatchesNumber] = useState(0);
    const [currentIndex, setCurrentIndex] = useState(0);

    const [loading, setLoading] = useState(true);
    const booksLoaded = useRef(false);

    const getBooksBySubject = async (subject) => {
        const response = await backend_api.get(`/books/${subject}`);
        const loadedBooks = response.data.books;
        setBooks(loadedBooks);
        setCurrentIndex(0);
        setLoading(false);
    }

    // Effect for initial load (only once)
    useEffect(() => {
        if (!booksLoaded.current) {
            booksLoaded.current = true;
            getBooksBySubject(subject);
        }
    }, [subject]);

    // Effect for fetching new batch of books
    useEffect(() => {
        if (currentIndex === books.length - 1) {
            getBooksBySubject(subject);
            setBatchesNumber(batchesNumber + 1);
        }
    }, [currentIndex, subject]);

    if (loading) {
        return <CustomSpinner />
    }

    return <BookCarousel
        books={books}
        currentIndex={currentIndex}
        setCurrentIndex={setCurrentIndex}
        subject={subject}
        batchesNumber={batchesNumber}
    />;
}

export default CategoryBooks;