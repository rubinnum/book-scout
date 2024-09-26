import React, {useEffect, useState, useRef} from 'react';
import {useOutletContext, useParams} from "react-router-dom";
import backend_api from "../api/backend_api.js";
import BookCarousel from "../components/BookCarousel/BookCarousel.jsx";
import CustomSpinner from "../components/elements/CustomSpinner.jsx";

function CategoryBooks() {
    const {subject} = useParams();
    const {books, setBooks} = useOutletContext();
    const [currentIndex, setCurrentIndex] = useState(0);

    const [loading, setLoading] = useState(true);

    const booksLoaded = useRef(false);

    const getBooksBySubject = async (subject) => {
        const response = await backend_api.get(`/books/${subject}`);
        const loadedBooks = response.data.books;

        const uniqueBooks = loadedBooks.filter((book) => !books.some((b) => b.title === book.title));
        setBooks((prevBooks) => [...prevBooks, ...uniqueBooks]);
        setLoading(false);
    }

    useEffect(() => {
        if (!booksLoaded.current && (books.length === 0 || currentIndex === books.length - 1)) {
            booksLoaded.current = true;
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